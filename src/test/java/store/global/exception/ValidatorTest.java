package store.global.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static store.global.exception.Validator.validateIsInteger;
import static store.global.exception.Validator.validateIsPositive;

import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    void 입력된_값이_정수가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> validateIsInteger("test"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력된_정수값이_0_이하면_예외가_발생한다() {
        assertThatThrownBy(() -> validateIsPositive(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
