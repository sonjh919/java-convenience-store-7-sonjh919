package store.view.input;

import static store.global.exception.ExceptionMessage.INVALID_YES_OR_NO;

public enum Answer {
    YES("Y"),
    NO("N");;

    final String answer;

    Answer(final String answer) {
        this.answer = answer;
    }

    public static Answer from(final String input) {
        for (Answer answer : Answer.values()) {
            if (answer.answer.equals(input)) {
                return answer;
            }
        }
        throw new IllegalArgumentException(INVALID_YES_OR_NO.message);
    }

    public boolean isNo() {
        return this == NO;
    }

    public boolean isYes() {
        return this == YES;
    }
}
