package Mizut452.time_keeper.Model.Entity;

import lombok.Data;

@Data
public class CompanyList {
    private int id;
    private String companyName;
    private String industry;
    private String headLocation;
    private boolean areOsaka;
    private String CompanyURL;
    private String companyListOther;

    /*public int getId = getId();
    public String getCompanyName = getCompanyName();
    public String getIndustry = getIndustry();
    public String getHeadLocation = getHeadLocation();
    public boolean getAreOsaka = isAreOsaka();
    public String getCompanyURL = getCompanyURL();
    public String getOther = getCompanyListOther();*/
}
