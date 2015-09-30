package com.example.alumno05.sqlite01;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import Coneccion.sqlite;


public class MainActivity extends ActionBarActivity {
    sqlite cx;
    EditText edit1,edit2;
    ListView lista;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cx=new sqlite(this,"bdusuario",null,1);
        edit1=(EditText) findViewById(R.id.EditText);
        edit2=(EditText) findViewById(R.id.EditText2);

        lista=(ListView) findViewById(R.id.listView);
        listar();
       /* lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Toast.makeText(getApplicationContext(),"Se elimino", Toast.LENGTH_SHORT).show();
            }
        } );*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public  void insertusuario(View v) {
        if (cx.getWritableDatabase()!=null) {
            cx.getWritableDatabase().execSQL("INSERT INTO usuario (campo1,campo2) values ('" + edit1.getText().toString() + "','" + edit2.getText().toString() + "')");
            Toast.makeText(getApplicationContext(), "Insertado", Toast.LENGTH_LONG).show();
            listar();
            limpiar();
        }
    }
    public void listar(){

        Cursor cursor=cx.getReadableDatabase().rawQuery("SELECT campo1 FROM usuario",null);

         data=new String[cursor.getCount()];

   int n=0;
        if(cursor.moveToFirst()){
        do {
            data[n]=cursor.getString(0).toString();
            n++;
        }while (cursor.moveToNext());

        }
        lista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, data));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cx.getWritableDatabase().execSQL("DELETE FROM usuario where campo1='"+data[position]+"'");
                listar();
            }
        });
    }

    public void update(View v){

        listar();
        limpiar();
    }


    public void limpiar(){
        edit1.setText("");
        edit2.setText("");
    }
}
