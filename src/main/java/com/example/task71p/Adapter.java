package com.example.task71p;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.LostAndFoundHolder> {

    private ArrayList<DATA> list = new ArrayList<>();
    private OnClickListener onClickListener;

    public Adapter(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    // Method to submit a new list of data to the adapter
    public void submit(ArrayList<DATA> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    // ViewHolder class to hold and initialize UI components of each item in the RecyclerView
    public class LostAndFoundHolder extends RecyclerView.ViewHolder {
        private TextView heading;
        private TextView detail;

        public LostAndFoundHolder(View view) {
            super(view);
            heading = view.findViewById(R.id.heading);
            detail = view.findViewById(R.id.detail);
        }

        // Method to bind the data to the ViewHolder
        public void bind(final DATA data, final OnClickListener onClickListener) {
            heading.setText(data.getIsLostOrFound() + ": " + data.getName());
            detail.setText(new StringBuilder()
                    .append(data.getDate())
                    .append("\n")
                    .append(data.getLocation())
                    .append("\n")
                    .append(data.getPhone())
                    .append("\n")
                    .append(data.getDescription())
                    .toString());

            // Set a click listener for the item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onItemClickListener(data);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(LostAndFoundHolder holder, int position) {
        // Bind the data to the ViewHolder at the given position
        holder.bind(list.get(position), onClickListener);
    }

    @Override
    public LostAndFoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for the ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view, parent, false);
        return new LostAndFoundHolder(view);
    }

    @Override
    public int getItemCount() {
        // Return the size of the list
        return list.size();
    }
}

// Interface to handle item click events in the RecyclerView
interface OnClickListener {
    void onItemClickListener(DATA data);
}
