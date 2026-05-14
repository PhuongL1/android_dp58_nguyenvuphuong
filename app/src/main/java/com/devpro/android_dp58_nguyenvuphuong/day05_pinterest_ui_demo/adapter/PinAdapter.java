package com.devpro.android_dp58_nguyenvuphuong.day05_pinterest_ui_demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.devpro.android_dp58_nguyenvuphuong.R;
import com.devpro.android_dp58_nguyenvuphuong.day05_pinterest_ui_demo.model.PinItem;
import java.util.List;
public class PinAdapter extends RecyclerView.Adapter<PinAdapter.PinViewHolder> {

    private List<PinItem> pinList;
    public PinAdapter(List<PinItem> pinList) {
        this.pinList = pinList;
    }
    @NonNull
    @Override
    public PinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pin, parent, false);

        return new PinViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PinViewHolder holder, int position) {
        PinItem pinItem = pinList.get(position);
        holder.imgPin.setImageResource(pinItem.getImageResource());
        ViewGroup.LayoutParams params = holder.imgPin.getLayoutParams();
        params.height = dpToPx(holder.itemView, pinItem.getImageHeight());
        holder.imgPin.setLayoutParams(params);
    }
    @Override
    public int getItemCount() {
        return pinList.size();
    }
    private int dpToPx(View view, int dp) {
        float density = view.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density);
    }
    static class PinViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPin;
        public PinViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPin = itemView.findViewById(R.id.img_pin);
        }
    }
}