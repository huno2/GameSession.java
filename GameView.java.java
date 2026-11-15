package coderoom.features.gameplay;

import coderoom.entities.game.GameStage;
import coderoom.entities.game.ItemId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 담당: E
 * 역할: UI 표시를 위한 게임 화면 상태 전달 객체 (DTO / 값 객체).
 * 정답이나 DB 객체를 포함하지 않으며, 외부 조작을 방지하기 위해 읽기 전용 및 방어적 복사로 구현됩니다.
 */
public class GameView {

    private final GameStage gameStage;
    private final List<ItemView> items;
    private final ItemId selectedItem;
    private final long elapsedSeconds;

    /**
     * 생성자: 게임 뷰에 필요한 상태 정보를 받아 초기화합니다.
     * items 리스트는 외부 조작으로부터 보호하기 위해 방어적 복사 및 수정 불가 리스트로 감쌉니다.
     */
    public GameView(GameStage gameStage, List<ItemView> items, ItemId selectedItem, long elapsedSeconds) {
        this.gameStage = gameStage;
        this.items = (items != null) 
                ? Collections.unmodifiableList(new ArrayList<>(items)) 
                : Collections.emptyList();
        this.selectedItem = selectedItem;
        this.elapsedSeconds = elapsedSeconds;
    }

    // --- Getter 메서드 (읽기 전용) ---

    public GameStage getGameStage() {
        return gameStage;
    }

    public List<ItemView> getItems() {
        return items;
    }

    public ItemId getSelectedItem() {
        return selectedItem;
    }

    public long getElapsedSeconds() {
        return elapsedSeconds;
    }
}