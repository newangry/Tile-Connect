package com.funkyland.tileconnecttravel.NewVersion.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Pereferences;
import com.funkyland.tileconnecttravel.R;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    Context context;
    Integer[] list;
    String[] listpath;
    Integer[] image;
    AdapterClick adapterClick;
    Boolean isSetUnlockLevel = true;
    int setBanner = -1;

    public CountryAdapter(Context context, Integer[] list, String[] listpath, Integer[] image) {
        this.context = context;
        this.list = list;
        this.listpath = listpath;
        this.image = image;
    }


    public void setAdapterClick(AdapterClick adapterClick) {
        this.adapterClick = adapterClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_country, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_country.setText(context.getString(list[position]));
        holder.img.setImageResource(image[position]);

        if (position == 0) {
            holder.lock.setVisibility(View.GONE);
            Pereferences.set_LockTypeStatus(listpath[position], true);
        }

        Pereferences.set_gameType_InSubType(listpath[position]);
        if (IPreferences.getInstance(context).getTotal_level() != 1) {
            holder.txt_start.setText("⭐ " + IPreferences.getInstance(context).getTotal_level());
        }

        if (position != 0) {
            if (Pereferences.get_LockTypeStatus(listpath[position])) {
                holder.lock.setVisibility(View.GONE);
            } else {
                holder.lock.setVisibility(View.VISIBLE);
                if (isSetUnlockLevel) {
                    setBanner = position;
                    isSetUnlockLevel = false;
                }
            }
        }


        if (position != 0) {
            if (setBanner == position) {
                String strMeatFormat = context.getResources().getString(R.string.will_unlock_when_country_has_15_starts, listpath[position - 1]);
                holder.txt_start.setText(strMeatFormat);
                holder.txt_start.setVisibility(View.VISIBLE);
            }
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Pereferences.get_LockTypeStatus(listpath[position])) {
                    Pereferences.set_gameType_InSubType(listpath[position]);
                    adapterClick.ClickOnItem(position, listpath[position]);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface AdapterClick {
        public void ClickOnItem(int pos, String name);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_country, txt_start;
        ImageView img, lock;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_country = itemView.findViewById(R.id.txt_country);
            txt_start = itemView.findViewById(R.id.txt_start);
            img = itemView.findViewById(R.id.img);
            lock = itemView.findViewById(R.id.lock);
        }
    }
}
