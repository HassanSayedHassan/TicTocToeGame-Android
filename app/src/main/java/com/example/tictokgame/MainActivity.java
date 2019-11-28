package com.example.tictokgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button tappedButton;
    LinearLayout linearLayout;
    TextView winningText;
    int activePlayer = 0;
    int[] fill = {2,2,2,2,2,2,2,2,2};
    int[][] winningLocation = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int count = 0;
    boolean gameOver = false;
    String msg;
    public void gameLogic(View view){
        tappedButton = (Button) view;
        linearLayout = findViewById(R.id.linearLayout);
        winningText = findViewById(R.id.winningText);
        count ++;
        int tappedButtonLocation = Integer.parseInt(tappedButton.getTag().toString());
        if(fill[tappedButtonLocation] == 2 && !gameOver) {
            fill[tappedButtonLocation] = activePlayer;
            if (activePlayer == 0) {
                tappedButton.setBackgroundResource(R.drawable.circle_yellow);
                activePlayer = 1;
            } else {
                tappedButton.setBackgroundResource(R.drawable.circle_yelp);
                activePlayer = 0;
            }
        }

            for (int[] winningPosition : winningLocation) {
                if (fill[winningPosition[0]] == fill[winningPosition[1]] && fill[winningPosition[1]] == fill[winningPosition[2]]
                        && fill[winningPosition[0]] != 2) {
                    if (activePlayer == 0) {
                        msg = "Red is winner";
//                    Toast.makeText(getApplicationContext(), "Red is the gameOver", Toast.LENGTH_LONG).show();
                    } else if (activePlayer == 1) {
                        msg = "Yellow is winner";
//                    Toast.makeText(getApplicationContext(),"Yellow is the gameOver",Toast.LENGTH_LONG).show();
                    }
                    gameOver = true;
                    linearLayout.setVisibility(view.VISIBLE);
                    winningText.setText(msg);
                }
                if (count == 9 && gameOver == false) {
                    msg = "Game is Draw";
                    linearLayout.setVisibility(view.VISIBLE);
                    winningText.setText(msg);
                }
            }
    }

    public void playAgain(View view){
        gameOver = false;

        count = 0;

        msg = "";

        activePlayer = 0;

        linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setVisibility(view.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount();i++){
            ((Button) gridLayout.getChildAt(i)).setBackgroundResource(android.R.drawable.btn_default);
        }

        for (int i= 0; i<fill.length; i++){
            fill[i] = 2;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
