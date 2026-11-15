java
package coderoom.entities.puzzle;

/**
 * 담당: E
 * 역할: 퍼즐 자체의 판정 결과
 * 직접 선언한 상속·구현 관계 없음.
 */
public enum AnswerCheck {
    INVALID,  // 형식 오류 (빈 값, 자릿수 불일치, 문자, 공백 등)
    WRONG,    // 형식은 맞지만 오답 (보상 없음)
    CORRECT   // 정답
}