package com.example.fireb.userlisting;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.database.sqlite.*;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddUser extends AppCompatActivity{
    private SQLiteHelper myHelper;
    private SQLiteDatabase myDB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        Intent intent = getIntent();
        myHelper = new SQLiteHelper(AddUser.this,"USERDB",null,1);
        myDB = myHelper.getWritableDatabase();
    }

    public void saveUser(View view){
        EditText txtUsername = findViewById(R.id.addUsername);
        EditText txtName = findViewById(R.id.addName);
        EditText txtEmail = findViewById(R.id.addEmail);
        RadioGroup rg = findViewById(R.id.rdoGender);
        RadioButton rbtnGender;
        EditText txtJoinDate = findViewById(R.id.joinDate);
        EditText txtDOB = findViewById(R.id.dateOfBirth);

        String username = txtUsername.getText().toString();
        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        int rgSelectedID = rg.getCheckedRadioButtonId();
        rbtnGender = findViewById(rgSelectedID);
        String gender = rbtnGender.getText().toString();
        char g = gender.charAt(0);
        String joinDate = txtJoinDate.getText().toString();
        String DOB = txtDOB.getText().toString();

        Cursor c = myDB.query("user", new String[] {"username"},"username ='" + username + "'", null,
                null,null,null);
        c.moveToFirst();

        if (!c.isFirst()) {
            ContentValues cv = new ContentValues();
            cv.put("username", username);
            cv.put("name", name);
            cv.put("email", email);
            cv.put("gender", String.valueOf(g));
            cv.put("joinDate", joinDate);
            cv.put("DOB", DOB);
            myDB.insert("user", null, cv);

            Toast.makeText(AddUser.this, "New user added!", Toast.LENGTH_SHORT).show();


            txtUsername.setText(null);
            txtName.setText(null);
            txtEmail.setText(null);
            txtDOB.setText(null);
            txtJoinDate.setText(null);
            rg.clearCheck();
        }else{
            Toast.makeText(AddUser.this, "Username exists!", Toast.LENGTH_SHORT).show();
        }
c.close();


    }



}
