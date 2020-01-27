package com.example.fourthtry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button findItem; //1st screen saying "I found an item"
    private Button searchItem; //1st screen saying "I'm looking for an item"

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //When you click on findItem, takes you to findItem screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findItem = (Button) findViewById(R.id.findItem);
        findItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFindItem();
            }
        });

        //When you click on searchItem, takes you directly to the searchItem screen
        searchItem = (Button) findViewById(R.id.searchItem);
        searchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                openSearchItem();
            }
        });

    }
    public void openFindItem() {
        Intent intent = new Intent(this, findItem.class);
        startActivity(intent);
    }

    public void openSearchItem() {
        Intent intent = new Intent(this, searchItem.class);
        startActivity(intent);
    }

}

