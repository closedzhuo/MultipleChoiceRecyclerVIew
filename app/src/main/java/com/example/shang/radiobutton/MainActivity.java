package com.example.shang.radiobutton;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MainBean> item;
    private MainAdapater adapater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        item = new ArrayList<>();

        initData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapater = new MainAdapater();
        recyclerView.setAdapter(adapater);
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            MainBean mainBean = new MainBean();
            item.add(mainBean);
        }

    }

    SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();

    class MainAdapater extends RecyclerView.Adapter<MainViewHolder> {
        public int selecterPostion = -1;
        public boolean isBind;

        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new MainViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, final int position) {
            isBind = true;
            final MainBean mainBean = item.get(position);
            if (mainBean.isChecked){
                holder.checkBox.setImageResource(R.drawable.shape_select);
            }else{
                holder.checkBox.setImageResource(R.drawable.shape_normal);
            }

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selecterPostion != position && selecterPostion != -1) {
                        item.get(selecterPostion).isChecked = false;
                        notifyItemChanged(selecterPostion);
                    }
                    selecterPostion = position;
                    item.get(position).isChecked = true;
                    notifyItemChanged(position);
                }
            });



        }

        @Override
        public int getItemCount() {
            return item.size();
        }
    }


    static class MainViewHolder extends RecyclerView.ViewHolder {

        public ImageView checkBox;

        public MainViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.chekbox);
        }
    }
}
