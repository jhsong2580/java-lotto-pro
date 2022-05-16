package step3.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class LottoElementTest {

    @Test
    public void createLottoElementByNumber() {
        assertAll(
            () -> assertThatThrownBy(() -> new LottoElement(-1)).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> new LottoElement(0)).isInstanceOf(IllegalArgumentException.class)
        );
    }
}
