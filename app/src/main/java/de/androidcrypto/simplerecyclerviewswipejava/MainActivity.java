package de.androidcrypto.simplerecyclerviewswipejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
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

    SwipeController swipeController = null;

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

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                recyclerViewAdapter.entryList.remove(position);
                recyclerViewAdapter.notifyItemRemoved(position);
                recyclerViewAdapter.notifyItemRangeChanged(position, recyclerViewAdapter.getItemCount());
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });

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