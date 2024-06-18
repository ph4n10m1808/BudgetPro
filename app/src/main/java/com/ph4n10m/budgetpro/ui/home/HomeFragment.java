package com.ph4n10m.budgetpro.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.entity.ChartCollect;
import com.ph4n10m.budgetpro.entity.ChartSpend;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private PieChart mChart;
    private PieChart mChart1;
    private HomeViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mChart = view.findViewById(R.id.piechartCollect);
        mChart1 = view.findViewById(R.id.piechartSpend);
        setupPieChart(mChart, "Biểu đồ thu");
        setupPieChart(mChart1, "Biểu đồ chi");

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getChartCollect().observe(getViewLifecycleOwner(), chartCollects -> {
            if (chartCollects != null) {
                updateChartData(mChart, chartCollects);
            }
        });
        viewModel.getChartSpend().observe(getViewLifecycleOwner(), chartSpends -> {
            if (chartSpends != null) {
                updateChartData(mChart1, chartSpends);
            }
        });
        return view;
    }

    private void setupPieChart(PieChart pieChart, String centerText) {
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(45f);
        pieChart.setTransparentCircleAlpha(50);
        pieChart.setCenterText(centerText);
        pieChart.setCenterTextSize(16);
        pieChart.setDrawEntryLabels(false);
        pieChart.getDescription().setEnabled(false);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);
        legend.setTextSize(12f);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(5f);
    }

    private <T> void updateChartData(PieChart pieChart, List<T> chartData) {
        ArrayList<PieEntry> yEntries = new ArrayList<>();

        for (T data : chartData) {
            if (data instanceof ChartCollect) {
                ChartCollect chartCollect = (ChartCollect) data;
                yEntries.add(new PieEntry(chartCollect.money, chartCollect.name));
            } else if (data instanceof ChartSpend) {
                ChartSpend chartSpend = (ChartSpend) data;
                yEntries.add(new PieEntry(chartSpend.money, chartSpend.name));
            }
        }

        PieDataSet pieDataSet = new PieDataSet(yEntries, "Tên khoản tiền");
        pieDataSet.setSliceSpace(3);
        pieDataSet.setValueTextSize(12);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setColors(generateColors(yEntries.size()));

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private ArrayList<Integer> generateColors(int size) {
        ArrayList<Integer> colors = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            colors.add(Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
        }
        return colors;
    }
}
