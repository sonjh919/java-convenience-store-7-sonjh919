package store.view;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputParserTest {
    private InputParser inputParser;

    @BeforeEach
    void setUp() {
        inputParser = new InputParser();
    }

    @Nested
    class Products_구매_검증_테스트 {
        @Test
        void 구매_입력_성공_테스트() {
            assertThatCode(() -> inputParser.purchase("[사이다-2],[감자칩-1]"))
                    .doesNotThrowAnyException();
        }

        @ParameterizedTest
        @ValueSource(strings = {"사이다-2", "[감자칩:1]", "[콜라:1,사이다:2]", "사이다-2,감자칩-1"})
        void 올바르지_않은_형식으로_구매_목록을_입력하면_예외가_발생한다(String products) {
            assertThatThrownBy(() -> inputParser.purchase(products))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
