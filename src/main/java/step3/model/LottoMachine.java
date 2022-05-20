package step3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import step3.domain.LottoTicket;
import step3.domain.Money;
import step3.enums.LottoReward;

public class LottoMachine {

    private final LottoGenerator lottoGenerator;
    private final LottoWinChecker lottoWinChecker;

    private final int LOTTO_PRICE = 1_000;
    private final int EMPTY = 0;

    private final String CANT_BUY_LOTTO_EXCEPTION = "돈은 최소 " + LOTTO_PRICE + "이상 입력해야합니다";
    private final String MANUAL_LOTTO_COUNT_OVER_TICKET_EXCEPTION_MSG = "입금한 돈을 초과할수 없습니다.";

    public LottoMachine(LottoGenerator lottoGenerator, LottoWinChecker lottoWinChecker) {
        this.lottoGenerator = lottoGenerator;
        this.lottoWinChecker = lottoWinChecker;
    }

    public List<LottoTicket> makeRandomLottoTickets(int ticket) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticket; i++) {
            lottoTickets.add(lottoGenerator.makeRandomLottoTicket());
        }
        return lottoTickets;
    }

    public List<LottoTicket> makeManualLottoTickets(List<String> manualLottoTicketsSource) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (String manualLottoElements : manualLottoTicketsSource) {
            lottoTickets.add(lottoGenerator.makeManualLottoTicket(manualLottoElements));
        }
        return lottoTickets;
    }

    public void setWinnerLottoTicket(String winnerSource) {
        LottoTicket winnerTicket = lottoGenerator.makeManualLottoTicket(winnerSource);
        lottoWinChecker.setWinnerLottoTicket(winnerTicket);
    }

    public void setBonusNumber(String lottoElementSource) {
        lottoWinChecker.setBonusNumber(lottoElementSource);
    }

    public int getLottoTicketCount(Money money) {
        int ticket = money.getMoney() / LOTTO_PRICE;
        if (ticket == EMPTY) {
            throw new IllegalArgumentException(CANT_BUY_LOTTO_EXCEPTION);
        }
        return ticket;
    }

    public Map<LottoReward, Integer> checkWin(List<LottoTicket> userLottoTickets) {
        return lottoWinChecker.checkWin(userLottoTickets);
    }

    public int getUsingMoneyByTicket(int ticket) {
        return ticket * LOTTO_PRICE;
    }

    public void validateManualLottoCount(int ticket, int manualLottoCount) {
        if (ticket < manualLottoCount) {
            throw new IllegalArgumentException(MANUAL_LOTTO_COUNT_OVER_TICKET_EXCEPTION_MSG);
        }
    }
}
