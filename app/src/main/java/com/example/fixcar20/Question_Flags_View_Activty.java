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

public class Question_Flags_View_Activty extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;
    private List<QuestionModel> list;
    private int hearts = 3;
    ImageView heart2;
    ImageView heart3;
   private int bals = 0;
   private Long maxBals = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_flags_view_activty);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleview);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful() && task.getResult().exists()) {
                    if (task.getResult().get("bal_flags") == null) {
                        
                        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update("bal_flags", 0).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });

                    } else {

                        maxBals = (Long) task.getResult().get("bal_flags");
                    }
                }
            }
        });
        // Assigning recyclerView
        ///////
        questionAdapter = new QuestionAdapter(list, Question_Flags_View_Activty.this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Question_Flags_View_Activty.this));
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
                        Intent intent = new Intent(Question_Flags_View_Activty.this,End.class);
                        if(bals >= maxBals){
                            intent.putExtra("uraa","uraa");
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("bals", String.valueOf(bals));
                        intent.putExtra("maxBals", String.valueOf(maxBals));
                        intent.putExtra("context", "Question_Flags_View_Activty");
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
                                UserModel.baler(bals, "bal_flags");
                            }
                            Intent intent = new Intent(Question_Flags_View_Activty.this, End.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("bals", String.valueOf(bals));
                            intent.putExtra("maxBals", String.valueOf(maxBals));
                            intent.putExtra("context", "Question_Flags_View_Activty");
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
                        Intent intent = new Intent(Question_Flags_View_Activty.this,End.class);
                        if(bals >= maxBals){
                            intent.putExtra("uraa","uraa");
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("bals", String.valueOf(bals));
                        intent.putExtra("maxBals", String.valueOf(maxBals));
                        intent.putExtra("context", "Question_Flags_View_Activty");
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
                                UserModel.baler(bals, "bal_flags");
                            }
                            Intent intent = new Intent(Question_Flags_View_Activty.this, End.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("bals", String.valueOf(bals));
                            intent.putExtra("maxBals", String.valueOf(maxBals));
                            intent.putExtra("context", "Question_Flags_View_Activty");
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
                        Intent intent = new Intent(Question_Flags_View_Activty.this,End.class);
                        if(bals >= maxBals){
                            intent.putExtra("uraa","uraa");
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("bals", String.valueOf(bals));
                        intent.putExtra("maxBals", String.valueOf(maxBals));
                        intent.putExtra("context", "Question_Flags_View_Activty");
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
                                UserModel.baler(bals, "bal_flags");
                            }
                            Intent intent = new Intent(Question_Flags_View_Activty.this, End.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("bals", String.valueOf(bals));
                            intent.putExtra("maxBals", String.valueOf(maxBals));
                            intent.putExtra("context", "Question_Flags_View_Activty");
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
                        Intent intent = new Intent(Question_Flags_View_Activty.this,End.class);
                        if(bals >= maxBals){
                            intent.putExtra("uraa","uraa");
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("bals", String.valueOf(bals));
                        intent.putExtra("maxBals", String.valueOf(maxBals));
                        intent.putExtra("context", "Question_Flags_View_Activty");
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
                                UserModel.baler(bals, "bal_flags");
                            }
                            Intent intent = new Intent(Question_Flags_View_Activty.this, End.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("bals", String.valueOf(bals));
                            intent.putExtra("maxBals", String.valueOf(maxBals));
                            intent.putExtra("context", "Question_Flags_View_Activty");
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
        list.add(new QuestionModel(
                "Ватикан",
                "Сан-Марино",
                "Ватикан",
                "Бутан",
                "Марокко",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%92%D0%90%D0%A2%D0%98%D0%9A%D0%90%D0%9D.png?alt=media&token=0617c1cc-7284-4ab8-9558-8606e296c457",
                "Флаг",
                "215ef5n3215"
        )); //21
        list.add(new QuestionModel(
                "Греция",
                "Грузия",
                "Финландия",
                "Норвегия",
                "Греция",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%93%D0%A0%D0%95%D0%A6%D0%98%D0%AF.png?alt=media&token=2377b3eb-5784-4763-bbff-75b16bd7e34b",
                "Флаг",
                "215ef5n3215"
        )); //22
        list.add(new QuestionModel(
                "Испания",
                "Индия",
                "Португалия",
                "Испания",
                "Израиль",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%98%D0%A1%D0%9F%D0%90%D0%9D%D0%98%D0%AF.png?alt=media&token=daaa6384-1d91-4115-8ae0-819a15d67573",
                "Флаг",
                "215ef5n3215"
        )); //23
        list.add(new QuestionModel(
                "Вьетнам",
                "Мьянма",
                "Лаос",
                "Вьетнам",
                "Палестина",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%92%D0%AC%D0%95%D0%A2%D0%9D%D0%90%D0%9C.png?alt=media&token=4c48ec51-eb50-46a0-ba94-2158672db622",
                "Флаг",
                "215ef5n3215"
        )); //24
        list.add(new QuestionModel(
                "Камбоджа",
                "Мьянма",
                "Лаос",
                "Палестина",
                "Камбоджа",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%90%D0%9C%D0%91%D0%9E%D0%94%D0%96%D0%90.png?alt=media&token=6033b99f-5c47-43d6-a35a-b83428c885c7",
                "Флаг",
                "215ef5n3215"
        )); //25
        list.add(new QuestionModel(
                "Лаос",
                "Лаос",
                "Латвия",
                "Таиланд",
                "Непал",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9B%D0%90%D0%9E%D0%A1.png?alt=media&token=20cb7274-8ed2-4c1a-9871-ff5dd7addfe1",
                "Флаг",
                "215ef5n3215"
        )); //26
        list.add(new QuestionModel(
                "Мьянма",
                "Литва",
                "Турция",
                "Тайвань",
                "Мьянма",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9C%D0%AC%D0%AF%D0%9D%D0%9C%D0%90.png?alt=media&token=4ebbee77-44e3-4bbf-8712-bcd74e084bcd",
                "Флаг",
                "215ef5n3215"
        )); //27
        list.add(new QuestionModel(
                "Непал",
                "Индия",
                "Пакистан",
                "Непал",
                "Бутан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9D%D0%95%D0%9F%D0%90%D0%9B.png?alt=media&token=c79a5aa5-f93d-4994-b6a4-1bcbaea0c063",
                "Флаг",
                "215ef5n3215"
        )); //28
        list.add(new QuestionModel(
                "Палестина",
                "ОАЭ",
                "Палестина",
                "Ирак",
                "Сирия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9F%D0%90%D0%9B%D0%95%D0%A1%D0%A2%D0%98%D0%9D%D0%90.png?alt=media&token=0b49642b-df78-4ba9-a7f8-a758827e9ea3",
                "Флаг",
                "215ef5n3215"
        )); //29
        list.add(new QuestionModel(
                "Португалия",
                "Португалия",
                "Гана",
                "Испания",
                "Бутан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9F%D0%9E%D0%A0%D0%A2%D0%A3%D0%93%D0%90%D0%9B%D0%98%D0%AF.png?alt=media&token=72de1edc-59e1-4288-9c75-e999a2cc6ab9",
                "Флаг",
                "215ef5n3215"
        )); //30

        list.add(new QuestionModel(
                "Бутан",
                "Бутан",
                "Китай",
                "Непал",
                "Индия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%91%D0%A3%D0%A2%D0%90%D0%9D.png?alt=media&token=39194b9a-e0f2-4bd4-9717-575091c8d92d",
                "Флаг",
                "215ef5n3215"
        )); //31

        list.add(new QuestionModel(
                "Тайвань",
                "Китай",
                "Тайвань",
                "Япония",
                "Корея",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A2%D0%90%D0%99%D0%92%D0%90%D0%9D%D0%AC.png?alt=media&token=9b93b66d-e2dd-4662-bb21-2262ad4fdbdf",
                "Флаг",
                "215ef5n3216"
        )); //32

        list.add(new QuestionModel(
                "Марокко",
                "Алжир",
                "Марокко",
                "Тунис",
                "Ливия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9C%D0%90%D0%A0%D0%9E%D0%9A%D0%9A%D0%9E.png?alt=media&token=94958829-b69f-4d90-b4be-407c5133333e",
                "Флаг",
                "215ef5n3217"
        )); //33

        list.add(new QuestionModel(
                "Монако",
                "Франция",
                "Италия",
                "Монако",
                "Испания",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9C%D0%9E%D0%9D%D0%90%D0%9A%D0%9E.png?alt=media&token=bff10867-ff28-4048-ad15-801f3224bf51",
                "Флаг",
                "215ef5n3218"
        )); //34

        list.add(new QuestionModel(
                "Индонезия",
                "Малайзия",
                "Филиппины",
                "Индонезия",
                "Таиланд",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%98%D0%9D%D0%94%D0%9E%D0%9D%D0%95%D0%97%D0%98%D0%AF.png?alt=media&token=6725eec6-ccd2-4712-a5e6-a8c78b74a6a2",
                "Флаг",
                "215ef5n3219"
        )); //35

        list.add(new QuestionModel(
                "Бельгия",
                "Франция",
                "Бельгия",
                "Нидерланды",
                "Германия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%91%D0%95%D0%9B%D0%AC%D0%93%D0%98%D0%AF.png?alt=media&token=973bcc07-14f0-4de0-8410-e0031c5740b1",
                "Флаг",
                "215ef5n3220"
        )); //36

        list.add(new QuestionModel(
                "Израиль",
                "Египет",
                "Иордания",
                "Сирия",
                "Израиль",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%98%D0%97%D0%A0%D0%90%D0%98%D0%9B%D0%AC.png?alt=media&token=c3266152-4e86-4588-8db0-ca379d4948d7",
                "Флаг",
                "215ef5n3221"
        )); //37

        list.add(new QuestionModel(
                "Ирак",
                "Ирак",
                "Сирия",
                "Иран",
                "Турция",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%98%D0%A0%D0%90%D0%9A.png?alt=media&token=af228c36-00e3-4403-a8b6-47738c64912b",
                "Флаг",
                "215ef5n3222"
        )); //38

        list.add(new QuestionModel(
                "Ливан",
                "Ливия",
                "Ливан",
                "Иордания",
                "Израиль",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9B%D0%98%D0%92%D0%90%D0%9D.png?alt=media&token=175f688c-6b5d-4502-919c-dc5e17a27937",
                "Флаг",
                "215ef5n3223"
        )); //39

        list.add(new QuestionModel(
                "Польша",
                "Чехия",
                "Германия",
                "Польша",
                "Словакия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9F%D0%9E%D0%9B%D0%AC%D0%A8%D0%90.png?alt=media&token=b2291521-c2f1-4645-b787-ed5632f7cbb4",
                "Флаг",
                "215ef5n3224"
        )); //40

        list.add(new QuestionModel(
                "Сербия",
                "Россия",
                "Сербия",
                "Черногория",
                "Хорватия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A1%D0%95%D0%A0%D0%91%D0%98%D0%AF.png?alt=media&token=b718fb2b-ceca-43f4-afb7-947ad8cfe33e",
                "Флаг",
                "215ef5n3225"
        )); //41

        list.add(new QuestionModel(
                "Сирия",
                "Ливан",
                "Иордания",
                "Сирия",
                "Турция",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A1%D0%98%D0%A0%D0%98%D0%AF.png?alt=media&token=37aa5df2-cf27-4a82-b9e4-9348aec6d158",
                "Флаг",
                "215ef5n3226"
        )); //42

        list.add(new QuestionModel(
                "Словакия",
                "Словакия",
                "Чехия",
                "Словения",
                "Россия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A1%D0%9B%D0%9E%D0%92%D0%90%D0%9A%D0%98%D0%AF.png?alt=media&token=3ee4a0e7-7d60-4155-b9d9-b7b96b3ff872",
                "Флаг",
                "215ef5n3227"
        )); //43

        list.add(new QuestionModel(
                "Словения",
                "Словакия",
                "Словения",
                "Россия",
                "Сербия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A1%D0%9B%D0%9E%D0%92%D0%95%D0%9D%D0%98%D0%AF.png?alt=media&token=86f339f3-5d15-4445-88a8-66fce788771c",
                "Флаг",
                "215ef5n3228"
        )); //44

        list.add(new QuestionModel(
                "Шри-Ланка",
                "Индия",
                "Мальдивы",
                "Пакистан",
                "Шри-Ланка",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%A8%D0%A0%D0%98-%D0%9B%D0%90%D0%9D%D0%9A%D0%90.png?alt=media&token=f039b404-fcc7-4d96-b73c-e03a3e1039a4",
                "Флаг",
                "215ef5n3229"
        )); //45

        list.add(new QuestionModel(
                "Дания",
                "Норвегия",
                "Швеция",
                "Дания",
                "Финляндия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%94%D0%90%D0%9D%D0%98%D0%AF.png?alt=media&token=7a85b5d9-09a0-4467-bdc3-f29f023e75b2",
                "Флаг",
                "215ef5n3228"
        )); //46

        list.add(new QuestionModel(
                "Мозамбик",
                "Малави",
                "Замбия",
                "Мозамбик",
                "Зимбабве",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9C%D0%9E%D0%97%D0%90%D0%9C%D0%91%D0%98%D0%9A.png?alt=media&token=6fb8ada8-90b9-49d6-9442-1489798712ed",
                "Флаг",
                "215ef5n3229"
        )); //47

        list.add(new QuestionModel(
                "Доминикана",
                "Гаити",
                "Доминикана",
                "Куба",
                "Ямайка",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%94%D0%9E%D0%9C%D0%98%D0%9D%D0%98%D0%9A%D0%90%D0%9D%D0%90.png?alt=media&token=3f0533b5-3cde-43d0-9699-83eff548eb1f",
                "Флаг",
                "215ef5n3230"
        )); //48

        list.add(new QuestionModel(
                "Колумбия",
                "Колумбия",
                "Венесуэла",
                "Эквадор",
                "Перу",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%9A%D0%9E%D0%9B%D0%A3%D0%9C%D0%91%D0%98%D0%AF.png?alt=media&token=76b0cd6e-b5f2-4fc8-ac42-db964c956d4c",
                "Флаг",
                "215ef5n3231"
        )); //49

        list.add(new QuestionModel(
                "Венесуэла",
                "Колумбия",
                "Венесуэла",
                "Эквадор",
                "Перу",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/%D0%92%D0%95%D0%9D%D0%95%D0%A1%D0%A3%D0%AD%D0%9B%D0%90.png?alt=media&token=b90ebe16-b381-43c7-bee9-8cdb97e73c4f",
                "Флаг",
                "215ef5n3232"
        )); //50


        questionAdapter.notifyDataSetChanged();
    }
}

