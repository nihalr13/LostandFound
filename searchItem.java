package com.example.fourthtry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
//import android.widget.AdapterView.OnItemClickListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class searchItem extends AppCompatActivity {

    private List<Information> myArrayList = new ArrayList<>();
    private List<String> nameArray = new ArrayList<>();
    private List<String> descriptionArray = new ArrayList<>();

    private ListView myListView;
    private FirebaseFirestore myFirestore;

   // private EditText editText = (EditText) findViewById(R.id.showDescription);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        myFirestore = FirebaseFirestore.getInstance();
        myFirestore.collection("items").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && task.getResult().getDocuments() != null) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Debug:", document.getId() + " => " + document.getData());
                                myArrayList.add(new Information((String)document.get("itemName"), (String)document.get("itemDescription")));
                            }
                        }

                        for (int i = 0; i < myArrayList.size(); i++) {
                            nameArray.add(myArrayList.get(i).getItemName());
                        }

                        for (int i = 0; i < myArrayList.size(); i++) {
                            descriptionArray.add(myArrayList.get(i).getItemDescription());
                        }

                        myListView = (ListView) findViewById(R.id.list);
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,nameArray);
                        myListView.setAdapter(arrayAdapter);

//                        myListView.setOnItemClickListener(new OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                editText.setText(descriptionArray.get(position));
//                            }
//                        });
                    }
                });




    }
}
