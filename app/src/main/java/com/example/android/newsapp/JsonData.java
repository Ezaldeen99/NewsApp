package com.example.android.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azozs on 10/28/2017.
 */

public class JsonData {
    public static final String LOG_TAG = MainActivity.class.getName();

    public JsonData() {
    }

    public static List<News> fetchData(String url) {
        String jsonRequest = null;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL nUrl = createUrl(url);
        try {
            jsonRequest = httpRequest(nUrl);
        } catch (IOException e) {
            Log.e(LOG_TAG, "error in http request");
        }
        return readJson(jsonRequest);
    }

    private static List<News> readJson(String jsonRequest) {
        if (TextUtils.isEmpty(jsonRequest)) {
            return null;
        }
        List<News> list = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(jsonRequest);
            JSONObject response = json.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject element = results.getJSONObject(i);
                String sectionName = element.getString("sectionName");
                String webTitle = element.getString("webTitle");
                String publishDate;
                try {
                    publishDate = element.getString("webPublicationDate");
                } catch (Exception e) {
                    publishDate = "N/A";
                    Log.e(LOG_TAG, "no publishDate");
                }
                String webUrl = element.getString("webUrl");
                JSONObject fields = element.getJSONObject("fields");
                String thumbnail = fields.getString("thumbnail");
                JSONArray tags = element.getJSONArray("tags");
                JSONObject author;
                String authorName;
                try {
                    author = tags.getJSONObject(0);
                    authorName = author.getString("webTitle");
                } catch (Exception e) {
                    authorName = "N/A";
                    Log.e(LOG_TAG, "no authorName");
                }
                News news = new News(webTitle, sectionName, webUrl, authorName, thumbnail, publishDate);
                list.add(news);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "error in json reading request");
        }
        return list;
    }


    private static URL createUrl(String url) {
        URL newsUrl = null;
        try {
            newsUrl = new URL(url);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "error in creating URL");
        }
        return newsUrl;
    }

    private static String httpRequest(URL nUrl) throws IOException {
        String jsonObject = null;
        InputStream inputStream = null;
        HttpURLConnection httpUrlConnection = null;
        if (nUrl == null) {
            return null;
        }
        try {
            httpUrlConnection = (HttpURLConnection) nUrl.openConnection();
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.setConnectTimeout(10000);
            httpUrlConnection.setReadTimeout(10000);
            httpUrlConnection.connect();
            if (httpUrlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpUrlConnection.getInputStream();
                jsonObject = readFromInputStream(inputStream);
            }


        } catch (ProtocolException e) {
            Log.e(LOG_TAG, "error in http request");
        } finally {
            if (jsonObject != null) {
                httpUrlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonObject;
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader buffer = new BufferedReader(reader);
            String line = buffer.readLine();
            while (line != null) {
                jsonBuilder.append(line);
                line = buffer.readLine();
            }

        }
        return jsonBuilder.toString();
    }
}