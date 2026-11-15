package coderoom.features.gameplay;

import coderoom.entities.record.ClearRecord;

/**
 * 담당: E
 * 역할: 게임 처리용 값 전달 객체 (Value Object / DTO).
 * 읽기 전용으로 작성되었으며, 성공/실패 여부에 따른 결과와 상태를 담습니다.
 */
public class EscapeResult {

    private final boolean success;
    private final String reason;
    private final GameView gameView;
    private final ClearRecord clearRecord; // 실패 시 null

    /**
     * 기본 생성자 (직접 호출보다는 정적 팩토리 메서드 사용 권장)
     */
    public EscapeResult(boolean success, String reason, GameView gameView, ClearRecord clearRecord) {
        this.success = success;
        this.reason = reason;
        this.gameView = gameView;
        this.clearRecord = clearRecord;
    }

    /**
     * 성공 시 사용하는 정적 팩토리 메서드
     * 실패 시에는 게임 상태를 유지하고 기록을 생성하지 않으므로 ClearRecord를 포함합니다.
     */
    public static EscapeResult success(String reason, GameView gameView, ClearRecord clearRecord) {
        return new EscapeResult(true, reason, gameView, clearRecord);
    }

    /**
     * 실패 시 사용하는 정적 팩토리 메서드
     * 게임 상태는 유지되며, 성공 시 생성되는 ClearRecord는 전달하지 않습니다 (null 처리).
     */
    public static EscapeResult failure(String reason, GameView gameView) {
        return new EscapeResult(false, reason, gameView, null);
    }

    // --- Getter 메서드 (읽기 전용) ---

    public boolean isSuccess() {
        return success;
    }

    public String getReason() {
        return reason;
    }

    public GameView getGameView() {
        return gameView;
    }

    public ClearRecord getClearRecord() {
        return clearRecord;
    }
}