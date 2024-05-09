package com.example.fixcar20;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.UserHolder> {
    int number1 = 0;
    private List<UserModel> list;
    Context context;

    public adapter(List<UserModel> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_item, parent, false);
        return new UserHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull adapter.UserHolder holder, int position) {
        // Сортируем список по балансу (от большего к меньшему)

        Collections.sort(list, new Comparator<UserModel>() {
            @Override
            public int compare(UserModel user1, UserModel user2) {
                // Сравниваем балансы в обратном порядке (от большего к меньшему)
                return Double.compare(user2.getBal(), user1.getBal());
            }
        });

        // Устанавливаем данные в элементы ViewHolder
        holder.name.setText(list.get(position).getName());
        holder.bal.setText("" + list.get(position).getBal());
        holder.number.setText(Integer.toString(position + 1) + ")");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class UserHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView bal;
        private TextView uzor;
        private TextView number;


        public UserHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name);
            bal = itemView.findViewById(R.id.bal);
            number = itemView.findViewById(R.id.number);



        }

    }

}