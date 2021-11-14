package com.example.hilo;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

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

    //private TextView triesOutput; //PLANNED FUNCTIONALITY FOR GUI COUNTER
    private int tries = 0;

    public void checkGuess() {
        String guessText = txtGuess.getText().toString();
        String message1 = "";
        String message2 = "You Win!! It only took you "+tries+" tries!!";49

        try {
            int theGuess = Integer.parseInt(guessText);
            tries++;
            if (theGuess < theNumber) {
                message1 = theGuess + " is too low. Try again.";
            } else if (theGuess > theNumber) {
                message1 = theGuess + " is too high. Try again.";
            } else {
                message1 = theGuess + " is correct! You win! Let's play again!";
                Toast.makeText(MainActivity.this, message2, Toast.LENGTH_LONG).show();
                newGame(this.theScore);
            }
        } catch (Exception e) {
            message1 = "ERROR: Enter a whole number between 1 and 100";
        } finally {
            lblOutput.setText(message1);
            //triesOutput.setText(Integer.toString(tries));
            txtGuess.setText("");
        }
    }

    public void newGame(int theScore) {
        this.theScore = theScore;
        Random rand = new Random();
        theNumber = rand.nextInt(101);
    }

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}