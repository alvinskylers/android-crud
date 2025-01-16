package com.ass.bukukontak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    EditText formName, formNumber, formEmail;
    Button submitButton;
    FloatingActionButton contactListButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        formName = findViewById(R.id.form_name);
        formNumber =  findViewById(R.id.form_phone);
        formEmail = findViewById(R.id.form_mail);
        submitButton = findViewById(R.id.form_submit);
        contactListButton = findViewById(R.id.contact_list_button);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ContactHelper contactHelper = new ContactHelper(MainActivity.this);

        contactListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ContactListActivity.class));
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (formEmail.length() > 0 && formEmail.length() > 0 && formNumber.length() > 0) {
                    contactHelper.insertData(formName.getText().toString(), formNumber.getText().toString(), formEmail.getText().toString() );
                    formName.setText("");
                    formNumber.setText("");
                    formEmail.setText("");
                    Toast.makeText(MainActivity.this,"Kontak berhasil disimpan!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Field data kontak masih kosong!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}