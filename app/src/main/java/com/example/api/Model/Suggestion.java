package com.example.api.Model;

public class Suggestion {
    private String activityName;
    private String activityDescription;
    private int activityImageRes; // Pour stocker l'image si tu utilises des ressources locales

    public Suggestion(String activityName, String activityDescription, int activityImageRes) {
        this.activityName = activityName;
        this.activityDescription = activityDescription;
        this.activityImageRes = activityImageRes;
    }

    public String getActivityName() { return activityName; }
    public String getActivityDescription() { return activityDescription; }
    public int getActivityImageRes() { return activityImageRes; }
}
