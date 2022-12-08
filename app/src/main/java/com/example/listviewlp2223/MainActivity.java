package com.example.listviewlp2223;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.listviewlp2223.Model.Task;
import com.example.listviewlp2223.Tools.InterfaceMyListener;
import com.example.listviewlp2223.Tools.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> data = new ArrayList<>();
    private int index = 0;
    private LinearLayoutManager linearLayoutManager;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
        adapter.setMyListener(new InterfaceMyListener() {
            @Override
            public void onItemClick(int position, View v) {
                Task task = data.get(position);
                new AlertDialog.Builder(v.getContext()).setTitle(task.getTitle()).setMessage(task.getDescription()).show();
            }

            @Override
            public void onItemLongClick(int position, View view) {
                data.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addTask(View view) {
        data.add(new Task(index, "Task"+index, "DescriptionTest"+index));
        index++;
        Log.d("MesLogs", "Apres le add : "+data.size());
        adapter.notifyDataSetChanged();
    }
}