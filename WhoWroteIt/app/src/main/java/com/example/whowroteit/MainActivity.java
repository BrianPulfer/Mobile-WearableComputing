package com.example.whowroteit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private TextView author;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.title = findViewById(R.id.title);
        this.author = findViewById(R.id.author);

        this.editText = findViewById(R.id.edittext);
    }

    public void searchBooks(View view) {
        new FetchBook(this.title, this.author).execute(this.editText.getText().toString());
    }
}
