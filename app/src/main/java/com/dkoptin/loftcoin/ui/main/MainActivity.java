package com.dkoptin.loftcoin.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;

import com.dkoptin.loftcoin.BaseComponent;
import com.dkoptin.loftcoin.LoftApp;
import com.dkoptin.loftcoin.R;
import com.dkoptin.loftcoin.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class MainActivity extends AppCompatActivity {

    private MainComponent component;

    @Inject FragmentFactory fragmentFactory;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        BaseComponent baseComponent = ((LoftApp) newBase.getApplicationContext()).getComponent();
         component = DaggerMainComponent.builder().baseComponent(baseComponent).build();
         component.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().setFragmentFactory(fragmentFactory);
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);
        setContentView(binding.getRoot());
        final NavController navController = Navigation.findNavController(this, R.id.main_host);
        NavigationUI.setupWithNavController(binding.bottomNav, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, new AppBarConfiguration
                .Builder(binding.bottomNav.getMenu())
                .build());
    }

}