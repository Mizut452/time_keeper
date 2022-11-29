package Mizut452.time_keeper.Model.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Timekeep implements Serializable {
    private int timekeepid;
    private String username;
    private String subject;
    private String context;
    private String totalTime;
    private String Wdate;
}

