package step3.view;

import static step3.constant.LottoViewConstant.LOTTOS_INFO_FORMAT;
import static step3.constant.LottoViewConstant.OVERVIEW_FORMAT;
import static step3.constant.LottoViewConstant.REWARD_RATE_FORMAT;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import step3.constant.enums.LottoReward;

public class OutputView {

    private final String isLoss = "손해";
    private final String isBenefit = "이득";
    private final int MATCH_COUNT_LIMIT = 3;
    public OutputView() {
    }

    public void printOutput(HashMap<String, Integer> statistics, int money) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        long reward = printOverview(statistics);
        printRewardRate(reward, money);
    }

    private void printRewardRate(long reward, int money) {
        System.out.println(String.format(REWARD_RATE_FORMAT, reward * 1.0 / money, checkBenefit(reward, money)));
    }

    private String checkBenefit(long reward, int money) {
        if (money > reward) {
            return isLoss;
        }
        return isBenefit;
    }

    private long printOverview(HashMap<String, Integer> statistics) {
        long reward = 0;
        for (Entry<String, Integer> lottoMatchEntry : statistics.entrySet()) {
            reward += printOverViewPerEntry(LottoReward.valueOf(lottoMatchEntry.getKey()), lottoMatchEntry.getValue());
        }
        return reward;
    }

    private long printOverViewPerEntry(LottoReward lottoReward, int matchCount) {
        if (lottoReward.ordinal() < MATCH_COUNT_LIMIT) {
            return 0;
        }
        System.out.println(String.format(OVERVIEW_FORMAT, matchCount, lottoReward.getReward(), matchCount));
        return lottoReward.getReward() * matchCount;
    }

    public void printLottoInfo(List<List<String>> lottoNumbers) {
        System.out.println(String.format(LOTTOS_INFO_FORMAT, lottoNumbers.size()));
        for (int i = 0; i < lottoNumbers.size(); i++) {
            System.out.println(lottoNumbers.get(i).toString());
        }
    }
}
