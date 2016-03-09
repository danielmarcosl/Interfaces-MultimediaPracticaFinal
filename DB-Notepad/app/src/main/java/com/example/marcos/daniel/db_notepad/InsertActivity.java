package com.example.marcos.daniel.db_notepad;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends Activity {

	private EditText etText;

	private Button bInsert;
	private Button bDiscard;

	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert);

		etText = (EditText) findViewById(R.id.textFieldIns);
		bInsert = (Button) findViewById(R.id.buttonInsert);
		bDiscard = (Button) findViewById(R.id.buttonDiscardIns);

		initializeDB();

		bInsert.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				insertValues();
			}
		});

		bDiscard.setOnClickListener(new OnClickListener() {
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

	private void insertValues() {
		String values = etText.getText().toString();

		ContentValues cv = new ContentValues();

		cv.put("content", values);
		cv.putNull("code");
		db.insert("notepad", null, cv);
		finish();
	}
}
