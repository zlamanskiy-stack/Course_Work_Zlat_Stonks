package com.example.course_work_zlat_stonks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    private SwitchCompat switchTheme;
    private TextView tvUsername, tvMode;
    private UserPreferences userPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userPrefs = new UserPreferences(this);
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();
        setupListeners();
        loadUserInfo();
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        switchTheme = findViewById(R.id.switch_theme);
        tvUsername = findViewById(R.id.tv_username);
        tvMode = findViewById(R.id.tv_mode);

        switchTheme.setChecked(!userPrefs.isDarkMode());
    }

    private void setupListeners() {
        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userPrefs.setDarkMode(!isChecked);
            recreate();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void loadUserInfo() {
        tvUsername.setText("Имя: " + userPrefs.getUsername());
        if (userPrefs.isGuest()) {
            tvMode.setText("Режим: 🎭 Гостевой (ограниченный функционал)");
        } else {
            tvMode.setText("Режим: 👤 Пользователь (полный доступ)");
        }
    }

    private void applyTheme() {
        if (userPrefs != null && !userPrefs.isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}