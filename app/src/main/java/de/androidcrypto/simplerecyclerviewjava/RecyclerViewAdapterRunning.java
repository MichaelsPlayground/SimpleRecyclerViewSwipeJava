package de.androidcrypto.simplerecyclerviewjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

// extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
public class RecyclerViewAdapterRunning extends RecyclerView.Adapter<RecyclerViewAdapterRunning.ViewHolder> {

    private List<EntryModel> entryList;
    Context context;

    public RecyclerViewAdapterRunning(List<EntryModel> entryList, Context context) {
        this.entryList = entryList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterRunning.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // step 2 bind the single_entry.xml with our recyclerView
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_entry, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterRunning.ViewHolder holder, int position) {
        // step 3 bind the data of each element to a single_entry
        // setting data to our text views from our modal class.
        EntryModel entryModel = entryList.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        String entryTime  = dateFormat.format(entryModel.getTimestamp());
        holder.entryTitle.setText(entryModel.getTitle());
        holder.entryUrl.setText(entryModel.getUrl());
        holder.entryTimestamp.setText(entryTime);
    }

    @Override
    public int getItemCount() {
        // step 4 return the size of our entryList
        return entryList.size();
    }

    // add the inner class ViewHolder manually
    class ViewHolder extends RecyclerView.ViewHolder {

        // step 1 creating variables for our text views.
        private final TextView entryTitle;
        private final TextView entryUrl;
        private final TextView entryTimestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            entryTitle = itemView.findViewById(R.id.tvEntryTitle);
            entryUrl = itemView.findViewById(R.id.tvEntryUrl);
            entryTimestamp = itemView.findViewById(R.id.tvEntryTimestamp);
        }
    }

}