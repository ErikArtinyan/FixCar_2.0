package com.example.fixcar20;

import static com.example.fixcar20.QuestionAdapter.context;

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

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class Question_Flags_View_Activty extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;
    private List<QuestionModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_flags_view_activty);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleview); // Assigning recyclerView
        ///////
        questionAdapter = new QuestionAdapter(list,Question_Flags_View_Activty.this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Question_Flags_View_Activty.this));
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
                        startActivity(new Intent(Question_Flags_View_Activty.this,Main_Menu.class));
                    }else {
                        list.remove(position);
                    }
                    questionAdapter.notifyDataSetChanged();

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
                        startActivity(new Intent(Question_Flags_View_Activty.this,Main_Menu.class));
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
                        startActivity(new Intent(Question_Flags_View_Activty.this,Main_Menu.class));
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
                        startActivity(new Intent(Question_Flags_View_Activty.this,Main_Menu.class));
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
                "Армения",
                "Грузия",
                "Колумбия",
                "Армения",
                "Бангладеш",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/armenia.png?alt=media&token=144ac9c4-b9df-48e1-aa1b-340d4d76df2a",
                "Флаг",
                "215ef5D3215"
        ));
        list.add(new QuestionModel(
                "ОАЭ",
                "Иран",
                "Кувейт",
                "Катар",
                "ОАЭ",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/united-arab-emirates.png?alt=media&token=9e2b42e4-224f-4ac8-9144-6f71a209e6cb",
                "Флаг",
                "65ea156576"
        ));
        list.add(new QuestionModel(
                "Иран",
                "Пакистан",
                "Иран",
                "Узбекистан",
                "Мексика",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/world.png?alt=media&token=b9e07280-1047-4b1f-adc6-e92710904859",
                "Флаг",
                "21231lp897"
        ));
        list.add(new QuestionModel(
                "Казахстан",
                "Казахстан",
                "Таджикистан",
                "Узбекистан",
                "Кыргызстан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/flag%20(2).png?alt=media&token=ffbd6a74-87a1-4c9c-b3ac-cb3deafa6cdb",
                "Флаг",
                " gh561jmt789"
        ));
        list.add(new QuestionModel(
                "Грузия",
                "Австралия",
                "Армения",
                "Финландия",
                "Грузия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/georgia.png?alt=media&token=7864862e-b592-4427-bebb-b7f2a153e54e",
                "Флаг",
                "215ef5n3215"
        ));
        questionAdapter.notifyDataSetChanged();
    }
}