// package com.example.fixcar20;
//
// import static com.example.fixcar20.QuestionAdapter.context;
//
// import androidx.appcompat.app.AppCompatActivity;
// import androidx.core.content.res.ResourcesCompat;
// import androidx.recyclerview.widget.LinearLayoutManager;
// import androidx.recyclerview.widget.RecyclerView;
//
// import android.annotation.SuppressLint;
// import android.app.Activity;
// import android.os.Bundle;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import www.sanju.motiontoast.MotionToast;
// import www.sanju.motiontoast.MotionToastStyle;
//
// public class Question_Maps_View_Activity extends AppCompatActivity {
//
//     private RecyclerView recyclerView;
//     private QuestionAdapter questionAdapter;
//     private List<QuestionModel> list;
//
//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_question_flags_view_activty);
//         list = new ArrayList<>();
//         recyclerView = findViewById(R.id.recycleview); // Assigning recyclerView
//         ///////
//         questionAdapter = new QuestionAdapter(list, Question_Maps_View_Activity.this);
//
//         recyclerView.setHasFixedSize(true);
//         recyclerView.setLayoutManager(new LinearLayoutManager(Question_Maps_View_Activity.this));
//         recyclerView.setAdapter(questionAdapter);
//
//         questionAdapter.OnPressed(new QuestionAdapter.OnPressed() {
//             @SuppressLint("NotifyDataSetChanged")
//             @Override
//             public void onanswer1(int position, String answer1, String answer) {
//                 if (answer1.equals(answer)) {
//                     MotionToast.Companion.createColorToast((Activity) context,
//                             "Ответ правелен!",
//                             "Молодец!",
//                             MotionToastStyle.SUCCESS,
//                             MotionToast.GRAVITY_BOTTOM,
//                             MotionToast.LONG_DURATION,
//                             ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
//
//                     list.remove(position);
//                     questionAdapter.notifyDataSetChanged();
//
//                 } else {
//                     MotionToast.Companion.createColorToast((Activity) context,
//                             "Ответ неправильный!",
//                             "подумай еще",
//                             MotionToastStyle.ERROR,
//                             MotionToast.GRAVITY_BOTTOM,
//                             MotionToast.LONG_DURATION,
//                             ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
// //
//
//                 }
//             }
//
//             @SuppressLint("NotifyDataSetChanged")
//             @Override
//             public void onanswer2(int position, String answer2, String answer) {
//                 if (answer2.equals(answer)) {
//                     MotionToast.Companion.createColorToast((Activity) context,
//                             "Ответ правелен!",
//                             "Молодец!",
//                             MotionToastStyle.SUCCESS,
//                             MotionToast.GRAVITY_BOTTOM,
//                             MotionToast.LONG_DURATION,
//                             ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
//
//                     list.remove(position);
//                     questionAdapter.notifyDataSetChanged();
//
//                 } else {
//                     MotionToast.Companion.createColorToast((Activity) context,
//                             "Ответ неправильный!",
//                             "подумай еще",
//                             MotionToastStyle.ERROR,
//                             MotionToast.GRAVITY_BOTTOM,
//                             MotionToast.LONG_DURATION,
//                             ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
// //
//
//                 }
//
//             }
//
//             @SuppressLint("NotifyDataSetChanged")
//             @Override
//             public void onanswer3(int position, String answer3, String answer) {
//                 if (answer3.equals(answer)) {
//                     MotionToast.Companion.createColorToast((Activity) context,
//                             "Ответ правелен!",
//                             "Молодец!",
//                             MotionToastStyle.SUCCESS,
//                             MotionToast.GRAVITY_BOTTOM,
//                             MotionToast.LONG_DURATION,
//                             ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
//
//                     list.remove(position);
//                     questionAdapter.notifyDataSetChanged();
//                 } else {
//                     MotionToast.Companion.createColorToast((Activity) context,
//                             "Ответ неправильный!",
//                             "подумай еще",
//                             MotionToastStyle.ERROR,
//                             MotionToast.GRAVITY_BOTTOM,
//                             MotionToast.LONG_DURATION,
//                             ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
// //
//
//                 }
//
//             }
//
//             @SuppressLint("NotifyDataSetChanged")
//             @Override
//             public void onanswer4(int position, String answer4, String answer) {
//                 if (answer4.equals(answer)) {
//                     MotionToast.Companion.createColorToast((Activity) context,
//                             "Ответ правелен!",
//                             "Молодец!",
//                             MotionToastStyle.SUCCESS,
//                             MotionToast.GRAVITY_BOTTOM,
//                             MotionToast.LONG_DURATION,
//                             ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
//
//                     list.remove(position);
//                     questionAdapter.notifyDataSetChanged();
//                 } else {
//                     MotionToast.Companion.createColorToast((Activity) context,
//                             "Ответ неправильный!",
//                             "подумай еще",
//                             MotionToastStyle.ERROR,
//                             MotionToast.GRAVITY_BOTTOM,
//                             MotionToast.LONG_DURATION,
//                             ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helveticabold));
// //
//
//                 }
//
//             }
//         });
//
//
//         loadData();
//     }
//
//     @SuppressLint("NotifyDataSetChanged")
//     private void loadData() {
//         list.add(new QuestionModel(
//                 "Армения",
//                 "Грузия",
//                 "Колумбия",
//                 "Армения",
//                 "Бангладеш",
//                 "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/armenia.png?alt=media&token=144ac9c4-b9df-48e1-aa1b-340d4d76df2a",
//                 "Карта",
//                 "215ef5D3215"
//         ));
//         list.add(new QuestionModel(
//                 "ОАЭ",
//                 "Иран",
//                 "Кувейт",
//                 "Катар",
//                 "ОАЭ",
//                 "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/united-arab-emirates.png?alt=media&token=9e2b42e4-224f-4ac8-9144-6f71a209e6cb",
//                 "Карта",
//                 "65ea156576"
//         ));
//         list.add(new QuestionModel(
//                 "Иран",
//                 "Пакистан",
//                 "Иран",
//                 "Узбекистан",
//                 "Мексика",
//                 "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/world.png?alt=media&token=b9e07280-1047-4b1f-adc6-e92710904859",
//                 "Карта",
//                 "21231lp897"
//         ));
//         list.add(new QuestionModel(
//                 "Казахстан",
//                 "Казахстан",
//                 "Таджикистан",
//                 "Узбекистан",
//                 "Кыргызстан",
//                 "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/flag%20(2).png?alt=media&token=ffbd6a74-87a1-4c9c-b3ac-cb3deafa6cdb",
//                 "Карта",
//                 " gh561jmt789"
//         ));
//         list.add(new QuestionModel(
//                 "Грузия",
//                 "Австралия",
//                 "Армения",
//                 "Финландия",
//                 "Грузия",
//                 "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/georgia.png?alt=media&token=7864862e-b592-4427-bebb-b7f2a153e54e",
//                 "Карта",
//                 "215ef5n3215"
//         ));
//         questionAdapter.notifyDataSetChanged();
//     }
// }
//


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
                "Армения",
                "Афганистан",
                "Армения",
                "Индия",
                "Франция",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/ArmeniaMap.png?alt=media&token=d5c824af-0cec-49ae-9ca2-1e4d1350c670",
                "Карта",
                "215ef5D3215"
        ));
        list.add(new QuestionModel(
                "США",
                "Иран",
                "Россия",
                "Канада",
                "США",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/USAmap.png?alt=media&token=4e89d1ea-8a9e-4c70-a255-45edd68ebff1",
                "Карта",
                "65ea156576"
        ));
        list.add(new QuestionModel(
                "Грузия",
                "Армения",
                "Грузия",
                "Япония",
                "Уругвай",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/GeorgiaMap.png?alt=media&token=544289a5-c90b-41c9-b091-d19b2aa3fafa",
                "Карта",
                "21231lp897"
        ));
        list.add(new QuestionModel(
                "Россия",
                "Канада",
                "Таджикистан",
                "Россия",
                "Украина",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/RussiaMap.png?alt=media&token=a104fd66-53ed-426b-9268-c4f0565fba9f",
                "Карта",
                " gh561jmt789"
        ));
        list.add(new QuestionModel(
                "Узбекистан",
                "Китай",
                "Казахстан",
                "Катар",
                "Узбекистан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/UzbekistanMap.png?alt=media&token=a719b28a-bd2f-4c91-8960-883fd538ba0a",
                "Карта",
                "215ef5n3215"
        ));
        list.add(new QuestionModel(
                "Германия",
                "Британия",
                "Франция",
                "Испания",
                "Германия",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/GermanyMap.png?alt=media&token=5c54e357-8880-453c-9ef7-3bc5daa7398c",
                "Карта",
                " gh561jmt789"
        ));
        list.add(new QuestionModel(
                "Иран",
                "Иран",
                "Ирак",
                "Сирия",
                "Пакистан",
                "https://firebasestorage.googleapis.com/v0/b/fixcar2-0.appspot.com/o/IranMap.png?alt=media&token=6db411c2-112b-42ac-a0b6-9c3f1d8802d7",
                "Карта",
                " gh561jmt789"
        ));
        questionAdapter.notifyDataSetChanged();
    }
}

