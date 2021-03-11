package com.guo.yy.relearning.view.mynestedscrolling;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.guo.yy.relearning.R;

import java.util.ArrayList;

/**
 * author : Guo
 * date   : 2021/3/1
 * desc   :
 */
public class CoordinatorTestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_test);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ArrayList<String> strings = new ArrayList<>(100);
        for (int i = 0; i <99;i++){
            strings.add("AAAAAAAAAAAAAAA -> "+i);
        }
        RecyclerView.Adapter<TestViewHolder> stringViewHolderListAdapter = new RecyclerView.Adapter<TestViewHolder>() {
            @NonNull
            @Override
            public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coordinator_test, null);
                return new TestViewHolder(inflate);
            }

            @Override
            public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
                holder.tvContent.setText(strings.get(position));

            }

            @Override
            public int getItemCount() {
                return strings.size();
            }






        };
        recyclerView.setAdapter(stringViewHolderListAdapter);





    }

    public static class TestViewHolder extends RecyclerView.ViewHolder{
        public TextView tvContent;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
