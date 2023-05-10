package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private EditText ed_name;
    private TextView tv_show;
    private Button btn_submit;
    private EditText ed_height;
    private EditText ed_weight;
    private RadioButton girl;
    private RadioButton boy;
    private TextView demo;
    private CheckBox apple;
    private Spinner steaks;
    private final String[] choose1 = {"蘋果","香蕉","橘子","西瓜","奇異果"};
    private boolean[] chooseFruit = new boolean[5];
    private int checkedFruit = 0;
    private ListView fruit;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private final int SET_REQUEST = 1234;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        showSex();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,choose1);
        fruit.setAdapter(adapter);
        steaks.setAdapter(adapter);
//        steaks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                tv_show.setText( String.valueOf(position));
//
////                  String[] steak_arr = getResources().getStringArray(R.array.streak);
////                  tv_show.setText(steak_arr[position]);
//                  tv_show.setText(steaks.getSelectedItem().toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        fruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_show.setText(choose1[position]);
            }
        });

        fruit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_show.setText(fruit.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void showSex() {
        String msg = "";
        if(boy.isChecked()) {
            msg += boy.getText().toString();
        }
        if(girl.isChecked()) {
            msg += girl.getText().toString();
        }
        if(apple.isChecked()) {
            msg += apple.getText().toString();
        }
        demo.setText(msg);
    }

    public void showSex(View view) {
        showSex();
    }

    @SuppressLint("SetTextI18n")
    public void submit(View view) {
        String name = ed_name.getText().toString();

        ed_name.setText("");
        double height =  Double.parseDouble(ed_height.getText().toString());
        double weight =  Double.parseDouble(ed_weight.getText().toString());
        double bmi = weight / ((height /100.0) * (height /100.0));

//        tv_show.setText(name+ getString(R.string.welcome) + bmi);
//        Toast.makeText(this,String.valueOf(bmi),Toast.LENGTH_LONG)
//             .show();
//        Log.d("BMI=",String.valueOf(bmi));
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("測試對話")
//                .setMessage("AAAAAAAA")
//                .show();
        new AlertDialog.Builder(this)
                .setTitle("這是個訊息對話框")
//                .setMessage(String.valueOf(bmi))
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String msg = "";
                        for (int i = 0; i < chooseFruit.length; i++) {
                            if(chooseFruit[i]) {
                                msg += choose1[i] + " ";
                            }
                        }
                        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
//                        checkedFruit = 0;
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(R.mipmap.s1)
//                .setSingleChoiceItems(choose1, checkedFruit, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        checkedFruit = which;
//                    }
//                })
                .setMultiChoiceItems(choose1, chooseFruit, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                })
                .show();



    }

    @SuppressLint("SetTextI18n")
    public void submit1(View view) {
        datePickerDialog = new DatePickerDialog(MainActivity.this);
        datePickerDialog.show();
    }

    private void findViews() {
        ed_name = findViewById(R.id.edName);
        tv_show = findViewById(R.id.tvShow);
        btn_submit = findViewById(R.id.btnSummit);
        ed_height = findViewById(R.id.edHeight);
        ed_weight = findViewById(R.id.edWeight);
        girl = findViewById(R.id.rbFemale);
        boy = findViewById(R.id.rbMale);
        demo = findViewById(R.id.tvdemo);
        apple = findViewById(R.id.cbApple);
        steaks = findViewById(R.id.spSteak);
        fruit = findViewById(R.id.lvFruit);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1234:
                if(resultCode==1111) {
                    tv_show.setText("從ResultActivity返回!!");
                }

        }
    }

    public void goResult(View view) {

        String name = ed_name.getText().toString();
        double height =  Double.parseDouble(ed_height.getText().toString());
        double weight =  Double.parseDouble(ed_weight.getText().toString());
        Intent intent = new Intent(this, ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("keyname",name);
        bundle.putDouble("keyheight",height);
        bundle.putDouble("keyweight",weight);
        intent.putExtras(bundle);
//        startActivity(intent);
        setResult(1234,intent);
        startActivityIfNeeded(intent,SET_REQUEST);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
}