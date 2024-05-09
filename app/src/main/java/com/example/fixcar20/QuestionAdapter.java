package com.example.fixcar20;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.health.connect.datatypes.HeightRecord;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.google.api.Distribution;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.Random;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder>{
    private static List<QuestionModel> list;
    static Context context;
    static OnPressed onPressed;

    public QuestionAdapter(List<QuestionModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.capitals_item, parent, false);
        return new QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
        if(!list.isEmpty()) {
            if (list.get(position).getImageURL() != null && !list.get(position).getImageURL().isEmpty()) {
                Random random = new Random();
                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

                Glide.with(context.getApplicationContext())
                        .load(list.get(position).getImageURL())
                        .placeholder(new ColorDrawable(color))
                        .timeout(7000)
                        .into(holder.countryImage);
            }else{
                holder.countryImage.setVisibility(View.GONE);

                ViewGroup.LayoutParams params = holder.layout.getLayoutParams();
                params.height= ViewGroup.LayoutParams.WRAP_CONTENT;
                holder.layout.setLayoutParams(params);
            }

            holder.answer1.setText(list.get(position).getAnswer1());
            holder.answer2.setText(list.get(position).getAnswer2());
            holder.answer3.setText(list.get(position).getAnswer3());
            holder.answer4.setText(list.get(position).getAnswer4());
            holder.countryName.setText((list.get(position).getCountryName()));

            holder.clickListener(position,
                    list.get(position).getAnswerRight(),
                    list.get(position).getAnswer1(),
                    list.get(position).getAnswer2(),
                    list.get(position).getAnswer3(),
                    list.get(position).getAnswer4());



        }




    }

    @Override
    public int getItemCount() {
        return 1;
    }
    public  interface OnPressed{
        void onanswer1(int position,String  answer1, String answer);
        ///stex comment list@ petqa obyektanman ban lini
        void onanswer2(int position,String  answer2, String answer);
        void onanswer3(int position,String  answer3, String answer);
        void onanswer4(int position,String  answer4, String answer);

    }
    ///////////////
    public void OnPressed(OnPressed onPressed){
        this.onPressed = onPressed;
    }


    static class QuestionHolder extends RecyclerView.ViewHolder {
        ImageView countryImage;
        Button answer1, answer2, answer3, answer4;
        TextView countryName;
        LinearLayout layout,parentLayout;

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            countryImage = itemView.findViewById(R.id.imageView2);
            answer1 = itemView.findViewById(R.id.answer1);
            answer2 = itemView.findViewById(R.id.answer2);
            answer3 = itemView.findViewById(R.id.answer3);
            answer4 = itemView.findViewById(R.id.answer4);
            countryName = itemView.findViewById(R.id.countryesName);
            layout = itemView.findViewById(R.id.layout);




        }
        public void clickListener(int position,String answer,String answer11,String answer22,String answer33,String answer44){
            answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPressed.onanswer1(position,answer,answer11);
                }
            });
            answer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPressed.onanswer2(position,answer,answer22);
                }
            });
            answer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPressed.onanswer3(position,answer,answer33);
                }
            });
            answer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPressed.onanswer4(position,answer,answer44);
                }
            });
        }


    }


}

