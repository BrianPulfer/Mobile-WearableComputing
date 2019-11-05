package com.example.whowroteit;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();


    private static final String URL =  "https://www.googleapis.com/books/v1/volumes?";
    private static final String QUERY = "q";
    private static final String MAX_RES = "maxResults";
    private static final String PRINT = "printType";
    private static final String DOWNLOAD = "download";

    public static String getBookInfos(String query){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String bookJSON = null;

        try {
            java.net.URL url = new URL(
                    Uri.parse(URL).buildUpon()
                    .appendQueryParameter(QUERY, query)
                    .appendQueryParameter(MAX_RES, "10")
                    .appendQueryParameter(PRINT, "books")
                    .appendQueryParameter(DOWNLOAD, "epub")
                    .build().toString());

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            bookJSON = builder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bookJSON;
    }
}
