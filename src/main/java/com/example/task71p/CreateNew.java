package com.example.task71p;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
//This activity is responsible for capturing user input for a new data entry and saving it in the database using the DatabaseClass.
public class CreateNew extends AppCompatActivity {

    // UI components
    private EditText dateEditText;
    private EditText locationEditText;
    private ImageView datePicker;
    private DatabaseClass database;
    private RadioButton radioButtonLost;
    private RadioButton radioButtonFound;
    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText descriptionEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Initialize the database
        database = new DatabaseClass(this);

        // Initialize UI components
        radioButtonLost = findViewById(R.id.lostRadioButton);
        radioButtonFound = findViewById(R.id.foundRadioButton);
        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        dateEditText = findViewById(R.id.dateEditText);
        locationEditText = findViewById(R.id.locationEditText);
        datePicker = findViewById(R.id.datePickerImageView);

        // Set a click listener for the date picker
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateNew.this);
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Set the selected date in the date EditText
                        dateEditText.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                });
                datePickerDialog.show();
            }
        });

        // Set a click listener for the save button
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new DATA object with the entered data
                DATA data = new DATA(
                        radioButtonLost.isChecked() ? "Lost" : "Found",
                        nameEditText.getText().toString(),
                        phoneEditText.getText().toString(),
                        descriptionEditText.getText().toString(),
                        dateEditText.getText().toString(),
                        locationEditText.getText().toString(),
                        0
                );

                // Insert the data into the database
                database.insertData(data);

                // Finish the activity and return to the previous screen
                finish();
            }
        });
    }
}
