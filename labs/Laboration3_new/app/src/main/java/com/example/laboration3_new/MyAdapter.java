package com.example.laboration3_new;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/**
 * Adapter for displaying the listpopupwindow. GetView and Row will work hand in hand to
 * draw the whole listpopupwindow.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> data; // raw data
    public MyAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * We will create a new Row for each position and return that view, onMeasure/onDraw in Row
     * will then draw that row.
     *
     * @param position The position of the item within the adapter's data set of the item whose view
     *        we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *        is non-null and of an appropriate type before using. If it is not possible to convert
     *        this view to display the correct data, this method can create a new view.
     *        Heterogeneous lists can specify their number of view types, so that this View is
     *        always of the right type (see {@link #getViewTypeCount()} and
     *        {@link #getItemViewType(int)}).
     * @param parent The parent that this view will eventually be attached to
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = new Row(context, data.get(position));
        return v;
    }

    /**
     * Setting raw data.
     * @param data
     */
    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    /**
     * Empty out the dataset.
     */
    public void clearData(){
        this.data.clear();
    }

    /**
     * Check which row is the widest.
     * @return maxWidth
     */
    public int getWidestTextWidth(){
        ArrayList<Row> rows = new ArrayList<>();
        for(String suggestion : data){
            Row row = new Row(context, suggestion);
            rows.add(row);
        }
        int maxWidth = 0;
        for (Row row : rows) {
            float textWidth = row.getTextWidth();
            maxWidth = (int) Math.max(maxWidth, textWidth);
        }
        return maxWidth;
    }
}