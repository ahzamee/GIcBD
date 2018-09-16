package com.zamee.gicbd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class Dashboard extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    DatabaseAdapter databaseAdapter;
    private List<Datamodel> datamodels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        databaseAdapter = new DatabaseAdapter(this);

        recyclerView = (RecyclerView)findViewById(R.id.users);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        datamodels = databaseAdapter.getData();
        dataAdapter = new DataAdapter(datamodels);
        recyclerView.setAdapter(dataAdapter);

    }
}
