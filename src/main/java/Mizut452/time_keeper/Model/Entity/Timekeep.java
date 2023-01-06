package Mizut452.time_keeper.Model.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Timekeep implements Serializable {
    private int timeKeepId;
    private String username;
    private String subject;
    private String context;
    private String hours;
    private String minutes;
    private String WhatDate;

    /*public int getTimeKeepId = getTimeKeepId();
    public String getUserName = getUsername();
    public String getSubject = getSubject();
    public String getContext = getContext();
    public String getTotalTime = getTotalTime();
    public String getWhatDate = getWhatDate();*/
}

