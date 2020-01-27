package com.example.fourthtry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class findItem extends AppCompatActivity {

    private Button send; // Found an item screen saying "Add and item to the lost and found"
    private EditText name, description;

    FirebaseFirestore db;
    CollectionReference itemsCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_item);

        //When you click on addItem, sends data to Firebase

//        databaseReference = FirebaseDatabase.getInstance().getReference("items");

        db = FirebaseFirestore.getInstance();
        itemsCollection = db.collection("items");

        name = (EditText) findViewById(R.id.enterName);
        description = (EditText) findViewById(R.id.enterDescription);

        send = (Button) findViewById(R.id.addItem);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItems();
                openSearchItem();
            }
        });

    }

    public void openSearchItem() {
        Intent intent = new Intent(this, searchItem.class);
        startActivity(intent);
    }

    public void addItems() {

        String itemName = name.getText().toString();
        String itemDescription = description.getText().toString();

        if (!TextUtils.isEmpty(itemName) && !TextUtils.isEmpty(itemDescription)) {

            itemsCollection.add(new Information(itemName, itemDescription))
                    .addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            System.out.println("canceled");
                        }
                    })
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            System.out.println("Worked!");
                        }
                    })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("error");
                        System.out.println(e.toString());
                    }
                });
        }

        else {
            Toast.makeText(findItem.this, "Please Type The Item's Name or Description", Toast.LENGTH_LONG).show();
        }
    }
}
