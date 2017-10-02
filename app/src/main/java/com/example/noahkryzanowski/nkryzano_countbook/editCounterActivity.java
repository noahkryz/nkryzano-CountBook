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

    /**
     * Called on the creation of the edit activity, when the user selects a counter
     * from the current ListView
     *
     * @param savedInstanceState
     */
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

    /**
     * Used to edit the values in the counter. Used in resetCounter(), incrementCounter(),
     * decrementCounter()
     *
     */
    public void editCounter() {
        textName = (EditText) findViewById(R.id.editCounterName);
        textName.setText(counter.getName());

        textInitValue = (EditText) findViewById(R.id.editInitialValue);
        textInitValue.setText(counter.getInitialValue().toString());

        textCurrentValue = (EditText) findViewById(R.id.editCounterValue);
        textCurrentValue.setText(counter.getCurrentValue().toString());

        textComment = (EditText) findViewById(R.id.editComment);
        textComment.setText(counter.getComment());
    }

    /**
     * Used to reset the current value of the counter to the initial value. Called onClick
     * on the buttonReset in activity_edit_counter.xml
     *
     * @param view
     */
    public void resetCounter(View view) {
        counter.reset();
        editCounter();
    }

    /**
     * Used to increment the counter by a value of one. Called onClick on the buttonPlus
     * in activity_edit_counter.xml
     *
     * @param view
     */
    public void incrementCounter(View view) {
        counter.increment();
        editCounter();
    }

    /**
     * Used to decrement the counter by a value of one. Called onClick on the buttonMinus
     * in activity_edit_counter.xml
     *
     * @param view
     */
    public void decrementCounter(View view) {
        counter.decrement();
        editCounter();
    }

    /**
     * Deletes the counter from the list of counters. Called onClick on the buttonDelete
     *
     * @param view
     */
    public void deleteCounter(View view) {
        intent.putExtra("delete", "true");
        intent.putExtra("index", index);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Used to save the changes that were made in the edit screen, and push them back
     * to the MainActivity function
     *
     * @param view
     */
    public void saveChanges(View view) {
        //Variable Initialization
        String nameStr;
        Integer valueInit;
        Integer valueCurr;
        String commentStr;

        //Getting Counter Name
        try {
            EditText textName = (EditText) findViewById(R.id.editCounterName);
            nameStr = textName.getText().toString();
            if(checkEmpty(textName)) {
                return;
            }
        } catch (Exception e) {
            return;
        }

        //Getting Counter Values - Initial and Current
        try {
            EditText textCurrentValue = (EditText) findViewById(R.id.editCounterValue);
            valueCurr = Integer.parseInt(textCurrentValue.getText().toString());
            if(checkEmpty(textCurrentValue)) {
                return;
            }
        } catch (Exception e) {
            return;
        }

        try {
            EditText textInitialValue = (EditText) findViewById(R.id.editInitialValue);
            valueInit = Integer.parseInt(textInitialValue.getText().toString());
            if(checkEmpty(textInitialValue)) {
                return;
            }
        } catch (Exception e) {
            return;
        }

        //Getting Counter Comment
        EditText textComment = (EditText) findViewById(R.id.editComment);
        commentStr = textComment.getText().toString();

        //Setting new values of the counter
        counter.setName(nameStr);
        counter.setInitialValue(valueInit);
        counter.setCurrentValue(valueCurr);
        counter.setComment(commentStr);

        //Setup the intent, and use Gson to send it back to the MainActivity
        Gson gson = new Gson();
        String counterFromActivity = gson.toJson(counter);
        intent.putExtra("counterFromActivity", counterFromActivity);
        intent.putExtra("delete", "false");
        intent.putExtra("index", index);
        setResult(RESULT_OK, intent);

        finish();

    }
}
