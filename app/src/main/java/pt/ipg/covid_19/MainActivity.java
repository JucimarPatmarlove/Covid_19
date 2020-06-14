package pt.ipg.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Metodo
    public void Bem (View view){

        Intent intent = new Intent(this,Bem2Activity.class);


        startActivity(intent);

    }

    public void Mal (View view){

        Intent intent = new Intent(this,MalActivity.class);


        startActivity(intent);

    }


}
