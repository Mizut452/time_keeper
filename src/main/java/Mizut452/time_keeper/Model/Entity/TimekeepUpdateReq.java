package Mizut452.time_keeper.Model.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class TimekeepUpdateReq implements Serializable {
    private int timeKeepId;
    private String subject;
    private String context;
    private String hours;
    private String minutes;
    private String WhatDate;
}
