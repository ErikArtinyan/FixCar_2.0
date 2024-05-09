package com.example.fixcar20;

import static com.example.fixcar20.QuestionAdapter.context;
import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class Questions_View_Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;
    private List<QuestionModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_view);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleview); // Assigning recyclerView
        ///////
        questionAdapter = new QuestionAdapter(list,Questions_View_Activity.this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Questions_View_Activity.this));
        recyclerView.setAdapter(questionAdapter);

        questionAdapter.OnPressed(new QuestionAdapter.OnPressed() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onanswer1(int position, String answer1, String answer) {
                if(answer1.equals(answer)){

                    UserModel.baler(context);

                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ правелен!",
                            "Молодец!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));

                    if(list.size() == 1){
                        startActivity(new Intent(Questions_View_Activity.this, Main_Menu.class));

                    }else{
                        list.remove(position);
                        questionAdapter.notifyDataSetChanged();
                    }


                }
                else {
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
                if(answer2.equals(answer)){

                    UserModel.baler(context);

                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ правелен!",
                            "Молодец!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));

                    if(list.size() == 1){
                        startActivity(new Intent(Questions_View_Activity.this,Main_Menu.class));
                    }else {
                        list.remove(position);
                    }
                    questionAdapter.notifyDataSetChanged();

                }  else {
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
                if(answer3.equals(answer)){


                    UserModel.baler(context);

                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ правелен!",
                            "Молодец!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));

                    if(list.size() == 1){
                        startActivity(new Intent(Questions_View_Activity.this,Main_Menu.class));
                    }else {
                        list.remove(position);
                    }
                    questionAdapter.notifyDataSetChanged();
                }  else {
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
            public void onanswer4(int position, String answer4, String answer) {
                if(answer4.equals(answer)){
                    UserModel.baler(context);

                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ правелен!",
                            "Молодец!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));



                    if(list.size() == 1){
                        startActivity(new Intent(Questions_View_Activity.this,Main_Menu.class));
                    }else {
                        list.remove(position);
                    }
                    questionAdapter.notifyDataSetChanged();
                }  else {
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
        list.add(new QuestionModel(
                "Ереван",
                "Ванадзор",
                "Тбилиси",
                "Гюмри",
                "Ереван",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%B5%D1%80%D0%B5%D0%B2%D0%B0%D0%BD.jpg?alt=media&token=0b5f8896-eb13-4ed3-99d4-a62a59965147",
                "Армения",
                "215ef5D3215"
        ));
        list.add(new QuestionModel(
                "Абу-Даби",
                "Дубай",
                "Абу-Даби",
                "Стамбул",
                "Шарджа",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/abu%20dhabi.jpg?alt=media&token=8a59dd9c-6890-4cb5-a87b-98b118ebf719",
                "ОАЭ",
                "65ea156576"
        ));
        list.add(new QuestionModel(
                "Тегеран",
                "Мегри",
                "Баку",
                "Гори",
                "Тегеран",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%98%D1%80%D0%B0%D0%BD.jpg?alt=media&token=f8d428c8-239e-4d72-ab60-625b6e2bad01",
                "Иран",
                "21231lp897"
        ));
        list.add(new QuestionModel(
                "Берлин",
                "Франкфурт",
                "Штутгарт",
                "Берлин",
                "Париж",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/berlin.jpg?alt=media&token=d7ef5955-b236-4122-a01e-08bb7f886cbb",
                "Германия",
                " gh561jmt789"
        ));
        list.add(new QuestionModel(
                "Тбилиси",
                "Гори",
                "Тбилиси",
                "Батуми",
                "Тегеран",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D1%82%D0%B1%D0%B8%D0%BB%D0%B8%D1%81%D0%B8.jpg?alt=media&token=17cf604d-8f9c-4755-8847-3c9a17676484",
                "Грузия",
                "215ef5n3215"
        ));
        questionAdapter.notifyDataSetChanged();
    }
}