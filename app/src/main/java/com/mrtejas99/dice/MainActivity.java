package com.mrtejas99.dice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_single = findViewById(R.id.btn_single);
        btn_single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPlayGame = new Intent(MainActivity.this, PlayGame.class);
                EditText edit_moves = findViewById(R.id.edit_moves);
                Bundle bundle = new Bundle();
                String moves = edit_moves.getText().toString();
                if (moves.matches("")) {
                    Toast.makeText(getApplicationContext(), "Enter number of moves", Toast.LENGTH_LONG).show();
                    return;
                }
                bundle.putString("moves", moves);
                bundle.putBoolean("singleMode", true);
                toPlayGame.putExtra("bundle", bundle);
                startActivity(toPlayGame);
            }
        });

        Button btn_multi = findViewById(R.id.btn_multi);
        btn_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPlayerData = new Intent(MainActivity.this, PlayerData.class);
                EditText edit_moves = findViewById(R.id.edit_moves);
                Bundle bundle = new Bundle();
                String moves = edit_moves.getText().toString();
                if (moves.matches("")) {
                    Toast.makeText(getApplicationContext(), "Enter number of moves", Toast.LENGTH_LONG).show();
                    return;
                }
                bundle.putString("moves", moves);
                bundle.putBoolean("singleMode", false);
                toPlayerData.putExtra("bundle", bundle);
                startActivity(toPlayerData);
            }
        });
    }
}
