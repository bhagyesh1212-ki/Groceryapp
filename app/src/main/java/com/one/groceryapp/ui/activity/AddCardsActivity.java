package com.one.groceryapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.one.groceryapp.databinding.ActivityAddCardsBinding;

public class AddCardsActivity extends AppCompatActivity {
    ActivityAddCardsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddCardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> {
            startActivity(new Intent(this, MyCardsActivity.class));
            finish();
        });

        binding.addCardBtn.setOnClickListener(v -> {
            String nameEdt = binding.nameEdt.getText().toString();
            String cardNumEdt = binding.cardNumEdt.getText().toString();
            String dateEdt = binding.dateEdt.getText().toString();
            String cvvEdt = binding.cvvEdt.getText().toString();

            if (nameEdt.isEmpty() || cardNumEdt.isEmpty() || dateEdt.isEmpty() || cvvEdt.isEmpty()) {
                Toast.makeText(this, "Please fill all the detail", Toast.LENGTH_SHORT).show();
            } else if (!dateEdt.matches("^(0[1-9]|1[0-2])/[2-9]{2}$")) {
                Toast.makeText(this, "Enter valid date", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(this, MyCardsActivity.class);
                i.putExtra("nameEdt", nameEdt);
                i.putExtra("cardNumEdt", cardNumEdt);
                i.putExtra("dateEdt", dateEdt);
                i.putExtra("cvvEdt", cvvEdt);
                startActivity(i);
                finish();
            }
        });
    }
}


