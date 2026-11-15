package coderoom;

import coderoom.entities.game.GameSession;
import coderoom.entities.game.ItemId;

public class GameSessionDemo {
    public static void main(String[] args) {
        long start = System.nanoTime();
        GameSession s = new GameSession("user1", "map1", start);

        System.out.println("시작: " + s.getStage() + " " + s.inventory());
        System.out.println("KEY 선택(거절돼야 함): " + s.select(ItemId.KEY));
        System.out.println("서랍 열기: " + s.unlockDrawer() + " " + s.inventory());
        System.out.println("서랍 다시 열기(거절): " + s.unlockDrawer());
        System.out.println("금고 열기: " + s.unlockSafe() + " " + s.inventory());
        System.out.println("KEY 선택: " + s.select(ItemId.KEY));
        System.out.println("탈출: " + s.escape(System.nanoTime()) + " " + s.getStage());
        System.out.println("탈출 다시(거절): " + s.escape(System.nanoTime()));
        System.out.println("클리어 시간(초): " + s.getClearedSeconds());
    }
}