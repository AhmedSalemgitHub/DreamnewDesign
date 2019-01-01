package com.tellyourdream.tellyourdream;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DreamsRecyclerAdapter extends RecyclerView.Adapter<DreamsRecyclerAdapter.ViewHolder> {

    private List<Dreams> dreamsList;
    private Context context;

    public DreamsRecyclerAdapter(Context context , List<Dreams> dreamsList)
    {

        this.dreamsList = dreamsList;
        this.context = context;

    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dream_list_item , viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.userName_View.setText(dreamsList.get(i).getName());

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return dreamsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View mView;

        private TextView userName_View;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            userName_View = mView.findViewById(R.id.NameListItem);
        }
    }
}
