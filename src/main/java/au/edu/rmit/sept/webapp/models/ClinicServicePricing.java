package au.edu.rmit.sept.webapp.models;

import java.math.BigDecimal;

public class ClinicServicePricing {

    private Long clinicId;
    private Long serviceId;
    private float price;
    private String clinicName;

    public ClinicServicePricing( Long clinicId, Long serviceId, float price, String clinicName){
        this.clinicId = clinicId;
        this.serviceId = serviceId;
        this.price = price;
        this.clinicName = clinicName;
    }

    public ClinicServicePricing(){}



    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getClinicName(){
        return  clinicName;
    }

    public void setClinicName(String clinicName){
        this.clinicName= clinicName;
    }
}
