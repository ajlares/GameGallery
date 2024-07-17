package com.example.gamegallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TicTacToe extends AppCompatActivity {

    private Button[] buttons;
    private boolean playerX = true; // True for player X, false for player O
    private int[][] board;
    private int moves;
    private RelativeLayout menu;
    Button button_menu;
    Button button_continue;
    Button button_restart;
    Button button_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tit_tac_toe);

        buttons = new Button[9];
        board = new int[3][3];
        moves = 0;

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            final int index = i;
            buttons[i] = (Button) gridLayout.getChildAt(i);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClick(index);
                }
            });
        }
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
                Intent load = new Intent(TicTacToe.this, MainActivity.class);
                startActivity(load);
            }
        });

        resetGame();
    }

    private void onButtonClick(int index) {
        if (buttons[index].getText().toString().isEmpty()) {
            buttons[index].setText(playerX ? "X" : "O");
            board[index / 3][index % 3] = playerX ? 1 : 2;
            moves++;

            if (checkWin()) {
                Toast.makeText(this, "Player " + (playerX ? "X" : "O") + " wins!", Toast.LENGTH_LONG).show();
                disableButtons();
                button_menu.setVisibility((View.GONE));
                menu.setVisibility(View.VISIBLE);
            } else if (moves == 9) {
                Toast.makeText(this, "It's a draw!", Toast.LENGTH_LONG).show();
                button_menu.setVisibility((View.GONE));
                menu.setVisibility(View.VISIBLE);
            }

            playerX = !playerX;
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }

        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }

        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }

    private void disableButtons() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }

        moves = 0;
        playerX = true;
    }
}
