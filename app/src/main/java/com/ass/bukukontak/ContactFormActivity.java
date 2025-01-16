package com.ass.bukukontak;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactFormActivity extends AppCompatActivity {

    EditText editName, editNumber, editEmail;
    Button saveEdit, delete;
    ContactHelper contactHelper =new ContactHelper(ContactFormActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_form);

        editName = findViewById(R.id.form_name_edit);
        editNumber = findViewById(R.id.form_phone_edit);
        editEmail = findViewById(R.id.form_mail_edit);
        saveEdit = findViewById(R.id.form_submit_edit);
        delete = findViewById(R.id.form_delete);

        String name = getIntent().getStringExtra("name");
        String number = getIntent().getStringExtra("number");
        String email = getIntent().getStringExtra("email");
        int id = getIntent().getIntExtra("id",0);

        editName.setText(name);
        editNumber.setText(number);
        editEmail.setText(email);


        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactHelper.updateData(
                        String.valueOf(id),
                        editName.getText().toString(),
                        editNumber.getText().toString(),
                        editEmail.getText().toString()
                );
                Toast.makeText(ContactFormActivity.this, "Perubahan berhasil disimpan", Toast.LENGTH_SHORT).show();
                finishActivity(1);
                startActivity(new Intent(ContactFormActivity.this, ContactListActivity.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ContactFormActivity.this)
                        .setTitle("Konfirmasi Tindakan")
                        .setMessage("Apakah anda yakin untuk menghapus kontak ini?")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                contactHelper.deleteData(String.valueOf(id));
                                dialogInterface.dismiss();
                                Toast.makeText(ContactFormActivity.this, "Kontak telah dihapus", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ContactFormActivity.this, ContactListActivity.class));
                            }
                        })
                        .setNegativeButton("tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setIcon(R.drawable.icon_delete)
                        .show();
            }
        });

    }


}