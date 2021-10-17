package com.example.eattogether.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.eattogether.AppConfig;
import com.example.eattogether.Models.FindPartnersModel;
import com.example.eattogether.R;
import com.example.eattogether.ui.Fragments.FragmentHome;
import com.example.eattogether.ui.Fragments.FragmentMessage;
import com.example.eattogether.ui.Fragments.FragmentResultSearch;
import com.example.eattogether.ui.Fragments.FragmentSearch;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Home extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @BindView(R.id.tv_titel_appbar)
    TextView tvTitleAppbar;
    @BindView(R.id.image_message)
    ImageView imageMessage;
    @BindView(R.id.image_search)
    ImageView imageSearch;
    @BindView(R.id.image_home)
    ImageView imageHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        AppConfig.getSharedPreferencesInstance(this);
        listeners();
    }

    void listeners() {
        imageHome.setOnClickListener(v -> {
            restColor();
            tvTitleAppbar.setText("Home");
            imageHome.setColorFilter(Color.parseColor("#FF6F00"));
            navigateFragment(new FragmentHome());
        });
        imageMessage.setOnClickListener(v -> {
            tvTitleAppbar.setText("Messages");
            restColor();
            imageMessage.setColorFilter(Color.parseColor("#FF6F00"));
            navigateFragment(new FragmentMessage());

        });
        imageSearch.setOnClickListener(v -> {
            tvTitleAppbar.setText("Search");
            restColor();
            imageSearch.getBackground().setTint(Color.parseColor("#FF6F00"));
            navigateFragment(new FragmentSearch());

        });
    }

    void restColor() {
        imageHome.setColorFilter(Color.BLACK);
        imageSearch.getBackground().setTint(Color.BLACK);
        imageMessage.setColorFilter(Color.BLACK);
    }

    void navigateFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    public void replaceFragment(ArrayList<FindPartnersModel.ResultFindPartnersModel> model) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        FragmentResultSearch mFrag = new FragmentResultSearch(model);
        t.replace(R.id.fragment, mFrag);
        t.addToBackStack(null);
        t.commit();
    }

}