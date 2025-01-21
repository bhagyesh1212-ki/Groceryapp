package com.one.groceryapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.one.groceryapp.R;
import com.one.groceryapp.databinding.ActivityForgotBinding;

public class ForgotActivity extends AppCompatActivity {

    ActivityForgotBinding binding;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityForgotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String etemail = getIntent().getExtras().getString("email");
        binding.email.setText(etemail);
        binding.sendLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendlink();
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void sendlink(){
        String email = binding.email.getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(ForgotActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(ForgotActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}