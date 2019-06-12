package com.example.wbs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class JsonUtil {

    private static final String PROFILE_FILE_NAME = "wbs_profile.json";
    private static final String VIDEO_FILE_NAME = "wbs_videos.json";


    public static void WBSProfileToJson(UserProfileClass upc) {
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

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(PROFILE_FILE_NAME, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    public static void VideoToJson(VideoClass vc) {
        JSONObject jsonObj = new JSONObject();

        try {
            // Here we convert Java Object to JSON
            jsonObj.put("id", vc.getId());
            jsonObj.put("name", vc.getName());
            jsonObj.put("path", vc.getPath());
            jsonObj.put("length", vc.getLenght());
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(VIDEO_FILE_NAME, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
