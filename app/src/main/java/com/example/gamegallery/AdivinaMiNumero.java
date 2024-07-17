package com.example.gamegallery;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class AdivinaMiNumero extends AppCompatActivity {

    private int generatedNumber;
    private EditText inputNumber;
    private TextView lastGuess;
    private TextView attempsText;

    private TextView resultTextView;

    private int attemps = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivina_mi_numero);

        inputNumber = findViewById(R.id.inputNumber);
        lastGuess = findViewById(R.id.lastGuess);
        attempsText = findViewById(R.id.attempsText);
        Button guessButton = findViewById(R.id.guessButton);
        Button restartButton = findViewById(R.id.btn_restart);
        resultTextView = findViewById(R.id.resultTextView);

        // Generar un número aleatorio entre 1 y 100 al inicio
        Iniciar();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Iniciar();
            }
        });



    }

    public  void Iniciar(){
        generatedNumber = generateRandomNumber(1, 100);
        attemps = 0;
        lastGuess.setText("Ultima adivinanza: ");
        attempsText.setText("Intentos: ");
        inputNumber.setText("");
        resultTextView.setText("");
    }

    private int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private void checkGuess() {
        String userInput = inputNumber.getText().toString();
        if (!userInput.isEmpty()) {
            int userNumber = Integer.parseInt(userInput);
            attemps++;
            if (userNumber < generatedNumber) {
                resultTextView.setText("Tu número es muy pequeño.");
            } else if (userNumber > generatedNumber) {
                resultTextView.setText("Tu número es muy grande.");
            } else {
                resultTextView.setText("¡Bien hecho! Adivinaste el número.");
                String toasty = "Intentos: " + attemps;
                Toast.makeText(getApplicationContext(),toasty,Toast.LENGTH_LONG).show();
            }
            lastGuess.setText("Ultima adivinanza: " + userInput);
            attempsText.setText("Intentos: " + attemps);
            inputNumber.setText("");
        } else {
            resultTextView.setText("Por favor, ingresa un número.");
        }
    }

}