package com.one.groceryapp.ui.activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.auth.FirebaseUser;
import com.one.groceryapp.R;
import com.one.groceryapp.databinding.ActivityLoginBinding;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;
import com.one.groceryapp.roomdb.UserModel;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    AppDatabase appDatabase;
    UserDao userDao;
    UserModel userModel;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.hidePass.setOnClickListener(view -> togglePasswordVisibility(binding.password, binding.hidePass));
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        userDao = appDatabase.userDao();

        binding.signupBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, AccountCreateActivity.class);
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

        binding.loginBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkfield();
                savetopref();
            }
        });

        SharedPreferences sf = getApplicationContext().getSharedPreferences("userData", MODE_PRIVATE);
        Boolean ischeck =sf.getBoolean("ischecked", false);
        if(ischeck){
            binding.switchview.setChecked(true);
            String email = sf.getString("email", "");
            String pass = sf.getString("password", "");
            binding.email.setText(email);
            binding.password.setText(pass);
        }else {
                binding.switchview.setChecked(false);
        }

        binding.switchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.switchview.isChecked()) {
                    SharedPreferences sf = getApplicationContext().getSharedPreferences("userData", MODE_PRIVATE);
                    String email = sf.getString("email", "");
                    String pass = sf.getString("password", "");
                    binding.email.setText(email);
                    binding.password.setText(pass);
                }
            }
        });

        binding.forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgotActivity.class);
                i.putExtra("email", binding.email.getText().toString());
                startActivity(i);
            }
        });
    }

    private void savetopref() {
        if (binding.switchview.isChecked()) {
            SharedPreferences sf = getApplicationContext().getSharedPreferences("userData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString("email", binding.email.getText().toString());
            editor.putString("password", binding.password.getText().toString());
            editor.putBoolean("ischecked", true);
            editor.apply();
        }else{
            SharedPreferences sf = getApplicationContext().getSharedPreferences("userData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putBoolean("ischecked", false);
        }
    }

    private void checkfield() {
        String email = binding.email.getText().toString();
        String password = binding.password.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(LoginActivity.this, "email is required", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "password is required", Toast.LENGTH_SHORT).show();
        } else {
            loginauthentication();
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

    private void loginauthentication() {
        mAuth = FirebaseAuth.getInstance();
        String email = binding.email.getText().toString().trim();
        String password = binding.password.getText().toString().trim();
        showProgressBar(true);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        showProgressBar(false);
                        if (task.isSuccessful()) {
//                            userModel = userDao.verifyuser(email, password);
//                            if (userModel != null)
                            //{
//                                if (userModel.getEmail().equalsIgnoreCase(email) && userModel.getPassword().equalsIgnoreCase(password)) {
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
//                                } else {
//                                    Toast.makeText(LoginActivity.this, "Enter valid email and password", Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                Toast.makeText(LoginActivity.this, "User not found, Please signup first", Toast.LENGTH_SHORT).show();
//                            }
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            Toast.makeText(LoginActivity.this, "User not registered,Please signup first",
                                    Toast.LENGTH_SHORT).show();
                        }
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
}
