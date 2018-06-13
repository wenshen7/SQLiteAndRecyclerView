package com.example.fireb.userlisting;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {

    private SQLiteDatabase myDB;
    private SQLiteHelper myHelper;
    EditText txtUsername, txtName, txtEmail, txtJoinDate, txtDOB;
    RadioGroup rdgGender;

    protected void onCreate(Bundle savedInstanceSate) {

        super.onCreate(savedInstanceSate);



        setContentView(R.layout.edit_profile);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        txtUsername = findViewById(R.id.eUsername);
        txtName = findViewById(R.id.eName);
        txtEmail = findViewById(R.id.eEmail);
        rdgGender = findViewById(R.id.rdoeGender);
        txtJoinDate = findViewById(R.id.eJoinDate);
        txtDOB = findViewById(R.id.eDOB);

        myHelper = new SQLiteHelper(EditProfile.this, "USERDB", null, 1);
        myDB = myHelper.getWritableDatabase();

        Cursor result = myDB.query("user",new String[] {"username", "name", "email", "gender", "joinDate", "DOB"},
                "username ='" + username + "'", null, null,null,null);

        result.moveToFirst();
        txtUsername.setText(result.getString(0));
        txtName.setText(result.getString(1));
        txtEmail.setText(result.getString(2));
        String gender = result.getString(3);
        txtJoinDate.setText(result.getString(4));
        txtDOB.setText(result.getString(5));

        if(gender.equals("M")) {
            rdgGender.check(R.id.rdoeMale);
        }else if(gender.equals("F")) {
            rdgGender.check(R.id.rdoeFemale);
        }


    }

    public void updateDetail(View view){
        RadioButton rBtn = findViewById(rdgGender.getCheckedRadioButtonId());


        ContentValues cv = new ContentValues();
        //cv.put("username", txtUsername.getText().toString());
        cv.put("name", txtName.getText().toString());
        cv.put("email",txtEmail.getText().toString());
        cv.put("joinDate", txtJoinDate.getText().toString());
        cv.put("DOB", txtDOB.getText().toString());
        cv.put("gender", String.valueOf(rBtn.getText().toString().charAt(0)));
        myDB.update("user",cv, "username ='" + txtUsername.getText() + "'",null);

        Toast.makeText(EditProfile.this,"Changes made!", Toast.LENGTH_SHORT).show();

    }

    public void deteleDetail(View view){
        myDB.delete("user","username ='" + txtUsername.getText() + "'", null);
        //myDB.delete("user","username ='user8'", null);

        Toast.makeText(EditProfile.this,"Deleted!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
