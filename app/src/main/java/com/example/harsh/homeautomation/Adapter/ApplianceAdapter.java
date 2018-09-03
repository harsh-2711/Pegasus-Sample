package com.example.harsh.homeautomation.Adapter;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.harsh.homeautomation.Models.Appliance;
import com.example.harsh.homeautomation.R;

import java.util.List;

public class ApplianceAdapter extends RecyclerView.Adapter<ApplianceAdapter.Holder> {

    private Context mContext;
    private List<Appliance> applianceList;
    private final OnItemClickListener listener;


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /**
     * View holder for application list item
     */
    public class Holder extends RecyclerView.ViewHolder {

        TextView applianceName;
        SwitchCompat applianceStatus;
        TextView clientID;
        TextView powerConsumed;
        TextView timeElapsed;

        public Holder(View itemView) {
            super(itemView);
            this.applianceName = itemView.findViewById(R.id.name);
            this.clientID = itemView.findViewById(R.id.client_id);
            this.powerConsumed = itemView.findViewById(R.id.power_consumed);
            this.applianceStatus = itemView.findViewById(R.id.selector);
            this.timeElapsed = itemView.findViewById(R.id.elapsed_time);
        }

        public void setup(final Appliance appliance) {
            applianceName.setText(appliance.getName());
            clientID.setText(appliance.getClientID());
            powerConsumed.setText(appliance.getPowerConsumed());
            applianceStatus.setChecked(appliance.getStatus());
            timeElapsed.setText(String.valueOf(appliance.getTimeElapsed()));


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(appliance);
                }
            });
        }
    }

    public ApplianceAdapter(Context mContext, List<Appliance> applianceList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.applianceList = applianceList;
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setup(applianceList.get(position));
    }

    @Override
    public int getItemCount() {
        return applianceList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Appliance item);
    }

}