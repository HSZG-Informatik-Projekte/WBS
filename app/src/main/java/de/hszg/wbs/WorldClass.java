package de.hszg.wbs;

import java.util.ArrayList;

public class WorldClass {

    private int id;
    private String name;
    private String map;
    private String roadmap;
    private ArrayList<ArrayList<String>> waypoints;

    WorldClass(int id, String name, String map, String roadmap, ArrayList<ArrayList<String>> waypoints) {
            this.id = id;
            this.name = name;
            this.map = map;
            this.roadmap = roadmap;
            this.waypoints = waypoints;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMap() { return map; }

    public String getRoadMap() { return roadmap; }

    public ArrayList<ArrayList<String>> getWaypoints() {
        return waypoints;
    }

}


