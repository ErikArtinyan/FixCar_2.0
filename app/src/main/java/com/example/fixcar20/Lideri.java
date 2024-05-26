package com.example.fixcar20;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;





import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Lideri extends AppCompatActivity {
    private List<UserModel> list;
    private RecyclerView recyclerView;
    private TextView Title;
    adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lideri);

        Title = findViewById(R.id.Title);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Lideri.this));
        FirebaseAuth auth = FirebaseAuth.getInstance();
        list = new ArrayList<>();
        adapter = new adapter(list, Lideri.this);
        recyclerView.setAdapter(adapter);
        loadDataFromFirestore();

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Lideri.this, Main_Menu.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadDataFromFirestore() {
        CollectionReference reference = FirebaseFirestore.getInstance().collection("users");






        reference.addSnapshotListener((value, error) -> {
            Log.e("reference updated", "reference");
            if (error != null) {
                Log.e("Error:", error.getMessage());
                return;
            }
            if (value == null || value.isEmpty()) {
                return; // Нет данных в снимке, просто выходим
            }

            // Очистить список перед добавлением новых данных
            list.clear();



            for (QueryDocumentSnapshot snapshot : value) {


                if (!snapshot.exists()) {
                    continue;
                }
                // if( snapshot.getDouble("location latitude") == 0.0){
                //     Log.e("double111","null");
                // }



                UserModel model = snapshot.toObject(UserModel.class);
                int bal_Capitals = 0;
                int bal_Maps = 0;
                int bal_flags = 0;


                if(snapshot.getDouble("bal_Capitals") != null) {
                    bal_Capitals = (int) Math.round(snapshot.getDouble("bal_Capitals"));
                }
                if(snapshot.getDouble("bal_Maps") != null) {
                    bal_Maps = (int) Math.round(snapshot.getDouble("bal_Maps"));
                }
                if(snapshot.getDouble("bal_flags") != null) {
                    bal_flags = (int) Math.round(snapshot.getDouble("bal_flags"));
                }

                //assert geoPoint != null;
                //Toast.makeText(getContext(), geoPoint.toString(), Toast.LENGTH_SHORT).show();
                if(snapshot.getString("name") == null){
                    //Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
                }



                list.add(new UserModel(
                        snapshot.getString("name"),
                        bal_Capitals+bal_flags+bal_Maps,
                        model.getEmil(),
                        model.getPassword()
                ));
            }
            // Обновляем адаптер после того, как все данные добавлены
            adapter.notifyDataSetChanged();


        });

    }
}