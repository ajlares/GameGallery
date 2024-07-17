package com.example.gamegallery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Memorama extends AppCompatActivity implements View.OnClickListener {

    private List<Button> selectedButtons = new ArrayList<>(2);
    private Handler handler = new Handler();
    private LinearLayout winLayout;
    private int[] buttonIds = {
            R.id.buttonA1, R.id.buttonB1, R.id.buttonC1, R.id.buttonD1, R.id.buttonE1,
            R.id.buttonA2, R.id.buttonB2, R.id.buttonC2, R.id.buttonD2, R.id.buttonE2
    };

    private RelativeLayout menu;
    Button button_menu;
    Button button_continue;
    Button button_restart;
    Button button_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorama);

        winLayout = findViewById(R.id.winLayout);
        Button exitButton = findViewById(R.id.exitButton);
        Button restartButton = findViewById(R.id.restartButton);

        menu = findViewById(R.id.menu);
        button_menu = findViewById(R.id.button_show_menu);
        button_continue = findViewById(R.id.button_continue);
        button_restart = findViewById(R.id.button_restart);
        button_home = findViewById(R.id.button_home);

        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setVisibility(View.VISIBLE);
                button_menu.setVisibility(View.GONE);
            }
        });

        // boton continuar
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setVisibility(View.GONE);
                button_menu.setVisibility(View.VISIBLE);
            }
        });

        // uso de boton restart
        button_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

        // uso de boton home
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load = new Intent(Memorama.this, MainActivity.class);
                startActivity(load);
            }
        });

        // Establecer OnClickListener para los botones de salir y volver a jugar
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });

        // Obtener referencias a todos los botones del memorama y establecer OnClickListener
        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            Button button = (Button) v;

            if (selectedButtons.size() < 2 && !selectedButtons.contains(button)) {
                button.setText(button.getTag().toString());
                selectedButtons.add(button);

                if (selectedButtons.size() == 2) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            checkMatch();
                        }
                    }, 1000);
                }
            }
        }
    }

    private void checkMatch() {
        Button button1 = selectedButtons.get(0);
        Button button2 = selectedButtons.get(1);

        if (button1.getTag().toString().equals(button2.getTag().toString())) {
            // Los tags coinciden, eliminar los botones
            button1.setVisibility(View.INVISIBLE);
            button2.setVisibility(View.INVISIBLE);
        } else {
            // Los tags no coinciden, resetear los textos
            button1.setText("");
            button2.setText("");
        }

        // Limpiar la lista de botones seleccionados
        selectedButtons.clear();

        // Verificar si todos los botones están invisibles
        checkWinCondition();
    }

    private void checkWinCondition() {
        for (int id : buttonIds) {
            Button button = findViewById(id);
            if (button.getVisibility() == View.VISIBLE) {
                return; // Aún hay botones visibles, no has ganado
            }
        }
        // Todos los botones están invisibles, mostrar mensaje de victoria y botones
        menu.setVisibility(View.GONE);
        button_menu.setVisibility(View.GONE);
        winLayout.setVisibility(View.VISIBLE);
    }

    private void restartGame() {
        // Reiniciar la visibilidad de los botones
        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setVisibility(View.VISIBLE);
            button.setText("");
        }
        // Ocultar el mensaje de victoria y los botones
        winLayout.setVisibility(View.GONE);
    }
}
