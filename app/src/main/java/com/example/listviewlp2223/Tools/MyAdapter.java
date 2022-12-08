package com.example.listviewlp2223.Tools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listviewlp2223.Model.Task;
import com.example.listviewlp2223.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Task> localData = new ArrayList<>();

    public static InterfaceMyListener getMyListener() {
        return myListener;
    }

    public static void setMyListener(InterfaceMyListener myListener) {
        MyAdapter.myListener = myListener;
    }

    private static InterfaceMyListener myListener;

    public MyAdapter(List<Task> l){
        //localData.addAll(l);
        localData = l;
    }

    //Create new Views (invoked by LayoutManager)
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create new View which defines the UI of list item

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlayout, parent, false);

        return new MyViewHolder(view);
    }

    //Replace the contents of a View(invoked by LayoutManager)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Task task = localData.get(position);
        holder.display(task);
    }

    @Override
    public int getItemCount() {
        return localData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        private TextView tvid, tvtitle, tvdescription;
        //private Task myTask;


        private MyViewHolder(View itemView){
            super(itemView);
            tvid = itemView.findViewById(R.id.tvid);
            tvtitle = itemView.findViewById(R.id.tvtitle);
            tvdescription = itemView.findViewById(R.id.tvdescription);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void display(Task task){
            tvid.setText(String.valueOf(task.getId()));
            tvtitle.setText(task.getTitle());
            tvdescription.setText(task.getDescription());
            //myTask=task;
        }

        @Override
        public void onClick(View view) {
            myListener.onItemClick(getAdapterPosition(), view);
        }

        @Override
        public boolean onLongClick(View view) {
            myListener.onItemLongClick(getAdapterPosition(), view);
            return false;
        }
    }
}
