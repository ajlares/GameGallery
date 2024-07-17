package com.example.gamegallery;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        setContentView(gameView);
    }

    public void winGame() {
        Intent intent = new Intent(this, WinActivity.class);
        startActivity(intent);
        finish();
    }
}
