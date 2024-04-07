package com.example.fixcar20.ui.Mayraqaxaq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.fixcar20.Mode_Selection;
import com.example.fixcar20.R;

import java.util.Random;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class Armenia extends AppCompatActivity {
    Button astana,tbilisi,Yerevan,glendel;
    ImageView imageView;
    TextView nameofCountry;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armenia);

    }

    public void next(View view) {

        MotionToast.Companion.createColorToast(this,
                "Ответ правелен!",
                "Молодец!",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helveticabold));

        // Создаем объект Random для генерации случайных чисел
        Random random = new Random();
        // Генерируем случайное число от 1 до 6
        int randomLevel = random.nextInt(4) + 1;

        // Создаем Intent для запуска соответствующего уровня
        Intent intent;
        switch (randomLevel) {
            case 1:
                intent = new Intent(Armenia.this, Vrastan.class);
                break;
            case 2:
                intent = new Intent(Armenia.this, Egiptos.class);
                break;
            case 3:
                intent = new Intent(Armenia.this, Germania.class);
                break;
            case 4:
                intent = new Intent(Armenia.this, Hndkastan.class);
                break;


            default:
                // Handle unexpected case
                return;
        }
        // Запускаем уровень
        startActivity(intent);
    }

    public void sxal(View view) {
        MotionToast.Companion.createColorToast(this,
                "Ответ неправильный!",
                "подумай еще",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helveticabold));
    }
}
