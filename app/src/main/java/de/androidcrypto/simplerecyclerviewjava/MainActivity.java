package de.androidcrypto.simplerecyclerviewjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "SimpleRecyclerViewJava";

    List<EntryModel> entryList;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        populateEntryList();

        //recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        //linearLayoutManager.setReverseLayout(false);
        //linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        // adding our array list to our recycler view adapter class.
        recyclerViewAdapter = new RecyclerViewAdapter(entryList, this);

        // setting adapter to our recycler view.
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void populateEntryList() {
        entryList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            String title = "Title " + String.valueOf(i + 1);
            String url = "https://pastebin.com/" + String.valueOf(i + 1000);
            long timestamp = new Date().getTime();
            EntryModel entryModel = new EntryModel(title, url, timestamp);
            entryList.add(entryModel);
        }
        Log.d(TAG, "populateEntryList with " + entryList.size() + " elements");
    }
}