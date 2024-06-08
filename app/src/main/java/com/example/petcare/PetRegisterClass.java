package com.example.petcare;

public class PetRegisterClass {
    String id = "";
    String petcategory = "";
    String petname = "";
    String petage = "";
    String petbread = "";
    String petgender = "";
    String petcolour = "";
    String petnote = "";
    String created_at = "";

    public PetRegisterClass(String id, String petcategory, String petname, String petage, String petbread, String petgender, String petcolour, String petnote, String created_at) {
        this.id = id;
        this.petcategory = petcategory;
        this.petname = petname;
        this.petage = petage;
        this.petbread = petbread;
        this.petgender = petgender;
        this.petcolour = petcolour;
        this.petnote = petnote;
        this.created_at = created_at;
    }

    public PetRegisterClass() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetcategory() {
        return petcategory;
    }

    public void setPetcategory(String petcategory) {
        this.petcategory = petcategory;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getPetage() {
        return petage;
    }

    public void setPetage(String petage) {
        this.petage = petage;
    }

    public String getPetbread() {
        return petbread;
    }

    public void setPetbread(String petbread) {
        this.petbread = petbread;
    }

    public String getPetgender() {
        return petgender;
    }

    public void setPetgender(String petgender) {
        this.petgender = petgender;
    }

    public String getPetcolour() {
        return petcolour;
    }

    public void setPetcolour(String petcolour) {
        this.petcolour = petcolour;
    }

    public String getPetnote() {
        return petnote;
    }

    public void setPetnote(String petnote) {
        this.petnote = petnote;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}