package step3.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step3.model.LottoUser;

public class LottoUserTest {

    LottoUser user;

    @BeforeEach
    public void init() {
        user = new LottoUser();
    }

    @Test
    @DisplayName("user의 getLottoNumbers는 LottoTicket의 getLottoNumbers의 값을 받아온다")
    public void getLottoNumbersTest() {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.add(new LottoTicket("1,2,3,4,5,6"));
        lottoTickets.add(new LottoTicket("7,2,3,4,5,6"));
        lottoTickets.add(new LottoTicket("8,2,3,4,5,6"));
        lottoTickets.add(new LottoTicket("9,2,3,4,5,6"));

        user.setUserLotto(lottoTickets);

        assertThat(user.getLottoNumbers())
            .hasSize(4)
            .contains(new LottoTicket("1,2,3,4,5,6").getLottoNumbers())
            .contains(new LottoTicket("7,2,3,4,5,6").getLottoNumbers())
            .contains(new LottoTicket("8,2,3,4,5,6").getLottoNumbers())
            .contains(new LottoTicket("9,2,3,4,5,6").getLottoNumbers());
    }

}
