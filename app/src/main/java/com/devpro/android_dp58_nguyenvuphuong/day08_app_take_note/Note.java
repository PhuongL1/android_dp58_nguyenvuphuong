package com.devpro.android_dp58_nguyenvuphuong.day08_app_take_note;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String content;
    private String date;
    private String duration;
    private String tag;
    private String iconName;
    private boolean isNew;
    private long createdAt;

    public Note(String title, String content, String date, String duration,
                String tag, String iconName, boolean isNew, long createdAt) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.duration = duration;
        this.tag = tag;
        this.iconName = iconName;
        this.isNew = isNew;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public String getTag() {
        return tag;
    }

    public String getIconName() {
        return iconName;
    }

    public boolean isNew() {
        return isNew;
    }

    public long getCreatedAt() {
        return createdAt;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}