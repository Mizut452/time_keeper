package Mizut452.time_keeper.Model.Entity;

import lombok.Data;

@Data
public class TimekeepUpdateReq {
    private int timekeepid;
    private String subject;
    private String context;
    private String totalTime;
    private String Wdate;
}
