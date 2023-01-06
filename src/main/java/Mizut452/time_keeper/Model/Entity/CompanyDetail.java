package Mizut452.time_keeper.Model.Entity;

import lombok.Data;

@Data
public class CompanyDetail {
    private int companyDetail_id;
    private String companyDetail_CompanyName;
    private String company_whatJob;
    private String company_strongPoint;
    private String company_weakPoint;
    private String company_treatment;
    private String company_welfare;
    private String company_flow;
    private String company_another;

    /*public int getId = getCompanyDetail_id();
    public String getDetail_CompanyName = getCompanyDetail_CompanyName();
    public String getWhatJob = getCompany_whatJob();
    public String getStrongPoint = getCompany_strongPoint();
    public String getWeakPoint = getCompany_weakPoint();
    public String getTreatment = getCompany_treatment();
    public String getWelfare = getCompany_welfare();
    public String getFlow = getCompany_flow();
    public String getAnother = getCompany_another();*/
}
