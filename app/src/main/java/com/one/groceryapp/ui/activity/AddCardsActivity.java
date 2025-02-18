package com.one.groceryapp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.one.groceryapp.databinding.ActivityAddCardsBinding;

public class AddCardsActivity extends AppCompatActivity {
    ActivityAddCardsBinding binding;
    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddCardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> {
            if (pressedTime + 2000 > System.currentTimeMillis()) {
                startActivity(new Intent(this, MyCardsActivity.class));
                finish();
            } else {
                hideKeyboard(AddCardsActivity.this);
            }
            pressedTime = System.currentTimeMillis();
        });

        binding.dateEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("TAG@@", "onTextChanged: " + s);
                if (s.length() == 2 && before == 0) { // Ensure it's an insertion, not deletion
                    binding.dateEdt.removeTextChangedListener(this); // Remove listener temporarily
                    String newText = s + "/";
                    binding.dateEdt.setText(newText);
                    binding.dateEdt.setSelection(newText.length()); // Move cursor to the end
                    binding.dateEdt.addTextChangedListener(this); // Reattach listener
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.addCardBtn.setOnClickListener(v -> {
            String nameEdt = binding.nameEdt.getText().toString();
            String cardNumEdt = binding.cardNumEdt.getText().toString();
            String dateEdt = binding.dateEdt.getText().toString();
            String cvvEdt = binding.cvvEdt.getText().toString();

            if (binding.switchView.isChecked()) {
                SharedPreferences sf = getSharedPreferences("saveCard", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                editor.putString("name", nameEdt);
                editor.putString("number", cardNumEdt);
                editor.putString("date", dateEdt);
                editor.putString("cvv", cvvEdt);
                editor.apply();
            }

            if (nameEdt.isEmpty() || cardNumEdt.isEmpty() || dateEdt.isEmpty() || cvvEdt.isEmpty()) {
                Toast.makeText(this, "Please fill all the detail", Toast.LENGTH_SHORT).show();
            } else if (cardNumEdt.length() != 16) {
                Toast.makeText(this, "Enter valid cardnumber", Toast.LENGTH_SHORT).show();
            } else if (cvvEdt.length() != 3) {
                Toast.makeText(this, "Enter valid cvv", Toast.LENGTH_SHORT).show();
            } else if (!dateEdt.matches("^(0[1-9]|1[0-2])/(2[5-9]|[3-9][0-9])$")) {
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

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}


