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

    /**
     * Called on creation of the addCounterActivity, when the user selects the add button,
     * attempting to create a new counter
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);

        intent = getIntent();
    }

    /**
     * Check to see whether or not the editText value is empty, prompting the user to enter
     * in a value instead of leaving the field blank
     *
     * @param editText
     * @return returns True (empty) or False (non-empty)
     */
    public boolean checkEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    /**
     * Used to add the counter to the list of counters, and sent back to the main activity.
     * Called onClick by the buttonConfirm in activity_add_counter.xml
     *
     * @param view
     */
    public void addCounter(View view) {
        //Variable Initialization
        String nameStr;
        Integer valueInit;
        String commentStr;

        //Getting Counter Name
        EditText textCounterName = (EditText) findViewById(R.id.editCounterName);
        nameStr = textCounterName.getText().toString();

        //Getting Counter Value
        EditText textCounterValue = (EditText) findViewById(R.id.newCounterValue);
        valueInit = Integer.parseInt(textCounterValue.getText().toString());

        //Getting Counter Comment
        EditText textComment = (EditText) findViewById(R.id.editComment);
        commentStr = textComment.getText().toString();

        Counter counter = new Counter(nameStr, valueInit, commentStr);

        //Setup the intent, and send back the new counters information to the MainActivity
        Gson gson = new Gson();
        String counterFromActivity = gson.toJson(counter);

        intent.putExtra("counterFromActivity", counterFromActivity);
        setResult(RESULT_OK, intent);

        //startActivity(new Intent(AddCounterActivity.this, MainActivity.class));
        finish();
    }

}
