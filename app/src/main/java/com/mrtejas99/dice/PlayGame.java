package com.mrtejas99.dice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
    TextView txt_turn, txt_greet, txt_prompt, txt_count;
    TextView txt_player1, txt_player2;
    Boolean singleMode;
    RotateAnimation animate;
    Random gen;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        txt_turn = findViewById(R.id.txt_turn);
        final Integer[] imageIds = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six};
        final Integer[] blkDice = {R.drawable.blkone, R.drawable.blktwo, R.drawable.blkthree, R.drawable.blkfour, R.drawable.blkfive, R.drawable.blksix};
        txt_player1 = findViewById(R.id.txt_player1);
        txt_player2 = findViewById(R.id.txt_player2);
        txt_greet = findViewById(R.id.txt_greet);
        txt_count = findViewById(R.id.txt_count);
        txt_prompt = findViewById(R.id.txt_prompt);
        txt_prompt.setVisibility(View.INVISIBLE);
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
                txt_greet.setVisibility(View.INVISIBLE);
                if (count > 0) {
                    animate = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animate.setInterpolator(new LinearInterpolator());
                    animate.setRepeatCount(2);
                    animate.setDuration(100);
                    dice.startAnimation(animate);
                    if (player == 1) {
                        player = 0;
                        gen = new Random();
                        Integer tmp_score1 = gen.nextInt(imageIds.length) + 1;
                        Integer randomImg = imageIds[tmp_score1 - 1];
                        dice.setImageResource(randomImg);
                        score1 += tmp_score1;
                        txt_player1.setText(player1 + " : " + score1.toString());
                        txt_turn.setText(player2 + "'s turn");
                        if (singleMode) {
                            txt_prompt.setVisibility(View.VISIBLE);
                            txt_prompt.setText("Please wait...");
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    player = 1;
                                    gen = new Random();
                                    Integer tmp_score2 = gen.nextInt(blkDice.length) + 1;
                                    Integer randomImg = blkDice[tmp_score2 - 1];
                                    dice.startAnimation(animate);
                                    dice.setImageResource(randomImg);
                                    score2 += tmp_score2;
                                    txt_player2.setText(player2 + " : " + score2.toString());
                                    txt_turn.setText(player1 + "'s turn");
                                    txt_prompt.setVisibility(View.INVISIBLE);
                                }
                            }, 1500);
                        }
                    } else {
                        player = 1;
                        Random gen = new Random();
                        Integer tmp_score2 = gen.nextInt(blkDice.length) + 1;
                        Integer randomImg = blkDice[tmp_score2 - 1];
                        dice.setImageResource(randomImg);
                        score2 += tmp_score2;
                        txt_player2.setText(player2 + " : " + score2.toString());
                        txt_turn.setText(player1 + "'s turn");
                    }
                    count -= 1;
                    txt_count.setText(count.toString() + " moves remaining");
                    Toast.makeText(getApplicationContext(), count.toString() + " moves remaining", Toast.LENGTH_LONG);
                } else {
                    txt_count.setVisibility(View.INVISIBLE);
                    txt_turn.setVisibility(View.INVISIBLE);
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
        singleMode = bundle.getBoolean("singleMode");

        if (player1 == player2) {
            player1 = "Human";
            player2 = "Computer";
        }

        txt_player1.setText(player1);
        txt_player2.setText(player2);
    }
}