package com.example.noahkryzanowski.nkryzano_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

public class editCounterActivity extends AddCounterActivity {

    private Intent intent;
    private Counter counter;
    private EditText textName;
    private EditText textInitValue;
    private EditText textCurrentValue;
    private EditText textComment;
    private String index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        intent = getIntent();
        String counterFromActivity = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        index = intent.getStringExtra("index");

        Gson gson = new Gson();
        counter = gson.fromJson(counterFromActivity, Counter.class);
        editCounter();

    }
    public void editCounter() {
        textName = (EditText) findViewById(R.id.editCounterName);
        textName.setText(counter.getName());

        textInitValue = (EditText) findViewById(R.id.editInitialValue);
        textInitValue.setText(counter.getInitialValue().toString());

        textCurrentValue = (EditText) findViewById(R.id.editCounterValue);
        textCurrentValue.setText(counter.getCurrentValue().toString());

        textComment = (EditText) findViewById(R.id.editComment);
        textComment.setText(counter.getComment());

        //TextView textDate = (TextView) findViewById(R.id.textDate);
        //textDate.setText(counter.getDate().toString());
    }

    public void resetCounter(View view) {
        counter.reset();
        editCounter();
    }

    public void incrementCounter(View view) {
        counter.increment();
        editCounter();
    }

    public void decrementCounter(View view) {
        counter.decrement();
        editCounter();
    }

    public void deleteCounter(View view) {
        intent.putExtra("delete", "true");
        intent.putExtra("index", index);
        setResult(RESULT_OK, intent);

        finish();

    }

    public void saveChanges(View view) {
        String nameStr;
        Integer valueInit;
        Integer valueCurr;
        String commentStr;

        EditText textName = (EditText) findViewById(R.id.editCounterName);
        nameStr = textName.getText().toString();

        EditText textCurrentValue = (EditText) findViewById(R.id.editCounterValue);
        valueCurr = Integer.parseInt(textCurrentValue.getText().toString());
        EditText textInitialValue = (EditText) findViewById(R.id.editInitialValue);
        valueInit = Integer.parseInt(textInitialValue.getText().toString());

        EditText textComment = (EditText) findViewById(R.id.editComment);
        commentStr = textComment.getText().toString();

        counter.setName(nameStr);
        counter.setInitialValue(valueInit);
        counter.setCurrentValue(valueCurr);
        counter.setComment(commentStr);

        Gson gson = new Gson();
        String counterFromActivity = gson.toJson(counter);
        intent.putExtra("counterFromActivity", counterFromActivity);
        intent.putExtra("delete", "false");
        intent.putExtra("index", index);
        setResult(RESULT_OK, intent);

        finish();

    }
}
