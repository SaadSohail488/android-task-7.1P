package com.example.task71p;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Remove extends AppCompatActivity {

    private TextView Head;
    private TextView Detail;
    private DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        // Initialize the database
        databaseClass = new DatabaseClass(this);

        // Initialize the TextViews
        Head = findViewById(R.id.head);
        Detail = findViewById(R.id.detail);

        // Retrieve the ID of the data to be removed from the intent
        int id = getIntent().getIntExtra("id", -1);

        // Check if a valid ID is received
        if (id != -1) {
            // Retrieve the data from the database using the ID
            DATA DATA = databaseClass.getDataById(id);

            // Check if the data exists
            if (DATA != null) {
                // Set the text for the header TextView
                Head.setText(DATA.getIsLostOrFound() + ": " + DATA.getName());

                // Build the detail text with information from the DATA object
                StringBuilder detailBuilder = new StringBuilder();
                detailBuilder.append(DATA.getDate()).append("\n");
                detailBuilder.append(DATA.getLocation()).append("\n");
                detailBuilder.append(DATA.getPhone()).append("\n");
                detailBuilder.append(DATA.getDescription());

                // Set the detail text for the TextView
                Detail.setText(detailBuilder.toString());
            }
        }

        // Button for removing the data
        Button removeButton = findViewById(R.id.remove);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if a valid ID is received
                if (id != -1) {
                    // Delete the data from the database using the ID
                    databaseClass.deleteDataById(id);
                    finish(); // Finish the activity and return to the previous screen
                }
            }
        });
    }
}
