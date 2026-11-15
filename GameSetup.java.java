package coderoom.features.gameplay;

import coderoom.entities.map.GameMap;
import coderoom.entities.puzzle.Puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 담당: E
 * 역할: 검증된 맵과 퍼즐 원본(정답 포함)을 Facade에 전달하기 위한 읽기 전용 값 전달 객체 (DTO).
 * 주의: 정답이 포함되어 있으므로 절대 UI 표시용으로 직접 사용하지 않습니다.
 */
public class GameSetup {

    private final GameMap gameMap;
    private final List<Puzzle> puzzles;

    /**
     * 생성자: 맵 데이터와 퍼즐 목록을 받아 불변(Read-only) 형태로 보관합니다.
     * 외부에서 리스트를 변경하더라도 내부 상태가 영향받지 않도록 방어적 복사를 수행합니다.
     */
    public GameSetup(GameMap gameMap, List<Puzzle> puzzles) {
        this.gameMap = gameMap;
        // null 체크 및 방어적 복사 후 수정 불가능한 리스트로 감싸기
        this.puzzles = (puzzles != null) 
                ? Collections.unmodifiableList(new ArrayList<>(puzzles)) 
                : Collections.emptyList();
    }

    // --- Getter 메서드 (읽기 전용) ---

    public GameMap getGameMap() {
        return gameMap;
    }

    public List<Puzzle> getPuzzles() {
        return puzzles;
    }
}