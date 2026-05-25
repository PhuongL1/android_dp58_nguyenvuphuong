package com.devpro.android_dp58_nguyenvuphuong.day08_app_take_note;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.devpro.android_dp58_nguyenvuphuong.R;

public class NoteEditorActivity extends AppCompatActivity {

    private ImageView btnBack;
    private EditText edtTitle;
    private EditText edtContent;
    private TextView tvDate;

    private NoteViewModel noteViewModel;
    private Note currentNote;

    private int noteId;
    private boolean isFirstLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_note_editor_day8);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_note_editor), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        noteId = getIntent().getIntExtra("note_id", -1);

        if (noteId == -1) {
            finish();
            return;
        }

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        noteViewModel.getNoteById(noteId).observe(this, note -> {
            if (note == null) {
                return;
            }

            currentNote = note;

            if (isFirstLoad) {
                edtTitle.setText(note.getTitle());
                edtContent.setText(note.getContent());
                tvDate.setText(note.getDate() + "  •  " + note.getDuration());
                isFirstLoad = false;
            }
        });

        btnBack.setOnClickListener(v -> {
            saveNote();
            finish();
        });
    }

    private void initView() {
        btnBack = findViewById(R.id.btn_back_note);
        edtTitle = findViewById(R.id.edt_editor_title);
        edtContent = findViewById(R.id.edt_editor_content);
        tvDate = findViewById(R.id.tv_editor_date);
    }

    private void saveNote() {
        if (currentNote == null) {
            return;
        }

        String title = edtTitle.getText().toString().trim();
        String content = edtContent.getText().toString().trim();

        if (title.isEmpty()) {
            title = "Untitled note";
        }

        currentNote.setTitle(title);
        currentNote.setContent(content);
        currentNote.setNew(false);

        noteViewModel.update(currentNote);

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        saveNote();
        super.onBackPressed();
    }
}