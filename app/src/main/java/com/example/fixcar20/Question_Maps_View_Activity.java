


package com.example.fixcar20;

import static com.example.fixcar20.QuestionAdapter.context;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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

public class Question_Maps_View_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;
    private List<QuestionModel> list;
    private int hearts = 3;
    ImageView heart2;
    ImageView heart3;
    private  int bals = 0;
    private  Long maxBals = 0L;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_maps_view);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleview); // Assigning recyclerView
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful() && task.getResult().exists()) {
                    Toast.makeText(Question_Maps_View_Activity.this, "5", Toast.LENGTH_SHORT).show();
                    if (task.getResult().get("bal_Maps") == null) {
                        Toast.makeText(Question_Maps_View_Activity.this, "1", Toast.LENGTH_SHORT).show();
                        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update("bal_Maps", 0).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Question_Maps_View_Activity.this, "2", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                       maxBals = (Long) task.getResult().get("bal_Maps");
                        Toast.makeText(Question_Maps_View_Activity.this, String.valueOf(maxBals), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Question_Maps_View_Activity.this, "3", Toast.LENGTH_SHORT).show();
            }
        });
        ///////
        questionAdapter = new QuestionAdapter(list, Question_Maps_View_Activity.this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Question_Maps_View_Activity.this));
        recyclerView.setAdapter(questionAdapter);

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
                         Intent intent = new Intent(Question_Maps_View_Activity.this,End.class);
                        if(bals >= maxBals){
                            intent.putExtra("uraa","uraa");
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("bals", String.valueOf(bals));
                        intent.putExtra("maxBals", String.valueOf(maxBals));
                        intent.putExtra("context", "Question_Maps_View_Activity");
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
                                UserModel.baler(bals, "bal_Maps");
                            }
                            Toast.makeText(Question_Maps_View_Activity.this, String.valueOf(maxBals), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Question_Maps_View_Activity.this, End.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("bals", String.valueOf(bals));
                            intent.putExtra("maxBals", String.valueOf(maxBals));
                            intent.putExtra("context", "Question_Maps_View_Activity");
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
                         Intent intent = new Intent(Question_Maps_View_Activity.this,End.class);
                        if(bals >= maxBals){
                            intent.putExtra("uraa","uraa");
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("bals", String.valueOf(bals));
                        intent.putExtra("maxBals", String.valueOf(maxBals));
                        intent.putExtra("context", "Question_Maps_View_Activity");
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
                                UserModel.baler(bals, "bal_Maps");
                            }
                            Toast.makeText(Question_Maps_View_Activity.this, String.valueOf(maxBals), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Question_Maps_View_Activity.this, End.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("bals", String.valueOf(bals));
                            intent.putExtra("maxBals", String.valueOf(maxBals));
                            intent.putExtra("context", "Question_Maps_View_Activity");
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
                         Intent intent = new Intent(Question_Maps_View_Activity.this,End.class);
                        if(bals >= maxBals){
                            intent.putExtra("uraa","uraa");
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("bals", String.valueOf(bals));
                        intent.putExtra("maxBals", String.valueOf(maxBals));
                        intent.putExtra("context", "Question_Maps_View_Activity");
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
                                UserModel.baler(bals, "bal_Maps");
                            }
                            Toast.makeText(Question_Maps_View_Activity.this, String.valueOf(maxBals), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Question_Maps_View_Activity.this, End.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("bals", String.valueOf(bals));
                            intent.putExtra("maxBals", String.valueOf(maxBals));
                            intent.putExtra("context", "Question_Maps_View_Activity");

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
                        Intent intent = new Intent(Question_Maps_View_Activity.this,End.class);
                        if(bals >= maxBals){
                            intent.putExtra("uraa","uraa");
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("bals", String.valueOf(bals));
                        intent.putExtra("maxBals", String.valueOf(maxBals));
                        intent.putExtra("context", "Question_Maps_View_Activity");
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
                                UserModel.baler(bals, "bal_Maps");
                            }
                            Toast.makeText(Question_Maps_View_Activity.this, String.valueOf(maxBals), Toast.LENGTH_SHORT).show();


                            Intent intent = new Intent(Question_Maps_View_Activity.this, End.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("bals", String.valueOf(bals));
                            intent.putExtra("maxBals", String.valueOf(maxBals));
                            intent.putExtra("context", "Question_Maps_View_Activity");


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
                "Великобритания",
                "Македония",
                "Новая Зеландия",
                "Исландия",
                "Великобритания",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%92%D0%B5%D0%BB%D0%B8%D0%BA%D0%BE%D0%B1%D1%80%D0%B8%D1%82%D0%B0%D0%BD%D0%B8%D1%8F%20Map.png?alt=media&token=e05c14b3-27c0-49b4-871a-38a78abf29f1",
                "Карта",
                " gh561jmt789"
        )); //1
        list.add(new QuestionModel(
                "Китай",
                "Иран",
                "Индия",
                "Китай",
                "Монголия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/ChinaFlag.png?alt=media&token=f2bbf8f5-68f3-4816-8bac-b0fdc21e716f",
                "Карта",
                " gh561jmt789"
        )); //2
        list.add(new QuestionModel(
                "Армения",
                "Афганистан",
                "Армения",
                "Индия",
                "Франция",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/ArmeniaMap.png?alt=media&token=7c24bcde-79de-406a-81cb-20e6e93990ad",
                "Карта",
                "215ef5D3215"
        )); //3
        list.add(new QuestionModel(
                "США",
                "Иран",
                "Россия",
                "Канада",
                "США",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/USAmap.png?alt=media&token=4e89d1ea-8a9e-4c70-a255-45edd68ebff1",
                "Карта",
                "65ea156576"
        )); //4
        list.add(new QuestionModel(
                "Грузия",
                "Армения",
                "Грузия",
                "Япония",
                "Уругвай",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/GeorgiaMap.png?alt=media&token=99946fc5-d6fb-42a0-83a1-a92668c1af9d",
                "Карта",
                "21231lp897"
        )); //5
        list.add(new QuestionModel(
                "Россия",
                "Канада",
                "Таджикистан",
                "Россия",
                "Украина",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/RussiaMap.png?alt=media&token=628ffa91-e6c5-43e5-a744-69e794dde8a1",
                "Карта",
                " gh561jmt789"
        )); //6
        list.add(new QuestionModel(
                "Узбекистан",
                "Китай",
                "Казахстан",
                "Катар",
                "Узбекистан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/UzbekistanMap.png?alt=media&token=a719b28a-bd2f-4c91-8960-883fd538ba0a",
                "Карта",
                "215ef5n3215"
        )); //7
        list.add(new QuestionModel(
                "Германия",
                "Британия",
                "Франция",
                "Испания",
                "Германия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/GermanyMap.png?alt=media&token=5c54e357-8880-453c-9ef7-3bc5daa7398c",
                "Карта",
                " gh561jmt789"
        )); //8
        list.add(new QuestionModel(
                "Иран",
                "Иран",
                "Ирак",
                "Сирия",
                "Пакистан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/IranMap.png?alt=media&token=6db411c2-112b-42ac-a0b6-9c3f1d8802d7",
                "Карта",
                " gh561jmt789"
        )); //9
        list.add(new QuestionModel(
                "Индия",
                "Канада",
                "Ирак",
                "Индия",
                "Пакистан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/IndiaFlag.png?alt=media&token=0ec4eccc-7065-46f9-8e6f-968e9d545985",
                "Карта",
                " gh561jmt789"
        )); //10
        list.add(new QuestionModel(
                "Австралия",
                "Австрия",
                "Австралия",
                "Дания",
                "Италия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/AustralliaFlag.png?alt=media&token=da8841af-2a7a-4b5d-b806-61a1ece82dd5",
                "Карта",
                " gh561jmt789"
        )); //11
        list.add(new QuestionModel(
                "Япония",
                "Юж. Корея",
                "КНДР",
                "Китай",
                "Япония",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/JapanFlag.png?alt=media&token=77a66812-135d-46ff-b429-2292090db72b",
                "Карта",
                " gh561jmt789"
        )); //12
        list.add(new QuestionModel(
                "Монголия",
                "Иран",
                "Монголия",
                "Мексика",
                "Лаос",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/MongoliaFlag.png?alt=media&token=a0d15d1c-277f-467c-ba4a-722e4704a76a",
                "Карта",
                " gh561jmt789"
        )); //13
        list.add(new QuestionModel(
                "Алжир",
                "Алжир",
                "Египет",
                "Д.Р. Конго",
                "Емен",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%90%D0%BB%D0%B6%D0%B8%D1%80Flag.png?alt=media&token=c2657e1f-541a-4398-af1a-97f7fccfe23d",
                "Карта",
                " gh561jmt789"
        )); //14
        list.add(new QuestionModel(
                "Чили",
                "Мексика",
                "Чили",
                "Бельгия",
                "Куба",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/ChiliFlag.png?alt=media&token=72024c0f-03cf-499a-b2ab-79cfa6b2f276",
                "Карта",
                " gh561jmt789"
        )); //15

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
        list.add(new QuestionModel(
                "Аргентина",
                "Португалия",
                "Аргентина",
                "Чили",
                "Лаос",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%90%D1%80%D0%B3%D0%B5%D0%BD%D1%82%D0%B8%D0%BD%D0%B0%20Map.png?alt=media&token=32f6d687-2910-4c66-a58e-7787e1b7b92b",
                "Карта",
                " gh561jmt789"
        )); //21
        list.add(new QuestionModel(
                "Египет",
                "Емен",
                "Пакистан",
                "Бангладеш",
                "Египет",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%95%D0%B3%D0%B8%D0%BF%D0%B5%D1%82Map.png?alt=media&token=8ae59244-ab6d-45ed-bbfb-b238711a7984",
                "Карта",
                " gh561jmt789"
        )); //22
        list.add(new QuestionModel(
                "Испания",
                "Испания",
                "Франция",
                "Германия",
                "Марокко",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%98%D1%81%D0%BF%D0%B0%D0%BD%D0%B8%D1%8FMap.png?alt=media&token=46fb23e6-7416-4d4a-9d12-a7c824e6f148",
                "Карта",
                " gh561jmt789"
        )); //23
        list.add(new QuestionModel(
                "Северная Корея",
                "Япония",
                "Южная Корея",
                "Северная Корея",
                "Норвегия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%9D%D0%94%D0%A0%20Map.png?alt=media&token=5e37f430-5b06-4733-9a18-91a2584c9499",
                "Карта",
                " gh561jmt789"
        )); //24
        list.add(new QuestionModel(
                "Новая Зеландия",
                "Япония",
                "Пакистан",
                "Шри-Ланка",
                "Новая Зеландия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9D%D0%BE%D0%B2%D0%B0%D1%8F%D0%97%D0%B5%D0%BB%D0%B0%D0%BD%D0%B4%D0%B8%D1%8F%20Map.png?alt=media&token=46e81e84-d5cf-4fb4-b729-96aa6c6f99c6",
                "Карта",
                " gh561jmt789"
        )); //25
        list.add(new QuestionModel(
                "Норвегия",
                "Швейцария",
                "Норвегия",
                "Мадагаскар",
                "Польша",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9D%D0%BE%D1%80%D0%B2%D0%B5%D0%B3%D0%B8%D1%8F%20Map.png?alt=media&token=4f572c29-953d-4d36-82de-b32fc1bafb1d",
                "Карта",
                " gh561jmt789"
        )); //26
        list.add(new QuestionModel(
                "Пакистан",
                "Бутан",
                "Южная Корея",
                "Израиль",
                "Пакистан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9F%D0%B0%D0%BA%D0%B8%D1%81%D1%82%D0%B0%D0%BD%20Map.png?alt=media&token=d8b87db6-692e-4a2f-b006-3899615bf445",
                "Карта",
                " gh561jmt789"
        )); //27
        list.add(new QuestionModel(
                "Португалия",
                "Аргентина",
                "Палестина",
                "ЮАР",
                "Португалия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9F%D0%BE%D1%80%D1%82%D1%83%D0%B3%D0%B0%D0%BB%D0%B8%D1%8FMap.png?alt=media&token=c8aeda98-11a0-433d-a6d3-f54cfc702123",
                "Карта",
                " gh561jmt789"
        )); //28
        list.add(new QuestionModel(
                "Финляндия",
                "Великобритания",
                "Финляндия",
                "Исландия",
                "Ирландия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A4%D0%B8%D0%BD%D0%BB%D1%8F%D0%BD%D0%B4%D0%B8%D1%8F%20Map.png?alt=media&token=c1a17521-2c0d-4e7c-848e-9f1c95ef68ec",
                "Карта",
                " gh561jmt789"
        )); //29
        list.add(new QuestionModel(
                "Швеция",
                "Чили",
                "Швейцария",
                "Швеция",
                "Италия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A8%D0%B2%D0%B5%D1%86%D0%B8%D1%8F%20Map.png?alt=media&token=d2191f2f-6914-4aef-bef1-50ae52b351c3",
                "Карта",
                " gh561jmt789"
        )); //30
        list.add(new QuestionModel(
                "Афганистан",
                "Пакистан",
                "Таджикистан",
                "Афганистан",
                "Иран",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%90%D1%84%D0%B3%D0%B0%D0%BD%D0%B8%D1%81%D1%82%D0%B0%D0%BD%20Map.png?alt=media&token=3ab74c9c-ed56-438a-9a34-1a23c38f6081",
                "Карта",
                " gh561jmt789"
        )); //31

        list.add(new QuestionModel(
                "Бангладеш",
                "Бангладеш",
                "Непал",
                "Шри-Ланка",
                "Мьянма",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%91%D0%B0%D0%BD%D0%B3%D0%BB%D0%B0%D0%B4%D0%B5%D1%88%20Map.png?alt=media&token=a7b7aa74-cec8-4cb5-ba72-c6e88a2297c1",
                "Карта",
                " gh561jmt789"
        )); //32

        list.add(new QuestionModel(
                "Боливия",
                "Парагвай",
                "Боливия",
                "Уругвай",
                "Эквадор",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%91%D0%BE%D0%BB%D0%B8%D0%B2%D0%B8%D1%8F%20Map.png?alt=media&token=bf8a7010-ed1b-4419-a528-4702b3e69d9c",
                "Карта",
                " gh561jmt789"
        )); //33

        list.add(new QuestionModel(
                "Венесуэла",
                "Венесуэла",
                "Колумбия",
                "Гайана",
                "Бразилия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%92%D0%B5%D0%BD%D0%B5%D1%81%D1%83%D1%8D%D0%BB%D0%B0%20Map.png?alt=media&token=f0649646-dcf3-4760-a122-f99c5d0ec176",
                "Карта",
                " gh561jmt789"
        )); //34

        list.add(new QuestionModel(
                "Вьетнам",
                "Лаос",
                "Камбоджа",
                "Таиланд",
                "Вьетнам",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%92%D1%8C%D0%B5%D1%82%D0%BD%D0%B0%D0%BCMap.png?alt=media&token=5ef295d5-b5f0-4956-a286-0802b9f68caf",
                "Карта",
                " gh561jmt789"
        )); //35

        list.add(new QuestionModel(
                "Греция",
                "Греция",
                "Италия",
                "Турция",
                "Албания",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%93%D1%80%D0%B5%D1%86%D0%B8%D1%8F%20Map.png?alt=media&token=a78178ba-ed25-48e6-8707-17bd00f362ec",
                "Карта",
                " gh561jmt789"
        )); //36

        list.add(new QuestionModel(
                "Доминикана",
                "Доминикана",
                "Куба",
                "Гаити",
                "Ямайка",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%94%D0%BE%D0%BC%D0%B8%D0%BD%D0%B8%D0%BA%D0%B0%D0%BD%D0%B0.png?alt=media&token=1be4d865-53f8-4073-b28b-8abf773d05a2",
                "Карта",
                " gh561jmt789"
        )); //37

        list.add(new QuestionModel(
                "Индонезия",
                "Филиппины",
                "Индонезия",
                "Малайзия",
                "Таиланд",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%98%D0%BD%D0%B4%D0%BE%D0%BD%D0%B5%D0%B7%D0%B8%D1%8F%20Map.png?alt=media&token=619578e7-2b82-4cae-8708-0338e2d122ba",
                "Карта",
                " gh561jmt789"
        )); //38

        list.add(new QuestionModel(
                "Иордания",
                "Иордания",
                "Саудовская Аравия",
                "Ирак",
                "Сирия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%98%D0%BE%D1%80%D0%B4%D0%B0%D0%BD%D0%B8%D1%8F%20Map.png?alt=media&token=616a4a9d-2a32-4efe-9113-6041621adcdd",
                "Карта",
                " gh561jmt789"
        )); //39

        list.add(new QuestionModel(
                "Исландия",
                "Гренландия",
                "Ирландия",
                "Исландия",
                "Норвегия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%98%D1%81%D0%BB%D0%B0%D0%BD%D0%B4%D0%B8%D1%8F%20Map.png?alt=media&token=90ec44ab-b807-46ce-acf8-2996de9d4b99",
                "Карта",
                " gh561jmt789"
        )); //40

        list.add(new QuestionModel(
                "Колумбия",
                "Венесуэла",
                "Колумбия",
                "Эквадор",
                "Перу",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%BE%D0%BB%D1%83%D0%BC%D0%B1%D0%B8%D1%8F%20Map.png?alt=media&token=95ca328a-6028-4686-a573-b3cff019e3ac",
                "Карта",
                " gh561jmt789"
        )); //41

        list.add(new QuestionModel(
                "Куба",
                "Куба",
                "Ямайка",
                "Доминикана",
                "Гаити",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D1%83%D0%B1%D0%B0%20Map.png?alt=media&token=06284f43-0ed6-46ae-b2c3-9b82a97935d6",
                "Карта",
                " gh561jmt789"
        )); //42

        list.add(new QuestionModel(
                "Мадагаскар",
                "Мадагаскар",
                "Маврикий",
                "Сейшелы",
                "Коморы",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9C%D0%B0%D0%B4%D0%B0%D0%B3%D0%B0%D1%81%D0%BA%D0%B0%D1%80%20Map.png?alt=media&token=4167a168-ea84-48be-ab63-b8954d31c6c5",
                "Карта",
                " gh561jmt789"
        )); //43

        list.add(new QuestionModel(
                "Непал",
                "Бутан",
                "Бангладеш",
                "Непал",
                "Шри-Ланка",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9D%D0%B5%D0%BF%D0%B0%D0%BB%20Map.png?alt=media&token=fef1dec2-9421-435c-b4b5-c164c749cb28",
                "Карта",
                " gh561jmt789"
        )); //44

        list.add(new QuestionModel(
                "Панама",
                "Коста-Рика",
                "Панама",
                "Никарагуа",
                "Гватемала",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9F%D0%B0%D0%BD%D0%B0%D0%BC%D0%B0%20Map.png?alt=media&token=4a64563d-961b-4fb8-9982-a1fd5038080d",
                "Карта",
                " gh561jmt789"
        )); //45

        list.add(new QuestionModel(
                "Сирия",
                "Ливан",
                "Сирия",
                "Иордания",
                "Ирак",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A1%D0%B8%D1%80%D0%B8%D1%8F%20Map.png?alt=media&token=60435751-b1eb-4be5-aec8-b861de57490b",
                "Карта",
                " gh561jmt789"
        )); //46

        list.add(new QuestionModel(
                "Сомали",
                "Кения",
                "Сомали",
                "Эфиопия",
                "Джибути",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A1%D0%BE%D0%BC%D0%B0%D0%BB%D0%B8%20Map.png?alt=media&token=17bfacb7-2076-436f-8b57-c01d72b8409e",
                "Карта",
                " gh561jmt789"
        )); //47

        list.add(new QuestionModel(
                "Таиланд",
                "Таиланд",
                "Вьетнам",
                "Лаос",
                "Мьянма",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A2%D0%B0%D0%B8%D0%BB%D0%B0%D0%BD%D0%B4%20Map.png?alt=media&token=42e63c4f-e3b5-4cfe-a837-a8639c7fe8c9",
                "Карта",
                " gh561jmt789"
        )); //48

        list.add(new QuestionModel(
                "Туркменистан",
                "Казахстан",
                "Узбекистан",
                "Туркменистан",
                "Таджикистан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A2%D1%83%D1%80%D0%BA%D0%BC%D0%B5%D0%BD%D0%B8%D1%81%D1%82%D0%B0%D0%BD%20Map.png?alt=media&token=6b5617e1-75f4-498f-bf98-13857b515699",
                "Карта",
                " gh561jmt789"
        )); //49

        list.add(new QuestionModel(
                "Южная Корея",
                "Япония",
                "Китай",
                "Северная Корея",
                "Южная Корея",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%AE%D0%B6%D0%BD%D0%B0%D1%8F%D0%9A%D0%BE%D1%80%D0%B5%D1%8F%20Map.png?alt=media&token=52942198-229a-4137-8884-0b65e31b3d0b",
                "Карта",
                " gh561jmt789"
        )); //50

        questionAdapter.notifyDataSetChanged();
    }
}

