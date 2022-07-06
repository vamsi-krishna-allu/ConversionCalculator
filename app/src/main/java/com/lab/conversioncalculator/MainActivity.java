package com.lab.conversioncalculator;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    private EditText editText;
    private TextView textView;

    private static final String DISTANCE = "Distance";
    private static final String TEMPERATURE = "Temperature";
    private static final String WEIGHT = "Weight";
    private static final String QUANTITY = "Quantity";
    
    private static final String KILOMETER = "Kilometers";
    private static final String MILES = "Miles";
    private static final String CENTIMETER = "Centimeters";
    private static final String INCH = "Inches";
    
    private static final String KILOGRAM = "Kilograms";
    private static final String LB = "Pounds";
    private static final String GRAM = "Grams";
    private static final String OZ = "Ounces";

    private static final String CENTIGRADE = "Celsius";
    private static final String FAHRENHEIT = "Fahrenheit";
    private static final String KELVIN = "Kelvin";
    
    private static final String LITRE = "Liters";
    private static final String CUPS = "Cups";

    private String inputConversionType = INCH;
    private String outputConversionType = INCH;
    private String selectedConversionType = DISTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting the full screen flag to hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // setting custom spinner to display it in the style we need using ArrayAdapter
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conversion_values, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(adapter);

        editText = findViewById(R.id.editTextNumberDecimal);
        textView = findViewById(R.id.textView);

        updateSpinners(DISTANCE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // update the change value based on user selection from spinner
                switch(i){
                    case 0:
                        updateSpinners(DISTANCE);
                        inputConversionType = INCH;
                        outputConversionType = INCH;
                        selectedConversionType = DISTANCE;
                        break;
                    case 1:
                        updateSpinners(TEMPERATURE);
                        inputConversionType = CENTIGRADE;
                        outputConversionType = CENTIGRADE;
                        selectedConversionType = TEMPERATURE;
                        break;
                    case 2:
                        updateSpinners(QUANTITY);
                        inputConversionType = LITRE;
                        outputConversionType = LITRE;
                        selectedConversionType = QUANTITY;
                        break;
                    case 3:
                        updateSpinners(WEIGHT);
                        inputConversionType = KILOGRAM;
                        outputConversionType = KILOGRAM;
                        selectedConversionType = WEIGHT;
                        break;
                    default:
                        break;
                }
                editText.setText(null);
                textView.setText(null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        updateSpinner2Listener();
        updateSpinner3Listener();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::convertData);
    }

    private void updateSpinner3Listener() {
        Spinner spinner3 = findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(selectedConversionType){
                    case DISTANCE:
                        if(i == 0){
                            outputConversionType = INCH;
                        }else if(i == 1){
                            outputConversionType = CENTIMETER;
                        }else if(i == 2){
                            outputConversionType = KILOMETER;
                        }else if(i == 3){
                            outputConversionType = MILES;
                        }
                        break;
                    case TEMPERATURE:
                        if(i == 0){
                            outputConversionType = CENTIGRADE;
                        }else if(i == 1){
                            outputConversionType = KELVIN;
                        }else if(i == 2){
                            outputConversionType = FAHRENHEIT;
                        }
                        break;
                    case WEIGHT:
                        if(i == 0){
                            outputConversionType = KILOGRAM;
                        }else if(i == 1){
                            outputConversionType = LB;
                        }else if(i == 2){
                            outputConversionType = GRAM;
                        }else if(i == 3){
                            outputConversionType = OZ;
                        }
                        break;
                    case QUANTITY:
                        if(i == 0){
                            outputConversionType = LITRE;
                        }else if(i == 1){
                            outputConversionType = CUPS;
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void updateSpinner2Listener() {
        Spinner spinner2 = findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(selectedConversionType){
                    case DISTANCE:
                        if(i == 0){
                            inputConversionType = INCH;
                        }else if(i == 1){
                            inputConversionType = CENTIMETER;
                        }else if(i == 2){
                            inputConversionType = KILOMETER;
                        }else if(i == 3){
                            inputConversionType = MILES;
                        }
                        break;
                    case TEMPERATURE:
                        if(i == 0){
                            inputConversionType = CENTIGRADE;
                        }else if(i == 1){
                            inputConversionType = KELVIN;
                        }else if(i == 2){
                            inputConversionType = FAHRENHEIT;
                        }
                        break;
                    case WEIGHT:
                        if(i == 0){
                            inputConversionType = KILOGRAM;
                        }else if(i == 1){
                            inputConversionType = LB;
                        }else if(i == 2){
                            inputConversionType = GRAM;
                        }else if(i == 3){
                            inputConversionType = OZ;
                        }
                        break;
                    case QUANTITY:
                        if(i == 0){
                            inputConversionType = LITRE;
                        }else if(i == 1){
                            inputConversionType = CUPS;
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void quantityConverter() {
        Float valueToBeConverted = Float.parseFloat(editText.getText().toString());
        String valueAfterConversion = "";
        if(inputConversionType.equals(LITRE)){
            switch(outputConversionType){
                case LITRE:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
                case CUPS:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 4.17f);
                    break;
            }
        }else if(inputConversionType.equals(CUPS)){
            switch(outputConversionType){
                case LITRE:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 0.24f);
                    break;
                case CUPS:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
            }
        }
        textView.setText(valueAfterConversion);
    }

    private void weightConverter() {
        Float valueToBeConverted = Float.parseFloat(editText.getText().toString());
        String valueAfterConversion = "";
        if(inputConversionType.equals(KILOGRAM)){
            switch(outputConversionType){
                case KILOGRAM:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
                case LB:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 2.2f);
                    break;
                case GRAM:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 1000f);
                    break;
                case OZ:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 35.274f);
                    break;
            }
        }else if(inputConversionType.equals(LB)){
            switch(outputConversionType){
                case KILOGRAM:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 0.45f);
                    break;
                case LB:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
                case GRAM:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 453.6f);
                    break;
                case OZ:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 16f);
                    break;
            }
        }
        else if(inputConversionType.equals(GRAM)){
            switch(outputConversionType){
                case KILOGRAM:
                    valueAfterConversion = String.valueOf(valueToBeConverted/1000f);
                    break;
                case LB:
                    valueAfterConversion = String.valueOf(valueToBeConverted/453.6f);
                    break;
                case GRAM:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
                case OZ:
                    valueAfterConversion = String.valueOf(valueToBeConverted/28.35f);
                    break;
            }
        }else if(inputConversionType.equals(OZ)){
            switch(outputConversionType){
                case KILOGRAM:
                    valueAfterConversion = String.valueOf(valueToBeConverted/35.274f);
                    break;
                case LB:
                    valueAfterConversion = String.valueOf(valueToBeConverted/16f);
                    break;
                case GRAM:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 28.35f);
                    break;
                case OZ:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
            }
        }
        textView.setText(valueAfterConversion);
    }

    private void temperatureConverter() {
        Float valueToBeConverted = Float.parseFloat(editText.getText().toString());
        String valueAfterConversion = "";
        if(inputConversionType.equals(CENTIGRADE)){
            switch(outputConversionType){
                case CENTIGRADE:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
                case KELVIN:
                    valueAfterConversion = String.valueOf(valueToBeConverted + 273.15f);
                    break;
                case FAHRENHEIT:
                    valueAfterConversion = String.valueOf((valueToBeConverted * 9f/5) + 32);
                    break;
            }
        }else if(inputConversionType.equals(KELVIN)){
            switch(outputConversionType){
                case CENTIGRADE:
                    valueAfterConversion = String.valueOf(valueToBeConverted - 273.15f);
                    break;
                case KELVIN:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
                case FAHRENHEIT:
                    valueAfterConversion = String.valueOf(((valueToBeConverted - 273.15f) * 9f/5) + 32);
                    break;
            }
        }else if(inputConversionType.equals(FAHRENHEIT)){
            switch(outputConversionType){
                case CENTIGRADE:
                    valueAfterConversion = String.valueOf((valueToBeConverted - 32) * 5f/9);
                    break;
                case KELVIN:
                    valueAfterConversion = String.valueOf(((valueToBeConverted - 32) * 5f/9) + 273.15f);
                    break;
                case FAHRENHEIT:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
            }
        }
        textView.setText(valueAfterConversion);
    }

    private void distanceConverter() {
        Float valueToBeConverted = Float.parseFloat(editText.getText().toString());
        String valueAfterConversion = "";
        if(inputConversionType.equals(INCH)){
            switch(outputConversionType){
                case INCH:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
                case CENTIMETER:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 2.54f);
                    break;
                case KILOMETER:
                    valueAfterConversion = String.valueOf(valueToBeConverted/39370f);
                    break;
                case MILES:
                    valueAfterConversion = String.valueOf(valueToBeConverted/63360f);
                    break;
            }
        }else if(inputConversionType.equals(CENTIMETER)){
            switch(outputConversionType){
                case INCH:
                    valueAfterConversion = String.valueOf(valueToBeConverted/2.54f);
                    break;
                case CENTIMETER:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
                case KILOMETER:
                    valueAfterConversion = String.valueOf(valueToBeConverted/100000f);
                    break;
                case MILES:
                    valueAfterConversion = String.valueOf(valueToBeConverted/160900f);
                    break;
            }
        }else if(inputConversionType.equals(KILOMETER)){
            switch(outputConversionType){
                case INCH:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 39370f);
                    break;
                case CENTIMETER:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 100000f);
                    break;
                case KILOMETER:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
                case MILES:
                    valueAfterConversion = String.valueOf(valueToBeConverted/1.609f);
                    break;
            }
        }else if(inputConversionType.equals(MILES)){
            switch(outputConversionType){
                case INCH:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 63360f);
                    break;
                case CENTIMETER:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 160900f);
                    break;
                case KILOMETER:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 1.609f);
                    break;
                case MILES:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
            }
        }
        textView.setText(valueAfterConversion);
    }


    private void updateSpinners(String conversionType) {
        Spinner spinner2 = findViewById(R.id.spinner2);
        Spinner spinner3 = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = null;
        switch(conversionType){
            case DISTANCE:
                adapter = ArrayAdapter.createFromResource(this, R.array.distance_conversion_values, R.layout.spinner_layout);
                break;
            case TEMPERATURE:
                adapter = ArrayAdapter.createFromResource(this, R.array.temperature_conversion_values, R.layout.spinner_layout);
                break;
            case WEIGHT:
                adapter = ArrayAdapter.createFromResource(this, R.array.weight_conversion_values, R.layout.spinner_layout);
                break;
            case QUANTITY:
                adapter = ArrayAdapter.createFromResource(this, R.array.quantity_conversion_values, R.layout.spinner_layout);
                break;
            default:
                break;
        }
        if (adapter != null) {
            adapter.setDropDownViewResource(R.layout.spinner_layout);
            spinner2.setAdapter(adapter);
            spinner3.setAdapter(adapter);
        }
    }


    public void convertData(View view) {
        if(editText.getText() == null || isEmpty(editText.getText().toString())){
            Toast toast = Toast.makeText(getApplicationContext(), "Enter Valid Value", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        switch(selectedConversionType){
            case DISTANCE:
                distanceConverter();
                break;
            case TEMPERATURE:
                temperatureConverter();
                break;
            case WEIGHT:
                weightConverter();
                break;
            case QUANTITY:
                quantityConverter();
                break;
            default:
                break;
        }
    }
}