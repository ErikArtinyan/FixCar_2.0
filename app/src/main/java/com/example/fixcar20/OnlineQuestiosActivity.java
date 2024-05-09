package com.example.fixcar20;

import static com.example.fixcar20.QuestionAdapter.context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class OnlineQuestiosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;
    private List<QuestionModel> list;
    OnlineQuestionModel models = new OnlineQuestionModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_questios);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleview); // Assigning recyclerView
        ///////
        questionAdapter = new QuestionAdapter(list, OnlineQuestiosActivity.this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(OnlineQuestiosActivity.this));
        recyclerView.setAdapter(questionAdapter);

        questionAdapter.OnPressed(new QuestionAdapter.OnPressed() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onanswer1(int position, String answer1, String answer) {
                if (answer1.equals(answer)) {

                    UserModel.baler(context);

                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ правелен!",
                            "Молодец!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
                    if(list.size() == 1){
                        startActivity(new Intent(OnlineQuestiosActivity.this, Main_Menu.class));

                    }else{
                        list.remove(position);
                        questionAdapter.notifyDataSetChanged();
                    }


                } else {
                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ неправильный!",
                            "подумай еще",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
//

                }
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onanswer2(int position, String answer2, String answer) {
                if (answer2.equals(answer)) {

                    UserModel.baler(context);

                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ правелен!",
                            "Молодец!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));

                    if (list.size() == 1) {
                        startActivity(new Intent(OnlineQuestiosActivity.this, Main_Menu.class));
                    } else {
                        list.remove(position);
                    }
                    questionAdapter.notifyDataSetChanged();

                } else {
                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ неправильный!",
                            "подумай еще",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
//

                }

            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onanswer3(int position, String answer3, String answer) {
                if (answer3.equals(answer)) {


                    UserModel.baler(context);

                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ правелен!",
                            "Молодец!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));

                    if (list.size() == 1) {
                        startActivity(new Intent(OnlineQuestiosActivity.this, Main_Menu.class));
                    } else {
                        list.remove(position);
                    }
                    questionAdapter.notifyDataSetChanged();
                } else {
                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ неправильный!",
                            "подумай еще",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));


                }

            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onanswer4(int position, String answer4, String answer) {
                if (answer4.equals(answer)) {
                    UserModel.baler(context);

                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ правелен!",
                            "Молодец!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));


                    if (list.size() == 1) {
                        startActivity(new Intent(OnlineQuestiosActivity.this, Main_Menu.class));
                    } else {
                        list.remove(position);
                    }
                    questionAdapter.notifyDataSetChanged();
                } else {
                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ неправильный!",
                            "подумай еще",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
//

                }

            }
        });


        loadData();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadData() {
        String id = getIntent().getStringExtra("id");
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        DocumentReference reference = FirebaseFirestore.getInstance().collection("Quiz").document(id);

        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                if (task.isSuccessful()) {
                    models = task.getResult().toObject(OnlineQuestionModel.class);
                    String username = (String) task.getResult().get("username");
                    Toast.makeText(OnlineQuestiosActivity.this, username, Toast.LENGTH_SHORT).show();

                    if (models != null) {
                        for (int i = 0; i < models.questions.size(); i++) {
                            Toast.makeText(OnlineQuestiosActivity.this, "+1", Toast.LENGTH_SHORT).show();
                            QuestionModel model = new QuestionModel(models.getCorrectAnswers().get(i), models.getOnlineAnswers1().get(i),
                                    models.getOnlineAnswers2().get(i), models.getOnlineAnswers3().get(i), models.getOnlineAnswers4().get(i),
                                    "", models.getQuestions().get(i), models.getQuestionID());
                            if(model == null){
                                Toast.makeText(OnlineQuestiosActivity.this, "null", Toast.LENGTH_SHORT).show();
                            }
                            list.add(model);

                        }
                      if(list != null && !list.isEmpty()){
                          questionAdapter.notifyDataSetChanged();
                      }


                    } else {
                        Toast.makeText(OnlineQuestiosActivity.this, "null", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }
}
