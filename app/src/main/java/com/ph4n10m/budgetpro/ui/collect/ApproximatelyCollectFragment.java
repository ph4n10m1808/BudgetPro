package com.ph4n10m.budgetpro.ui.collect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.adapter.CollectRecyclerviewAdapter;
import com.ph4n10m.budgetpro.adapter.ItemClickListener;
import com.ph4n10m.budgetpro.dialog.CollectDialog;
import com.ph4n10m.budgetpro.entity.Collect;

import java.util.List;

public class ApproximatelyCollectFragment extends Fragment {
    private RecyclerView mRv;
    private  CollectRecyclerviewAdapter mAdapter;
    private ApproximatelyCollectViewModel mViewModel;

    public static ApproximatelyCollectFragment newInstance() {
        return new ApproximatelyCollectFragment();
    }
    public ApproximatelyCollectViewModel getViewModel(){
        return mViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new CollectRecyclerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
//        final ApproximatelyCollectViewModel currentFragment = this;
//        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Collect collect = mAdapter.getItem(position);
//                CollectDialog dialog = new C
//            }
//        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_approximately_collect, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ApproximatelyCollectViewModel.class);
        mViewModel.getAllCollect().observe(getActivity(), new Observer<List<Collect>>() {
            @Override
            public void onChanged(List<Collect> collects) {
                mAdapter.setList(collects);
            }
        });
    }

}