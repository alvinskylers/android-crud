package com.ass.bukukontak;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ContactListActivity extends AppCompatActivity {

    RecyclerView contactsRecycler;
    ArrayList<ContactModel> arraylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_list);

        contactsRecycler = findViewById(R.id.contacts_recycler);
        contactsRecycler.setLayoutManager(new LinearLayoutManager(this));
        ContactHelper contactHelper = new ContactHelper(this);
        Cursor cursor = contactHelper.showData();

        while (cursor.moveToNext()) {
            arraylist.add(new ContactModel(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(0)));
        }

        ContactListAdapter adapter = new ContactListAdapter(arraylist, this);
        contactsRecycler.setAdapter(adapter);
    }


}