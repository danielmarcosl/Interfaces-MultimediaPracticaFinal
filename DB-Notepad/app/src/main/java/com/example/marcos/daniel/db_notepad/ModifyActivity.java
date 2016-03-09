package com.example.marcos.daniel.db_notepad;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ModifyActivity extends Activity {

    private EditText etText;

    private Button bInsert;
    private Button bDiscard;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
    }
}
