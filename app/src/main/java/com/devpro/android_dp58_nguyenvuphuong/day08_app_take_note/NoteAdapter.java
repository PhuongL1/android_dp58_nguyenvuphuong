package com.devpro.android_dp58_nguyenvuphuong.day08_app_take_note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.android_dp58_nguyenvuphuong.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList = new ArrayList<>();
    private OnNoteClickListener listener;

    public interface OnNoteClickListener {
        void onItemClick(Note note);

        void onMoreClick(Note note, View view);
    }

    public NoteAdapter(OnNoteClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Note> notes) {
        noteList = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note_day8, parent, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);

        holder.tvNoteTitle.setText(note.getTitle());
        holder.tvNoteInfo.setText(note.getDate() + "  •  " + note.getDuration());

        if (note.isNew()) {
            holder.tvNoteNew.setVisibility(View.VISIBLE);
        } else {
            holder.tvNoteNew.setVisibility(View.GONE);
        }

        if (note.getTag() != null && !note.getTag().isEmpty()) {
            holder.tvNoteTag.setVisibility(View.VISIBLE);
            holder.tvNoteTag.setText(note.getTag());
        } else {
            holder.tvNoteTag.setVisibility(View.GONE);
        }

        holder.imgNoteIcon.setImageResource(getIconResource(note.getIconName()));

        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(note);
        });

        holder.imgNoteMore.setOnClickListener(v -> {
            listener.onMoreClick(note, holder.imgNoteMore);
        });
    }

    private int getIconResource(String iconName) {
        if ("youtube".equals(iconName)) {
            return R.drawable.icon_youtube_d8;
        } else if ("twitter".equals(iconName)) {
            return R.drawable.icon_tweitter_d8;
        } else if ("facebook".equals(iconName)) {
            return R.drawable.icon_facebook_d8;
        } else if ("mic".equals(iconName)) {
            return R.drawable.icon_mic_d8;
        } else {
            return R.drawable.icon_file_d8;
        }
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        ImageView imgNoteIcon;
        ImageView imgNoteMore;
        TextView tvNoteTitle;
        TextView tvNoteInfo;
        TextView tvNoteNew;
        TextView tvNoteTag;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            imgNoteIcon = itemView.findViewById(R.id.img_note_icon);
            imgNoteMore = itemView.findViewById(R.id.img_note_more);
            tvNoteTitle = itemView.findViewById(R.id.tv_note_title);
            tvNoteInfo = itemView.findViewById(R.id.tv_note_info);
            tvNoteNew = itemView.findViewById(R.id.tv_note_new);
            tvNoteTag = itemView.findViewById(R.id.tv_note_tag);
        }
    }
}