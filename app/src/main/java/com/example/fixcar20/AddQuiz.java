package com.example.fixcar20;

import static com.example.fixcar20.QuestionAdapter.context;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class AddQuiz extends AppCompatActivity {
    LinearLayout addQuizLayout;
    TextView textViewTitle;
    EditText editTextQuestion;
    EditText editTextOption1;
    EditText editTextOption2;
    EditText editTextOption3;
    EditText editTextOption4;
    EditText editTextCorrectAnswer;
    Button buttonAddQuestion;
    ;;;
    RelativeLayout addQuestionLayout;
    EditText quizName;
    Button buttonAddQuiz;

    Button finishButton;
    FirebaseUser user;
    UserModel model;
    OnlineQuestionModel questionModel = new OnlineQuestionModel();
    List<String> questions, answers1, answers2, answers3, answers4, correctAnswers;
    String quizname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);
        // Находим все представления в первой вложенной RelativeLayout

        addQuestionLayout = findViewById(R.id.addQuestion);
        textViewTitle = addQuestionLayout.findViewById(R.id.textViewTitle);
        editTextQuestion = addQuestionLayout.findViewById(R.id.editTextQuestion);
        editTextOption1 = addQuestionLayout.findViewById(R.id.editTextOption1);
        editTextOption2 = addQuestionLayout.findViewById(R.id.editTextOption2);
        editTextOption3 = addQuestionLayout.findViewById(R.id.editTextOption3);
        editTextOption4 = addQuestionLayout.findViewById(R.id.editTextOption4);
        editTextCorrectAnswer = addQuestionLayout.findViewById(R.id.editTextCorrectAnswer);
        buttonAddQuestion = addQuestionLayout.findViewById(R.id.buttonAddQuestion);

// Находим все представления во второй вложенной LinearLayout
        addQuizLayout = findViewById(R.id.addQuiz);
        quizName = addQuizLayout.findViewById(R.id.QuestionName);
        buttonAddQuiz = addQuizLayout.findViewById(R.id.buttonAddQuiz);

        questions = new ArrayList<>();
        answers1 = new ArrayList<>();
        answers2 = new ArrayList<>();
        answers3 = new ArrayList<>();
        answers4 = new ArrayList<>();
        correctAnswers = new ArrayList<>();

        user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    model = task.getResult().toObject(UserModel.class);


                }
            }
        });

// Находим кнопку "finish"
        finishButton = findViewById(R.id.finish);





        buttonAddQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quizName.getText().length() >= 4) {
                    String id = user.getUid();
                    questionModel.setUsername(model.getName());
                    questionModel.setCountryName(quizName.getText().toString());
                     quizname = quizName.getText().toString();

                            addQuizLayout.setVisibility(View.GONE);
                    addQuestionLayout.setVisibility(View.VISIBLE);


                } else {
                    quizName.setError("length must be more than 4");
                }
            }
        });

        buttonAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextQuestion.getText().length() >= 4 && editTextOption1.getText().length() >= 4 &&
                        editTextOption2.getText().length() >= 4 && editTextOption3.getText().length() >= 4 &&
                        editTextOption4.getText().length() >= 4 && editTextCorrectAnswer.getText().length() >= 4) {

                    questions.add(editTextQuestion.getText().toString());
                    answers1.add(editTextOption1.getText().toString());
                    answers2.add(editTextOption2.getText().toString());
                    answers3.add(editTextOption3.getText().toString());
                    answers4.add(editTextOption4.getText().toString());
                     correctAnswers.add(editTextCorrectAnswer.getText().toString());



                    editTextQuestion.setText("");
                    editTextOption1.setText("");
                    editTextOption2.setText("");
                    editTextOption3.setText("");
                    editTextOption4.setText("");
                    editTextCorrectAnswer.setText("");
                    finishButton.setVisibility(View.VISIBLE);
                }
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionModel.setQuestions(questions);
                questionModel.setOnlineAnswers1(answers1);
                questionModel.setOnlineAnswers2(answers2);
                questionModel.setOnlineAnswers3(answers3);
                questionModel.setOnlineAnswers4(answers4);
                questionModel.setCorrectAnswers(correctAnswers);
                questionModel.setOnlineImages(new ArrayList<>());

                List<String> images = new ArrayList<>();
                CollectionReference reference =  FirebaseFirestore.getInstance().collection("Quiz");
                String id = reference.document().getId();

                questionModel = new OnlineQuestionModel(correctAnswers,quizname,answers1,answers2,answers3,answers4,images,id,questions,model.getName());




                reference.document(id).set(questionModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        MotionToast.Companion.createColorToast(AddQuiz.this,
                                "Опрос добавлен",
                                "",
                                MotionToastStyle.SUCCESS,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(AddQuiz.this, www.sanju.motiontoast.R.font.helveticabold));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        MotionToast.Companion.createColorToast(AddQuiz.this,
                                "Произошла ошибка",
                                "Проверьте подключение к интернету",
                                MotionToastStyle.ERROR,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(AddQuiz.this, www.sanju.motiontoast.R.font.helveticabold));
                    }
                });
            }
        });


    }
}