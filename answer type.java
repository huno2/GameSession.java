package coderoom.entities.puzzle;

/**
 * 담당: E
 * 역할: 암호 입력 형식
 * 직접 선언한 상속·구현 관계 없음.
 */
public enum AnswerType {
    DIGITS4(4),  // 숫자 4자리
    GRID3X3(9);  // 3x3 격자, 칸마다 숫자 1개 (총 9자리)

    private final int length;

    AnswerType(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    /** 형식 검사: null/빈 값/공백/문자/자릿수 불일치는 모두 false. */
    public boolean isValidFormat(String input) {
        if (input == null || input.length() != length) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /** 입력을 정답과 비교해 판정한다. 앞자리 0 보존을 위해 문자열로 비교한다. */
    public AnswerCheck check(String input, String answer) {
        if (!isValidFormat(input)) {
            return AnswerCheck.INVALID;
        }
        return input.equals(answer) ? AnswerCheck.CORRECT : AnswerCheck.WRONG;
    }
}