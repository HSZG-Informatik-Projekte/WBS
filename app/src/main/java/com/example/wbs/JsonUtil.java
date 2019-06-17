package com.example.wbs;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JsonUtil {
    private static final String PROFILE_FILE_NAME = "wbs_profile.json";
    private static final String VIDEO_FILE_NAME = "wbs_videos.json";

    public static void DeletProfile(Context context) {
        File file = new File(context.getFilesDir(), PROFILE_FILE_NAME);
        file.delete();
    }

    public static JSONObject readProfileFromJson(Context context) {
        StringBuilder brString = new StringBuilder();
        File file = new File(context.getFilesDir(), PROFILE_FILE_NAME);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
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

        Log.i("BLT JUTIL", "WRITE: jsonObj: " + jsonObj);

        File file = new File(context.getFilesDir(), PROFILE_FILE_NAME);

        Log.i("BLT JUTIL", "WRITE: getFilesDir: " + file.getAbsolutePath());

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);

            outputStream.write(jsonObj.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<VideoClass> readVideoFromJson(Context context) {
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
            JSONArray jArrAll = jsonObj.getJSONArray("videofiledetails");
            ArrayList<VideoClass> videoClass = new ArrayList<VideoClass>(jArrAll.length());

            for(int i = 0; i < jArrAll.length(); i++) {
                VideoClass vc = new VideoClass(
                        jArrAll.getJSONObject(i).getInt("videofileid"),
                        jArrAll.getJSONObject(i).getString("name"),
                        jArrAll.getJSONObject(i).getString("description"),
                        jArrAll.getJSONObject(i).getString("filepath"),
                        jArrAll.getJSONObject(i).getInt("duration")
                );
                videoClass.add(vc);
            }

            return videoClass;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
