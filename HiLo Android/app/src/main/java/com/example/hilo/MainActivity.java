package com.example.hilo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hilo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText txtGuess;
    private Button btnGuess;
    private TextView lblOutput;
    private int theNumber;
    private int theScore;
    private String difficulty = "Normal";
    private int tries = 0;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    /*PLANNED FUNCTIONALITY FOR GUI COUNTER
    private TextView triesOutput; */

    public void checkGuess() {
        String guessText = txtGuess.getText().toString();
        String message = "";
        String winMessage = "You Win!! It only took you "+tries+" tries!!";

        try {
            int theGuess = Integer.parseInt(guessText);
            tries++;
            if (theGuess < theNumber) {
                message = theGuess + " is too low. Try again.";
            } else if (theGuess > theNumber) {
                message = theGuess + " is too high. Try again.";
            } else {
                message = theGuess + " is correct! You win! Let's play again!";
                //Toast.makeText(MainActivity.this, winMessage, Toast.LENGTH_LONG).show();
                //newGame(this.theScore);
                final CharSequence[] items = {"Yes!", "No."};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Play again?");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                newGame(theScore);
                                break;
                            case 1:
                                break;
                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        } catch (Exception e) {
            switch (this.difficulty) {
                case "Easy":
                    message = "ERROR: Enter a whole number between 1 and 10";
                    break;
                case "Hard":
                    message = "ERROR: Enter a whole number between 1 and 1,000";
                    break;
                default:
                    message = "ERROR: Enter a whole number between 1 and 100";
            }
        } finally {
            lblOutput.setText(message);
            txtGuess.setText("");
            //triesOutput.setText(Integer.toString(tries));
        }
    }

    /*
    ALLOWS FOR NEW GAMES TO CONTINUE IN THE SAME DIFFICULTY THE PREVIOUS GAME WAS
    PASSES ALONG TO THE OVERLOADED METHOD THAT ACTUALLY STARTS THE NEW GAME
    */
    public void newGame(int theScore) {
        String newGameDiff;
        switch (this.difficulty) {
            case "Easy":
                newGameDiff = "Easy";
                break;
            case "Hard":
                newGameDiff = "Hard";
                break;
            default:
                newGameDiff = "Normal";
        }
        newGame(theScore, newGameDiff);
    }

    //OVERLOADED METHOD - SETS THE DEFAULT SCORE AND THE DIFFICULTY LEVEL
    public void newGame(int theScore, String difficulty) {
        this.theScore = theScore;
        tries = 0;
        Random rand = new Random();
        switch (difficulty){
            case "Easy":
                this.difficulty = "Easy";
                theNumber = rand.nextInt(11);
                txtGuess.setText("5");
                txtGuess.requestFocus();
                txtGuess.selectAll();
                break;
            case "Hard":
                this.difficulty = "Hard";
                theNumber = rand.nextInt(1001);
                txtGuess.setText("500");
                txtGuess.requestFocus();
                txtGuess.selectAll();
                break;
            default:
                this.difficulty = "Normal";
                theNumber = rand.nextInt(101);
                txtGuess.setText("50");
                txtGuess.requestFocus();
                txtGuess.selectAll();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        txtGuess = findViewById(R.id.txtGuess);
        btnGuess = findViewById(R.id.btnGuess);
        lblOutput = findViewById(R.id.lblOutput);

        //STARTS THE FIRST GAME
        newGame(theScore);

        //ADDS A LISTENER FOR THE GUI BUTTON
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        //ADDS A LISTENER FOR THE ENTER KEY -- BUG: THIS ALSO WORKS FOR THE MOUSE
        txtGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //This switch statement determines which item the user selected
        switch(item.getItemId()){
            case R.id.action_settings:
                final CharSequence[] items = {"Easy (1-10)", "Normal (1-100)", "Hard (1-1,000)"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select the Difficulty");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                newGame(theScore, "Easy");
                                break;
                            case 1:
                                newGame(theScore, "Normal");
                                break;
                            case 2:
                                newGame(theScore, "Hard");
                                break;
                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            case R.id.action_newgame:
                newGame(this.theScore);
                return true;
            case R.id.action_gamestats:
                return true;
            case R.id.action_about:
                AlertDialog aboutDialog = new AlertDialog.Builder(MainActivity.this).create();
                aboutDialog.setTitle("About Hi-Lo Guessing Game");
                aboutDialog.setMessage("\u00A9 2021 Austin Swack");
                aboutDialog.setMessage("All Rights Reserved");
                aboutDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }
                });
                aboutDialog.show();
                return true;
            case R.id.action_resetscore:
                this.theScore = 0;
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}