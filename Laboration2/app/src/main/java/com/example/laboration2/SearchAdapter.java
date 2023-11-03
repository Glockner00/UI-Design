package com.example.laboration2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<String> searchResults;

    public SearchAdapter(List<String> searchResults) {
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        String result = searchResults.get(position);
        holder.bind(result);
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public void updateData(List<String> newSearchResults) {
        searchResults.clear();
        searchResults.addAll(newSearchResults);
        notifyDataSetChanged();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private TextView resultText;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            resultText = itemView.findViewById(R.id.item);
        }

        public void bind(String result) {
            resultText.setText(result);
        }
    }
}
