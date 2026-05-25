package com.devpro.android_dp58_nguyenvuphuong.day08_app_take_note;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.android_dp58_nguyenvuphuong.R;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MainDay8Activity extends AppCompatActivity {

    private EditText edtSearchNote;
    private ImageButton btnFilterNote;
    private TextView tvCreateTag;
    private LinearLayout layoutTagContainer;
    private RecyclerView rcvNotes;
    private MaterialButton btnAddNote;

    private NoteAdapter noteAdapter;
    private NoteViewModel noteViewModel;

    private List<Note> allNotes = new ArrayList<>();
    private List<String> tagList = new ArrayList<>();

    private String selectedTag = "All tags";

    private static final String PREF_NAME = "note_tag_pref";
    private static final String KEY_TAGS = "tags";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_day8);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        loadTags();
        setupTagsView();
        setupRecyclerView();
        setupViewModel();
        setupEvent();
    }

    private void initView() {
        edtSearchNote = findViewById(R.id.edt_search_note);
        btnFilterNote = findViewById(R.id.btn_filter_note);
        tvCreateTag = findViewById(R.id.tv_create_tag);
        layoutTagContainer = findViewById(R.id.layout_tag_container);
        rcvNotes = findViewById(R.id.rcv_notes);
        btnAddNote = findViewById(R.id.btn_add_note);
    }

    private void setupRecyclerView() {
        noteAdapter = new NoteAdapter(new NoteAdapter.OnNoteClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(MainDay8Activity.this, NoteEditorActivity.class);
                intent.putExtra("note_id", note.getId());
                startActivity(intent);
            }

            @Override
            public void onMoreClick(Note note, View view) {
                showNoteMenu(note, view);
            }
        });

        rcvNotes.setLayoutManager(new LinearLayoutManager(this));
        rcvNotes.setAdapter(noteAdapter);
    }

    private void setupViewModel() {
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(this, notes -> {
            allNotes = notes;
            applyFilterAndSearch();
        });
    }

    private void setupEvent() {
        btnAddNote.setOnClickListener(v -> {
            showAddNoteDialog();
        });

        tvCreateTag.setOnClickListener(v -> {
            showCreateTagDialog();
        });

        btnFilterNote.setOnClickListener(v -> {
            applyFilterAndSearch();
        });
    }

    private void showAddNoteDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_note_day8, null);

        EditText edtTitle = view.findViewById(R.id.edt_note_title);
        EditText edtDuration = view.findViewById(R.id.edt_note_duration);
        EditText edtContent = view.findViewById(R.id.edt_note_content);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add Note")
                .setView(view)
                .setPositiveButton("Add", null)
                .setNegativeButton("Cancel", null)
                .create();

        dialog.setOnShowListener(dialogInterface -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                String title = edtTitle.getText().toString().trim();
                String duration = edtDuration.getText().toString().trim();
                String content = edtContent.getText().toString().trim();

                if (title.isEmpty()) {
                    edtTitle.setError("Please enter title");
                    return;
                }

                if (duration.isEmpty()) {
                    duration = "00:00:00";
                }

                Note note = new Note(
                        title,
                        content,
                        getCurrentDate(),
                        duration,
                        "",
                        "file",
                        true,
                        System.currentTimeMillis()
                );

                noteViewModel.insert(note);

                Toast.makeText(this, "Added note", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });
        });

        dialog.show();
    }

    private void showNoteMenu(Note note, View anchorView) {
        PopupMenu popupMenu = new PopupMenu(this, anchorView);

        popupMenu.getMenu().add("Chọn tag");
        popupMenu.getMenu().add("Chọn icon");
        popupMenu.getMenu().add("Xóa note");

        popupMenu.setOnMenuItemClickListener(item -> {
            String title = item.getTitle().toString();

            if (title.equals("Chọn tag")) {
                showChooseTagDialog(note);
                return true;
            }

            if (title.equals("Chọn icon")) {
                showChooseIconDialog(note);
                return true;
            }

            if (title.equals("Xóa note")) {
                showDeleteDialog(note);
                return true;
            }

            return false;
        });

        popupMenu.show();
    }

    private void showChooseTagDialog(Note note) {
        List<String> realTags = new ArrayList<>();

        for (String tag : tagList) {
            if (!tag.equals("All tags")) {
                realTags.add(tag);
            }
        }

        realTags.add(0, "No tag");

        String[] tagArray = realTags.toArray(new String[0]);

        new AlertDialog.Builder(this)
                .setTitle("Choose tag")
                .setItems(tagArray, (dialog, which) -> {
                    String selected = tagArray[which];

                    if (selected.equals("No tag")) {
                        note.setTag("");
                    } else {
                        note.setTag(selected);
                    }

                    noteViewModel.update(note);
                    Toast.makeText(this, "Updated tag", Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    private void showChooseIconDialog(Note note) {
        String[] iconLabels = {
                "Youtube",
                "Twitter",
                "Facebook",
                "File",
                "Mic"
        };

        String[] iconValues = {
                "youtube",
                "twitter",
                "facebook",
                "file",
                "mic"
        };

        new AlertDialog.Builder(this)
                .setTitle("Choose icon")
                .setItems(iconLabels, (dialog, which) -> {
                    note.setIconName(iconValues[which]);
                    noteViewModel.update(note);
                    Toast.makeText(this, "Updated icon", Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    private void showDeleteDialog(Note note) {
        new AlertDialog.Builder(this)
                .setTitle("Delete note")
                .setMessage("Do you want to delete this note?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    noteViewModel.delete(note);
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showCreateTagDialog() {
        EditText editText = new EditText(this);
        editText.setHint("Enter tag name");
        editText.setSingleLine(true);
        editText.setPadding(40, 20, 40, 20);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Create tag")
                .setView(editText)
                .setPositiveButton("Create", null)
                .setNegativeButton("Cancel", null)
                .create();

        dialog.setOnShowListener(dialogInterface -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                String tagName = editText.getText().toString().trim();

                if (tagName.isEmpty()) {
                    editText.setError("Please enter tag name");
                    return;
                }

                if (tagList.contains(tagName)) {
                    editText.setError("Tag already exists");
                    return;
                }

                tagList.add(tagName);
                saveTags();
                selectedTag = tagName;
                setupTagsView();
                applyFilterAndSearch();

                Toast.makeText(this, "Created tag", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });
        });

        dialog.show();
    }

    private void applyFilterAndSearch() {
        String keyword = edtSearchNote.getText().toString().trim().toLowerCase();

        List<Note> result = new ArrayList<>();

        for (Note note : allNotes) {
            boolean matchTag = true;
            boolean matchSearch = true;

            if (!selectedTag.equals("All tags")) {
                matchTag = note.getTag() != null && note.getTag().equals(selectedTag);
            }

            if (!keyword.isEmpty()) {
                String title = note.getTitle() == null ? "" : note.getTitle().toLowerCase();
                String content = note.getContent() == null ? "" : note.getContent().toLowerCase();
                String tag = note.getTag() == null ? "" : note.getTag().toLowerCase();

                matchSearch = title.contains(keyword)
                        || content.contains(keyword)
                        || tag.contains(keyword);
            }

            if (matchTag && matchSearch) {
                result.add(note);
            }
        }

        noteAdapter.setData(result);
    }

    private void setupTagsView() {
        layoutTagContainer.removeAllViews();

        for (String tag : tagList) {
            TextView textView = new TextView(this);
            textView.setText(tag);
            textView.setTextSize(11);
            textView.setGravity(android.view.Gravity.CENTER);
            textView.setPadding(24, 0, 24, 0);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    dpToPx(26)
            );
            params.setMargins(0, 0, dpToPx(8), 0);
            textView.setLayoutParams(params);

            if (tag.equals(selectedTag)) {
                textView.setTextColor(getColor(android.R.color.white));
                textView.setBackgroundResource(R.drawable.bg_tag_selected_d8);
            } else {
                textView.setTextColor(getColor(R.color.black));
                textView.setBackgroundResource(R.drawable.bg_tag_normal_d8);
            }

            textView.setOnClickListener(v -> {
                selectedTag = tag;
                setupTagsView();
                applyFilterAndSearch();
            });

            layoutTagContainer.addView(textView);
        }
    }

    private void loadTags() {
        tagList.clear();
        tagList.add("All tags");

        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Set<String> tagSet = sharedPreferences.getStringSet(KEY_TAGS, new HashSet<>());

        tagList.addAll(tagSet);
    }

    private void saveTags() {
        Set<String> tagSet = new HashSet<>();

        for (String tag : tagList) {
            if (!tag.equals("All tags")) {
                tagSet.add(tag);
            }
        }

        getSharedPreferences(PREF_NAME, MODE_PRIVATE)
                .edit()
                .putStringSet(KEY_TAGS, tagSet)
                .apply();
    }

    private String getCurrentDate() {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);

        return simpleDateFormat.format(new Date());
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}