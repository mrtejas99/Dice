package com.mrtejas99.dice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PlayGame extends AppCompatActivity {
    String moves = "", player1, player2;
    Integer player = 1, count, score1 = 0, score2 = 0;
    TextView txt_moves;
    TextView txt_player1, txt_player2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        txt_moves = findViewById(R.id.txt_moves);
        final Integer[] imageIds = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six};
        txt_player1 = findViewById(R.id.txt_player1);
        txt_player2 = findViewById(R.id.txt_player2);
        Button btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMAinActivity = new Intent(PlayGame.this, MainActivity.class);
                startActivity(toMAinActivity);
            }
        });

        final ImageButton dice = findViewById(R.id.dice);
        dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 0) {
                    RotateAnimation animate = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animate.setInterpolator(new LinearInterpolator());
                    animate.setRepeatCount(2);
                    animate.setDuration(100);
                    dice.startAnimation(animate);
                    if (player == 1) {
                        player = 0;
                        Random gen = new Random();
                        Integer tmp_score1 = gen.nextInt(imageIds.length) + 1;
                        Integer randomImg = imageIds[tmp_score1 - 1];
                        dice.setImageResource(randomImg);
                        score1 += tmp_score1;
                        txt_player1.setText(player1 + " : " + score1.toString());

                    } else {
                        player = 1;
                        Random gen = new Random();
                        Integer tmp_score2 = gen.nextInt(imageIds.length) + 1;
                        Integer randomImg = imageIds[tmp_score2 - 1];
                        dice.setImageResource(randomImg);
                        score2 += tmp_score2;
                        txt_player2.setText(player2 + " : " + score2.toString());
                    }
                    count -= 1;
                    txt_moves.setText(count.toString() + " moves remaining");
                } else {
                    if (score2 > score1)
                        Toast.makeText(getApplicationContext(), player2 + " is the winner", Toast.LENGTH_LONG).show();
                    else if (score1 > score2)
                        Toast.makeText(getApplicationContext(), player1 + " is the winner", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "It's a draw", Toast.LENGTH_LONG).show();
                }
            }
        });

        Intent getStats = getIntent();
        Bundle bundle = getStats.getBundleExtra("bundle");
        moves = bundle.getString("moves");
        count = Integer.parseInt(moves);

        player1 = bundle.getString("player1");
        player2 = bundle.getString("player2");
        if (player1 == player2) {
            player1 = "Computer";
            player2 = "Human";
        }

        txt_player1.setText(player1);
        txt_player2.setText(player2);
    }
}