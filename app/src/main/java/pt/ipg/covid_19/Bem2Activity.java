package pt.ipg.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bem2Activity extends AppCompatActivity {

    private EditText editTextPeso;
    private EditText editTextAltura;
    private TextView textViewResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem2);


    }

    public void calcularSi(View view) {

        Button buttonSi = (Button) findViewById(R.id.buttonSi);
        double peso = Double.parseDouble(editTextPeso.getText().toString());
        double altura = Double.parseDouble(editTextAltura.getText().toString());


        //resultado = peso/(altura*altura)
        double resultado = peso / (altura * altura);

        if (resultado >= 18.9 && resultado <= 24.99) {
            textViewResultado.setText("Peso normal, Si =" + resultado);
        } else if (resultado >= 25 && resultado <= 29.99) {
            textViewResultado.setText("Acima do peso, Si =" + resultado);

        } else if (resultado >= 30 && resultado <= 34.99) {
            textViewResultado.setText("Obesidade, Si =" + resultado);

        } else if (resultado >= 35 && resultado <= 39.99) {
            textViewResultado.setText("Obesidade severa, Si =" + resultado);

        }

    }
    public void Acerca (View view){

        Intent intent = new Intent(this, ArtigoCientificoActivity.class);


        startActivity(intent);

    }

    public void Dicas (View view){

        Intent intent = new Intent(this, DicaAlimentacao2.class);


        startActivity(intent);

    }

    public void Msaudavel (View view){

        Intent intent = new Intent(this,  EstrategiaAlimentacao2.class);


        startActivity(intent);

    }

    public void Estilo(View view) {
        Intent intent = new Intent(this, EstiloDeVida.class);


        startActivity(intent);
    }
}
