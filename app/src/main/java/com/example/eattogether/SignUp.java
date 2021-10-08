package com.example.eattogether;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity {


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
    @BindView(R.id.box_name)
    TextInputLayout boxName;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }
}