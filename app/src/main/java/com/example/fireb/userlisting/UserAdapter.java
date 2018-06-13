package com.example.fireb.userlisting;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> userList;

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        final User user = userList.get(position);
        holder.username.setText(user.getUsername());
        holder.Name.setText(user.getName());
        holder.email.setText(user.getEmail());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), EditProfile.class);
                intent.putExtra("username", user.getUsername());
                v.getContext().startActivity(intent);


              //  Toast.makeText(v.getContext(), user.getUsername(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username, Name, email;
        public LinearLayout linearLayout;



        ViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            Name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            linearLayout = itemView.findViewById(R.id.linerLayout);
        }
    }

    UserAdapter(List<User> usersList){
        this.userList = usersList;
    }
}
