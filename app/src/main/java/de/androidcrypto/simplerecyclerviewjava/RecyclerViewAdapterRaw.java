package de.androidcrypto.simplerecyclerviewjava;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
public class RecyclerViewAdapterRaw extends RecyclerView.Adapter<RecyclerViewAdapterRaw.ViewHolder> {
    @NonNull
    @Override
    public RecyclerViewAdapterRaw.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterRaw.ViewHolder holder, int position) {

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