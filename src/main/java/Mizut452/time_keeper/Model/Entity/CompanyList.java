package Mizut452.time_keeper.Model.Entity;

import lombok.Data;

@Data
public class CompanyList {
    private int id;
    private String companyName;
    private String industry;
    private String headlocate;
    private boolean areOsaka;
    private String CompanyURL;
    private String companyLother;
}