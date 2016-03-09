package com.example.marcos.daniel.db_notepad;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyActivity extends Activity {

    private int cod;
    private String text;

    private EditText etText;

    private Button bUpdate;
    private Button bDelete;
    private Button bWassap;
    private Button bDiscard;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        Bundle extras = getIntent().getExtras();
        Note currentNote = (Note) extras.getSerializable("note");

        cod = currentNote.getCode();
        text = currentNote.getText();

        etText = (EditText) findViewById(R.id.textFieldMod);
        bUpdate = (Button) findViewById(R.id.buttonUpdate);
        bDelete = (Button) findViewById(R.id.buttonDelete);
        bWassap = (Button) findViewById(R.id.buttonWassap);
        bDiscard = (Button) findViewById(R.id.buttonDiscardMod);

        pasteTextToEditText();
        initializeDB();

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateValues();
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteValues();
            }
        });

        bWassap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wassap();
            }
        });

        bDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initializeDB() {
        dbnotepadSQLiteHelper dbnt = new dbnotepadSQLiteHelper(this, "dbnotepad", null, 1);
        db = dbnt.getWritableDatabase();
    }

    private void pasteTextToEditText() {
        etText.setText(text);
    }

    private void updateValues() {
        String tx = etText.getText().toString();

        ContentValues cv = new ContentValues();

        cv.put("content", tx);
        db.update("notepad", cv, "code = " + cod, null);

        finish();
    }

    private void deleteValues() {
        db.delete("notepad", "code = " + cod, null);

        finish();
    }

    private void wassap() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
