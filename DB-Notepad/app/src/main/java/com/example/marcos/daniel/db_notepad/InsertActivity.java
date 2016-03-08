package com.example.marcos.daniel.db_notepad;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InsertActivity extends Activity {

	private EditText txtCodigo;
	private EditText txtNombre;
	private TextView txtResultado;

	private Button btnInsertar;
	private Button btnActualizar;
	private Button btnEliminar;
	private Button btnConsultar;

	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert);

		//Obtenemos las referencias a los controles
		txtNombre = (EditText)findViewById(R.id.textField);

		btnInsertar = (Button)findViewById(R.id.insertButton);
		btnActualizar = (Button)findViewById(R.id.updatebutton);
		btnEliminar = (Button)findViewById(R.id.deleteButton);
		btnConsultar = (Button)findViewById(R.id.wassapButton);

		//Abrimos la base de datos 'DBUsuarios' en modo escritura
        dbnotepadSQLiteHelper usdbh =
            new dbnotepadSQLiteHelper(this, "DBUsuarios", null, 1);

        db = usdbh.getWritableDatabase();

		btnInsertar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				//Recuperamos los valores de los campos de texto
				String cod = txtCodigo.getText().toString();
				String nom = txtNombre.getText().toString();

				//Alternativa 1: m�todo sqlExec()
				//String sql = "INSERT INTO Usuarios (codigo,nombre) VALUES ('" + cod + "','" + nom + "') ";
				//db.execSQL(sql);

				//Alternativa 2: m�todo insert()
				ContentValues nuevoRegistro = new ContentValues();
				nuevoRegistro.put("codigo", cod);
				nuevoRegistro.put("nombre", nom);
				db.insert("Usuarios", null, nuevoRegistro);
			}
		});

		btnActualizar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				//Recuperamos los valores de los campos de texto
				String cod = txtCodigo.getText().toString();
				String nom = txtNombre.getText().toString();

				//Alternativa 1: m�todo sqlExec()
				//String sql = "UPDATE Usuarios SET nombre='" + nom + "' WHERE codigo=" + cod;
				//db.execSQL(sql);

				//Alternativa 2: m�todo update()
				ContentValues valores = new ContentValues();
				valores.put("nombre", nom);
				db.update("Usuarios", valores, "codigo=" + cod, null);
			}
		});

		btnEliminar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				//Recuperamos los valores de los campos de texto
				String cod = txtCodigo.getText().toString();

				//Alternativa 1: m�todo sqlExec()
				//String sql = "DELETE FROM Usuarios WHERE codigo=" + cod;
				//db.execSQL(sql);

				//Alternativa 2: m�todo delete()
				db.delete("Usuarios", "codigo=" + cod, null);
			}
		});

		btnConsultar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				//Alternativa 1: m�todo rawQuery()
				Cursor c = db.rawQuery("SELECT codigo, nombre FROM Usuarios", null);

				//Alternativa 2: m�todo delete()
				//String[] campos = new String[] {"codigo", "nombre"};
				//Cursor c = db.query("Usuarios", campos, null, null, null, null, null);

				//Recorremos los resultados para mostrarlos en pantalla
				txtResultado.setText("");
				if (c.moveToFirst()) {
				     //Recorremos el cursor hasta que no haya m�s registros
				     do {
				          String cod = c.getString(0);
				          String nom = c.getString(1);

				          txtResultado.append(" " + cod + " - " + nom + "\n");
				     } while(c.moveToNext());
				}
			}
		});
	}
}
