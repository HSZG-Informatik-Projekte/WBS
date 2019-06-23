package de.hszg.wbs;

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
    private static final String WORLDS_FILE_NAME = "wbs_worlds.json";

    public static void DeleteProfile(Context context) {
        File file = new File(context.getFilesDir(), PROFILE_FILE_NAME);
        file.delete();
    }

    public static UserProfileClass readProfileFromJson(Context context) {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(readFromJsonFile(context, PROFILE_FILE_NAME, true));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(jsonObj != null){
            ArrayList<Integer> questionsids = new ArrayList<Integer>();
            try {
                for (String questionid : jsonObj.optString("questionsids", "").split(",")) {
                    questionsids.add(Integer.parseInt(questionid));
                }
            } catch(NumberFormatException e) {
                e.printStackTrace();
            } catch(NullPointerException e) {
                e.printStackTrace();
            }

            try {
                UserProfileClass userProfileClass = new UserProfileClass(
                        jsonObj.optString("name", ""),
                        UserProfileClass.Gender.getGender(jsonObj.optString("gender")),
                        jsonObj.optInt("age",0),
                        jsonObj.optInt("follower", 0),
                        jsonObj.optInt("stars", 0),
                        jsonObj.optString("color", ""),
                        jsonObj.optString("action", ""),
                        questionsids,
                        jsonObj.optInt("localmap", 0)
                );
                return userProfileClass;
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            jsonObj.put("stars", upc.getStars());
            jsonObj.put("questionsids", upc.getQuestionsid().toString().replace("[","").replace("]","").replace(" ", ""));
            jsonObj.put("localmap", upc.getLocalMap());
        } catch (Exception e) {
            e.printStackTrace();
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
        try {
            JSONObject jsonObj = new JSONObject(readFromJsonFile(context, VIDEO_FILE_NAME,false));
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

    public static ArrayList<WorldClass> readWorldFromJson(Context context) {
        try {
            JSONObject jsonObj = new JSONObject(readFromJsonFile(context, WORLDS_FILE_NAME,false));
            JSONArray jArrAll = jsonObj.getJSONArray("worlds");
            ArrayList<WorldClass> worldClass = new ArrayList<WorldClass>(jArrAll.length());

            for(int i = 0; i < jArrAll.length(); i++) {
                JSONObject jWaypoints = new JSONObject(jArrAll.getJSONObject(i).getString("waypoint"));
                ArrayList<ArrayList<String>> waypoints = new ArrayList<>(jWaypoints.length());
                for (int j = 0; j < jWaypoints.length(); j++) {
                    ArrayList<String> arli = new ArrayList<>();
                    arli.add(jWaypoints.getJSONObject("" + j).getString("icon"));
                    arli.add(jWaypoints.getJSONObject("" + j).getString("videoid"));
                    waypoints.add(arli);
                }
                WorldClass wc = new WorldClass(
                        jArrAll.getJSONObject(i).optInt("worldid",0),
                        jArrAll.getJSONObject(i).optString("name",""),
                        jArrAll.getJSONObject(i).optString("map",""),
                        jArrAll.getJSONObject(i).optString("roadmap",""),
                        waypoints
                );
                worldClass.add(wc);
            }

            return worldClass;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<QuestionClass> readQuestionFromJson(Context context) {
        try {
            JSONObject jsonObj = new JSONObject(readFromJsonFile(context, QUESTION_FILE_NAME, false));
            JSONArray jQuestion = jsonObj.getJSONArray("questions");
            ArrayList<QuestionClass> questionClass = new ArrayList<QuestionClass>(jQuestion.length());

            for(int i = 0; i < jQuestion.length(); i++) {
                JSONObject jAnwers = new JSONObject(jQuestion.getJSONObject(i).getString("answers"));
                ArrayList<String> anwers = new ArrayList<>(jAnwers.length());
                for (int j = 0; j < jAnwers.length(); j++) {
                    anwers.add(jAnwers.getString("" + j));
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

    private static String readFromJsonFile(Context context, String filename, boolean profileLoad) {
        StringBuilder brString = new StringBuilder();
        BufferedReader reader = null;
        try {
            if(profileLoad) {
                reader = new BufferedReader(new FileReader(new File(context.getFilesDir(), PROFILE_FILE_NAME)));
            } else {
                reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename), "UTF-8"));
            }
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
        return brString.toString();
    }
}
