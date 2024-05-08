package com.example.fixcar20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class UsersQuiz extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView addQuiz;
    onlineQuizAdapter adapter;
    List<QuestionModel> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_quiz);
        recyclerView = findViewById(R.id.recyclerView);
        addQuiz = findViewById(R.id.addQuiz);
        list = new ArrayList<>();
        adapter = new onlineQuizAdapter(list,UsersQuiz.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(UsersQuiz.this));
        recyclerView.setAdapter(adapter);

        loadQuizes();
        addQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UsersQuiz.this,AddQuiz.class));
            }
        });
    }

    private void loadQuizes() {
        CollectionReference reference = FirebaseFirestore.getInstance().collection("Quiz");






        reference.addSnapshotListener((value, error) -> {
            Log.e("reference updated", "reference");
            if (error != null) {
                Log.e("Error:", error.getMessage());
                return;
            }
            if (value == null || value.isEmpty()) {
                return; // Нет данных в снимке, просто выходим
            }

            // Очистить список перед добавлением новых данных
            list.clear();



            for (QueryDocumentSnapshot snapshot : value) {
                if (!snapshot.exists()) {
                    continue;
                }


                QuestionModel model = snapshot.toObject(QuestionModel.class);





                    list.add(new QuestionModel(
                            "",
                            model.getCountryName(),
                            model.getQuestionID(),
                            model.getUsername()
                    ));



            }
            // Обновляем адаптер после того, как все данные добавлены
            adapter.notifyDataSetChanged();


        });
    }
}