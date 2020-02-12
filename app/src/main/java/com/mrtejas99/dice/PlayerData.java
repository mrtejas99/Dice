package com.mrtejas99.dice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerData extends AppCompatActivity {
    private EditText edit_player1, edit_player2;
    private TextView txt_selectedMoves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_data);

        Button btn_start = findViewById(R.id.btn_start);
        txt_selectedMoves = findViewById(R.id.txt_selectedMoves);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPlayGame = new Intent(PlayerData.this, PlayGame.class);
                //receive intent
                Intent getMainActivity = getIntent();
                Bundle bundle = getMainActivity.getBundleExtra("bundle");
                String moves = bundle.getString("moves");
                txt_selectedMoves.setText("you have selected " + moves);

                //send intent
                edit_player1 = findViewById(R.id.edit_player1);
                edit_player2 = findViewById(R.id.edit_player2);
                bundle.putString("player1", edit_player1.getText().toString());
                bundle.putString("player2", edit_player2.getText().toString());
                toPlayGame.putExtra("bundle", bundle);
                startActivity(toPlayGame);
            }
        });
    }
}
