package com.example.course_work_zlat_stonks;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnGuest;
    private TextView tvError;
    private UserPreferences userPrefs;
    private static final String VALID_USERNAME = "Money";
    private static final String VALID_PASSWORD = "CCC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userPrefs = new UserPreferences(this);
        if (!userPrefs.isDarkMode()) {
            setTheme(R.style.AppTheme_Light);
        } else {
            setTheme(R.style.AppTheme_Dark);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (userPrefs.isLoggedIn()) {
            goToMain();
            return;
        }

        initViews();
        setupListeners();
    }

    private void initViews() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnGuest = findViewById(R.id.btn_guest);
        tvError = findViewById(R.id.tv_error);
    }

    private void setupListeners() {
        btnLogin.setOnClickListener(v -> login());
        btnGuest.setOnClickListener(v -> loginAsGuest());
    }

    private void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            showError("Введите имя пользователя");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            showError("Введите пароль");
            return;
        }

        // Простая проверка
        if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
            userPrefs.setLoggedIn(true, username, false);
            goToMain();
        } else {
            showError("Неверное имя пользователя или пароль");
        }
    }

    private void loginAsGuest() {
        userPrefs.setLoggedIn(true, "Гость", true);
        goToMain();
    }

    private void showError(String message) {
        tvError.setText(message);
        tvError.setVisibility(View.VISIBLE);
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void applyTheme() {
        if (userPrefs != null && !userPrefs.isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
}