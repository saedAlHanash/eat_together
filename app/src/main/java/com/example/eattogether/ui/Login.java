package com.example.eattogether.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.ApiClint;
import com.example.eattogether.APIs.LoginResponse;
import com.example.eattogether.APIs.ProcessRespondedCod;
import com.example.eattogether.Helper.ValidationHelper;
import com.example.eattogether.Models.LoginModel;
import com.example.eattogether.R;
import com.example.eattogether.SignUp;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    LoginModel loginModel = new LoginModel();
    API api = ApiClint.getRetrofitInstance().create(API.class);
    @BindView(R.id.login_email)
    TextInputEditText loginEmail;
    @BindView(R.id.login_password)
    TextInputEditText loginPassword;
    @BindView(R.id.login_remember_me)
    CheckBox loginRememberMe;
    @BindView(R.id.button_Login)
    AppCompatButton buttonLogin;
    @BindView(R.id.button_singup_dont_have_account)
    TextView buttonSingupDontHaveAccount;
    @BindView(R.id.textInputLayout_email)
    TextInputLayout textInputLayoutEmail;
    @BindView(R.id.textInputLayout_pass)
    TextInputLayout textInputLayoutPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        listener();
        //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();

    }

    void listener() {
        buttonSingupDontHaveAccount.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUp.class);
            this.startActivity(intent);
            this.finish();
        });
        buttonLogin.setOnClickListener(v -> {
            loginModel.setUserNameOrEmailAddress(loginEmail.getText().toString());
            loginModel.setPassword(loginPassword.getText().toString());
            loginModel.setRememberClient(loginRememberMe.isChecked());
            Call<LoginResponse> call = api.login(loginModel);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.code()!=200){
                        //Toast.makeText(Login.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(Login.this, ""+ ProcessRespondedCod.processRespondedCod(response), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Login.this, ""+response.body().getResult().getUserId()+"ok", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,Home.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


//            if(ValidationHelper.isValidPassword(loginPassword.getText().toString())){
//                loginModel.setPassword(loginPassword.getText().toString());
//            }else {
//                textInputLayoutPass.setError("wrong password...");
//            }
//
//            if (ValidationHelper.isValidMail(loginEmail.getText().toString())) {
//                loginModel.setUserNameOrEmailAddress(loginEmail.getText().toString());
//            } else {
//                textInputLayoutEmail.setError("wrong email...");
//            }
//            textInputLayoutEmail.clearFocus();
//            textInputLayoutPass.clearFocus();
//
//            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        });
        loginEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(textInputLayoutEmail.isErrorEnabled()){
                    textInputLayoutEmail.setError(null);
                    textInputLayoutEmail.setErrorEnabled(false);
                }
            }
        });
        loginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(textInputLayoutPass.isErrorEnabled()){
                    textInputLayoutPass.setError(null);
                    textInputLayoutPass.setErrorEnabled(false);
                }
            }
        });

    }
}