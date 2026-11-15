package coderoom.features.gameplay;

import coderoom.entities.game.ItemId;

/**
 * 담당: E
 * 역할: 아이템의 화면 표시용 값 전달 객체 (DTO / 값 객체).
 * 읽기 전용으로 작성되었으며, 아이템의 식별자, 제목, 그리고 설명이나 자바 코드 조각 등의 내용을 담습니다 (정답은 포함하지 않음).
 */
public class ItemView {

    private final ItemId id;
    private final String title;
    private final String content; // Java 코드 종이 또는 열쇠 설명 등 (정답 아님)

    /**
     * 생성자: 아이템 뷰 정보를 초기화합니다.
     */
    public ItemView(ItemId id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // --- Getter 메서드 (읽기 전용) ---

    public ItemId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}