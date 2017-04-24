package com.willowtree.namegame.view;

import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.willowtree.namegame.R;
import com.willowtree.namegame.controller.NormalController;
import com.willowtree.namegame.controller.ReverseController;
import com.willowtree.namegame.model.Person;
import com.willowtree.namegame.util.CircleBorderTransform;
import com.willowtree.namegame.util.Ui;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Created by Derek on 4/23/2017.
 */

public class ReverseFragment extends Fragment{

    @BindView(R.id.answer_group)
    RadioGroup answerGroup;

    @BindView(R.id.answer1)
    AppCompatRadioButton answer1;
    @BindView(R.id.answer2)
    AppCompatRadioButton answer2;
    @BindView(R.id.answer3)
    AppCompatRadioButton answer3;
    @BindView(R.id.answer4)
    AppCompatRadioButton answer4;
    @BindView(R.id.answer5)
    AppCompatRadioButton answer5;

    @BindView(R.id.reverse_container)
    ViewGroup container;
    @BindView(R.id.reverse_pic)
    ImageView face;
    private ReverseController reverseController;

    private static final Interpolator OVERSHOOT = new OvershootInterpolator();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        //was container instead of parent
        ViewGroup fragView =  (ViewGroup) inflater.inflate(R.layout.reverse_fragment, container, false);
        ButterKnife.bind(this, fragView);
        return fragView;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        reverseController = new ReverseController(this);
        reverseController.generateView();
    }


    public void setRadioButtons(List<String> names, int correctIndex) {
        for (int i = 0; i < names.size(); i++) {
            ((AppCompatRadioButton) answerGroup.getChildAt(i)).setText(names.get(i).toString());
            if (i == correctIndex) {
                ((AppCompatRadioButton) answerGroup.getChildAt(i)).setSupportButtonTintList(
                        ContextCompat.getColorStateList(getActivity(),
                                R.color.correct_selector));
            } else {
                ((AppCompatRadioButton) answerGroup.getChildAt(i)).setSupportButtonTintList(
                        ContextCompat.getColorStateList(getActivity(),
                                R.color.incorrect_selector));
            }
        }
    }

    public void setImage(String url) {
        int imageSize = (int) Ui.convertDpToPixel(250, getContext());
        Picasso.with(this.getContext()).load(url)
                .placeholder(R.drawable.ic_face_white_48dp)
                .error(R.drawable.ic_face_white_48dp)
                .resize(imageSize, imageSize)
                .transform(new CircleBorderTransform())
                .into(face);
    }

    public void initAnswerGroupListener() {
        answerGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.answer1:
                        reverseController.isCorrect(0);
                        break;
                    case R.id.answer2:
                        reverseController.isCorrect(1);
                        break;
                    case R.id.answer3:
                        reverseController.isCorrect(2);
                        break;
                    case R.id.answer4:
                        reverseController.isCorrect(3);
                        break;
                    case R.id.answer5:
                        reverseController.isCorrect(4);
                        break;
                }
            }
        });
    }

}
