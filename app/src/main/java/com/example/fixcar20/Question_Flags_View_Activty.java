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
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%90%D0%A0%D0%9C%D0%95%D0%9D%D0%98%D0%AF.png?alt=media&token=1c70af5b-3419-4307-b270-37a3e8542b97",
                "Флаг",
                "215ef5D3215"
        )); //1
        list.add(new QuestionModel(
                "ОАЭ",
                "Иран",
                "Кувейт",
                "Катар",
                "ОАЭ",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9E%D0%90%D0%AD.png?alt=media&token=84131c25-2d9e-433b-9860-6163e15b126c",
                "Флаг",
                "65ea156576"
        )); //2
        list.add(new QuestionModel(
                "Иран",
                "Пакистан",
                "Иран",
                "Узбекистан",
                "Мексика",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%98%D0%A0%D0%90%D0%9D.png?alt=media&token=c48989d0-ef5a-4ada-a079-6905db69cb15",
                "Флаг",
                "21231lp897"
        )); //3
        list.add(new QuestionModel(
                "Казахстан",
                "Казахстан",
                "Таджикистан",
                "Узбекистан",
                "Кыргызстан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%90%D0%97%D0%90%D0%A5%D0%A1%D0%A2%D0%90%D0%9D.png?alt=media&token=257ba0da-0a55-48db-812c-7886740a68b4",
                "Флаг",
                " gh561jmt789"
        )); //4
        list.add(new QuestionModel(
                "Грузия",
                "Австралия",
                "Армения",
                "Финландия",
                "Грузия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%93%D0%A0%D0%A3%D0%97%D0%98%D0%AF.png?alt=media&token=006eecf8-4a22-4df5-a92e-77ab56d54174",
                "Флаг",
                "215ef5n3215"
        )); //5
        list.add(new QuestionModel(
                "Катар",
                "Австрия",
                "Беларусь",
                "Катар",
                "Египет",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%90%D0%A2%D0%90%D0%A0.png?alt=media&token=9173b795-59fb-47b1-a12f-a8ea5ec722c3",
                "Флаг",
                "215ef5n3215"
        )); //6
        list.add(new QuestionModel(
                "Кыргызстан",
                "Казахстан",
                "Кыргызстан",
                "Туркменистан",
                "Узбекистан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%AB%D0%A0%D0%93%D0%AB%D0%97%D0%A1%D0%A2%D0%90%D0%9D.png?alt=media&token=5e215018-0dea-4c12-a4c7-0d3314fc69c6",
                "Флаг",
                "215ef5n3215"
        )); //7
        list.add(new QuestionModel(
                "Россия",
                "Молдова",
                "Словения",
                "Словакия",
                "Россия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A0%D0%9E%D0%A1%D0%A1%D0%98%D0%AF.png?alt=media&token=2c9c4737-dfdf-47db-9c3e-eaddd143821e",
                "Флаг",
                "215ef5n3215"
        )); //8
        list.add(new QuestionModel(
                "США",
                "Германия",
                "Мексика",
                "США",
                "Афганистан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A1%D0%A8%D0%90.png?alt=media&token=1206e970-57b6-48f4-8245-9c080a531911",
                "Флаг",
                "215ef5n3215"
        )); //9
        list.add(new QuestionModel(
                "Япония",
                "Япония",
                "США",
                "Индия",
                "Бангладеш",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%AF%D0%9F%D0%9E%D0%9D%D0%98%D0%AF.png?alt=media&token=abf9300e-1311-4a61-8c9c-402ca4bcee4a",
                "Флаг",
                "215ef5n3215"
        )); //10
        list.add(new QuestionModel(
                "Норвегия",
                "Швейцария",
                "Финляндия",
                "Швеция",
                "Норвегия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9D%D0%9E%D0%A0%D0%92%D0%95%D0%93%D0%98%D0%AF.png?alt=media&token=94a90504-3ab6-4812-8dc7-f45ee759bd6c",
                "Флаг",
                "215ef5n3215"
        )); //11
        list.add(new QuestionModel(
                "Швеция",
                "Швейцария",
                "Финляндия",
                "Швеция",
                "Норвегия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A8%D0%92%D0%95%D0%A6%D0%98%D0%AF.png?alt=media&token=ee134d0c-62d1-4955-ace0-d4b8af3dad82",
                "Флаг",
                "215ef5n3215"
        )); //12
        list.add(new QuestionModel(
                "Швейцария",
                "Швейцария",
                "Финляндия",
                "Швеция",
                "Норвегия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A8%D0%92%D0%95%D0%99%D0%A6%D0%90%D0%A0%D0%98%D0%AF.png?alt=media&token=8a9ccd14-1eab-42cb-8e4c-67caa1dabcdf",
                "Флаг",
                "215ef5n3215"
        )); //13
        list.add(new QuestionModel(
                "Финляндия",
                "Швейцария",
                "Финляндия",
                "Швеция",
                "Норвегия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A4%D0%98%D0%9D%D0%9B%D0%AF%D0%9D%D0%94%D0%98%D0%AF.png?alt=media&token=4626a339-42e2-4aeb-a9f4-9f6fa2249b46",
                "Флаг",
                "215ef5n3215"
        )); //14
        list.add(new QuestionModel(
                "Куба",
                "Коста-Рика",
                "Мексика",
                "Франция",
                "Куба",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%A3%D0%91%D0%90.png?alt=media&token=98ee86a8-2ead-4c9d-ada1-1a644b1e0e5d",
                "Флаг",
                "215ef5n3215"
        )); //15
        list.add(new QuestionModel(
                "Великобритания",
                "Великобритания",
                "Исландия",
                "Индия",
                "Норвегия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%91%D0%A0%D0%98%D0%A2%D0%90%D0%9D%D0%98%D0%AF.png?alt=media&token=50ebd4bb-84d6-4b41-95eb-7efb6591472d",
                "Флаг",
                "215ef5n3215"
        )); //16
        list.add(new QuestionModel(
                "Сауд. Аравия",
                "Египет",
                "Палестина",
                "Ирак",
                "Сауд. Аравия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A1%D0%90%D0%A3%D0%94.%20%D0%90%D0%A0%D0%90%D0%92%D0%98%D0%AF.png?alt=media&token=305e6740-6e45-44bc-98fc-cfdcdd301d49",
                "Флаг",
                "215ef5n3215"
        )); //17
        list.add(new QuestionModel(
                "Канада",
                "Великобритания",
                "Канада",
                "Новая Зеландия",
                "Дания",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%90%D0%9D%D0%90%D0%94%D0%90.png?alt=media&token=0c60b2e9-72f0-42ac-aaa2-4c42e6799557",
                "Флаг",
                "215ef5n3215"
        )); //18
        list.add(new QuestionModel(
                "Австралия",
                "Гвинея",
                "Новая Зеландия",
                "Индия",
                "Австралия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%90%D0%92%D0%A1%D0%A2%D0%A0%D0%90%D0%9B%D0%98%D0%AF.png?alt=media&token=67798f76-d414-4c33-8714-7d2feccf9b7a",
                "Флаг",
                "215ef5n3215"
        )); //19
        list.add(new QuestionModel(
                "Индия",
                "Индия",
                "Непал",
                "Бутан",
                "Пакистан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%98%D0%9D%D0%94%D0%98%D0%AF.png?alt=media&token=e0ea5daf-7dcf-4dca-905d-cf71bbfabbf6",
                "Флаг",
                "215ef5n3215"
        )); //20
        questionAdapter.notifyDataSetChanged();
    }
}

