package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String MAIN_ACTIVITY_DEBUG_TAG = "MAIN_ACTIVITY_DEBUG";
    private final String mcount_ID = "M_COUNT_ID";
    private int mcount = 0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.count_text);

        if(savedInstanceState != null){
            this.mcount = savedInstanceState.getInt(mcount_ID);
            this.textView.setText(Integer.toString(mcount));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putInt(mcount_ID, mcount);
    }


    // Button Handlers
    public void showToast(View view) {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show();
    }

    public void countUp(View view) {
        textView.setText(Integer.toString(++mcount));
    }
}
