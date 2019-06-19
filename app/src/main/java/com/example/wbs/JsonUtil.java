package com.example.wbs;

import android.content.Context;

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
    private static final String QUESTION_FILE_NAME = "wbs_questions.json";

    public static void DeletProfile(Context context) {
        File file = new File(context.getFilesDir(), PROFILE_FILE_NAME);
        file.delete();
    }

    public static UserProfileClass readProfileFromJson(Context context) {
        StringBuilder brString = new StringBuilder();
        File file;
        file = new File(context.getFilesDir(), PROFILE_FILE_NAME);
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

            UserProfileClass userProfileClass = new UserProfileClass(
                    jsonObj.optString("name", ""),
                    UserProfileClass.Gender.getGender(jsonObj.optString("gender")),
                    jsonObj.optInt("age",0),
                    jsonObj.optInt("follower", 0),
                    jsonObj.optString("color", ""),
                    jsonObj.optString("action", "")
            );
            return userProfileClass;
        } catch (JSONException e) {
            //e.printStackTrace();
        }
        UserProfileClass userProfileClass = new UserProfileClass();
        return userProfileClass;
    }

    public static void WBSProfileToJson(Context context, UserProfileClass upc) {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("name", upc.getName());
            jsonObj.put("gender", upc.getGender());
            jsonObj.put("age", upc.getAge());
            jsonObj.put("follower", upc.getFollower());
            jsonObj.put("color", upc.getColor());
            jsonObj.put("action", upc.getAction());
        } catch (JSONException e) {
            //e.printStackTrace();
        }

        File file = new File(context.getFilesDir(), PROFILE_FILE_NAME);
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
                        jArrAll.getJSONObject(i).optInt("videofileid",0),
                        jArrAll.getJSONObject(i).optString("name",""),
                        jArrAll.getJSONObject(i).optString("description",""),
                        jArrAll.getJSONObject(i).optString("filepath",""),
                        jArrAll.getJSONObject(i).optInt("duration",0),
                        jArrAll.getJSONObject(i).optInt("questionid",0)
                );
                videoClass.add(vc);
            }

            return videoClass;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<QuestionClass> readQuestionFromJson(Context context) {
        StringBuilder brString = new StringBuilder();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(QUESTION_FILE_NAME), "UTF-8"));
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
            JSONArray jQuestion = jsonObj.getJSONArray("questions");
            ArrayList<QuestionClass> questionClass = new ArrayList<QuestionClass>(jQuestion.length());

            for(int i = 0; i < jQuestion.length(); i++) {
                JSONObject jAnwers = new JSONObject(jQuestion.getJSONObject(i).getString("answers"));
                ArrayList<String> anwers = new ArrayList<>(jAnwers.length());
                for (int j = 0; j < jAnwers.length(); j++) {
                    anwers.add(jAnwers.getString(""+j));
                }
                QuestionClass qc = new QuestionClass(
                        jQuestion.getJSONObject(i).optInt("id",i),
                        jQuestion.getJSONObject(i).optString("question",""),
                        jQuestion.getJSONObject(i).optInt("right",0),
                        anwers
                );
                questionClass.add(qc);
            }
            return questionClass;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
