package com.willowtree.namegame.view;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.willowtree.namegame.R;
import com.willowtree.namegame.controller.HintController;
import com.willowtree.namegame.controller.MatController;
import com.willowtree.namegame.controller.NormalController;
import com.willowtree.namegame.model.Person;
import com.willowtree.namegame.util.CircleBorderTransform;
import com.willowtree.namegame.util.Ui;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Derek on 4/20/2017.
 */

public class NormalFragment extends Fragment {
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.image4)
    ImageView image4;
    @BindView(R.id.image5)
    ImageView image5;

    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.text5)
    TextView text5;

    @BindView(R.id.face_container)
    ViewGroup container;
    @BindView(R.id.title)
    TextView title;

    NormalController normalController;
    MatController matController;
    HintController hintController;

    private static final Interpolator OVERSHOOT = new OvershootInterpolator();
    private List<ImageView> faces = new ArrayList<>(5);
    private List<TextView> names = new ArrayList<>(5);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ViewGroup fragView =  (ViewGroup) inflater.inflate(R.layout.normal_fragment, container, false);
        ButterKnife.bind(this, fragView);
        return fragView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //if(savedInstanceState == null) {
            int gameMode = getArguments().getInt("GameMode");
            switch (gameMode) {
                case 0:
                    normalController = new NormalController(this);
                    normalController.generateView();
                    break;
                case 1:
                    matController = new MatController(this);
                    matController.generateView();
                    break;
                case 2:
                    hintController = new HintController(this);
                    hintController.generateView();
                    break;
            }
        //}

    }

    public void loadNames() {
        names.add(text1);
        names.add(text2);
        names.add(text3);
        names.add(text4);
        names.add(text5);
    }

    public void loadFaces() {
        faces.add(image1);
        faces.add(image2);
        faces.add(image3);
        faces.add(image4);
        faces.add(image5);
    }

    public void genComponents(String name) {
        title.setText(name);
    }

    /**
     * A method for setting the images from people into the imageviews
     */
    public void setImages(List<Person> randomPeople) {
        int imageSize = (int) Ui.convertDpToPixel(100, getContext());
        int n = faces.size();

        for (int i = 0; i < n; i++) {
            ImageView face = faces.get(i);
            Person person = randomPeople.get(i);
            face.setContentDescription(person
                    .getHeadshot()
                    .getAlt());
            Picasso.with(this.getContext()).load(person.getHeadshot().getUrl())
                    .placeholder(R.drawable.ic_face_white_48dp)
                    .error(R.drawable.ic_face_white_48dp)
                    .resize(imageSize, imageSize)
                    .transform(new CircleBorderTransform())
                    .into(face);
            setNames(person, i);
        }
    }

    private void setNames(Person person, int index) {
        names.get(index).setText(person.getFirstName() + " " + person.getLastName());
    }

    /**
     * A method to animate the faces into view
     */
    private void animateFacesIn() {
        title.animate().alpha(1).start();
        for (int i = 0; i < faces.size(); i++) {
            ImageView face = faces.get(i);
            face.animate().scaleX(1).scaleY(1).setStartDelay(800 + 120 * i).setInterpolator(OVERSHOOT).start();
        }
    }

    public void setCorrectFilter(int image) {
        faces.get(image).setColorFilter(0x8000ff00, PorterDuff.Mode.SRC_ATOP);
        names.get(image).setVisibility(View.VISIBLE);
        faces.get(image).setClickable(false);
    }

    public void setInCorrectFilter(int image) {
        faces.get(image).setColorFilter(0x80ff0000, PorterDuff.Mode.SRC_ATOP);
        names.get(image).setVisibility(View.VISIBLE);
        faces.get(image).setClickable(false);
    }

    public void setMissingPersonFilter() {

    }

    public void hidePerson(String alt) {
        for (int i = 0; i < faces.size(); i++) {
            if (faces.get(i).getContentDescription().equals(alt)) {
                faces.get(i).setVisibility(View.GONE);
                names.get(i).setVisibility(View.GONE);
                faces.get(i).setClickable(false);
            }
        }
    }

    // I know this is hackish but it's late -- sorry
    @OnClick(R.id.image1)
    public void onOneClick() {
        if (normalController != null) {
            normalController.isCorrect(0);
        } else if (matController != null) {
            matController.isCorrect(0);
        } else if (hintController != null) {
            hintController.isCorrect(0);
        }
    }

    @OnClick(R.id.image2)
    public void onTwoClick() {
        if (normalController != null) {
            normalController.isCorrect(1);
        } else if (matController != null) {
            matController.isCorrect(1);
        } else if (hintController != null) {
            hintController.isCorrect(1);
        }
    }

    @OnClick(R.id.image3)
    public void onThreeClick() {
        if (normalController != null) {
            normalController.isCorrect(2);
        } else if (matController != null) {
            matController.isCorrect(2);
        } else if (hintController != null) {
            hintController.isCorrect(2);
        }
    }

    @OnClick(R.id.image4)
    public void onFourClick() {
        if (normalController != null) {
            normalController.isCorrect(3);
        } else if (matController != null) {
            matController.isCorrect(3);
        } else if (hintController != null) {
            hintController.isCorrect(3);
        }
    }

    @OnClick(R.id.image5)
    public void onFiveClick() {
        if (normalController != null) {
            normalController.isCorrect(4);
        } else if (matController != null) {
            matController.isCorrect(4);
        } else if (hintController != null) {
            hintController.isCorrect(4);
        }
    }


}
