package com.ass.bukukontak;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ContactListActivity extends AppCompatActivity {

    RecyclerView contactsRecycler;
    ArrayList<ContactModel> arraylist = new ArrayList<>();
    EditText searchBar;
    ContactHelper contactHelper = new ContactHelper(this);
    ContactListAdapter adapter;
    FloatingActionButton addContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_list);
        searchBar = findViewById(R.id.search_bar);
        contactsRecycler = findViewById(R.id.contacts_recycler);
        addContacts = findViewById(R.id.add_contact);
        contactsRecycler.setLayoutManager(new LinearLayoutManager(this));

        addContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        loadData("");
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String key = searchBar.getText().toString();
                loadData(key);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    public void loadData(String key) {
        arraylist.clear();
        Cursor cursor;

        if (key.isEmpty()) {
            cursor = contactHelper.showData();
        } else {
            cursor = contactHelper.searchData(key);
        }

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String number = cursor.getString(2);
                String email = cursor.getString(3);
                arraylist.add(new ContactModel(name, number, email, id));
                adapter = new ContactListAdapter(arraylist, ContactListActivity.this);
                contactsRecycler.setAdapter(adapter);
            }
            cursor.close();
        }

    }


}