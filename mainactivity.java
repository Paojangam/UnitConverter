package com.example.converter1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private Spinner spinnerUnits;
    private TextView tvResultValue;
    private Button btnConvert;

    private String[] units = {"Meters", "Millimeters"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.et_input);
        spinnerUnits = findViewById(R.id.spinner_units);
        tvResultValue = findViewById(R.id.tv_result_value);
        btnConvert = findViewById(R.id.btn_convert);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnits.setAdapter(adapter);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String input = etInput.getText().toString();
        if (!input.isEmpty()) {
            double value = Double.parseDouble(input);
            int selectedUnit = spinnerUnits.getSelectedItemPosition();
            double result = 0.0;

            switch (selectedUnit) {
                case 0: // Centimeters to Meters
                    result = LengthConverter.centimetersToMeters(value);
                    break;
                case 1: // Centimeters to Millimeters
                    result = LengthConverter.centimetersToMillimeters(value);
                    break;
            }

            tvResultValue.setText(String.valueOf(result));
        } else {
            tvResultValue.setText("Please enter a value");
        }
    }
}

class LengthConverter {
    public static double centimetersToMeters(double centimeters) {
        return centimeters / 100.0;
    }

    public static double centimetersToMillimeters(double centimeters) {
        return centimeters * 10.0;
    }
}
