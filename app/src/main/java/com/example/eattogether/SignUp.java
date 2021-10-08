package com.example.eattogether;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.DragEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity {

    public static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 100;
    public static final int IMG_COD = 101;

    @BindView(R.id.check_gender)
    TextView checkGender;
    @BindView(R.id.check_pass)
    TextView checkPass;
    @BindView(R.id.check_email)
    TextView checkEmail;
    @BindView(R.id.check_phone)
    TextView checkPhone;
    @BindView(R.id.check_city)
    TextView checkCity;
    @BindView(R.id.check_country)
    TextView checkCountry;
    @BindView(R.id.check_re_pass)
    TextView checkRePass;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.edit_avatar)
    ImageButton editAvatar;
    @BindView(R.id.age)
    TextInputEditText age;
    @BindView(R.id.box_age)
    TextInputLayout boxAge;
    @BindView(R.id.name)
    TextInputEditText name;
    @BindView(R.id.phon_number)
    TextInputEditText phonNumber;
    @BindView(R.id.email)
    TextInputEditText email;
    @BindView(R.id.Password)
    TextInputEditText Password;
    @BindView(R.id.re_password)
    TextInputEditText rePassword;
    @BindView(R.id.check_name)
    TextView checkName;
    @BindView(R.id.check_age)
    TextView checkAge;
    @BindView(R.id.gender)
    Spinner gender;
    @BindView(R.id.country)
    Spinner country;
    @BindView(R.id.city)
    Spinner city;
    @BindView(R.id.checkBox_accept)
    CheckBox checkBoxAccept;
    @BindView(R.id.button_singup)
    AppCompatButton buttonSingup;
    @BindView(R.id.button_login_have_account)
    TextView buttonLoginHaveAccount;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        avatar.setOnClickListener(v -> checkPermeation());
        checkFields();

    }

    void checkFields() {
        name.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (!name.getText().toString().equals("")) {
                    checkName.setVisibility(View.VISIBLE);
                }
            }
        });
        age.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                int ageFromField = Integer.parseInt(age.getText().toString());
                if (ageFromField > 13 && ageFromField <= 100) {
                    checkAge.setVisibility(View.VISIBLE);
                }
            }
        });
        email.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if(isValidMail(email.getText().toString())){
                    checkEmail.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    void checkPermeation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                String permission = Manifest.permission.READ_EXTERNAL_STORAGE;
                requestPermissions(new String[]{permission}, REQUEST_CODE_READ_EXTERNAL_STORAGE);
            } else {
                pickImgFromGallery();
            }
        } else {
            pickImgFromGallery();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImgFromGallery();
                } else {
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    void pickImgFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMG_COD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMG_COD) {
            avatar.setImageURI(data.getData());
            avatar.setPadding(2, 2, 2, 2);
        }

    }
}