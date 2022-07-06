package com.lab.conversioncalculator;

import static android.text.TextUtils.isEmpty;

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

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;

    // constants for spinner related to type of conversion
    private static final String DISTANCE = "Distance";
    private static final String TEMPERATURE = "Temperature";
    private static final String WEIGHT = "Weight";
    private static final String QUANTITY = "Quantity";

    // constants for input/output spinner when type of conversion is Distance
    private static final String KILOMETER = "Kilometers";
    private static final String MILES = "Miles";
    private static final String CENTIMETER = "Centimeters";
    private static final String INCH = "Inches";

    // constants for input/output spinner when type of conversion is Weight
    private static final String KILOGRAM = "Kilograms";
    private static final String LB = "Pounds";
    private static final String GRAM = "Grams";
    private static final String OZ = "Ounces";

    // constants for input/output spinner when type of conversion is Temperature
    private static final String CENTIGRADE = "Celsius";
    private static final String FAHRENHEIT = "Fahrenheit";
    private static final String KELVIN = "Kelvin";

    // constants for input/output spinner when type of conversion is Quantity
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

        // setting custom spinner for type of conversion spinner to display it in the style we need using ArrayAdapter
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conversion_values, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(adapter);

        editText = findViewById(R.id.editTextNumberDecimal);
        textView = findViewById(R.id.textView);

        updateSpinners(DISTANCE);

        // listener for type of conversion spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // update the change value based on user selection from spinner
                switch (i) {
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

        updateSpinnerInputUnitListener();
        updateSpinnerOutputUnitListener();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::convertData);
    }

    /**
     * Adds listener for spinner related to output value unit
     */
    private void updateSpinnerOutputUnitListener() {
        Spinner outputSpinnerUnit = findViewById(R.id.spinner3);
        outputSpinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (selectedConversionType) {
                    case DISTANCE:
                        if (i == 0) {
                            outputConversionType = INCH;
                        } else if (i == 1) {
                            outputConversionType = CENTIMETER;
                        } else if (i == 2) {
                            outputConversionType = KILOMETER;
                        } else if (i == 3) {
                            outputConversionType = MILES;
                        }
                        break;
                    case TEMPERATURE:
                        if (i == 0) {
                            outputConversionType = CENTIGRADE;
                        } else if (i == 1) {
                            outputConversionType = KELVIN;
                        } else if (i == 2) {
                            outputConversionType = FAHRENHEIT;
                        }
                        break;
                    case WEIGHT:
                        if (i == 0) {
                            outputConversionType = KILOGRAM;
                        } else if (i == 1) {
                            outputConversionType = LB;
                        } else if (i == 2) {
                            outputConversionType = GRAM;
                        } else if (i == 3) {
                            outputConversionType = OZ;
                        }
                        break;
                    case QUANTITY:
                        if (i == 0) {
                            outputConversionType = LITRE;
                        } else if (i == 1) {
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

    /**
     * Adds listener for spinner related to input value unit
     */
    private void updateSpinnerInputUnitListener() {
        Spinner inputSpinnerUnit = findViewById(R.id.spinner2);
        inputSpinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (selectedConversionType) {
                    case DISTANCE:
                        if (i == 0) {
                            inputConversionType = INCH;
                        } else if (i == 1) {
                            inputConversionType = CENTIMETER;
                        } else if (i == 2) {
                            inputConversionType = KILOMETER;
                        } else if (i == 3) {
                            inputConversionType = MILES;
                        }
                        break;
                    case TEMPERATURE:
                        if (i == 0) {
                            inputConversionType = CENTIGRADE;
                        } else if (i == 1) {
                            inputConversionType = KELVIN;
                        } else if (i == 2) {
                            inputConversionType = FAHRENHEIT;
                        }
                        break;
                    case WEIGHT:
                        if (i == 0) {
                            inputConversionType = KILOGRAM;
                        } else if (i == 1) {
                            inputConversionType = LB;
                        } else if (i == 2) {
                            inputConversionType = GRAM;
                        } else if (i == 3) {
                            inputConversionType = OZ;
                        }
                        break;
                    case QUANTITY:
                        if (i == 0) {
                            inputConversionType = LITRE;
                        } else if (i == 1) {
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

    /**
     * converts the values based on different quantity units such as LITER, CUPS
     */
    private void quantityConverter() {
        Float valueToBeConverted = Float.parseFloat(editText.getText().toString());
        String valueAfterConversion = "";
        if (inputConversionType.equals(LITRE)) {
            switch (outputConversionType) {
                case LITRE:
                    valueAfterConversion = String.valueOf(valueToBeConverted);
                    break;
                case CUPS:
                    valueAfterConversion = String.valueOf(valueToBeConverted * 4.17f);
                    break;
            }
        } else if (inputConversionType.equals(CUPS)) {
            switch (outputConversionType) {
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

    /**
     * converts the values based on different weight measurement units such as KILOGRAM, GRAM, OUNCE, POUNDS
     */
    private void weightConverter() {
        Float valueToBeConverted = Float.parseFloat(editText.getText().toString());
        String valueAfterConversion = "";
        switch (inputConversionType) {
            case KILOGRAM:
                switch (outputConversionType) {
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
                break;
            case LB:
                switch (outputConversionType) {
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
                break;
            case GRAM:
                switch (outputConversionType) {
                    case KILOGRAM:
                        valueAfterConversion = String.valueOf(valueToBeConverted / 1000f);
                        break;
                    case LB:
                        valueAfterConversion = String.valueOf(valueToBeConverted / 453.6f);
                        break;
                    case GRAM:
                        valueAfterConversion = String.valueOf(valueToBeConverted);
                        break;
                    case OZ:
                        valueAfterConversion = String.valueOf(valueToBeConverted / 28.35f);
                        break;
                }
                break;
            case OZ:
                switch (outputConversionType) {
                    case KILOGRAM:
                        valueAfterConversion = String.valueOf(valueToBeConverted / 35.274f);
                        break;
                    case LB:
                        valueAfterConversion = String.valueOf(valueToBeConverted / 16f);
                        break;
                    case GRAM:
                        valueAfterConversion = String.valueOf(valueToBeConverted * 28.35f);
                        break;
                    case OZ:
                        valueAfterConversion = String.valueOf(valueToBeConverted);
                        break;
                }
                break;
        }
        textView.setText(valueAfterConversion);
    }

    /**
     * converts the values based on different temperature measurement units such as CELSIUS, FAHRENHEIT, KELVIN
     */
    private void temperatureConverter() {
        Float valueToBeConverted = Float.parseFloat(editText.getText().toString());
        String valueAfterConversion = "";
        switch (inputConversionType) {
            case CENTIGRADE:
                switch (outputConversionType) {
                    case CENTIGRADE:
                        valueAfterConversion = String.valueOf(valueToBeConverted);
                        break;
                    case KELVIN:
                        valueAfterConversion = String.valueOf(valueToBeConverted + 273.15f);
                        break;
                    case FAHRENHEIT:
                        valueAfterConversion = String.valueOf((valueToBeConverted * 9f / 5) + 32);
                        break;
                }
                break;
            case KELVIN:
                switch (outputConversionType) {
                    case CENTIGRADE:
                        valueAfterConversion = String.valueOf(valueToBeConverted - 273.15f);
                        break;
                    case KELVIN:
                        valueAfterConversion = String.valueOf(valueToBeConverted);
                        break;
                    case FAHRENHEIT:
                        valueAfterConversion = String.valueOf(((valueToBeConverted - 273.15f) * 9f / 5) + 32);
                        break;
                }
                break;
            case FAHRENHEIT:
                switch (outputConversionType) {
                    case CENTIGRADE:
                        valueAfterConversion = String.valueOf((valueToBeConverted - 32) * 5f / 9);
                        break;
                    case KELVIN:
                        valueAfterConversion = String.valueOf(((valueToBeConverted - 32) * 5f / 9) + 273.15f);
                        break;
                    case FAHRENHEIT:
                        valueAfterConversion = String.valueOf(valueToBeConverted);
                        break;
                }
                break;
        }
        textView.setText(valueAfterConversion);
    }

    /**
     * converts the values based on different distance measurement units such as KILOMETER, INCH, CENTIMETER, MILES
     */
    private void distanceConverter() {
        Float valueToBeConverted = Float.parseFloat(editText.getText().toString());
        String valueAfterConversion = "";
        switch (inputConversionType) {
            case INCH:
                switch (outputConversionType) {
                    case INCH:
                        valueAfterConversion = String.valueOf(valueToBeConverted);
                        break;
                    case CENTIMETER:
                        valueAfterConversion = String.valueOf(valueToBeConverted * 2.54f);
                        break;
                    case KILOMETER:
                        valueAfterConversion = String.valueOf(valueToBeConverted / 39370f);
                        break;
                    case MILES:
                        valueAfterConversion = String.valueOf(valueToBeConverted / 63360f);
                        break;
                }
                break;
            case CENTIMETER:
                switch (outputConversionType) {
                    case INCH:
                        valueAfterConversion = String.valueOf(valueToBeConverted / 2.54f);
                        break;
                    case CENTIMETER:
                        valueAfterConversion = String.valueOf(valueToBeConverted);
                        break;
                    case KILOMETER:
                        valueAfterConversion = String.valueOf(valueToBeConverted / 100000f);
                        break;
                    case MILES:
                        valueAfterConversion = String.valueOf(valueToBeConverted / 160900f);
                        break;
                }
                break;
            case KILOMETER:
                switch (outputConversionType) {
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
                        valueAfterConversion = String.valueOf(valueToBeConverted / 1.609f);
                        break;
                }
                break;
            case MILES:
                switch (outputConversionType) {
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
                break;
        }
        textView.setText(valueAfterConversion);
    }


    /**
     * update the spinner values based on the conversion type selected
     *
     * @param conversionType @NonNull
     */
    private void updateSpinners(String conversionType) {
        Spinner inputSpinnerUnit = findViewById(R.id.spinner2);
        Spinner outputSpinnerUnit = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = null;
        switch (conversionType) {
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
            inputSpinnerUnit.setAdapter(adapter);
            outputSpinnerUnit.setAdapter(adapter);
        }
    }


    /**
     * convert the input to the output based on the input and output unit of measurement when user clicks convert button
     *
     * @param view
     */
    public void convertData(View view) {
        if (editText.getText() == null || isEmpty(editText.getText().toString())) {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter Valid Value", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        switch (selectedConversionType) {
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