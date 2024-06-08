package com.example.petcare;

public class CaregiverMakeJobClass {

    String cid = "";
    String careid = "";
    String carename = "";
    String carecontact = "";
    String carelocation = "";
    String carepayment = "";
    String careexperience = "";
    String created_at_care = "";


    public CaregiverMakeJobClass(String cid, String careid, String carename, String carecontact, String carelocation, String carepayment, String careexperience, String created_at_care) {
        this.cid = cid;
        this.careid = careid;
        this.carename = carename;
        this.carecontact = carecontact;
        this.carelocation = carelocation;
        this.carepayment = carepayment;
        this.careexperience = careexperience;
        this.created_at_care = created_at_care;
    }

    public CaregiverMakeJobClass() {

    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCareid() {
        return careid;
    }

    public void setCareid(String careid) {
        this.careid = careid;
    }

    public String getCarename() {
        return carename;
    }

    public void setCarename(String carename) {
        this.carename = carename;
    }

    public String getCarecontact() {
        return carecontact;
    }

    public void setCarecontact(String carecontact) {
        this.carecontact = carecontact;
    }

    public String getCarelocation() {
        return carelocation;
    }

    public void setCarelocation(String carelocation) {
        this.carelocation = carelocation;
    }

    public String getCarepayment() {
        return carepayment;
    }

    public void setCarepayment(String carepayment) {
        this.carepayment = carepayment;
    }

    public String getCareexperience() {
        return careexperience;
    }

    public void setCareexperience(String careexperience) {
        this.careexperience = careexperience;
    }

    public String getCreated_at_care() {
        return created_at_care;
    }

    public void setCreated_at_care(String created_at_care) {
        this.created_at_care = created_at_care;
    }
}
