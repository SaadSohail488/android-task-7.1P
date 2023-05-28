package com.example.task71p;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button for creating a new advert
        Button createNewAdvertButton = findViewById(R.id.create);
        createNewAdvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the CreateNew activity when the button is clicked
                startActivity(new Intent(MainActivity.this, CreateNew.class));
            }
        });

        // Button for showing all items
        Button showAllItemsButton = findViewById(R.id.show);
        showAllItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Show activity when the button is clicked
                startActivity(new Intent(MainActivity.this, Show.class));
            }
        });
    }
}
