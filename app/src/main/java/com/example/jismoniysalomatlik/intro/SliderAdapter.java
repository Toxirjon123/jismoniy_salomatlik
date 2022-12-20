package com.example.jismoniysalomatlik.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jismoniysalomatlik.R;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.MainViewHolder> {
    List<String> list;
    Context context;

    public SliderAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout
               .item_photo,parent,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.txt.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
      public MainViewHolder(@NonNull View itemView) {
          super(itemView);
          txt = itemView.findViewById(R.id.txt);
      }
  }
}
