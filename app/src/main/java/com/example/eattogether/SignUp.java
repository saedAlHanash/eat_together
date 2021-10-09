package com.example.eattogether;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Patterns;
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

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.APIError;
import com.example.eattogether.APIs.ApiClint;
import com.example.eattogether.APIs.ProcessRespondedCod;
import com.example.eattogether.Helper.ConverterImage;
import com.example.eattogether.Helper.GetCountriesHelper;
import com.example.eattogether.Models.SingUpModel;
import com.example.eattogether.Models.SingUpResponseModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @BindView(R.id.email)
    TextInputEditText email;
    @BindView(R.id.Password)
    TextInputEditText password;
    @BindView(R.id.re_password)
    TextInputEditText rePassword;
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
                    if(response.code() == 200)
                    {
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
                if (isValidMail(emailFromField)) {
                    checkEmail.setVisibility(View.VISIBLE);
                    user.setEmailAddress(emailFromField);
                } else {
                    checkEmail.setVisibility(View.INVISIBLE);
                    user.setEmailAddress("");
                }
            }
        });
        password.setOnFocusChangeListener((v, hasFocus) -> {
            String passwordFromField = password.getText().toString();
            if (!hasFocus) {
                if (passwordFromField.length() < 8 && !isValidPassword(passwordFromField)) {
                    Toast.makeText(this, "not valid", Toast.LENGTH_SHORT).show();
                    checkPass.setVisibility(View.INVISIBLE);
                } else {
                    checkPass.setVisibility(View.VISIBLE);
                }
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
                        user.setPassword(password.getText().toString());
                    } else if (checkRePass.getVisibility() == View.VISIBLE) {
                        user.setPassword("");
                        checkRePass.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        phoneNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (isValidMobile(phoneNumber.getText().toString())) {
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

    private boolean isValidMobile(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    private boolean isValidMail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
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

    String convertUriToBase64(Uri selectedFile) {
        Bitmap bitmap = null;
        String encodedString = null;
        if (selectedFile != null) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] byteArray = outputStream.toByteArray();

            encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
        return encodedString;
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

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

}