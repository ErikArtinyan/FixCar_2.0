package com.example.fixcar20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fixcar20.ui.Mayraqaxaq.Armenia;
import com.example.fixcar20.ui.Mayraqaxaq.Egiptos;
import com.example.fixcar20.ui.Mayraqaxaq.Germania;
import com.example.fixcar20.ui.Mayraqaxaq.Vrastan;

import java.util.Random;

public class Mode_Selection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selection);
    }

    public void next(View view) {
            // Создаем объект Random для генерации случайных чисел
            Random random = new Random();
            // Генерируем случайное число от 1 до 6
            int randomLevel = random.nextInt(4) + 1;

            // Создаем Intent для запуска соответствующего уровня
            Intent intent;
            switch (randomLevel) {
                case 1:
                    intent = new Intent(Mode_Selection.this, Vrastan.class);
                    break;
                case 2:
                    intent = new Intent(Mode_Selection.this, Egiptos.class);
                    break;
                case 3:
                    intent = new Intent(Mode_Selection.this, Armenia.class);
                    break;
                case 4:
                    intent = new Intent(Mode_Selection.this, Germania.class);
                    break;

                default:
                    // Handle unexpected case
                    return;
            }
            // Запускаем уровень
            startActivity(intent);
        }
    }
