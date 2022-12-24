package de.androidcrypto.simplerecyclerviewswipejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

        /*
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

         */

        /*
    Note: This helper class is designed for left swipe. You can change swipe direction in SwipeHelper's constructor, and making changes based on dX in onChildDraw method accordingly.

If you want to show image in the button, just make the use of imageResId in UnderlayButton, and re-implement the onDraw method.

There is a known bug, when you swipe an item diagonally from one item to another, the first touched item will flash a little. This could be addressed by decreasing the value of getSwipeVelocityThreshold, but this makes harder for user to swipe the item. You can also adjust the swiping feeling by changing two other values in getSwipeThreshold and getSwipeEscapeVelocity. Check into the ItemTouchHelper source code, the comments are very helpful.

I believe there is a lot place for optimization. This solution just gives an idea if you want to stick with ItemTouchHelper. Please let me know if you have problem using it. Below is a screenshot.
     */

        /*
        // https://stackoverflow.com/a/45062745/8166854 by Wenxi Zeng
        // the SwipeHelper uses a Text for onClick
        SwipeHelper swipeHelper = new SwipeHelper(this, recyclerView) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        MainActivity.this, // new
                        "Delete",
                        0,
                        Color.parseColor("#FF3C30"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: onDelete
                                System.out.println("*** onDelete");
                            }
                        }
                ));

                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        MainActivity.this, // new
                        "Transfer",
                        0,
                        Color.parseColor("#FF9502"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: OnTransfer
                                System.out.println("*** onTransfer");
                            }
                        }
                ));
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        MainActivity.this, // new
                        "Unshare",
                        0,
                        Color.parseColor("#C7C7CB"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: OnUnshare
                                System.out.println("*** onUnshare");
                            }
                        }
                ));
            }
        };

         */

        // the SwipeHelperButtons uses an image for onClick
        // https://stackoverflow.com/a/58763541/8166854 by George
        SwipeHelperButtons swipeHelperButtons = new SwipeHelperButtons(this, recyclerView) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {

                // delete button
                int idDrawableDelete = R.drawable.ic_baseline_delete_outline_24;
                Bitmap bitmapDelete = getBitmapFromVectorDrawable(getApplicationContext(), idDrawableDelete);
                underlayButtons.add(new SwipeHelperButtons.UnderlayButton(
                        MainActivity.this, // new
                        "Delete",
                        bitmapDelete,
                        Color.parseColor("#FF3C30"),
                        new SwipeHelperButtons.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: onDelete
                                System.out.println("*** onDelete");
                            }
                        }
                ));

                // edit button
                int idDrawableEdit = R.drawable.ic_outline_edit_24;
                Bitmap bitmapEdit = getBitmapFromVectorDrawable(getApplicationContext(), idDrawableEdit);
                underlayButtons.add(new SwipeHelperButtons.UnderlayButton(
                        MainActivity.this, // new
                        "Edit",
                        bitmapEdit,
                        Color.parseColor("#FF9502"),
                        new SwipeHelperButtons.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: OnTransfer
                                System.out.println("*** onEdit");
                            }
                        }
                ));

                // view button
                int idDrawableView = R.drawable.ic_outline_visibility_24;
                Bitmap bitmapView = getBitmapFromVectorDrawable(getApplicationContext(), idDrawableView);
                underlayButtons.add(new SwipeHelperButtons.UnderlayButton(
                        MainActivity.this, // new
                        "View",
                        bitmapView,
                        // holo green FF99CC00
                        // light green FFBFFF00
                        Color.parseColor("#FF99CC00"),
                        new SwipeHelperButtons.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: OnUnshare
                                System.out.println("*** onView");
                            }
                        }
                ));
            }
        };

        // solution https://stackoverflow.com/a/45062745/8166854
        //ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeHelper);
        // solution https://stackoverflow.com/a/58763541/8166854
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeHelperButtons);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        // add a line between entries
        DividerItemDecoration decor = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decor);

        // adding our array list to our recycler view adapter class.
        recyclerViewAdapter = new RecyclerViewAdapter(entryList, this);

        // setting adapter to our recycler view.
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    // https://stackoverflow.com/a/38244327/8166854
    // from Alexey
    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
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

    /*
    Note: This helper class is designed for left swipe. You can change swipe direction in SwipeHelper's constructor, and making changes based on dX in onChildDraw method accordingly.

If you want to show image in the button, just make the use of imageResId in UnderlayButton, and re-implement the onDraw method.

There is a known bug, when you swipe an item diagonally from one item to another, the first touched item will flash a little. This could be addressed by decreasing the value of getSwipeVelocityThreshold, but this makes harder for user to swipe the item. You can also adjust the swiping feeling by changing two other values in getSwipeThreshold and getSwipeEscapeVelocity. Check into the ItemTouchHelper source code, the comments are very helpful.

I believe there is a lot place for optimization. This solution just gives an idea if you want to stick with ItemTouchHelper. Please let me know if you have problem using it. Below is a screenshot.
     */
/*
    // https://stackoverflow.com/a/45062745/8166854
    SwipeHelper swipeHelper = new SwipeHelper(this, recyclerView) {
        @Override
        public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
            underlayButtons.add(new SwipeHelper.UnderlayButton(
                    "Delete",
                    0,
                    Color.parseColor("#FF3C30"),
                    new SwipeHelper.UnderlayButtonClickListener() {
                        @Override
                        public void onClick(int pos) {
                            // TODO: onDelete
                            System.out.println("*** onDelete");
                        }
                    }
            ));

            underlayButtons.add(new SwipeHelper.UnderlayButton(
                    "Transfer",
                    0,
                    Color.parseColor("#FF9502"),
                    new SwipeHelper.UnderlayButtonClickListener() {
                        @Override
                        public void onClick(int pos) {
                            // TODO: OnTransfer
                            System.out.println("*** onTransfer");
                        }
                    }
            ));
            underlayButtons.add(new SwipeHelper.UnderlayButton(
                    "Unshare",
                    0,
                    Color.parseColor("#C7C7CB"),
                    new SwipeHelper.UnderlayButtonClickListener() {
                        @Override
                        public void onClick(int pos) {
                            // TODO: OnUnshare
                            System.out.println("*** onUnshare");
                        }
                    }
            ));
        }
    };
    */


}