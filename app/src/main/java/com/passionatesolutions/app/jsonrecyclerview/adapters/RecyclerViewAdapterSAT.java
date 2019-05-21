package com.passionatesolutions.app.jsonrecyclerview.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.passionatesolutions.app.jsonrecyclerview.R;
import com.passionatesolutions.app.jsonrecyclerview.model.SATscore;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterSAT extends RecyclerView.Adapter<RecyclerViewAdapterSAT.MyViewHolder> implements Filterable {

    private Context mContext;
    private List<SATscore> mDataSAT;
    // 2nd copy of list to be created for Filtering search results
    private List<SATscore> mDataSATSearched;

    public RecyclerViewAdapterSAT(Context mContext, List<SATscore> mDataSAT) {
        this.mContext = mContext;
        this.mDataSAT = mDataSAT;
        // 2nd copy of list to be created for Filtering search results
        mDataSATSearched = new ArrayList<>(mDataSAT);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.sat_row_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tv_dbn.setText(mDataSAT.get(i).getDbn());
        myViewHolder.tv_reading.setText(mDataSAT.get(i).getSatCriticalReadingAvgScore());
        myViewHolder.tv_math.setText(mDataSAT.get(i).getSatMathAvgScore());
        myViewHolder.tv_writing.setText(mDataSAT.get(i).getSatWritingAvgScore());
        myViewHolder.tv_name.setText(mDataSAT.get(i).getSchoolName());

    }

    @Override
    public int getItemCount() {
        return mDataSAT.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_dbn;
        TextView tv_reading;
        TextView tv_math;
        TextView tv_writing;
        TextView tv_name;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_dbn = itemView.findViewById(R.id.SATdbnTV);
            tv_reading = itemView.findViewById(R.id.readingTV);
            tv_math = itemView.findViewById(R.id.mathTV);
            tv_writing = itemView.findViewById(R.id.writingTV);
            tv_name = itemView.findViewById(R.id.nameTV);

        }
    }

    // Method to allow the list to be searchable

    @Override
    public Filter getFilter() {
        return mDataSATSearchFilter;
    }

    private Filter mDataSATSearchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<SATscore> filteredList = new ArrayList<>();

            // if the search bar is empty, show original list
            if (charSequence == null || charSequence.length() == 0) {
                filteredList = mDataSAT;
            } else {
                String filterPattern = charSequence.toString().toUpperCase().trim();

                // check to see if input in search matches the dbn id given to display it
                for (SATscore item : mDataSATSearched) {
                    if (item.getDbn().toUpperCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            // clears data from original list to only display the item we searched for
            mDataSAT.clear();
            mDataSAT.addAll((List) filterResults.values);
            notifyDataSetChanged();

        }
    };


}
