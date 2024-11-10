package store.controller;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import store.Application;

public class StoreControllerTest extends NsTest {

    @Test
    void 재고_수량을_초과하면_예외가_발생한다() {
        assertSimpleTest(() -> {
            runException("[탄산수-6]");
            assertThat(output()).contains("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @MethodSource("provideProductsForPrice")
    void 상품_구매_성공_테스트(String[] products, String expected) {
        assertSimpleTest(() -> {
            run(products);
            assertThat(output().replaceAll("\\s", "")).contains(expected);
        });
    }

    private static Stream<Arguments> provideProductsForPrice() {
        return Stream.of(
                Arguments.of(new String[]{"[오렌지주스-1]", "Y", "Y", "N"}, "내실돈1,800"),
                Arguments.of(new String[]{"[오렌지주스-1]", "N", "Y", "N"}, "내실돈1,260"),
                Arguments.of(new String[]{"[콜라-3],[에너지바-5]", "Y", "N"}, "내실돈9,000"),
                Arguments.of(new String[]{"[감자칩-5]", "N", "Y", "N"}, "내실돈2,100"),
                Arguments.of(new String[]{"[감자칩-5]", "Y", "Y", "N"}, "내실돈3,150"),
                Arguments.of(new String[]{"[감자칩-6]", "Y", "Y", "N"}, "내실돈4,200"),
                Arguments.of(new String[]{"[콜라-8]", "Y", "Y", "N"}, "내실돈4,200"),
                Arguments.of(new String[]{"[사이다-2],[감자칩-1]", "Y", "Y", "Y", "N"}, "내실돈3,500"),
                Arguments.of(new String[]{
                        "[콜라-20],[사이다-15],[오렌지주스-9],[탄산수-5],[물-10],[비타민워터-6],[감자칩-10],[초코바-10],[에너지바-5],[정식도시락-8],[컵라면-11]",
                        "Y", "Y", "Y", "Y", "Y", "Y", "Y", "N"}, "내실돈151,300")
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
