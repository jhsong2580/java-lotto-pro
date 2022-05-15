package step3.domain;

import static java.util.Collections.shuffle;
import static step3.LottoConstant.LOTTO_ELEMENTS_SIZE;
import static step3.LottoConstant.LOTTO_PRICE;
import static step3.LottoConstant.LOTTO_VALID_ELEMENTS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LottoManager {

    private List<LottoTicket> lottoTickets = new ArrayList<>();

    private HashMap<Integer, Integer> statistics;

    public LottoManager(){}

    public LottoManager(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }


    public int buyRandomTicket(String money) {
        if (!validMoney(money)) {
            throw new IllegalArgumentException("돈은 " + LOTTO_PRICE + "이상 입력하셔야합니다");
        }
        for (int i = 0; i < Integer.parseInt(money) / LOTTO_PRICE; i++) {
            lottoTickets.add(makeRandomLottoTicket());
        }
        return lottoTickets.size();
    }


    private boolean validMoney(String money) {
        boolean validResult = true;
        try {
            validResult = Integer.parseInt(money) >= LOTTO_PRICE;
        } catch (NumberFormatException e) {
            validResult = false;
        } finally {
            return validResult;
        }
    }

    private LottoTicket makeRandomLottoTicket() {
        List<Integer> lottoElements = new ArrayList<>();
        shuffle(LOTTO_VALID_ELEMENTS);
        for (int i = 0; i < LOTTO_ELEMENTS_SIZE; i++) {
            lottoElements.add(LOTTO_VALID_ELEMENTS.get(i));
        }
        return new LottoTicket(lottoElements.stream().map(String::valueOf).collect(Collectors.toList()));
    }

    public LottoTicket makeManualLottoTicket(String manualLottoSource){
        return new LottoTicket(manualLottoSource);
    }
    public HashMap<Integer, Integer> checkWin(LottoTicket winLotto) {
        initStatistics();
        for (LottoTicket lottoTicket : lottoTickets) {
            int matchCount = winLotto.getMatchCountWith(lottoTicket);
            statistics.replace(matchCount, statistics.get(matchCount) + 1);
        }
        return statistics;
    }

    private void initStatistics() {
        statistics = new HashMap<>();
        for (int i = 0; i <= LOTTO_ELEMENTS_SIZE; i++) {
            statistics.put(i, 0);
        }
    }

    public List<List<String>> getLottoNumbers() {
        return lottoTickets.stream().map(LottoTicket::getLottoNumbers)
            .collect(Collectors.toList());
    }
}
