package coderoom.entities.record;

import java.time.LocalDateTime;

/**
 * 담당: E
 * 역할: 게임 클리어 시 생성되는 성공 기록 객체.
 */
public class ClearRecord {

    private final String playerName;       // 플레이어 이름 혹은 ID
    private final long elapsedSeconds;     // 소요 시간 (초 단위 또는 밀리초)
    private final LocalDateTime clearedAt; // 클리어 시각

    public ClearRecord(String playerName, long elapsedSeconds, LocalDateTime clearedAt) {
        this.playerName = playerName;
        this.elapsedSeconds = elapsedSeconds;
        this.clearedAt = clearedAt;
    }

    // --- Getter 메서드 (읽기 전용) ---

    public String getPlayerName() {
        return playerName;
    }

    public long getElapsedSeconds() {
        return elapsedSeconds;
    }

    public LocalDateTime getClearedAt() {
        return clearedAt;
    }
}