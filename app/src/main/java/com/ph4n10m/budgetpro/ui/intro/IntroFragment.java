package com.ph4n10m.budgetpro.ui.intro;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ph4n10m.budgetpro.R;

public class IntroFragment extends Fragment {
    public IntroFragment() {
    }

    public static IntroFragment newInstance(String param1, String param2) {
        IntroFragment fragment = new IntroFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        TextView textView = view.findViewById(R.id.textView7);

        String contactInfo = "<b>Ứng dụng được xây dựng bằng Java và được viết bởi Võ Văn Bảo.</b><br><br>" +
                "Nếu bạn có bất cứ thắc mắc nào, hãy liên hệ đến tôi qua mail: " +
                "<a href='mailto:baovv.22ns@vku.udn.vn'>baovv.22ns@vku.udn.vn</a> hoặc thêm vào issues vào " +
                "reponsive github: <a href='https://github.com/ph4n10m/budgetpro'>BudgetPro GitHub</a>.";

        textView.setText(Html.fromHtml(contactInfo));
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}
