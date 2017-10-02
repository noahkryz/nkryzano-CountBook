package com.example.noahkryzanowski.nkryzano_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.Date;

public class AddCounterActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);

        intent = getIntent();

    }

    public boolean checkEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    public void addCounter(View view) {

        String nameStr;
        Integer valueInit;
        String commentStr;

        EditText textCounterName = (EditText) findViewById(R.id.editCounterName);
        nameStr = textCounterName.getText().toString();

        EditText textCounterValue = (EditText) findViewById(R.id.newCounterValue);
        valueInit = Integer.parseInt(textCounterValue.getText().toString());
        EditText textComment = (EditText) findViewById(R.id.editComment);
        commentStr = textComment.getText().toString();

        Counter counter = new Counter(nameStr, valueInit, commentStr);

        Gson gson = new Gson();
        String counterFromActivity = gson.toJson(counter);

        intent.putExtra("counterFromActivity", counterFromActivity);
        setResult(RESULT_OK, intent);

        //startActivity(new Intent(AddCounterActivity.this, MainActivity.class));
        finish();
    }

}
