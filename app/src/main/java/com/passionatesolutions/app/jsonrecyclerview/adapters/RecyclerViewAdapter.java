package com.passionatesolutions.app.jsonrecyclerview.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.passionatesolutions.app.jsonrecyclerview.activities.SATActivity;
import com.passionatesolutions.app.jsonrecyclerview.model.School;

import java.util.List;
import com.passionatesolutions.app.jsonrecyclerview.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<School> mDataSchool;

    public RecyclerViewAdapter(Context mContext, List<School> mDataSchool) {
        this.mContext = mContext;
        this.mDataSchool = mDataSchool;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.school_row_item, viewGroup, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        // method to send "dbn" data of school clicked on to the SATActivity.java SearchView

        viewHolder.layout_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, SATActivity.class);
                i.putExtra("dbn", mDataSchool.get(viewHolder.getAdapterPosition()).getDbn());
                mContext.startActivity(i);

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tv_dbn.setText(mDataSchool.get(i).getDbn());
        myViewHolder.tv_name.setText(mDataSchool.get(i).getName());
        myViewHolder.tv_location.setText(mDataSchool.get(i).getCity());

    }

    @Override
    public int getItemCount() {
        return mDataSchool.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_dbn;
        TextView tv_name;
        TextView tv_location;
        LinearLayout layout_school;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_dbn = itemView.findViewById(R.id.dbn);
            tv_name = itemView.findViewById(R.id.name);
            tv_location = itemView.findViewById(R.id.location);
            layout_school = itemView.findViewById(R.id.schoolContainer);

        }
    }
}
