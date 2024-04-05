package com.example.a21p;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;

        import androidx.activity.EdgeToEdge;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.graphics.Insets;
        import androidx.core.view.ViewCompat;
        import androidx.core.view.WindowInsetsCompat;

        import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Spinner sp1, sp2, sp3;
    EditText ed1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.value_input);
        sp1 = findViewById(R.id.source_type_spinner);
        sp2 = findViewById(R.id.source_unit_spinner);
        sp3 = findViewById(R.id.destination_unit_spinner);
        b1 = findViewById(R.id.convert_button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //validation
                String input = ed1.getText().toString();
                if(input.isEmpty()) {
                    ed1.setError("Input cannot be empty");
                    return;
                }
                try {
                    String sourceType = sp1.getSelectedItem().toString();
                    String sourceUnit = sp2.getSelectedItem().toString();
                    String destinationUnit = sp3.getSelectedItem().toString();
                    double inputValue = Double.parseDouble(ed1.getText().toString());

                    double result = convertUnits(sourceType, sourceUnit, destinationUnit, inputValue);

                    // Handling same unit conversion
                    if(sp2.getSelectedItem().toString().equals(sp3.getSelectedItem().toString())){
                        result = inputValue;
                    }

                    // display the result
                    TextView resultView = findViewById(R.id.result_output);
                    resultView.setText(String.format(Locale.US, "%.2f", result));
                } catch (NumberFormatException e) {
                    // Handle the case where ed1 does not contain a valid double
                    ed1.setError("Please enter a valid number");
                }
            }
        });

        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, R.array.conversion_types, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(typeAdapter);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // Length
                        setSpinnerAdapter(sp2, R.array.length_units);
                        setSpinnerAdapter(sp3, R.array.length_units);
                        break;
                    case 1: // Weight
                        setSpinnerAdapter(sp2, R.array.weight_units);
                        setSpinnerAdapter(sp3, R.array.weight_units);
                        break;
                    case 2: // Temperature
                        setSpinnerAdapter(sp2, R.array.temperature_units);
                        setSpinnerAdapter(sp3, R.array.temperature_units);
                        break;
                    // Add more cases as needed
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where nothing is selected if necessary
            }
        });

    }

    private void setSpinnerAdapter(Spinner spinner, int arrayResourceId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arrayResourceId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private double convertUnits(String sourceType, String sourceUnit, String destinationUnit, double value) {
        if (sourceType.equals("Length")) {
            // Example conversion from meters
            if (sourceUnit.equals("Meters")) {
                if (destinationUnit.equals("Inches")) {
                    return value * 39.3701;
                } else if (destinationUnit.equals("Feet")) {
                    return value * 3.28084;
                } else if (destinationUnit.equals("Yards")) {
                    return value * 1.09361;
                } else if (destinationUnit.equals("Miles")) {
                    return value * 0.000621371;
                } else if (destinationUnit.equals("Centimeters")) {
                    return value * 100;
                } else if (destinationUnit.equals("Kilometers")) {
                    return value * 0.001;
                }
            }
            // conversions for length
            if (sourceUnit.equals("Inches")) {
                if (destinationUnit.equals("Meters")) {
                    return value * 0.0254;
                } else if (destinationUnit.equals("Feet")) {
                    return value * 0.0833333;
                } else if (destinationUnit.equals("Yards")) {
                    return value * 0.0277778;
                } else if (destinationUnit.equals("Miles")) {
                    return value * 1.5783e-5;
                } else if (destinationUnit.equals("Centimeters")) {
                    return value * 2.54;
                } else if (destinationUnit.equals("Kilometers")) {
                    return value * 2.54e-5;
                }
            }
            if (sourceUnit.equals("Yards")) {
                if (destinationUnit.equals("Meters")) {
                    return value * 0.9144;
                } else if (destinationUnit.equals("Inches")) {
                    return value * 36;
                } else if (destinationUnit.equals("Feet")) {
                    return value * 3;
                } else if (destinationUnit.equals("Miles")) {
                    return value * 0.000568182;
                } else if (destinationUnit.equals("Centimeters")) {
                    return value * 91.44;
                } else if (destinationUnit.equals("Kilometers")) {
                    return value * 0.0009144;
                }
            }
            if (sourceUnit.equals("Feet")) {
                if (destinationUnit.equals("Meters")) {
                    return value * 0.3048;
                } else if (destinationUnit.equals("Inches")) {
                    return value * 12;
                } else if (destinationUnit.equals("Yards")) {
                    return value * 0.333333;
                } else if (destinationUnit.equals("Miles")) {
                    return value * 0.000189394;
                } else if (destinationUnit.equals("Centimeters")) {
                    return value * 30.48;
                } else if (destinationUnit.equals("Kilometers")) {
                    return value * 0.0003048;
                }
            }
            if (sourceUnit.equals("Miles")) {
                if (destinationUnit.equals("Meters")) {
                    return value * 1609.34;
                } else if (destinationUnit.equals("Inches")) {
                    return value * 63360;
                } else if (destinationUnit.equals("Yards")) {
                    return value * 1760;
                } else if (destinationUnit.equals("Feet")) {
                    return value * 5280;
                } else if (destinationUnit.equals("Centimeters")) {
                    return value * 160934;
                } else if (destinationUnit.equals("Kilometers")) {
                    return value * 1.60934;
                }
            }
            if (sourceUnit.equals("Centimeters")) {
                if (destinationUnit.equals("Meters")) {
                    return value * 0.01;
                } else if (destinationUnit.equals("Inches")) {
                    return value * 0.393701;
                } else if (destinationUnit.equals("Yards")) {
                    return value * 0.0109361;
                } else if (destinationUnit.equals("Feet")) {
                    return value * 0.0328084;
                } else if (destinationUnit.equals("Miles")) {
                    return value * 6.21371e-6;
                } else if (destinationUnit.equals("Kilometers")) {
                    return value * 1e-5;
                }
            }
            if (sourceUnit.equals("Kilometers")) {
                if (destinationUnit.equals("Meters")) {
                    return value * 1000;
                } else if (destinationUnit.equals("Inches")) {
                    return value * 39370.1;
                } else if (destinationUnit.equals("Yards")) {
                    return value * 1093.61;
                } else if (destinationUnit.equals("Feet")) {
                    return value * 3280.84;
                } else if (destinationUnit.equals("Miles")) {
                    return value * 0.621371;
                } else if (destinationUnit.equals("Centimeters")) {
                    return value * 100000;
                }
            }
        }
        // Conversions for Weight
        if (sourceType.equals("Weight")) {
            if (sourceUnit.equals("Pounds")) {
                if (destinationUnit.equals("Ounces")) {
                    return value * 16;
                } else if (destinationUnit.equals("Tons")) {
                    return value * 0.0005;
                } else if (destinationUnit.equals("Grams")) {
                    return value * 453.592;
                } else if (destinationUnit.equals("Kilograms")) {
                    return value * 0.453592;
                }
            }
            if (sourceUnit.equals("Ounces")) {
                if (destinationUnit.equals("Pounds")) {
                    return value * 0.0625;
                } else if (destinationUnit.equals("Tons")) {
                    return value * 3.125e-5;
                } else if (destinationUnit.equals("Grams")) {
                    return value * 28.3495;
                } else if (destinationUnit.equals("Kilograms")) {
                    return value * 0.0283495;
                }
            }
            if (sourceUnit.equals("Tons")) {
                if (destinationUnit.equals("Pounds")) {
                    return value * 2000;
                } else if (destinationUnit.equals("Ounces")) {
                    return value * 32000;
                } else if (destinationUnit.equals("Grams")) {
                    return value * 907185;
                } else if (destinationUnit.equals("Kilograms")) {
                    return value * 907.185;
                }
            }
            if (sourceUnit.equals("Grams")) {
                if (destinationUnit.equals("Pounds")) {
                    return value * 0.00220462;
                } else if (destinationUnit.equals("Ounces")) {
                    return value * 0.035274;
                } else if (destinationUnit.equals("Tons")) {
                    return value * 1.10231e-6;
                } else if (destinationUnit.equals("Kilograms")) {
                    return value * 907.185;
                }
            }
            if (sourceUnit.equals("Kilograms")) {
                if (destinationUnit.equals("Pounds")) {
                    return value * 2.20462;
                } else if (destinationUnit.equals("Ounces")) {
                    return value * 35.274;
                } else if (destinationUnit.equals("Tons")) {
                    return value * 0.00110231;
                } else if (destinationUnit.equals("Grams")) {
                    return value * 1000;
                }
            }
        }
        // Conversions for Temperature
        if (sourceType.equals("Temperature")) {
            if (sourceUnit.equals("Celsius")) {
                if (destinationUnit.equals("Fahrenheit")) {
                    return (value * 1.8) + 32;
                } else if (destinationUnit.equals("Kelvin")) {
                    return value + 273.15;
                }
            }
            if (sourceUnit.equals("Fahrenheit")) {
                if (destinationUnit.equals("Celsius")) {
                    return (value - 32) / 1.8;
                } else if (destinationUnit.equals("Kelvin")) {
                    return ((value - 32) * 5 / 9) + 273.15;
                }
            }
            if (sourceUnit.equals("Kelvin")) {
                if (destinationUnit.equals("Celsius")) {
                    return value - 273.15;
                } else if (destinationUnit.equals("Fahrenheit")) {
                    return ((value - 273.15) * 9 / 5) + 32;
                }
            }
        }
            return value; // Return the value unchanged if no conversion criteria are met
        }

    }