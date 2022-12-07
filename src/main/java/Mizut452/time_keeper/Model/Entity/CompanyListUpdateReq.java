package Mizut452.time_keeper.Model.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CompanyListUpdateReq {
    private int id;
    private String companyName;
    private String industry;
    private String headlocate;
    private boolean areOsaka;
    private String companyURL;
    private String companyLother;
}
