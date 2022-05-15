package step3.domain;

import static step3.LottoConstant.LOTTO_MAX;
import static step3.LottoConstant.LOTTO_MIN;

import java.util.Objects;

public class LottoElement {

    private final String element;

    public LottoElement(String element) {
        this.element = validElement(element);
    }

    private String validElement(String element) throws IllegalArgumentException {
        int parseElement = parseNumber(element);
        validNumberRange(parseElement);
        return element;
    }

    private void validNumberRange(int parseElement) {
        if (parseElement < LOTTO_MIN || parseElement > LOTTO_MAX) {
            throw new IllegalArgumentException("로또 번호는 0 이상의 숫자여야합니다");
        }
    }

    private int parseNumber(String element) {
        try {
            return Integer.parseInt(element);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 번호는 0 이상의 숫자여야합니다");
        }
    }

    public String getElement() {
        return element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoElement that = (LottoElement) o;
        return Objects.equals(element, that.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element);
    }
}
