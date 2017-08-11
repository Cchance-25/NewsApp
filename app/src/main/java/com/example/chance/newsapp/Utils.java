package com.example.chance.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by chance on 8/10/17.
 */

public final class Utils {

    /* To Be Implemented
    * Connection to internet and Fetching JSON data
    * Convert InputStream to String
    *
    * */
    private static final String TAG = Utils.class.getSimpleName();
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 15000;
    private static ArrayList<News> list = new ArrayList<>();

    // JSON KEYS
    private static final String BASE_JSON_ARRAY_NAME_KEY = "response";
    private static final String ARTICLE_NEWS_ARRAY_KEY = "results";
    private static final String ARTICLE_TITLE_KEY = "webTitle";
    private static final String ARTICLE_SECTION_KEY = "sectionName";
    private static final String ARTICLE_AUTHOR_KEY = "author";
    private static final String ARTICLE_PUBLISH_DATE_KEY = "webPublicationDate";
    private static final String ARTICLE_URL_KEY = "webUrl";


    private Utils() {
        // No objects should be created of this class
    }


    public static void makeHttpConnection(String url) throws IOException {
        InputStream inputStream = null;
        HttpURLConnection con = null;

        URL link = createUrl(url);

        try {
            con = (HttpURLConnection) link.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(CONNECTION_TIMEOUT);
            con.setReadTimeout(READ_TIMEOUT);
            con.connect();
            Log.e(TAG, "Connected123"+con.getResponseCode());

            if (con.getResponseCode() == con.HTTP_OK) {
                inputStream = new BufferedInputStream(con.getInputStream());
                String jsonResponse = getStringResponse(inputStream);
                extractFeaturesFromJson(jsonResponse);
            }

        } catch (IOException e) {
            Log.e(TAG, "Error opening a connection: " + e);
        }finally {
            if(con!=null)
                con.disconnect();
            if(inputStream!=null)
                inputStream.close();
        }

    }

    public static ArrayList<News> getFinalList(String url) {

        ArrayList<News> finalList = null;
        if(TextUtils.isEmpty(url))
            return finalList;
        try {
            makeHttpConnection(url);
            finalList = list;
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return finalList;

    }

    public static void extractFeaturesFromJson(String jsonResponse) {

        try {
            Log.e(TAG, jsonResponse);
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);
            JSONObject response = baseJsonResponse.getJSONObject(BASE_JSON_ARRAY_NAME_KEY);
            Log.e(TAG, response.toString());
            JSONArray articlesArray = response.getJSONArray(ARTICLE_NEWS_ARRAY_KEY);
            Log.e(TAG, "LENGTH: "+articlesArray.length());

            if (articlesArray.length() < 0) {
                Log.e(TAG, "Base array empty!");
                return;
            }

            for (int i = 0; i < articlesArray.length(); i++) {
                JSONObject article = articlesArray.getJSONObject(i);
                News newsObject = new News();
                // Use setters to immediately insert data into the object
                newsObject.setNewsHeaderTitle(article.getString(ARTICLE_TITLE_KEY));
                newsObject.setNewsArticleSection(article.getString(ARTICLE_SECTION_KEY));
                if (article.has(ARTICLE_AUTHOR_KEY)) {
                   newsObject.setAuthor("By: "+article.getString(ARTICLE_AUTHOR_KEY)+" in ");
                }
                if (article.has(ARTICLE_PUBLISH_DATE_KEY)) {
                    newsObject.setNewsPublishDate(article.getString(ARTICLE_PUBLISH_DATE_KEY));
                }
                newsObject.setURL(article.getString(ARTICLE_URL_KEY));

                list.add(newsObject);
            }

        } catch (JSONException e) {
            Log.e(TAG, "Error extracting json features: " + e);
        }

    }

    private static String getStringResponse(InputStream inputStream) {
        InputStreamReader isr = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);
        StringBuilder output = new StringBuilder();
        try {
            String line = br.readLine();
            while (line != null){

                output.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            Log.e(TAG, "Error getting jsonResponse: " + e);
            return null;
        }
        return output.toString();
    }

    private static URL createUrl(String urls) {
        URL url = null;
        try {
            url = new URL(urls);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Error creating URL: " + e);
        }
        return url;
    }

    public static void clearList() {
        list.clear();
    }


}
