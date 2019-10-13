package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
    }

    public void returnItem(View view) {
        String selectedItem = ((Button)view).getText().toString();

        Intent intent = MainActivity.returnItemIntent(selectedItem);
        setResult(MainActivity.REQUEST_ITEM_CODE_OK, intent);
        finish();
    }
}
