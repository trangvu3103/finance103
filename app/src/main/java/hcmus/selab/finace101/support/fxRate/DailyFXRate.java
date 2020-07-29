package hcmus.selab.finace101.support.fxRate;

public class DailyFXRate {
    private String dailyDate;
    private double dailyOpen;
    private double dailyHigh;
    private double dailyLow;
    private double dailyClose;

    public DailyFXRate(String dailyDate, double dailyOpen, double dailyHigh, double dailyLow, double dailyClose) {
        this.dailyDate = dailyDate;
        this.dailyOpen = dailyOpen;
        this.dailyHigh = dailyHigh;
        this.dailyLow = dailyLow;
        this.dailyClose = dailyClose;
    }


    public String getDailyDate() {
        return dailyDate;
    }

    public double getDailyOpen() {
        return dailyOpen;
    }

    public double getDailyHigh() {
        return dailyHigh;
    }

    public double getDailyLow() {
        return dailyLow;
    }

    public double getDailyClose() {
        return dailyClose;
    }
}


