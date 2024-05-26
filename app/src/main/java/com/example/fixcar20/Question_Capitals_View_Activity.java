package com.example.fixcar20;

import static com.example.fixcar20.QuestionAdapter.context;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class Question_Capitals_View_Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;
    private List<QuestionModel> list;
    ImageView heart2;
    ImageView heart3;
    int hearts = 3;
    private int bals = 0;
    private Long maxBals = 0L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_view);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleview); // Assigning recyclerView
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);
        ///////
        questionAdapter = new QuestionAdapter(list, Question_Capitals_View_Activity.this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Question_Capitals_View_Activity.this));
        recyclerView.setAdapter(questionAdapter);

        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful() && task.getResult().exists()) {
                    if (task.getResult().get("bal_Capitals") == null) {
                        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update("bal_Capitals", 0).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });

                    } else {
                        maxBals = (Long) task.getResult().get("bal_Capitals");
                    }
                }
            }
        });

        questionAdapter.OnPressed(new QuestionAdapter.OnPressed() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onanswer1(int position, String answer1, String answer) {
                if (answer1.equals(answer)) {
                    bals++;


                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ правелен!",
                            "Молодец!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.SHORT_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));

                    if (list.size() == 1) {
                        Intent intent = new Intent(Question_Capitals_View_Activity.this,End.class);
                        if(bals >= maxBals){
                            intent.putExtra("uraa","uraa");
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("bals", String.valueOf(bals));
                        intent.putExtra("maxBals", String.valueOf(maxBals));
                        intent.putExtra("context", "Question_Capitals_View_Activity");
                        startActivity(intent);
                        finish();

                    } else {
                        list.remove(position);
                        questionAdapter.notifyDataSetChanged();
                    }


                } else {
                    switch (hearts) {
                        case 1:
                            if (maxBals < bals) {
                                UserModel.baler(bals, "bal_Capitals");
                            }


                            Intent intent = new Intent(Question_Capitals_View_Activity.this, End.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("bals", String.valueOf(bals));
                            intent.putExtra("maxBals", String.valueOf(maxBals));
                            intent.putExtra("context", "Question_Capitals_View_Activity");
                            startActivity(intent);
                            finish();
                            break;
                        case 2:
                            hearts--;
                            heart2.setVisibility(View.INVISIBLE);
                            break;
                        case 3:
                            hearts--;
                            heart3.setVisibility(View.INVISIBLE);
                            break;

                    }
                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ неправильный!",
                            "подумай еще",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.SHORT_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
//

                }
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onanswer2(int position, String answer2, String answer) {
                if (answer2.equals(answer)) {
                    bals++;


                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ правелен!",
                            "Молодец!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.SHORT_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));

                    if (list.size() == 1) {
                        Intent intent = new Intent(Question_Capitals_View_Activity.this,End.class);
                        if(bals >= maxBals){
                            intent.putExtra("uraa","uraa");
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("bals", String.valueOf(bals));
                        intent.putExtra("maxBals", String.valueOf(maxBals));
                        intent.putExtra("context", "Question_Capitals_View_Activity");
                        startActivity(intent);
                        finish();
                    } else {
                        list.remove(position);
                    }
                    questionAdapter.notifyDataSetChanged();

                } else {
                    switch (hearts) {
                        case 1:
                            if (maxBals < bals) {
                                UserModel.baler(bals, "bal_Capitals");
                            }

                            Intent intent = new Intent(Question_Capitals_View_Activity.this, End.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("bals", String.valueOf(bals));
                            intent.putExtra("maxBals", String.valueOf(maxBals));
                            intent.putExtra("context", "Question_Capitals_View_Activity");
                            startActivity(intent);
                            finish();
                            break;
                        case 2:
                            hearts--;
                            heart2.setVisibility(View.INVISIBLE);
                            break;
                        case 3:
                            hearts--;
                            heart3.setVisibility(View.INVISIBLE);
                            break;

                    }
                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ неправильный!",
                            "подумай еще",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.SHORT_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
//

                }

            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onanswer3(int position, String answer3, String answer) {
                if (answer3.equals(answer)) {
                    bals++;


                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ правелен!",
                            "Молодец!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.SHORT_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));

                    if (list.size() == 1) {
                        Intent intent = new Intent(Question_Capitals_View_Activity.this,End.class);
                        if(bals >= maxBals){
                            intent.putExtra("uraa","uraa");
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("bals", String.valueOf(bals));
                        intent.putExtra("maxBals", String.valueOf(maxBals));
                        intent.putExtra("context", "Question_Capitals_View_Activity");
                        startActivity(intent);
                        finish();
                    } else {
                        list.remove(position);
                    }
                    questionAdapter.notifyDataSetChanged();
                } else {
                    switch (hearts) {
                        case 1:
                            if (maxBals < bals) {
                                UserModel.baler(bals, "bal_Capitals");
                            }
                            Intent intent = new Intent(Question_Capitals_View_Activity.this, End.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("bals", String.valueOf(bals));
                            intent.putExtra("maxBals", String.valueOf(maxBals));
                            intent.putExtra("context", "Question_Capitals_View_Activity");
                            startActivity(intent);
                            finish();
                            break;
                        case 2:
                            hearts--;
                            heart2.setVisibility(View.INVISIBLE);
                            break;
                        case 3:
                            hearts--;
                            heart3.setVisibility(View.INVISIBLE);
                            break;

                    }
                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ неправильный!",
                            "подумай еще",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.SHORT_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
//

                }

            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onanswer4(int position, String answer4, String answer) {
                if (answer4.equals(answer)) {
                    bals++;


                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ правелен!",
                            "Молодец!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.SHORT_DURATION,
                            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));


                    if (list.size() == 1) {
                        Intent intent = new Intent(Question_Capitals_View_Activity.this,End.class);
                        if(bals >= maxBals){
                            intent.putExtra("uraa","uraa");
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("bals", String.valueOf(bals));
                        intent.putExtra("maxBals", String.valueOf(maxBals));
                        intent.putExtra("context", "Question_Capitals_View_Activity");
                        startActivity(intent);

                        finish();
                    } else {
                        list.remove(position);
                    }
                    questionAdapter.notifyDataSetChanged();
                } else {
                    switch (hearts) {
                        case 1:
                            if (maxBals < bals) {
                                UserModel.baler(bals, "bal_Capitals");
                            }
                            Intent intent = new Intent(Question_Capitals_View_Activity.this, End.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("bals", String.valueOf(bals));
                            intent.putExtra("maxBals", String.valueOf(maxBals));
                            intent.putExtra("context", "Question_Capitals_View_Activity");
                            startActivity(intent);
                            finish();
                            break;
                        case 2:
                            hearts--;
                            heart2.setVisibility(View.INVISIBLE);
                            break;
                        case 3:
                            hearts--;
                            heart3.setVisibility(View.INVISIBLE);
                            break;

                    }
                    MotionToast.Companion.createColorToast((Activity) context,
                            "Ответ неправильный!",
                            "подумай еще",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.SHORT_DURATION,
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
                "Санто-Доминго",
                "Санто-Доминго",
                "Сантьяго",
                "Ла-Вега",
                "Пуэрто-Плата",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A1%D0%B0%D0%BD%D1%82%D0%BE-%D0%94%D0%BE%D0%BC%D0%B8%D0%BD%D0%B3%D0%BE.png?alt=media&token=df75e454-4817-497b-bdde-45f03b4ad7bb",
                "Доминиканская Республика",
                "65ea156591"
        )); //42
        list.add(new QuestionModel(
                "Ереван",
                "Ванадзор",
                "Тбилиси",
                "Гюмри",
                "Ереван",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%B5%D1%80%D0%B5%D0%B2%D0%B0%D0%BD.jpg?alt=media&token=0b5f8896-eb13-4ed3-99d4-a62a59965147",
                "Армения",
                "215ef5D3215"
        )); //1
        list.add(new QuestionModel(
                "Абу-Даби",
                "Дубай",
                "Абу-Даби",
                "Стамбул",
                "Шарджа",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/abu%20dhabi.jpg?alt=media&token=8a59dd9c-6890-4cb5-a87b-98b118ebf719",
                "ОАЭ",
                "65ea156576"
        )); //2
        list.add(new QuestionModel(
                "Тегеран",
                "Мегри",
                "Баку",
                "Гори",
                "Тегеран",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%98%D1%80%D0%B0%D0%BD.jpg?alt=media&token=f8d428c8-239e-4d72-ab60-625b6e2bad01",
                "Иран",
                "21231lp897"
        )); //3
        list.add(new QuestionModel(
                "Берлин",
                "Франкфурт",
                "Штутгарт",
                "Берлин",
                "Париж",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/berlin.jpg?alt=media&token=d7ef5955-b236-4122-a01e-08bb7f886cbb",
                "Германия",
                " gh561jmt789"
        )); //4
        list.add(new QuestionModel(
                "Тбилиси",
                "Гори",
                "Тбилиси",
                "Батуми",
                "Тегеран",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D1%82%D0%B1%D0%B8%D0%BB%D0%B8%D1%81%D0%B8.jpg?alt=media&token=17cf604d-8f9c-4755-8847-3c9a17676484",
                "Грузия",
                "215ef5n3215"
        )); //5
        list.add(new QuestionModel(
                "Лондон",
                "Париж",
                "Оксфорд",
                "Вашингтон",
                "Лондон",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9B%D0%BE%D0%BD%D0%B4%D0%BE%D0%BD.png?alt=media&token=04f40330-10f0-41fa-b03d-c3459d38adca",
                "Великобритания",
                "215ef5n3215"
        )); //6
        list.add(new QuestionModel(
                "Бразилиа",
                "Бразилиа",
                "Рио-де-Жанейро",
                "Берн",
                "Сидней",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%91%D1%80%D0%B0%D0%B7%D0%B8%D0%BB%D0%B8%D0%B0.png?alt=media&token=f688d808-fee1-490d-b8b7-2e70e5a66790",
                "Бразилия",
                "215ef5n3215"
        )); //7
        list.add(new QuestionModel(
                "Вена",
                "Канберра",
                "Сидней",
                "Вена",
                "Берлин",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%92%D0%B5%D0%BD%D0%B0.png?alt=media&token=7ba305c7-f486-469d-bb2c-27e980f13432",
                "Австрия",
                "215ef5n3215"
        )); //8
        list.add(new QuestionModel(
                "Вильнюс",
                "Таллин",
                "Кишинёв",
                "Рига",
                "Вильнюс",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%92%D0%B8%D0%BB%D1%8C%D0%BD%D1%8E%D1%81.png?alt=media&token=704a7ee1-20d9-4158-9704-2c466f04161b",
                "Литва",
                "215ef5n3215"
        )); //9
        list.add(new QuestionModel(
                "Дели",
                "Дели",
                "Мумбаи",
                "Калькутта",
                "Агра",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%94%D0%B5%D0%BB%D0%B8.png?alt=media&token=0a955799-9695-4d90-9c30-8ed5240fcb20",
                "Индия",
                "215ef5n3215"
        )); //10
        list.add(new QuestionModel(
                "Душанбе",
                "Самарканд",
                "Душанбе",
                "Ташкент",
                "Бишкек",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%94%D1%83%D1%88%D0%B0%D0%BD%D0%B1%D0%B5.png?alt=media&token=14c44218-2a1f-4d1a-81da-e9688c508999",
                "Таджикистан",
                "215ef5n3215"
        )); //11
        list.add(new QuestionModel(
                "Каир",
                "Гиза",
                "Александрия",
                "Порт-Саид",
                "Каир",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%B0%D0%B8%D1%80.png?alt=media&token=98b3c7f6-abb1-4daa-a822-6ff75a8adcab",
                "Египет",
                "215ef5n3215"
        )); //12
        list.add(new QuestionModel(
                "Канберра",
                "Сидней",
                "Оттава",
                "Мельбурн",
                "Канберра",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%B0%D0%BD%D0%B1%D0%B5%D1%80%D1%80%D0%B0.png?alt=media&token=563710f0-ba99-4446-a02b-4ff0085ecd21",
                "Австралия",
                "215ef5n3215"
        )); //13
        list.add(new QuestionModel(
                "Оттава",
                "Торонто",
                "Оттава",
                "Монреаль",
                "Ванкувер",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9E%D1%82%D1%82%D0%B0%D0%B2%D0%B0.png?alt=media&token=4efa8d13-0e88-448a-aa8b-fa53eb44b327",
                "Канада",
                "215ef5n3215"
        )); //14
        list.add(new QuestionModel(
                "Париж",
                "Париж",
                "Марсель",
                "Страсбург",
                "Ницца",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9F%D0%B0%D1%80%D0%B8%D0%B6.png?alt=media&token=0d01a38d-88c0-4d81-8d5f-4196bee0d156",
                "Франция",
                "215ef5n3215"
        )); //15
        list.add(new QuestionModel(
                "Пекин",
                "Шанхай",
                "Токио",
                "Гонконг",
                "Пекин",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9F%D0%B5%D0%BA%D0%B8%D0%BD.png?alt=media&token=97d5706f-6f27-40f8-a9a6-2f9382dc699a",
                "Китай",
                "215ef5n3215"
        )); //16
        list.add(new QuestionModel(
                "Рига",
                "Вильнюс",
                "Рига",
                "Арташат",
                "Ашхабад",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A0%D0%B8%D0%B3%D0%B0.png?alt=media&token=206bee36-385c-45bb-a1bd-a4a6ef8c6e2a",
                "Латвия",
                "215ef5n3215"
        )); //17
        list.add(new QuestionModel(
                "Сеул",
                "Гонконг",
                "Пхеньян",
                "Сеул",
                "Мунгён",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A1%D0%B5%D1%83%D0%BB.png?alt=media&token=cbdc966d-470a-4ff4-a010-991d43e91a96",
                "Южная Корея",
                "215ef5n3215"
        )); //18
        list.add(new QuestionModel(
                "Ташкент",
                "Ташкент",
                "Джалакудук",
                "Самарканд",
                "Бухара",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A2%D0%B0%D1%88%D0%BA%D0%B5%D0%BD%D1%82.png?alt=media&token=88dbba13-4265-4b02-8a4a-d98989d7975b",
                "Узбекистан",
                "215ef5n3215"
        )); //19
        list.add(new QuestionModel(
                "Токио",
                "Хиросима",
                "Токио",
                "Нагасаки",
                "Киото",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A2%D0%BE%D0%BA%D0%B8%D0%BE.png?alt=media&token=7692827f-420a-4f5f-a745-12baa75d988f",
                "Япония",
                "215ef5n3215"
        )); //20
        list.add(new QuestionModel(
                "Улан-Батор",
                "Чойбалсан",
                "Улан-Удэ",
                "Улан-Батор",
                "Алматы",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A3%D0%BB%D0%B0%D0%BD-%D0%91%D0%B0%D1%82%D0%BE%D1%80.png?alt=media&token=cb065ec3-a91e-4081-8b8a-1c9a6d213b6b",
                "Монголия",
                "215ef5n3215"
        )); //21
        list.add(new QuestionModel(
                "Афины",
                "Гавана",
                "Копенгаген",
                "Афины",
                "Киото",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%90%D1%84%D0%B8%D0%BD%D1%8B.png?alt=media&token=a54e9bd9-bf16-44bb-8519-f4607c97fe5c",
                "Греция",
                "215ef5n3215"
        )); //22
        list.add(new QuestionModel(
                "Берн",
                "Цюрих",
                "Шаффхаузен",
                "Берн",
                "Женева",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%91%D0%B5%D1%80%D0%BD.png?alt=media&token=6c383af1-c758-4aa3-bf73-1db6e95bdc7f",
                "Швейцария",
                "215ef5n3215"
        )); //23
        list.add(new QuestionModel(
                "Вашингтон",
                "Чикаго",
                "Нью-Йорк",
                "Лос-Анджелес",
                "Вашингтон",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%92%D0%B0%D1%88%D0%B8%D0%BD%D0%B3%D1%82%D0%BE%D0%BD.png?alt=media&token=cde7e914-267b-49c6-ac53-9b3b141e00a3",
                "США",
                "215ef5n3215"
        )); //24
        list.add(new QuestionModel(
                "Копенгаген",
                "Лиссабон",
                "Копенгаген",
                "Хельсинки",
                "Осло",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%BE%D0%BF%D0%B5%D0%BD%D0%B3%D0%B0%D0%B3%D0%B5%D0%BD.png?alt=media&token=34725ba1-7752-459b-9c92-3777d5b850f8",
                "Дания",
                "215ef5n3215"
        )); //25
        list.add(new QuestionModel(
                "Лиссабон",
                "Лиссабон",
                "Порту",
                "Гимарайнш",
                "Барселона",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9B%D0%B8%D1%81%D1%81%D0%B0%D0%B1%D0%BE%D0%BD.png?alt=media&token=247b22a6-bd2e-4855-b4f4-259a943b20ba",
                "Португалия",
                "215ef5n3215"
        )); //26
        list.add(new QuestionModel(
                "Мадрид",
                "Барселона",
                "Севилья",
                "Валенсия",
                "Мадрид",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9C%D0%B0%D0%B4%D1%80%D0%B8%D0%B4.png?alt=media&token=cc42d247-6f86-42b5-9973-87e0e91a3106",
                "Испания",
                "215ef5n3215"
        )); //27
        list.add(new QuestionModel(
                "Мехико",
                "Гуанахуато",
                "Ла-Пас",
                "Мехико",
                "Чиуауа",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9C%D0%B5%D1%85%D0%B8%D0%BA%D0%BE.png?alt=media&token=f24e3882-f2ec-41dc-92d8-dbaa256755a8",
                "Мексика",
                "215ef5n3215"
        )); //28
        list.add(new QuestionModel(
                "Рим",
                "Милан",
                "Рим",
                "Венеция",
                "Флоренция",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A0%D0%B8%D0%BC.png?alt=media&token=b1905ead-a432-425b-b60e-8f1aceea1341",
                "Италия",
                "215ef5n3215"
        )); //29
        list.add(new QuestionModel(
                "Москва",
                "Санкт-Петербург",
                "Махачкала",
                "Владикавказ",
                "Москва",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0.png?alt=media&token=ff55fdaf-bcb3-41b5-b04b-77757f1d2a15",
                "Россия",
                "215ef5n3215"
        )); //30
        list.add(new QuestionModel(
                "Анкара",
                "Стамбул",
                "Измир",
                "Анкара",
                "Бурса",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%90%D0%BD%D0%BA%D0%B0%D1%80%D0%B0.png?alt=media&token=67e846da-87a8-49c1-9e67-a6689a6dd1d9",
                "Турция",
                "65ea156576"
        )); //31

        list.add(new QuestionModel(
                "Ханой",
                "Ханой",
                "Хошимин",
                "Дананг",
                "Хайфон",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A5%D0%B0%D0%BD%D0%BE%D0%B9.png?alt=media&token=149dbdad-d535-46c3-8925-e2553263222d",
                "Вьетнам",
                "65ea156594"
        )); //32

        list.add(new QuestionModel(
                "Бухарест",
                "Клуж-Напока",
                "Бухарест",
                "Тимишоара",
                "Констанца",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%91%D1%83%D1%85%D0%B0%D1%80%D0%B5%D1%81%D1%82.png?alt=media&token=4a661b3a-c7de-412c-b19f-a02e4c1906c6",
                "Румыния",
                "65ea156578"
        )); //33

        list.add(new QuestionModel(
                "Джакарта",
                "Сурабая",
                "Джакарта",
                "Бандунг",
                "Медан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%94%D0%B6%D0%B0%D0%BA%D0%B0%D1%80%D1%82%D0%B0.png?alt=media&token=04eb87c5-6d2b-4af0-ba4b-9170cd562b0f",
                "Индонезия",
                "65ea156581"
        )); //34

        list.add(new QuestionModel(
                "Сантьяго",
                "Сантьяго",
                "Вальпараисо",
                "Консепсьон",
                "Ла-Серена",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A1%D0%B0%D0%BD%D1%82%D1%8C%D1%8F%D0%B3%D0%BE.png?alt=media&token=d585b71e-702b-4487-bd0a-861c11c6614a",
                "Чили",
                "65ea156592"
        )); //35

        list.add(new QuestionModel(
                "Монтевидео",
                "Монтевидео",
                "Пайсанду",
                "Сальто",
                "Ривера",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9C%D0%BE%D0%BD%D1%82%D0%B5%D0%B2%D0%B8%D0%B4%D0%B5%D0%BE.png?alt=media&token=f4d3784e-f6d7-42cf-a4d4-de7c5d545854",
                "Уругвай",
                "65ea156587"
        )); //36

        list.add(new QuestionModel(
                "Хельсинки",
                "Турку",
                "Хельсинки",
                "Тампере",
                "Оулу",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A5%D0%B5%D0%BB%D1%8C%D1%81%D0%B8%D0%BD%D0%BA%D0%B8.png?alt=media&token=9fd601d8-bdaa-4d41-b940-cde1320d1d1b",
                "Финляндия",
                "65ea156595"
        )); //37

        list.add(new QuestionModel(
                "Панама",
                "Панама",
                "Колон",
                "Давид",
                "Сан-Мигелито",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9F%D0%B0%D0%BD%D0%B0%D0%BC%D0%B0.png?alt=media&token=8fb45390-75f5-4c44-9da4-49feb21f54e0",
                "Панама",
                "65ea156588"
        )); //38

        list.add(new QuestionModel(
                "Варшава",
                "Варшава",
                "Краков",
                "Вроцлав",
                "Познань",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%92%D0%B0%D1%80%D1%88%D0%B0%D0%B2%D0%B0.png?alt=media&token=ed4de916-314b-483f-80e9-ff7c88b89c2c",
                "Польша",
                "65ea156580"
        )); //39

        list.add(new QuestionModel(
                "Кито",
                "Куэнка",
                "Гуаякиль",
                "Кито",
                "Манта",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%B8%D1%82%D0%BE.png?alt=media&token=2a8c0eae-2e08-480b-8c02-a971fcd8dea5",
                "Эквадор",
                "65ea156583"
        )); //40

        list.add(new QuestionModel(
                "Доха",
                "Доха",
                "Эль-Вакра",
                "Аль-Хор",
                "Ар-Райян",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%94%D0%BE%D1%85%D0%B0.png?alt=media&token=07f74731-3267-42af-a720-91ed2243b235",
                "Катар",
                "65ea156582"
        )); //41



        list.add(new QuestionModel(
                "Рабат",
                "Касабланка",
                "Марракеш",
                "Рабат",
                "Фес",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A0%D0%B0%D0%B1%D0%B0%D1%82.png?alt=media&token=7f040c6f-c1cc-4bfd-9fd6-8f01dfaea3dc",
                "Марокко",
                "65ea156590"
        )); //43

        list.add(new QuestionModel(
                "Бангкок",
                "Чиангмай",
                "Бангкок",
                "Паттайя",
                "Пхукет",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%91%D0%B0%D0%BD%D0%B3%D0%BA%D0%BE%D0%BA.png?alt=media&token=2c530c9f-1d30-47d0-810d-e832592fbb04",
                "Таиланд",
                "65ea156577"
        )); //44

        list.add(new QuestionModel(
                "Буэнос-Айрес",
                "Кордова",
                "Мендоса",
                "Буэнос-Айрес",
                "Сальта",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%91%D1%83%D1%8D%D0%BD%D0%BE%D1%81-%D0%90%D0%B9%D1%80%D0%B5%D1%81.png?alt=media&token=da211d71-af07-46ee-9557-1629fae671f8",
                "Аргентина",
                "65ea156579"
        )); //45

        list.add(new QuestionModel(
                "Лима",
                "Арекипа",
                "Лима",
                "Куско",
                "Трухильо",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9B%D0%B8%D0%BC%D0%B0.png?alt=media&token=203491b7-71e0-4c85-be48-a967508ad3ae",
                "Перу",
                "65ea156585"
        )); //46

        list.add(new QuestionModel(
                "Манила",
                "Манила",
                "Давао",
                "Себу",
                "Кесон-Сити",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9C%D0%B0%D0%BD%D0%B8%D0%BB%D0%B0.png?alt=media&token=baa6d21f-d38c-44c2-81b5-509b116fb906",
                "Филиппины",
                "65ea156586"
        )); //47

        list.add(new QuestionModel(
                "Тирана",
                "Тирана",
                "Дуррес",
                "Влёра",
                "Шкодер",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A2%D0%B8%D1%80%D0%B0%D0%BD%D0%B0.png?alt=media&token=066a4e8a-44f0-4292-b51c-930ae8524a84",
                "Албания",
                "65ea156593"
        )); //48

        list.add(new QuestionModel(
                "Куала-Лумпур",
                "Пенанг",
                "Джохор-Бару",
                "Куала-Лумпур",
                "Малакка",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D1%83%D0%B0%D0%BB%D0%B0-%D0%9B%D1%83%D0%BC%D0%BF%D1%83%D1%80.png?alt=media&token=2afff8ae-6a68-40d5-8fed-8f4afd913ad1",
                "Малайзия",
                "65ea156584"
        )); //49

        list.add(new QuestionModel(
                "Подгорица",
                "Подгорица",
                "Никшич",
                "Цетине",
                "Бар",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9F%D0%BE%D0%B4%D0%B3%D0%BE%D1%80%D0%B8%D1%86%D0%B0.png?alt=media&token=372a7570-4b91-4062-86c5-81050582da0c",
                "Черногория",
                "65ea156589"
        )); //50


        questionAdapter.notifyDataSetChanged();
    }
}