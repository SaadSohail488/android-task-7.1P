package com.example.task71p;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Show extends AppCompatActivity implements OnClickListener {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private DatabaseClass databaseClass;
    private TextView noData;

    @Override
    protected void onResume() {
        super.onResume();
        // Retrieve data from the database
        final ArrayList<DATA> list = databaseClass.getData();

        // Check if the data list is empty
        if (list.isEmpty()) {
            noData.setVisibility(View.VISIBLE); // Show "no data" message
        } else {
            noData.setVisibility(View.GONE); // Hide "no data" message
        }

        // Submit the data list to the adapter for display
        adapter.submit(list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // Initialize the database
        databaseClass = new DatabaseClass(this);

        // Initialize views
        recyclerView = findViewById(R.id.lostAndFound);
        noData = findViewById(R.id.noData);

        // Initialize the adapter and set it to the RecyclerView
        adapter = new Adapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClickListener(DATA DATA) {
        // Handle item click event
        Intent intent = new Intent(Show.this, Remove.class);
        intent.putExtra("id", DATA.getId());
        startActivity(intent);
    }
}
