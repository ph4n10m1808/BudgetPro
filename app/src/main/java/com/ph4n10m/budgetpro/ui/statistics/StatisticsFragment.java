package com.ph4n10m.budgetpro.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.adapter.StatisticsCategoryCollectRecyclerViewAdapter;
import com.ph4n10m.budgetpro.adapter.StatisticsCategorySpendRecyclerViewAdapter;
import com.ph4n10m.budgetpro.entity.StatisticsCategoryCollect;
import com.ph4n10m.budgetpro.entity.StatisticsCategorySpend;

import java.util.List;


public class StatisticsFragment extends Fragment {
    private StatisticsViewModel mstatisticsViewModel;
    private EditText mEtTotalCollect;
    private EditText mEtTotalSpend;
    private RecyclerView rvStatisticsCategoryCollect;
    private StatisticsCategoryCollectRecyclerViewAdapter mStatisticsCategoryCollectAdapter;
    private RecyclerView rvStatisticsCategorySpend;
    private StatisticsCategorySpendRecyclerViewAdapter mStatisticsCategorySpendAdapter;

    public StatisticsFragment() {

    }

    public static StatisticsFragment newInstance() {
        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        mEtTotalCollect = view.findViewById(R.id.etTotalCollect);
        mEtTotalSpend = view.findViewById(R.id.etTotalSpend);
        rvStatisticsCategoryCollect = view.findViewById(R.id.rvCategoryCollect);
        rvStatisticsCategorySpend = view.findViewById(R.id.rvCategorySpend);
        mStatisticsCategoryCollectAdapter = new StatisticsCategoryCollectRecyclerViewAdapter(getActivity());
        mStatisticsCategorySpendAdapter = new StatisticsCategorySpendRecyclerViewAdapter(getActivity());
        rvStatisticsCategoryCollect.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStatisticsCategorySpend.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStatisticsCategoryCollect.setAdapter(mStatisticsCategoryCollectAdapter);
        rvStatisticsCategorySpend.setAdapter(mStatisticsCategorySpendAdapter);

        mstatisticsViewModel = new ViewModelProvider(this).get(StatisticsViewModel.class);
        mstatisticsViewModel.getTotalCollect().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float total) {
                mEtTotalCollect.setText(total + " Đồng");
            }
        });
        mstatisticsViewModel.getTotalSpend().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float total) {
                mEtTotalSpend.setText(total + " Đồng");
            }
        });
        mstatisticsViewModel.getStatisticsCategoryCollect().observe(getActivity(), new Observer<List<StatisticsCategoryCollect>>() {
            @Override
            public void onChanged(List<StatisticsCategoryCollect> statisticsCategoryCollects) {
                mStatisticsCategoryCollectAdapter.setList(statisticsCategoryCollects);
            }
        });
        mstatisticsViewModel.getStatisticsCategorySpend().observe(getActivity(), new Observer<List<StatisticsCategorySpend>>() {
            @Override
            public void onChanged(List<StatisticsCategorySpend> statisticsCategorySpends) {
                mStatisticsCategorySpendAdapter.setList(statisticsCategorySpends);
            }
        });
        return view;
    }
}