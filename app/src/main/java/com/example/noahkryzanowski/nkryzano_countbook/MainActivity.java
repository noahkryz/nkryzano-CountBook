package com.example.noahkryzanowski.nkryzano_countbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "countbook.sav";

    private ListView counterList;
    private ArrayList<Counter> counters = new ArrayList<>();
    private ArrayAdapter<Counter> adapter;

    public static final int CREATE_CODE = 1;
    public static final int EDIT_CODE = 2;
    public static final String EXTRA_MESSAGE = "noahkryzanowski.nkryzano-Countbook.MESSAGE";

    /**
     * Called on the creation of the MainActivity. This is the main window that the user
     * interacts with
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button addNewCounterButton = (Button) findViewById(R.id.add);
        counterList = (ListView) findViewById(R.id.counterList);

        counterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Object o = counterList.getItemAtPosition(position);
                Counter editCounter = (Counter)o;

                Intent intent = new Intent(MainActivity.this, editCounterActivity.class);
                Gson gson = new Gson();
                String counterFromActivity = gson.toJson(editCounter);
                intent.putExtra(EXTRA_MESSAGE, counterFromActivity);
                intent.putExtra("index", Integer.toString(position));
                startActivityForResult(intent, EDIT_CODE);
            }
        });

        addNewCounterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCounterActivity.class);
                startActivityForResult(intent, CREATE_CODE);
            }
        });

    }

    /**
     * Takes in 3 parameters and decides whether to pull data from the AddCounterActivity
     * or the EditCounterActivity.
     *
     * @param requestCode - set to 1 for create, 2 for edit counter
     * @param resultCode - looks for result ok
     * @param data - the data from the gson call
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == CREATE_CODE  && resultCode  == RESULT_OK) {
                //Do Something
                Gson gson = new Gson();
                String gsonString = data.getStringExtra("counterFromActivity");
                Counter newCounter = gson.fromJson(gsonString, Counter.class);
                counters.add(newCounter);
                updateList();
            }
            else if (requestCode == EDIT_CODE && resultCode == RESULT_OK) {
                Gson gson = new Gson();

                String index = data.getStringExtra("index");
                String delete = data.getStringExtra("delete");
                if(Boolean.parseBoolean(delete)) {
                    counters.remove(Integer.parseInt(index));
                    updateList();
                    return;
                }

                String gsonString = data.getStringExtra("counterFromActivity");
                Counter newCounter = gson.fromJson(gsonString, Counter.class);
                counters.set(Integer.parseInt(index), newCounter);
                updateList();
            }
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Called on the start of the MainActivity
     *
     */
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<>(this, R.layout.list_item, counters);
        counterList.setAdapter(adapter);
        updateTotal();
    }

    /**
     * Updates the list of counters in the ListView
     *
     */
    private void updateList() {
        updateTotal();
        saveInFile();
        adapter.notifyDataSetChanged();
    }

    /**
     * Updates the number of counters on the main screen
     *
     */
    private void updateTotal() {
        TextView counterTotal = (TextView) findViewById(R.id.textCount);
        counterTotal.setText(Integer.toString(counters.size()));
    }

    /**
     * Loads the counters from the file countbook.sav
     *
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();
            counters = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            counters = new ArrayList<>();
        }
    }

    /**
     * Saves the counters in the file countbook.sav
     *
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counters, writer);
            writer.flush();

            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
