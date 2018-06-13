package com.example.fireb.userlisting;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private SQLiteDatabase myDB;
    private SQLiteHelper myHelper;
    boolean isScrolling = false;
    int currentItem, totalItem, scrollOutItem, total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        userAdapter = new UserAdapter(userList);
        final LinearLayoutManager uLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(uLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);
        myHelper = new SQLiteHelper(MainActivity.this, "USERDB", null, 1);
        myDB = myHelper.getWritableDatabase();

        testData(0);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { //execute during scroll the list
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem = uLayoutManager.getChildCount();
                totalItem = uLayoutManager.getItemCount();
                scrollOutItem = uLayoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItem + scrollOutItem == totalItem)) {
                    isScrolling = false;
                    testData(totalItem);
                    userAdapter.notifyDataSetChanged();
                }
            }
        });


    }

    private void testData(int i) { // get data from database
        Cursor result = myDB.query("user", null, null, null, null, null, null);
        try {

            for (int j = i; j < i + 7; j++) {
                result.moveToPosition(j);
                String username = result.getString(0);
                String name = result.getString(1);
                String email = result.getString(2);
                char gender = result.getString(3).charAt(0);
                String joinDate = result.getString(4);
                String DOB = result.getString(5);

                User user = new User(username, name, email, gender, joinDate, DOB);
                userList.add(user);


            }
        } catch (Exception e) {
            Toast.makeText(MainActivity.this,"No more user!", Toast.LENGTH_SHORT).show();
        }
result.close();

    }

    public void addNewUser(View view) {
        Intent intent = new Intent(this, AddUser.class);
        startActivity(intent);
    }
}
