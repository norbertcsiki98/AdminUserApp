package com.example.userapp;

public class Fire_Vote {
    private String ID;
    private String TASK;
    private String GROUP;
    private String NAME;
    private String WHAT;

    public Fire_Vote(String ID, String TASK, String GROUP, String NAME, String WHAT) {
        this.ID = ID;
        this.TASK = TASK;
        this.GROUP=GROUP;
        this.NAME = NAME;
        this.WHAT = WHAT;
    }
    public Fire_Vote(){

    }

    public String getID() {
        return ID;
    }

    public String getTASK() {
        return TASK;
    }

    public String getNAME() {
        return NAME;
    }

    public String getWHAT() {
        return WHAT;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTASK(String TASK) {
        this.TASK = TASK;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setWHAT(String WHAT) {
        this.WHAT = WHAT;
    }

    public String getGROUP() {
        return GROUP;
    }

    public void setGROUP(String GROUP) {
        this.GROUP = GROUP;
    }
}
