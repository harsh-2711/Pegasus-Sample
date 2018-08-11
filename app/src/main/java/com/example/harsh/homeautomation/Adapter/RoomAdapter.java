package com.example.harsh.homeautomation.Adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harsh.homeautomation.Models.Room;
import com.example.harsh.homeautomation.R;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.Holder> {

    private Context mContext;
    private List<Room> roomList;
    private final OnItemClickListener listener;


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /**
     * View holder for application list item
     */
    public class Holder extends RecyclerView.ViewHolder {

        TextView roomName;
        ImageView roomImage;

        public Holder(View itemView) {
            super(itemView);
            this.roomName = itemView.findViewById(R.id.room_name);
            this.roomImage = itemView.findViewById(R.id.room_image);
        }

        public void setup(final Room room, final OnItemClickListener listener) {
            roomName.setText(room.getRoomName());
            roomImage.setImageDrawable(ContextCompat.getDrawable(mContext,room.getRoomImage()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(room);
                }
            });
        }
    }

    public RoomAdapter(Context mContext, List<Room> roomList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.roomList = roomList;
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_list, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setup(roomList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Room item);
    }

}
