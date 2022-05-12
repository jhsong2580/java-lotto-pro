package step2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ResolverTest {

    @ParameterizedTest
    @CsvSource(value = {"1,2,3&,&3", "1,2,3&:&1", "1:2:3&:&3", "1:2:3&,&1"}, delimiter = '&')
    @DisplayName("Source와 Delimiter별 Split결과 확인")

    public void checkSplit(String source, String delimiter, int size) {
        assertThat(source.split(delimiter)).hasSize(size);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,2,3&,&true", "1,2,3&:&false", "1:2:3&:&true", "1:2:3&,&false"}, delimiter = '&')
    @DisplayName("delimiter가 정상적으로 확인되는가")
    public void checkDelimiter(String source, String delimiter, boolean expected) {
        assertThat(source.indexOf(delimiter) != -1).isEqualTo(expected);
    }
}
