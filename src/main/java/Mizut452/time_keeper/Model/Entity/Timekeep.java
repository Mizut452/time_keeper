package Mizut452.time_keeper.Model.Entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Timekeep {
    private int id;
    private String username;
    private String subject;
    private String context;
    private String totalTime;
    private LocalDateTime Wdate;
}
