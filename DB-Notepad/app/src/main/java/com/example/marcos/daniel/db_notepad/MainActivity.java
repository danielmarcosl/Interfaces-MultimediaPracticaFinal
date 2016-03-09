package com.example.marcos.daniel.db_notepad;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private ArrayList<Note> ar_note = new ArrayList<Note>();
    private ArrayList<String> headerList = new ArrayList<String>();

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emptyAll();
        fillArray();
        mountListView();

        Button addButton = (Button) findViewById(R.id.buttonAdd);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newInsert();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        emptyAll();
        fillArray();
        mountListView();
    }

    private void fillArray() {
        Cursor c = db.rawQuery("SELECT code, text FROM dbnotepad", null);

        if (c.moveToFirst()) {
            do {
                int cod = c.getInt(0);
                String text = c.getString(1);

                ar_note.add(new Note(cod, text));
                headerList.add(text);
            } while (c.moveToNext());
        }
    }

    private void mountListView() {
        ListView lv = (ListView) findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, headerList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, ModifyActivity.class);

                i.putExtra("note", ar_note.get(position));
                startActivity(i);
            }
        });
    }

    private void newInsert() {
        Intent i = new Intent(MainActivity.this, InsertActivity.class);

        startActivity(i);
    }

    private void emptyAll() {
        ar_note.clear();
        headerList.clear();

        ListView lv = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, headerList);

        lv.setAdapter(adapter);
    }
}
