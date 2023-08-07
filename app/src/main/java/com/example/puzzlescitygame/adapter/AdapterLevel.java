package com.example.puzzlescitygame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlescitygame.databinding.ItemqBinding;
import com.example.puzzlescitygame.roomdatabase.entity.Level;
import com.example.puzzlescitygame.sherdPreferance.Sherdpreferanses;

import java.util.List;

public class AdapterLevel extends RecyclerView.Adapter<AdapterLevel.LevelsHolder> {

    List<Level> list;
    Context context;
    CallLevel callLevel;
    public AdapterLevel(List<Level> list, Context context, CallLevel callLevel) {
        this.list = list;
        this.context = context;
        this.callLevel = callLevel;
    }

    public List<Level> getList() {
        return list;
    }

    public void setList(List<Level> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LevelsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemqBinding binding = ItemqBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LevelsHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull LevelsHolder holder, int position) {
        Level level = list.get(position);
        int pos =position;
        holder.setData(list.get(position));
        if (level.getScoreLevel() <= Sherdpreferanses.getInstance().getScore()){
            holder.item.itemTextlock.setText("");
            holder.item.itemTextUnlock.setText("");
            holder.item.getRoot().setOnClickListener(view -> {
                callLevel.callLevel(list.get(pos).getIdLevel());
            });
        }
//          Rating Bar
      /*  // Get the data model for this position
        DataModel dataModel = mDataList.get(position);

        // Set the rating for this list item
        holder.ratingBar.setRating(dataModel.getRating());
*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class LevelsHolder extends RecyclerView.ViewHolder {
        ItemqBinding item;

        public LevelsHolder(@NonNull View itemView) {
            super(itemView);
            item = ItemqBinding.bind(itemView);
        }

        public void setData(Level level) {
            item.itemTextLevel.setText(String.valueOf(level.getIdLevel()));
            item.itemTextUnlock.setText(String.valueOf(level.getScoreLevel()));
        }
    }

    public static interface CallLevel {
        void callLevel(int x);
    }
}