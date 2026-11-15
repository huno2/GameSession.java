package coderoom.entities.puzzle;

/**
 * 담당: E
 * 역할: 코드 퍼즐과 정답 판정.
 * 직접 선언한 상속·구현 관계 없음.
 */
public class Puzzle {
    private final String puzzleId;
    private final String mapId;
    private final int stepNo;
    private final String code;
    private final AnswerType answerType;
    private final String answer;   // 외부로 내보내지 않는다 (getter 없음)
    private final String hint;

    public Puzzle(String puzzleId, String mapId, int stepNo, String code,
                  AnswerType answerType, String answer, String hint) {
        if (answerType == null) {
            throw new IllegalArgumentException("answerType must not be null");
        }
        if (!answerType.isValidFormat(answer)) {
            throw new IllegalArgumentException(
                    "answer does not match " + answerType + " format");
        }
        this.puzzleId = puzzleId;
        this.mapId = mapId;
        this.stepNo = stepNo;
        this.code = code;
        this.answerType = answerType;
        this.answer = answer;
        this.hint = hint;
    }

    /**
     * 입력을 판정한다. 형식 오류는 INVALID, 형식은 맞지만 틀리면 WRONG, 맞으면 CORRECT.
     * 게임 단계나 보상은 변경하지 않는 순수 판정이다.
     * 정답은 문자열로 비교해 앞자리 0을 보존한다.
     */
    public AnswerCheck check(String rawAnswer) {
        return answerType.check(rawAnswer, answer);
    }

    public String getPuzzleId() { return puzzleId; }
    public String getMapId() { return mapId; }
    public int getStepNo() { return stepNo; }
    public String getCode() { return code; }
    public AnswerType getAnswerType() { return answerType; }
    public String getHint() { return hint; }

    // 주의: getAnswer()는 의도적으로 만들지 않았다.
    // 정답을 화면 DTO로 내보내지 않는다는 설계 주석을 따른 것이다.
}