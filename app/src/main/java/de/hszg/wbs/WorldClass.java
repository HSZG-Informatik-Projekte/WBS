package de.hszg.wbs;

import java.util.ArrayList;

public class WorldClass {

    private int id;
    private String name;
    private String map;
    private ArrayList<ArrayList<String>> waypoints;

    WorldClass(int id, String name, String map, ArrayList<ArrayList<String>> waypoints) {
            this.id = id;
            this.name = name;
            this.map = map;
            this.waypoints = waypoints;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMap() { return map; }

    public ArrayList<ArrayList<String>> getWaypoints() {
        return waypoints;
    }

}


