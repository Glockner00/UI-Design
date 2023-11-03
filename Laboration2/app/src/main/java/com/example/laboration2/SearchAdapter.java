package com.example.laboration2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * A modified RecyclerView-adapter. Handles showing the search suggestions in
 * MainActivitiy's RecyclerView.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<String> searchResults;
    public SearchAdapter(List<String> searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * Creates a new view for every search suggestion.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     * @return SearchViewHolder
     */
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new SearchViewHolder(view);
    }

    /**
     * Fills every view with the search suggestions.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        String result = searchResults.get(position);
        holder.bind(result);
    }

    /**
     * Returns the number of search suggestions.
     * @return int size()
     */
    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    /**
     * A helper method for updating the adapter with new search suggestions.
     * Notifies the changes to the RecyclerView.
     * @param newSearchResults
     */
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
