package com.example.gamegallery;

// librerias
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// clase principal
public class MainActivity extends AppCompatActivity {

    //Game selection layout variables
    ConstraintLayout gameSelection;

    //Level details layout variables
    ConstraintLayout gameDetails;
    Button closeDetails;
    TextView gameTitle;
    ImageView gameImage;
    TextView gameDescription;
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Welcome screen layout variables

        //Game selection layout variables
        gameSelection = findViewById(R.id.GameSelection);
        CardView minesweeper = findViewById(R.id.level1);
        CardView ligthParkour = findViewById(R.id.level2);
        CardView memoryMinigame = findViewById(R.id.level3);
        CardView ticTacToe = findViewById(R.id.level4);
        CardView level5 = findViewById(R.id.level5);
        CardView level6 = findViewById(R.id.level6);

        //Level details layout
        gameDetails = findViewById(R.id.GameDetails);
        gameTitle = findViewById(R.id.GameTitle);
        gameImage = findViewById(R.id.GameImage);
        gameDescription = findViewById(R.id.GameDescription);
        closeDetails = findViewById(R.id.close);
        closeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDetails.setVisibility(View.GONE);
                gameSelection.setVisibility(View.VISIBLE);
            }
        });
        playButton = findViewById(R.id.play);


        gameDetails.setVisibility(View.GONE);
        gameSelection.setVisibility(View.VISIBLE);


        //Minesweeper minigame
        minesweeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetGameDetails("Minesweeper",R.drawable.minesweeper, "xd",Minesweeper.class);
            }
        });
        // light parkour minigame
        ligthParkour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = "Light Parkour es un juego donde mueves tu celular para navegar un mapa con múltiples plataformas, evitando estas para llegar a su meta\n"+
                        "La posición de las plataformas cambian dependiendo de la luz ambiental, creando varias rutas a la meta.\n" +
                        "\n" +
                        "Inclina el dispositivo para mover al jugador\n" +
                        "Si el jugador choca con las plataformas, regresará a su posición inicial\n" +
                        "\n" +
                        "Hay plataformas sensibles a la luz que solo se activan cuando hay mucha o poca\n";
                SetGameDetails("Light Parkour",R.drawable.lightparkour, description,LightParkour.class);
            }
        });
        // Memory minigame
        memoryMinigame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetGameDetails("Memorama",R.drawable.memorygame, "El objetivo es lograr memorizar la ubicación de las diferentes cartas con el fin de voltear sucesivamente las 2 cartas idénticas que formen pareja, para llevárselas. ",Memorama.class);
            }
        });
        // TicTacToe minigame
        ticTacToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = "Tic Tac Toe es un juego de rompecabezas para dos jugadores, llamados \"X\" y \"O\", que se turnan para marcar los espacios en una cuadrícula de 3 × 3." +
                        "\nGana el jugador que consiga 3 marcas seguidas";
                SetGameDetails("TicTacToe",R.drawable.tictactoe, description, TicTacToe.class);
            }
        });
        // al selecionar lvl 5 te manda a el
        level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetGameDetails("Adivina mi numero",R.drawable.guessmynumber, "Un divertido juego de adivinar mi numero",AdivinaMiNumero.class);
            }
        });
        // al selecionar lvl 6 te manda a el
        level6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetGameDetails("Minesweeper",R.drawable.minesweeper, "xd",Minesweeper.class);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void SetGameDetails(String _gameTitle, int _gameImage, String _gameDescription, Class _game){
        gameTitle.setText(_gameTitle);
        gameImage.setImageResource(_gameImage);
        gameDescription.setText(_gameDescription);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game = new Intent(MainActivity.this,_game);
                startActivity(game);
            }
        });
        gameDetails.setVisibility(View.VISIBLE);
        gameSelection.setVisibility(View.GONE);
    }


}