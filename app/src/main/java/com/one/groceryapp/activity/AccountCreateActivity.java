package com.one.groceryapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.one.groceryapp.R;
import com.one.groceryapp.databinding.ActivityAccountCreateBinding;
import com.one.groceryapp.room.AppDatabase;
import com.one.groceryapp.room.UserDao;
import com.one.groceryapp.room.UserModel;

import java.util.ArrayList;

public class AccountCreateActivity extends AppCompatActivity {
    ActivityAccountCreateBinding binding;
    AppDatabase appDatabase;
    UserDao userDao;
    ArrayList<String> userlist;
    UserModel userModel;
    FirebaseAuth mAuth;
    ProgressBar progress_circular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAccountCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.hidePass.setOnClickListener(view -> togglePasswordVisibility(binding.password, binding.hidePass));

        binding.loginBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccountCreateActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        userDao = appDatabase.userDao();

        binding.signupBottom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkuserfiled();
            }
        });
    }

    private void showProgressBar(boolean show) {
        if (show) {
            binding.progressCircular.setVisibility(View.VISIBLE);
        } else {
            binding.progressCircular.setVisibility(View.GONE);
        }
    }

    private void checkuserfiled() {
        final String email = binding.email.getText().toString();
        final String phonenum = binding.phonenum.getText().toString();
        final String password = binding.password.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(AccountCreateActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
        } else if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            Toast.makeText(AccountCreateActivity.this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
        } else if (phonenum.isEmpty()) {
            Toast.makeText(AccountCreateActivity.this, "Phone number is required", Toast.LENGTH_SHORT).show();
        } else if (phonenum.length() > 10) {
            Toast.makeText(AccountCreateActivity.this, "PhoneNumber contains only 10 digit", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(AccountCreateActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
        } else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            Toast.makeText(AccountCreateActivity.this, "Password must contain at least 8 characters, including uppercase, lowercase, a number, and a special character", Toast.LENGTH_LONG).show();
        } else {
            emailauthentication();
        }
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

    private void emailauthentication() {
        final String email = binding.email.getText().toString();
        final String phonenum = binding.phonenum.getText().toString();
        final String password = binding.password.getText().toString();
        showProgressBar(true);
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showProgressBar(false);
                            userModel = new UserModel();
                            userModel.setEmail(email);
                            userModel.setPassword(password);
                            userModel.setPhonenumber(phonenum);
                            userDao.insertdata(userModel);
                            Intent i = new Intent(AccountCreateActivity.this, MainActivity.class);
                            startActivity(i);
                            Toast.makeText(AccountCreateActivity.this, "Signup successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            showProgressBar(false);
                            Toast.makeText(AccountCreateActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}