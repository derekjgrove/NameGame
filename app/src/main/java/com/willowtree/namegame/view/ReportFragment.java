package com.willowtree.namegame.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.willowtree.namegame.NameGameApplication;
import com.willowtree.namegame.R;

import java.util.jar.Attributes;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Derek on 4/24/2017.
 */

public class ReportFragment extends Fragment {

    @BindView(R.id.report_container)
    ViewGroup container;
    @BindView(R.id.seconds_textview)
    TextView secondsTextView;
    @BindView(R.id.correct_textview)
    TextView scoreTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ViewGroup fragView =  (ViewGroup) inflater.inflate(R.layout.report_fragment, container, false);
        ButterKnife.bind(this, fragView);
        return fragView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        scoreTextView.setText((((NameGameApplication) getActivity().getApplication()).getCorrect()));
        secondsTextView.setText(((NameGameApplication) getActivity().getApplication()).getSeconds() + "");
    }
}
