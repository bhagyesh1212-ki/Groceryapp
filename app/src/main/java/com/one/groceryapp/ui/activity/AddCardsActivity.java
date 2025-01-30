package com.one.groceryapp.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.ActivityAddCardsBinding;
import com.one.groceryapp.databinding.ActivityAddToCartBinding;
import com.one.groceryapp.databinding.ActivityMyCardsBinding;
import com.one.groceryapp.model.CardModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

public class AddCardsActivity extends AppCompatActivity {

    ActivityAddCardsBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddCardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.addCardBtn.setOnClickListener(v -> {

            String nameEdt = binding.nameEdt.getText().toString();
            String cardNumEdt = binding.cardNumEdt.getText().toString();
            String dateEdt = binding.dateEdt.getText().toString();
            String cvvEdt = binding.cvvEdt.getText().toString();

            if (nameEdt.isEmpty() || cardNumEdt.isEmpty() || dateEdt.isEmpty() || cvvEdt.isEmpty()) {
                Toast.makeText(this, "Please fill all the detail", Toast.LENGTH_SHORT).show();
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
