package com.one.groceryapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.ActivityAboutMeBinding;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;
import com.one.groceryapp.ui.fragment.ProfileFragment;

public class AboutMeActivity extends AppCompatActivity {

    ActivityAboutMeBinding binding;
    AppDatabase appDatabase;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutMeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appDatabase = AppDatabase.getInstance(getApplicationContext());
        userDao = appDatabase.userDao();

        binding.back.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.showPassword.setOnClickListener(v -> {
            togglePasswordVisibility(binding.newPassword, binding.showPassword);
        });

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");

        binding.name.setText(name);
        binding.email.setText(email);

        String phonenumber = String.valueOf(userDao.getphonenumber());
        binding.phone.setText(phonenumber);

        String password = userDao.getpassword();


        binding.saveSettings.setOnClickListener(v -> {
            String password_edt = binding.currrentPassword.getText().toString();
            String new_pasword_edt = binding.newPassword.getText().toString();
            String new_confirm_password_edt = binding.confirmNewPassword.getText().toString();

            if (password_edt.isEmpty()) {
                Toast.makeText(AboutMeActivity.this, "Current Password is required to change password", Toast.LENGTH_SHORT).show();
            } else if (!password_edt.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
                Toast.makeText(AboutMeActivity.this, "Confirm Password must contain at least 8 characters, including uppercase, lowercase, a number, and a special character", Toast.LENGTH_LONG).show();
            } else if (!password_edt.matches(password)) {
                Toast.makeText(this, "Current password does not match with your actual password", Toast.LENGTH_SHORT).show();
            } else if (new_pasword_edt.isEmpty()) {
                Toast.makeText(this, "New Password is required", Toast.LENGTH_SHORT).show();
            } else if (!new_pasword_edt.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
                Toast.makeText(AboutMeActivity.this, "New Password must contain at least 8 characters, including uppercase, lowercase, a number, and a special character", Toast.LENGTH_LONG).show();
            } else if (new_confirm_password_edt.isEmpty()) {
                Toast.makeText(this, "New Confirm Password is required", Toast.LENGTH_SHORT).show();
            } else if (!new_confirm_password_edt.matches(new_pasword_edt)) {
                Toast.makeText(this, "New password does not match with confirm password", Toast.LENGTH_SHORT).show();
            } else {
                updatepassword(new_pasword_edt,password);
                startActivity(new Intent(AboutMeActivity.this, ProfileFragment.class));
                Toast.makeText(this, " Password is Successfully updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updatepassword(String Newpassword, String password) {
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        userDao = appDatabase.userDao();
        userDao.update(Newpassword,password);
    }


    private void togglePasswordVisibility(EditText passwordField, ImageView toggleIcon) {
        if (passwordField.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
            toggleIcon.setImageResource(R.drawable.seeview);
            passwordField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            toggleIcon.setImageResource(R.drawable.hideview);
            passwordField.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        passwordField.setSelection(passwordField.getText().length());
    }
}