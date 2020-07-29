//package hcmus.selab.finace101.support.fxRate;
//
//import android.view.View;
//
//import androidx.annotation.NonNull;
//
//import com.github.mikephil.charting.charts.CandleStickChart;
//import com.github.mikephil.charting.data.CandleData;
//
//import java.util.ArrayList;
//
//import hcmus.selab.finace101.R;
//
//public class LoadFXChart {
//    private static ArrayList<DailyFXRate> data;
//
//    public LoadFXChart(ArrayList<DailyFXRate> data_input) {
//        this.data = data_input;
//    }
//
//
//    public void loadChart() {
//        String[] arrayIndexName = {"S&P/ASX 200 Index","S&P/ASX 50 Index"};
//        CandleStickChart fx_chart = (CandleStickChart) findViewById(R.id.fx_chart);
//
//        stockIndexViewHolder.candleStickChart.setHighlightPerDragEnabled(true);
//        stockIndexViewHolder.candleStickChart.setDrawBorders(true);
//        stockIndexViewHolder.candleStickChart.setBorderColor(Color.LTGRAY);
//
//        YAxis yAxis = stockIndexViewHolder.candleStickChart.getAxisLeft();
//        YAxis rightAxis = stockIndexViewHolder.candleStickChart.getAxisRight();
//        yAxis.setDrawGridLines(true);
//        rightAxis.setDrawGridLines(true);
//        stockIndexViewHolder.candleStickChart.requestDisallowInterceptTouchEvent(true);
//
//        XAxis xAxis = stockIndexViewHolder.candleStickChart.getXAxis();
//
//        xAxis.setDrawGridLines(true);// disable x axis grid lines
//        xAxis.setDrawLabels(true);
//        rightAxis.setTextColor(Color.WHITE);
//        yAxis.setDrawLabels(true);
//        xAxis.setGranularity(1f);
//        xAxis.setGranularityEnabled(true);
//        xAxis.setAvoidFirstLastClipping(true);
//
//        Legend l = stockIndexViewHolder.candleStickChart.getLegend();
//        l.setEnabled(true);
//
//        ArrayList<CandleEntry> candleValues = new ArrayList<>();
//
//        String[] dateIndex = new String[index.getCompanyStockPrices().size()];
//        try {
//            for (int j = 0; j < index.getCompanyStockPrices().size(); j++) {
//                //System.out.println((float)index.getCompanyStockPrices().get(j).getDailyHigh());
//                if(index.getCompanyStockPrices().get(j).getDailyClose() != 0){
//                    dateIndex[j] = String.valueOf(index.getCompanyStockPrices().get(j).getDailyDate());
//                    candleValues.add(new CandleEntry(
//                            (float)j * 1f,
//                            (float)index.getCompanyStockPrices().get(j).getDailyHigh() * 1f,
//                            (float)index.getCompanyStockPrices().get(j).getDailyLow() * 1f,
//                            (float)index.getCompanyStockPrices().get(j).getDailyOpen() * 1f,
//                            (float)index.getCompanyStockPrices().get(j).getDailyClose() * 1f));
//                }
//            }
//        }catch (Exception ex){ex.printStackTrace();}
//
//        IndexAxisValueFormatter indexAxisValueFormatter = new IndexAxisValueFormatter(dateIndex);
//        xAxis.setValueFormatter(indexAxisValueFormatter);
//        xAxis.setLabelCount(4);
//
//        //System.out.println(candleValues.toString());
//        CandleDataSet set1 = new CandleDataSet(candleValues, "Stock Prices");
//        set1.setColor(Color.rgb(80, 80, 80));
//        set1.setShadowColor(Color.GRAY);
//        set1.setShadowWidth(0.8f);
//        set1.setDecreasingColor(Color.RED);
//        set1.setDecreasingPaintStyle(Paint.Style.FILL);
//        set1.setIncreasingColor(Color.GREEN);
//        set1.setIncreasingPaintStyle(Paint.Style.FILL);
//        set1.setNeutralColor(Color.LTGRAY);
//        set1.setDrawValues(false);
//
//        Description description = new Description();
//        description.setText(arrayIndexName[i]);
//
//        CandleData data = new CandleData(set1);
//        stockIndexViewHolder.candleStickChart.setDescription(description);
//        stockIndexViewHolder.candleStickChart.setData(data);
//        stockIndexViewHolder.candleStickChart.notifyDataSetChanged();
//        stockIndexViewHolder.candleStickChart.invalidate();
//    }
//}
