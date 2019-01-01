package com.tellyourdream.tellyourdream;

public class Dreams {

    private String Name,Email, Age, Marital, Gender;

    public Dreams() {

    }

    public Dreams(String name, String email, String age, String marital, String gender) {
        Name = name;
        Email = email;
        Age = age;
        Marital = marital;
        Gender = gender;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getAge() {
        return Age;
    }

    public String getMarital() {
        return Marital;
    }

    public String getGender() {
        return Gender;
    }
}
