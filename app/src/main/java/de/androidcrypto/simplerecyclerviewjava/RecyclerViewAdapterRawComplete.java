package de.androidcrypto.simplerecyclerviewjava;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
public class RecyclerViewAdapterRawComplete extends RecyclerView.Adapter<RecyclerViewAdapterRawComplete.ViewHolder> {

    private List<EntryModel> entryList;
    Context context;

    public RecyclerViewAdapterRawComplete(List<EntryModel> entryList, Context context) {
        this.entryList = entryList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterRawComplete.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterRawComplete.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    // add the inner class ViewHolder manually
    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}