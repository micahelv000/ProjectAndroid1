package com.example.projectandroid1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private ImageView logo;
    private Animation rotate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }



        logo = findViewById(R.id.logo);
        rotate = AnimationUtils.loadAnimation(this, R.anim.fadein);
        logo.startAnimation(rotate);

        FireBaseHandler fireBaseHandler = new FireBaseHandler();
        if (FireBaseHandler.getCurrentUser() != null) {
            FirebaseUser user = FireBaseHandler.getCurrentUser();
            Intent intent = new Intent(this, LayoutFragments.class);
            fireBaseHandler.getUserData(user).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    JSONObject userJson = task.getResult();
                    intent.putExtra("user", userJson.toString());
                    startActivity(intent);
                }
            });
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish();
                }
            }, 3000);
        }




    }

}
