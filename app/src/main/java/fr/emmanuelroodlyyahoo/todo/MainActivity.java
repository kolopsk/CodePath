package fr.emmanuelroodlyyahoo.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> todoItems;
    ArrayAdapter<String> aToDoAdapter;
    ListView lvItems;
    EditText etEditText;
    String toReplace;
    private final int REQUEST_CODE = 10;
    //int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(aToDoAdapter);
        etEditText = (EditText) findViewById(R.id.etEditText);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id)
            {
                todoItems.remove(pos);
                aToDoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
            {
                toReplace = todoItems.get(pos);
                Intent other = new Intent(MainActivity.this, OtherScreen.class);
                other.putExtra("position", pos);
                startActivityForResult(other, REQUEST_CODE);
                startActivity(other);
            }
        });
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE)
        {
            if(requestCode == REQUEST_CODE)
            {
                Bundle resultat = getIntent().getExtras();
                int place = resultat.getInt("position");
                String changement = resultat.getString("modification");
                todoItems.set(place, changement);
                writeItems();
            }
        }
    }




    public void populateArrayItems()
    {
        readItems();
        aToDoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
    }


    private void readItems()
    {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try
        {
            todoItems = new ArrayList<String>(FileUtils.readLines(file));
        }catch (IOException e){
            todoItems = new ArrayList<String>();
        }
    }

    private void writeItems()
    {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try
        {
            FileUtils.writeLines(file, todoItems);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void onAddItem(View view) {
        aToDoAdapter.add(etEditText.getText().toString());
        etEditText.setText("");
        writeItems();
    }

    /*
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE)
        {
            Bundle donnee = getIntent().getExtras();
            if(donnee != null) {
                int place = donnee.getInt("position");
                String changement = donnee.getString("modification");
                todoItems.set(place, changement);
                writeItems();
            }
        }
    }
    */


}
