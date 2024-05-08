package com.example.fixcar20;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class onlineQuizAdapter extends RecyclerView.Adapter<onlineQuizAdapter.onlineQuizHolder> {
    List<QuestionModel> list;
    Context context;

    public onlineQuizAdapter(List<QuestionModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public onlineQuizAdapter.onlineQuizHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.online_question_item, parent, false);
        return new onlineQuizHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull onlineQuizAdapter.onlineQuizHolder holder, int position) {
        holder.quizName.setText(list.get(position).getCountryName());
        holder.name.setText(list.get(position).getUsername());

        holder.quizItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,OnlineQuestiosActivity.class);
                intent.putExtra("id",list.get(position).getQuestionID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class onlineQuizHolder extends RecyclerView.ViewHolder{
        TextView name,quizName;
        RelativeLayout quizItem;

        public onlineQuizHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.username);
            quizName = itemView.findViewById(R.id.quizName);
            quizItem = itemView.findViewById(R.id.quizItem);
        }
    }

}
