package coderoom.features.gameplay;

/**
 * 담당: E
 * 역할: 퍼즐 제출 결과 및 상태를 담는 읽기 전용 값 전달 객체 (DTO).
 * 사용자 오답, 시스템 오류, 정답 여부 등의 판정(Verdict)과 최신 게임 뷰 상태를 포함합니다.
 */
public class SubmitResult {

    /**
     * 퍼즐 제출 결과 판정을 나타내는 내부 열거형 (Enum)
     */
    public enum Verdict {
        WRONG,          // 사용자 오답
        INVALID,        // 유효하지 않은 형식 또는 요청
        BLOCKED,        // 진행 불가 상태 (아직 해제되지 않은 장치 등)
        ALREADY_SOLVED, // 이미 해결된 퍼즐
        SOLVED          // 정답 성공
    }

    private final Verdict verdict;
    private final String message;
    private final GameView gameView;

    /**
     * 생성자: 판정 결과, 안내 메시지, 최신 게임 화면 상태를 받아 초기화합니다.
     */
    public SubmitResult(Verdict verdict, String message, GameView gameView) {
        this.verdict = verdict;
        this.message = message;
        this.gameView = gameView;
    }

    // --- Getter 메서드 (읽기 전용) ---

    public Verdict getVerdict() {
        return verdict;
    }

    public String getMessage() {
        return message;
    }

    public GameView getGameView() {
        return gameView;
    }
}