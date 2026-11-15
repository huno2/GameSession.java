package coderoom.entities.game;

import java.util.List;
import java.util.OptionalLong;
import java.util.concurrent.TimeUnit;

/**
 * 담당: E
 * 역할: 플레이 한 회의 메모리 상태와 전이 규칙.
 * 직접 선언한 상속·구현 관계 없음.
 *
 * 상태 변경은 EDT에서만 실행하고 재시작 시 새 세션을 생성한다.
 * (스레드 안전하지 않다. Swing을 참조하지 않으므로 호출 측이 EDT를 보장한다.)
 *
 * 경계:
 * Swing·SQL·entities.puzzle 등 다른 엔티티 슬라이스를 참조하지 않는다.
 * 정답 판정과 상태 전이의 결합은 상위 GameFacade가 담당한다.
 */
public class GameSession {
    private final String userId;
    private final String mapId;
    private GameStage stage;
    private ItemId selectedItem;
    private final long startedAtNanos;
    private Long clearedSeconds; // 탈출 전에는 null

    public GameSession(String userId, String mapId, long startedAtNanos) {
        this.userId = userId;
        this.mapId = mapId;
        this.startedAtNanos = startedAtNanos;
        this.stage = GameStage.START;
        this.selectedItem = null;
        this.clearedSeconds = null;
    }

    /** 단계에서 NOTE1 / NOTE1·NOTE2 / NOTE1·NOTE2·KEY 보유 목록을 계산한다. */
    public List<ItemId> inventory() {
        switch (stage) {
            case START:
                return List.of(ItemId.NOTE1);
            case DRAWER_OPEN:
                return List.of(ItemId.NOTE1, ItemId.NOTE2);
            case SAFE_OPEN:
            case ESCAPED:
                return List.of(ItemId.NOTE1, ItemId.NOTE2, ItemId.KEY);
            default:
                throw new IllegalStateException("알 수 없는 단계: " + stage);
        }
    }

    /** 미획득 아이템은 거절한다. 선택에 성공하면 true. */
    public boolean select(ItemId itemId) {
        if (itemId == null || !inventory().contains(itemId)) {
            return false;
        }
        selectedItem = itemId;
        return true;
    }

    /** START 단계에서만 단 한 번 진행한다. 성공하면 true. */
    public boolean unlockDrawer() {
        if (stage != GameStage.START) {
            return false;
        }
        stage = GameStage.DRAWER_OPEN;
        return true;
    }

    /** DRAWER_OPEN 단계에서만 단 한 번 진행한다. 성공하면 true. */
    public boolean unlockSafe() {
        if (stage != GameStage.DRAWER_OPEN) {
            return false;
        }
        stage = GameStage.SAFE_OPEN;
        return true;
    }

    /** SAFE_OPEN과 KEY 선택을 확인하고 시간을 한 번만 확정한다. 성공하면 true. */
    public boolean escape(long nowNanos) {
        if (stage != GameStage.SAFE_OPEN || selectedItem != ItemId.KEY) {
            return false;
        }
        clearedSeconds = TimeUnit.NANOSECONDS.toSeconds(nowNanos - startedAtNanos);
        stage = GameStage.ESCAPED;
        return true;
    }

    public String getUserId() { return userId; }

    public String getMapId() { return mapId; }

    public GameStage getStage() { return stage; }

    public ItemId getSelectedItem() { return selectedItem; }

    public long getStartedAtNanos() { return startedAtNanos; }

    /** 탈출 전에는 비어 있다. */
    public OptionalLong getClearedSeconds() {
        return clearedSeconds == null ? OptionalLong.empty() : OptionalLong.of(clearedSeconds);
    }

    public boolean isEscaped() { return stage == GameStage.ESCAPED; }
}
