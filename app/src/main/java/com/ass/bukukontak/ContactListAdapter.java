package com.ass.bukukontak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    ArrayList<ContactModel> contactModels = new ArrayList<>();
    Context context;

    public ContactListAdapter(ArrayList<ContactModel> contactModels, Context context) {
        this.contactModels = contactModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.ViewHolder holder, int position) {
        holder.textName.setText(contactModels.get(position).getName());
        holder.textNumber.setText(contactModels.get(position).getNumber());
        holder.textEmail.setText(contactModels.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return contactModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textNumber, textEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textNumber = itemView.findViewById(R.id.textNumber);
            textEmail = itemView.findViewById(R.id.textEmail);
        }
    }

}

