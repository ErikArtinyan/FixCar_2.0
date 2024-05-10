


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

public class Question_Maps_View_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;
    private List<QuestionModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_maps_view);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleview); // Assigning recyclerView
        ///////
        questionAdapter = new QuestionAdapter(list,Question_Maps_View_Activity.this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Question_Maps_View_Activity.this));
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
                        startActivity(new Intent(Question_Maps_View_Activity.this,Main_Menu.class));
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
                        startActivity(new Intent(Question_Maps_View_Activity.this,Main_Menu.class));
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
                        startActivity(new Intent(Question_Maps_View_Activity.this,Main_Menu.class));
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
                        startActivity(new Intent(Question_Maps_View_Activity.this,Main_Menu.class));
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
                "Великобритания",
                "Македония",
                "Новая Зеландия",
                "Исландия",
                "Великобритания",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/BritainFlag.png?alt=media&token=ea268644-3e9d-46b8-814a-28f357e5cf95",
                "Карта",
                " gh561jmt789"
        )); //14
        list.add(new QuestionModel(
                "Китай",
                "Иран",
                "Индия",
                "Китай",
                "Монголия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/ChinaFlag.png?alt=media&token=f2bbf8f5-68f3-4816-8bac-b0fdc21e716f",
                "Карта",
                " gh561jmt789"
        )); //15
        list.add(new QuestionModel(
                "Армения",
                "Афганистан",
                "Армения",
                "Индия",
                "Франция",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/ArmeniaMap.png?alt=media&token=7c24bcde-79de-406a-81cb-20e6e93990ad",
                "Карта",
                "215ef5D3215"
        )); //1
        list.add(new QuestionModel(
                "США",
                "Иран",
                "Россия",
                "Канада",
                "США",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/USAmap.png?alt=media&token=4e89d1ea-8a9e-4c70-a255-45edd68ebff1",
                "Карта",
                "65ea156576"
        )); //2
        list.add(new QuestionModel(
                "Грузия",
                "Армения",
                "Грузия",
                "Япония",
                "Уругвай",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/GeorgiaMap.png?alt=media&token=99946fc5-d6fb-42a0-83a1-a92668c1af9d",
                "Карта",
                "21231lp897"
        )); //3
        list.add(new QuestionModel(
                "Россия",
                "Канада",
                "Таджикистан",
                "Россия",
                "Украина",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/RussiaMap.png?alt=media&token=628ffa91-e6c5-43e5-a744-69e794dde8a1",
                "Карта",
                " gh561jmt789"
        )); //4
        list.add(new QuestionModel(
                "Узбекистан",
                "Китай",
                "Казахстан",
                "Катар",
                "Узбекистан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/UzbekistanMap.png?alt=media&token=a719b28a-bd2f-4c91-8960-883fd538ba0a",
                "Карта",
                "215ef5n3215"
        )); //5
        list.add(new QuestionModel(
                "Германия",
                "Британия",
                "Франция",
                "Испания",
                "Германия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/GermanyMap.png?alt=media&token=5c54e357-8880-453c-9ef7-3bc5daa7398c",
                "Карта",
                " gh561jmt789"
        )); //6
        list.add(new QuestionModel(
                "Иран",
                "Иран",
                "Ирак",
                "Сирия",
                "Пакистан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/IranMap.png?alt=media&token=6db411c2-112b-42ac-a0b6-9c3f1d8802d7",
                "Карта",
                " gh561jmt789"
        )); //7
        list.add(new QuestionModel(
                "Индия",
                "Канада",
                "Ирак",
                "Индия",
                "Пакистан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/IndiaFlag.png?alt=media&token=0ec4eccc-7065-46f9-8e6f-968e9d545985",
                "Карта",
                " gh561jmt789"
        )); //8
        list.add(new QuestionModel(
                "Австралия",
                "Австрия",
                "Австралия",
                "Дания",
                "Италия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/AustralliaFlag.png?alt=media&token=da8841af-2a7a-4b5d-b806-61a1ece82dd5",
                "Карта",
                " gh561jmt789"
        )); //9
        list.add(new QuestionModel(
                "Япония",
                "Юж. Корея",
                "КНДР",
                "Китай",
                "Япония",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/JapanFlag.png?alt=media&token=77a66812-135d-46ff-b429-2292090db72b",
                "Карта",
                " gh561jmt789"
        )); //10
        list.add(new QuestionModel(
                "Монголия",
                "Иран",
                "Монголия",
                "Мексика",
                "Лаос",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/MongoliaFlag.png?alt=media&token=a0d15d1c-277f-467c-ba4a-722e4704a76a",
                "Карта",
                " gh561jmt789"
        )); //11
        list.add(new QuestionModel(
                "Алжир",
                "Алжир",
                "Египет",
                "Д.Р. Конго",
                "Емен",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%90%D0%BB%D0%B6%D0%B8%D1%80Flag.png?alt=media&token=c2657e1f-541a-4398-af1a-97f7fccfe23d",
                "Карта",
                " gh561jmt789"
        )); //12
        list.add(new QuestionModel(
                "Чили",
                "Мексика",
                "Чили",
                "Бельгия",
                "Куба",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/ChiliFlag.png?alt=media&token=72024c0f-03cf-499a-b2ab-79cfa6b2f276",
                "Карта",
                " gh561jmt789"
        )); //13

        list.add(new QuestionModel(
                "Бразилия",
                "Аргентина",
                "Бразилия",
                "Боливия",
                "Португалия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/BrazilFlag.png?alt=media&token=8f485bc8-335a-482a-943e-84a98e480f5c",
                "Карта",
                " gh561jmt789"
        )); //16
        list.add(new QuestionModel(
                "Сауд. Аравия",
                "Оман",
                "ОАЭ",
                "Сирия",
                "Сауд. Аравия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/SaudiArabiaFlag.png?alt=media&token=2fa715f9-926d-4ee3-aa64-b5bc58923767",
                "Карта",
                " gh561jmt789"
        )); //17
        list.add(new QuestionModel(
                "Казахстан",
                "Узбекистан",
                "Монголия",
                "Казахстан",
                "Беларусь",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%B0%D0%B7%D0%B0%D1%85%D1%81%D1%82%D0%B0%D0%BDFlag.png?alt=media&token=ad60e5eb-c096-4089-bb9b-45d0cc3cd149",
                "Карта",
                " gh561jmt789"
        )); //18
        list.add(new QuestionModel(
                "Ирак",
                "Иран",
                "Оман",
                "Емен",
                "Ирак",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/IraqFlag.png?alt=media&token=4b38afb4-10fd-4850-9086-bc6b98da2c23",
                "Карта",
                " gh561jmt789"
        )); //19
        list.add(new QuestionModel(
                "Украина",
                "Латвия",
                "Молдова",
                "Украина",
                "Литва",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/UkraineFlag.png?alt=media&token=eeb32986-4dc1-4e40-85e4-9aaa794f23c8",
                "Карта",
                " gh561jmt789"
        )); //20
        questionAdapter.notifyDataSetChanged();
    }
}

