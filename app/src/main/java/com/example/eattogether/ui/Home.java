package com.example.eattogether.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.eattogether.AppConfig;
import com.example.eattogether.R;
import com.example.eattogether.ui.Fragments.FragmentHome;
import com.example.eattogether.ui.Fragments.FragmentMessage;
import com.example.eattogether.ui.Fragments.FragmentSearch;

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

    NavController navController;

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
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment,new FragmentHome());
            fragmentTransaction.commit();

        });
        imageMessage.setOnClickListener(v -> {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment,new FragmentMessage());
            fragmentTransaction.commit();

        });
        imageSearch.setOnClickListener(v -> {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment,new FragmentSearch());
            fragmentTransaction.commit();

        });
    }


}