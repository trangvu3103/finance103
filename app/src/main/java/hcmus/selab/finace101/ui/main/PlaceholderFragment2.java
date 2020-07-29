package hcmus.selab.finace101.ui.main;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import hcmus.selab.finace101.R;
import hcmus.selab.finace101.support.fxRate.DailyFXRate;
import hcmus.selab.finace101.support.fxRate.ForexRate;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment2 extends Fragment {
    ForexRate forex = new ForexRate();
    ArrayList<DailyFXRate> dailyFXRates = new ArrayList<DailyFXRate>();
    ArrayList<DailyFXRate> data = new ArrayList<DailyFXRate>();

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        forex.execute();
        try {
            dailyFXRates = forex.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        //inflater
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View fragment_news = inflater.inflate(R.layout.fragment_news, null);
//
//        CandleStickChart chart = (CandleStickChart) fragment_news.findViewById(R.id.fx_chart);

//        LoadFXChart test_load = new LoadFXChart(dailyFXRates, chart);
        data = dailyFXRates;

//        CandleStickChart chart = (CandleStickChart) fragment_news.findViewById(R.id.fx_chart);
        CandleStickChart chart = view.findViewById(R.id.fx_chart);

        chart.setBackgroundColor(Color.WHITE);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setEnabled(false);
        leftAxis.setLabelCount(7, false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        ArrayList<CandleEntry> candleValues = new ArrayList<>();
        chart.resetTracking();
        ArrayList<String> dateIndex = new ArrayList<>();
        for (int j = 0; j < 30; j++) {

            dateIndex.add(data.get(j).getDailyDate());
            candleValues.add(new CandleEntry(
                    j* 1f,
                    (float)data.get(j).getDailyHigh() * 1f,
                    (float)data.get(j).getDailyLow() * 1f,
                    (float)data.get(j).getDailyOpen() * 1f,
                    (float)data.get(j).getDailyClose() * 1f));

            Log.d("TAG", "loadChart: " + data.get(j).getDailyDate());
        }

        IndexAxisValueFormatter indexAxisValueFormatter = new IndexAxisValueFormatter(dateIndex);
        xAxis.setValueFormatter(indexAxisValueFormatter);
        xAxis.setLabelCount(4);

        CandleDataSet set1 = new CandleDataSet(candleValues, "Foreign Exchange Rate");
        set1.setDrawIcons(false);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        set1.setColor(Color.rgb(80, 80, 80));

        set1.setShadowColor(Color.DKGRAY);
        set1.setShadowWidth(0.7f);
        set1.setDecreasingColor(Color.RED);
        set1.setDecreasingPaintStyle(Paint.Style.FILL);
        set1.setIncreasingColor(Color.rgb(122, 242, 84));
        set1.setIncreasingPaintStyle(Paint.Style.STROKE);
        set1.setNeutralColor(Color.BLUE);

        Description description = new Description();
//            description.setText(arrayIndexName[i]);

        CandleData data = new CandleData(set1);
//        chart.setDescription(description);
        chart.setData(data);
//        chart.notifyDataSetChanged();
        chart.invalidate();

        super.onViewCreated(view, savedInstanceState);
    }
}