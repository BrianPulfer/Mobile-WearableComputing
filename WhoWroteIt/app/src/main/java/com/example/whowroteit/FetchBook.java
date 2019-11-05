package com.example.whowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask<String, Void, String> {

    private WeakReference<TextView> title;
    private WeakReference<TextView> author;

    FetchBook(TextView titleText, TextView authorText) {
        this.title = new WeakReference<>(titleText);
        this.author = new WeakReference<>(authorText);
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfos(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try{
            JSONObject json = new JSONObject(s);
            JSONArray array = json.getJSONArray("items");

            int i = 0;
            String title = null;
            String authors = null;

            while (i < array.length() && (authors == null && title == null)) {
                JSONObject book = array.getJSONObject(i);
                JSONObject volume = book.getJSONObject("volumeInfo");

                try {
                    title = volume.getString("title");
                    authors = volume.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i ++;
            }

            if (title != null && authors != null) {
                this.title.get().setText(title);
                this.author.get().setText(authors);
            } else {
                this.title.get().setText("No results were found...");
                this.author.get().setText("");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
