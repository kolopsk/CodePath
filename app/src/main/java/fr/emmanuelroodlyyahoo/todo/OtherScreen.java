package fr.emmanuelroodlyyahoo.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class OtherScreen extends AppCompatActivity {

    EditText etNewText;
    Intent toReturn;
    String theText;
    int line;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_screen);
        etNewText = (EditText) findViewById(R.id.etNewText);
        toReturn = new Intent(OtherScreen.this, MainActivity.class);
        Bundle extras = getIntent().getExtras();
        line = extras.getInt("position");

    }

    /*
    public void onSaveItem(View view) {
        theText = etNewText.getText().toString();
        this.finish();
    }*/
}
