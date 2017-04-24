package com.willowtree.namegame.DAO;

import android.content.Context;
import android.os.AsyncTask;

import com.willowtree.namegame.DO.APISingleton;
import com.willowtree.namegame.controller.ActivityController;
import com.willowtree.namegame.model.Headshot;
import com.willowtree.namegame.model.Person;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Derek on 4/20/2017.
 */

public class APITask extends AsyncTask<String, Void, JSONObject> {

    private Context context;

    private static final String ID = "id";
    private static final String TYPE = "type";
    private static final String SLUG = "slug";
    private static final String JOB_TITLE = "jobTitle";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String HEAD_SHOT = "headshot";
    private static final String SOCIAL_LINKS = "socialLinks";

    private static final String HEAD_SHOT_TYPE = "type";
    private static final String HEAD_SHOT_MIME_TYPE = "mimeType";
    private static final String HEAD_SHOT_ID = "id";
    private static final String HEAD_SHOT_URL = "url";
    private static final String HEAD_SHOT_ALT = "alt";
    private static final String HEAD_SHOT_HEIGHT = "height";
    private static final String HEAD_SHOT_WIDTH = "width";

    ActivityController activityController;

    public APITask(ActivityController activityController) {
        this.activityController = activityController;
    }

    @Override
    protected JSONObject doInBackground(String... urls) {

        String response = "";
        for (String url : urls) {

            DefaultHttpClient client = new DefaultHttpClient();

            HttpGet httpGet = new HttpGet(url);

            try {
                HttpResponse execute = client.execute(httpGet);
                InputStream content = execute.getEntity().getContent();

                BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                String s = "";
                while ((s = buffer.readLine()) != null) {
                    response += s;
                }
                buffer.close();

                return new JSONObject(response.toString());
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        try {
            JSONArray people = result.getJSONArray("items");
            for (int i = 0; i < people.length(); i++) {
                JSONObject jsonPerson = people.getJSONObject(i);

                JSONObject headShotObject = jsonPerson.getJSONObject(HEAD_SHOT);

                //JSONObject socialLinkObject = jsonPerson.getJSONObject(SOCIAL_LINKS);
                JSONArray socialLinkArr = jsonPerson.getJSONArray(SOCIAL_LINKS);
                ArrayList<String> socialLinks = new ArrayList<String>();
                for (int j = 0; j < socialLinkArr.length(); j++) {
                    socialLinks.add(socialLinkArr.optString(j));
                }

                Person person = new Person(
                        jsonPerson.optString(ID),
                        jsonPerson.optString(TYPE),
                        jsonPerson.optString(SLUG),
                        jsonPerson.optString(JOB_TITLE),
                        jsonPerson.optString(FIRST_NAME),
                        jsonPerson.optString(LAST_NAME),
                        new Headshot(
                                headShotObject.optString(HEAD_SHOT_TYPE),
                                headShotObject.optString(HEAD_SHOT_MIME_TYPE),
                                headShotObject.optString(HEAD_SHOT_ID),
                                "http:" + headShotObject.optString(HEAD_SHOT_URL),
                                headShotObject.optString(HEAD_SHOT_ALT),
                                headShotObject.optInt(HEAD_SHOT_HEIGHT),
                                headShotObject.optInt(HEAD_SHOT_WIDTH)
                        ),
                        socialLinks
                );
                APISingleton.getInstance().getPeople().add(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        activityController.hideLoader();
    }

}
