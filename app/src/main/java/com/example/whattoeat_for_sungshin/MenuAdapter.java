package com.example.whattoeat_for_sungshin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//menu_item을 recyclerView에 넣기 위한 adapter 정의
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> implements OnMenuItemClickListener{
    public static ArrayList<Menu> items = new ArrayList<Menu>();

    public interface OnItemClickLisener{
        void onItemClick(View v, int posision);
    }


    private OnMenuItemClickListener mListener = null;

    public void setOnItemClickListener(OnMenuItemClickListener listener){
        this.mListener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(mListener != null)
            mListener.onItemClick(holder, view, position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.store_item, viewGroup, false);

        return new MenuAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Menu item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Menu item){
        items.add(item);
    }

    public void setItems(ArrayList<Menu> items){
        this.items = items;
    }

    public Menu getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, Menu item){
        items.set(position, item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.item_store);
            textView2 = itemView.findViewById(R.id.item_tag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        if(mListener != null){
                            mListener.onItemClick(ViewHolder.this, view, position);
                        }
                    }
                }
            });
        }

        public void setItem(Menu item) {
            textView.setText(item.getName());
            textView2.setText(item.getHashtag());
        }
    }
}

