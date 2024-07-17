package com.example.gamegallery;

import android.content.Intent;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class Minesweeper extends AppCompatActivity {

    private static final int ROWS = 8;
    private static final int COLS = 8;
    private static final int MINES = (ROWS * COLS) / 4;
    private boolean[][] mineField;
    private Button[][] buttons;

    private RelativeLayout menu;
    Button button_menu;
    Button button_continue;
    Button button_restart;
    Button button_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper);

        mineField = new boolean[ROWS][COLS];
        buttons = new Button[ROWS][COLS];

        setupGrid();
        generateMines();
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
                Intent load = new Intent(Minesweeper.this, MainActivity.class);
                startActivity(load);
            }
        });
    }

    private void generateMines() {
        Random random = new Random();
        int placedMines = 0;

        while (placedMines < MINES) {
            int r = random.nextInt(ROWS);
            int c = random.nextInt(COLS);

            if (!mineField[r][c]) {
                mineField[r][c] = true;
                placedMines++;
            }
        }
    }

    private void setupGrid() {
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.removeAllViews();

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                buttons[r][c] = new Button(this);
                final int row = r;
                final int col = c;

                buttons[r][c].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mineField[row][col]) {
                            // Game over
                            Toast.makeText(Minesweeper.this, "Game Over", Toast.LENGTH_SHORT).show();
                            revealMines();
                            button_menu.setVisibility((View.GONE));
                            menu.setVisibility(View.VISIBLE);
                        } else {
                            // Safe square
                            int adjacentMines = countAdjacentMines(row, col);
                            buttons[row][col].setText(String.valueOf(adjacentMines));
                            buttons[row][col].setEnabled(false);
                        }
                    }
                });

                gridLayout.addView(buttons[r][c], new GridLayout.LayoutParams(
                        GridLayout.spec(r), GridLayout.spec(c)
                ));
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (row + i >= 0 && row + i < ROWS && col + j >= 0 && col + j < COLS) {
                    if (mineField[row + i][col + j]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private void revealMines() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (mineField[r][c]) {
                    buttons[r][c].setText("\uD83D\uDCA3");
                    buttons[r][c].setEnabled(false);
                }
            }
        }
    }

    private void resetGame() {
        mineField = new boolean[ROWS][COLS];
        setupGrid();
        generateMines();
    }
}
