package com.example.wbs;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

public class JsonUtil {

    private static final String PROFILE_FILE_NAME = "wbs_profile.json";
    private static final String VIDEO_FILE_NAME = "wbs_videos.json";


    public static JSONObject readProfileFromJson(Context context) {
        StringBuilder brString = new StringBuilder();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(PROFILE_FILE_NAME), "UTF-8"));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                brString.append(mLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            JSONObject jsonObj = new JSONObject(brString.toString());
            return jsonObj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void WBSProfileToJson(Context context, UserProfileClass upc) {
        JSONObject jsonObj = new JSONObject();

        try {
            // Here we convert Java Object to JSON
            jsonObj.put("name", upc.getName());
            jsonObj.put("gender", upc.getGender());
            jsonObj.put("age", upc.getAge());
            jsonObj.put("follower", upc.getFollower());
            jsonObj.put("color", upc.getColor());
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        File file = new File(context.getFilesDir(), PROFILE_FILE_NAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);

            outputStream.write(jsonObj.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject readVideoFromJson(Context context) {
        StringBuilder brString = new StringBuilder();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(VIDEO_FILE_NAME), "UTF-8"));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                brString.append(mLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            JSONObject jsonObj = new JSONObject(brString.toString());
            return jsonObj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void VideoToJson(Context context, List<VideoClass> vcs) {
        JSONObject jsonObj = new JSONObject();

        try {
            for (VideoClass vc : vcs) {
                JSONObject jObj = new JSONObject();
                jObj.put("id", vc.getId());
                jObj.put("name", vc.getName());
                jObj.put("path", vc.getPath());
                jObj.put("length", vc.getLenght());
                jsonObj.put("" + vc.getId(), jObj);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        File file = new File(context.getFilesDir(), VIDEO_FILE_NAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);

            outputStream.write(jsonObj.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
