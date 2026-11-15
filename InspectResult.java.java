package coderoom.features.gameplay;

import coderoom.entities.puzzle.AnswerType;

/**
 * 담당: E
 * 역할: 게임 내 오브젝트 조사(Inspect) 결과를 전달하는 읽기 전용 값 객체 (DTO).
 * 설명, 입력창, 진행 불가 상태를 구분하며 정답 정보는 절대 포함하지 않습니다.
 */
public class InspectResult {

    /**
     * 조사 결과의 유형을 나타내는 열거형
     */
    public enum InspectType {
        DESCRIPTION,    // 단순 설명 (텍스트 출력)
        INPUT_BOX,      // 입력창 (퍼즐 풀이용 입력 필요)
        UNPROGRESSABLE  // 진행 불가 / 상호작용 불가 상태
    }

    private final InspectType type;         // 구분 (설명 / 입력창 / 진행 불가)
    private final String message;           // 안내 메시지 또는 설명 텍스트
    private final String puzzleId;          // 입력창용 퍼즐 ID (입력창 유형이 아닐 경우 null)
    private final AnswerType answerType;    // 입력 형식 타입 (입력창 유형이 아닐 경우 null)
    private final String displayText;       // 입력창 표시 문구 또는 플레이스홀더 (입력창 유형이 아닐 경우 null)

    /**
     * 전체 필드 초기화 생성자
     */
    public InspectResult(InspectType type, String message, String puzzleId, AnswerType answerType, String displayText) {
        this.type = type;
        this.message = message;
        this.puzzleId = puzzleId;
        this.answerType = answerType;
        this.displayText = displayText;
    }

    /**
     * 단순 설명 또는 진행 불가 상태를 생성하기 위한 팩토리 메서드
     */
    public static InspectResult ofMessage(InspectType type, String message) {
        return new InspectResult(type, message, null, null, null);
    }

    /**
     * 입력창이 필요한 퍼즐 오브젝트를 조사했을 때 생성하기 위한 팩토리 메서드
     */
    public static InspectResult ofInputBox(String message, String puzzleId, AnswerType answerType, String displayText) {
        return new InspectResult(InspectType.INPUT_BOX, message, puzzleId, answerType, displayText);
    }

    // --- Getter 메서드 (읽기 전용) ---

    public InspectType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getPuzzleId() {
        return puzzleId;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public String getDisplayText() {
        return displayText;
    }
}