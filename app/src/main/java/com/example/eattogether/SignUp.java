package com.example.eattogether;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.ApiClint.ApiClint;
import com.example.eattogether.APIs.ResponseModle.ProcessRespondedCod;
import com.example.eattogether.Helper.ConverterImage;
import com.example.eattogether.Helper.GetCountriesHelper;
import com.example.eattogether.Helper.ValidationHelper;
import com.example.eattogether.Models.SingUpModel;
import com.example.eattogether.Models.SingUpResponseModel;
import com.example.eattogether.ui.Login;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    public static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 100;
    public static final int IMG_COD = 101;
    API api = ApiClint.getRetrofitInstance().create(API.class);
    SingUpModel user = new SingUpModel();

    @BindView(R.id.blurView)
    BlurView blurView;
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
    @BindView(R.id.check_name)
    TextView checkName;
    @BindView(R.id.check_age)
    TextView checkAge;
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
    TextInputEditText phoneNumber;
    @BindView(R.id.login_email)
    TextInputEditText email;
    @BindView(R.id.login_password)
    TextInputEditText password;
    @BindView(R.id.re_password)
    TextInputEditText rePassword;
    @BindView(R.id.country)
    Spinner country;
    @BindView(R.id.city)
    Spinner city;
    @BindView(R.id.checkBox_accept)
    CheckBox checkBoxAccept;
    @BindView(R.id.button_Login)
    AppCompatButton buttonSingup;
    @BindView(R.id.button_singup_dont_have_account)
    TextView buttonLoginHaveAccount;
    @BindView(R.id.gender)
    Spinner gender;
    @BindView(R.id.tv_dialog)
    TextView tvDialog;
    @BindView(R.id.tv_dialog1)
    TextView tvDialog1;
    @BindView(R.id.btn_dialog)
    AppCompatButton btnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        checkFields();

        country.setAdapter(GetCountriesHelper.getAllCountries(getApplicationContext()));

        buttonSingup.setOnClickListener(v -> {

            Call<SingUpResponseModel> call = api.registerNewUser(user);
            call.enqueue(new Callback<SingUpResponseModel>() {
                @Override
                public void onResponse(Call<SingUpResponseModel> call, Response<SingUpResponseModel> response) {
                    if (response.code() == 200) {
                        blurViewBackground();
                        btnDialog.setVisibility(View.VISIBLE);
                        tvDialog1.setVisibility(View.VISIBLE);
                        tvDialog.setVisibility(View.VISIBLE);
                    }
                    Toast.makeText(SignUp.this, "" + ProcessRespondedCod.processRespondedCod(response), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<SingUpResponseModel> call, Throwable t) {
                    Toast.makeText(SignUp.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });
        buttonLoginHaveAccount.setOnClickListener(v -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            this.finish();
        });
    }

    void checkFields() {
        name.setOnFocusChangeListener((v, hasFocus) -> {
            String nameFromField = name.getText().toString();
            if (!hasFocus) {
                if (!nameFromField.equals("")) {
                    checkName.setVisibility(View.VISIBLE);
                    user.setName(nameFromField);
                } else {
                    checkName.setVisibility(View.INVISIBLE);
                    user.setName("");
                }
            }
        });
        age.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                int ageFromField = 0;
                try {
                    ageFromField = Integer.parseInt(age.getText().toString());
                } catch (Exception ignored) {
                }

                if (ageFromField > 13 && ageFromField <= 100) {
                    checkAge.setVisibility(View.VISIBLE);
                    user.setAge(ageFromField);
                } else {
                    checkAge.setVisibility(View.INVISIBLE);
                    user.setAge(13);
                }
            }
        });
        email.setOnFocusChangeListener((v, hasFocus) -> {

            String emailFromField = email.getText().toString();
            if (!hasFocus) {
                if (ValidationHelper.isValidMail(emailFromField)) {
                    checkEmail.setVisibility(View.VISIBLE);
                    user.setEmailAddress(emailFromField);
                    setIconColor(R.drawable.ic_email, Color.parseColor("#F76700"), email);
                } else {
                    checkEmail.setVisibility(View.INVISIBLE);
                    user.setEmailAddress("");
                    setIconColor(R.drawable.ic_email, Color.BLACK, email);
                }
            } else {
                setIconColor(R.drawable.ic_email, Color.GRAY, email);
            }
        });
        password.setOnFocusChangeListener((v, hasFocus) -> {
            String passwordFromField = password.getText().toString();
            if (!hasFocus) {
                if (ValidationHelper.isValidPassword(passwordFromField)) {
                    checkPass.setVisibility(View.INVISIBLE);
                    setIconColor(R.drawable.ic_key, Color.BLACK, password);
                } else {
                    setIconColor(R.drawable.ic_key, Color.parseColor("#F76700"), password);
                    checkPass.setVisibility(View.VISIBLE);
                }
            } else {
                setIconColor(R.drawable.ic_key, Color.GRAY, password);
            }
        });
        rePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (rePassword.length() >= 8) {
                    if (rePassword.getText().toString().equals(password.getText().toString())) {
                        checkRePass.setVisibility(View.VISIBLE);
                        setIconColor(R.drawable.ic_key, Color.parseColor("#F76700"), rePassword);
                        user.setPassword(password.getText().toString());
                    } else if (checkRePass.getVisibility() == View.VISIBLE) {
                        user.setPassword("");
                        setIconColor(R.drawable.ic_key, Color.BLACK, rePassword);
                        checkRePass.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        rePassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                setIconColor(R.drawable.ic_key, Color.GRAY, rePassword);
            }
        });
        phoneNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (ValidationHelper.isValidMobile(phoneNumber.getText().toString())) {
                    checkPhone.setVisibility(View.VISIBLE);
                    user.setPhoneNumber(phoneNumber.getText().toString());
                }
            } else {
                checkPhone.setVisibility(View.INVISIBLE);
                user.setPhoneNumber("");
            }
        });
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    checkGender.setVisibility(View.VISIBLE);
                    user.setGender(position);
                    return;
                }
                checkGender.setVisibility(View.INVISIBLE);
                user.setGender(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    checkCountry.setVisibility(View.VISIBLE);
                    city.setAdapter(GetCountriesHelper.getAllCities(position, getApplicationContext()));
                    return;
                }
                checkCountry.setVisibility(View.INVISIBLE);
                city.setAdapter(GetCountriesHelper.getAllCities(position, getApplicationContext()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    checkCity.setVisibility(View.VISIBLE);
                    user.setCityId(position);
                    return;
                }
                checkCity.setVisibility(View.INVISIBLE);
                user.setCityId(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        avatar.setOnClickListener(v -> checkPermeation());

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
            String s = ConverterImage.convertUriToBase64(this, data.getData());
            user.setAvatar(s);
            avatar.setImageBitmap(ConverterImage.convertBase64ToBitmap(s));
            avatar.setPadding(10, 10, 10, 10);
            avatar.setScaleType(ImageView.ScaleType.FIT_XY);
        }

    }


    private void blurViewBackground() {
        float radius = 6f;

        View decorView = getWindow().getDecorView();
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);

        Drawable windowBackground = decorView.getBackground();
        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(this))
                .setBlurRadius(radius)
                .setBlurAutoUpdate(true)
                .setHasFixedTransformationMatrix(true); // Or false if it's in a scrolling container or might be animated

    }

    void setIconColor(int res, int color, TextInputEditText tx) {

        Drawable drawable = getApplicationContext().getResources().getDrawable(res);
        DrawableCompat.setTint(drawable, color); // Set whatever color you want
        tx.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }

}