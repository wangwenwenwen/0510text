package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String Mes;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("keyname");
        double hh = bundle.getDouble("keyheight",100.0);
        double ww = bundle.getDouble("keyweight",50.0);
        double bmi = ww / ((hh / 100.0) * (hh /100.0));

        ImageView showpic = findViewById(R.id.ivShow);


        if(bmi<18.5) {
            Mes = "體重過輕";
            showpic.setImageResource(R.mipmap.s1);
        } else if (bmi >=18.5 && bmi < 24) {
            Mes = "體重正常";
            showpic.setImageResource(R.mipmap.s2);
        } else if (bmi >=24 && bmi < 27) {
            Mes = "體重過重";
            showpic.setImageResource(R.mipmap.s3);
        } else {
            Mes = "太胖了";
            showpic.setImageResource(R.mipmap.s4);
        }

        TextView showbmi = findViewById(R.id.tvShowBMI);
        showbmi.setText(name+", " + Mes);
        setResult(1111,intent);
    }

    public void Gohome(View view) {
//        Intent intent  = new Intent(this, MainActivity.class);
//        startActivity(intent);

        finish();
    }
}