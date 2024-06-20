package com.ph4n10m.budgetpro.ui.intro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.getSupportActionBar().setTitle("Giới thiệu");
        }
        WebView webView = view.findViewById(R.id.webView);
        // Enable JavaScript and Mixed Content
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        webView.setWebViewClient(new WebViewClient());

        String contactInfo = "<b>Ứng dụng được viết bằng Java.</b><br><br>" +
                "Nếu bạn có bất cứ thắc mắc nào, hãy liên hệ đến tôi qua mail: " +
                "<a href='mailto:baovv.22ns@vku.udn.vn'>" +
                "<img src='https://upload.wikimedia.org/wikipedia/commons/4/4e/Gmail_Icon.png' alt='Gmail' width='24' height='24' /> " +
                "baovv.22ns@vku.udn.vn</a> hoặc thêm vào issues vào " +
                "reponsive github: <a href='https://github.com/ph4n10m1808/BudgetPro'>" +
                "<img src='https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png' alt='GitHub' width='24' height='24' /> BudgetPro GitHub</a>.";

        webView.loadDataWithBaseURL(null, contactInfo, "text/html", "UTF-8", null);

        return view;
    }
}
