package Mizut452.time_keeper.Model.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CompanyDetailUpdateReq {
    private int companyDetail_id;
    private String companyDetail_CompanyName;
    private String company_whatJob;
    private String company_strongPoint;
    private String company_weakPoint;
    private String company_treatment;
    private String company_welfare;
    private String company_flow;
    private String company_another;
}