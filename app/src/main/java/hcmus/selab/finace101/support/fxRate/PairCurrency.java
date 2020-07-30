package hcmus.selab.finace101.support.fxRate;

import java.util.ArrayList;

public class PairCurrency {
    private String curr_code;
    private ArrayList<DailyFXRate> pair_price;

    public PairCurrency(String curr_code, ArrayList<DailyFXRate> pair_price) {
        this.curr_code = curr_code;
        this.pair_price = pair_price;
    }

    public String getCurr_code() {
        return curr_code;
    }

    public ArrayList<DailyFXRate> getPair_price() {
        return pair_price;
    }
}
