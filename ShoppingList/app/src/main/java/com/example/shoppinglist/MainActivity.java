package com.example.shoppinglist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_ITEM_CODE = 1;
    public static final int REQUEST_ITEM_CODE_OK = 11;
    public static final int REQUEST_ITEM_CODE_FAIL = 12;

    private final int NR_TEXT_VIEWS = 10;
    private static final String ITEM_KEY = "ITEM_KEY";

    private static final String SAVED_ITEMS_KEY = "SAVED_ITEMS_KEY";

    private static final String ITEM_COUNT_KEY = "ITEM_COUNT_KEY";
    private int currentItemNumber = 0;

    TextView[] textViews;

    public static Intent returnItemIntent(String item){
        Intent intent = new Intent();
        intent.putExtra(ITEM_KEY, item);
        return intent;
    }

    private void getTextViews(){
        textViews = new TextView[NR_TEXT_VIEWS];

        textViews[0] = (TextView)findViewById(R.id.textView1);
        textViews[1] = (TextView)findViewById(R.id.textView2);
        textViews[2] = (TextView)findViewById(R.id.textView3);
        textViews[3] = (TextView)findViewById(R.id.textView4);
        textViews[4] = (TextView)findViewById(R.id.textView5);
        textViews[5] = (TextView)findViewById(R.id.textView6);
        textViews[6] = (TextView)findViewById(R.id.textView7);
        textViews[7] = (TextView)findViewById(R.id.textView8);
        textViews[8] = (TextView)findViewById(R.id.textView9);
        textViews[9] = (TextView)findViewById(R.id.textView10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getTextViews();
        fillViews(savedInstanceState);
        Log.d("MYTAG", "Ricreata");
    }

    private void fillViews(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            if (savedInstanceState.getStringArrayList(SAVED_ITEMS_KEY) != null) {
                ArrayList<String> itemsStrings = savedInstanceState.getStringArrayList(SAVED_ITEMS_KEY);
                currentItemNumber = savedInstanceState.getInt(ITEM_COUNT_KEY);

                for (int i = 0; i < itemsStrings.size(); i++) {
                    textViews[i].setText(itemsStrings.get(i));
                }
            }
        }
    }

    public void requestItem(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        startActivityForResult(intent, REQUEST_ITEM_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ITEM_CODE){
            if(resultCode == REQUEST_ITEM_CODE_OK){
                if(data.getStringExtra(ITEM_KEY) != null) {
                    textViews[currentItemNumber].setText(data.getStringExtra(ITEM_KEY));
                    currentItemNumber++;

                    if(currentItemNumber == textViews.length)
                        currentItemNumber = 0;
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList(SAVED_ITEMS_KEY, getItemsArrayList());
        outState.putInt(ITEM_COUNT_KEY, currentItemNumber);
    }

    private ArrayList<String> getItemsArrayList() {
        ArrayList<String> strings = new ArrayList<>();

        for(TextView tv : textViews){
            if(tv.getText() != null){
                strings.add(tv.getText().toString());
            } else{
                break;
            }
        }

        return strings;
    }
}
