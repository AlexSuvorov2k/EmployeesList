package ru.alexsuvorov.employee_service.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

//Generated by http://www.jsonschema2pojo.org/

@Entity(indices = {@Index(value = {"f_name", "l_name", "birthday", "avatarLink"}, unique = false)},
        inheritSuperIndices = true)
public class Employee {
    @PrimaryKey(autoGenerate = true)
    private int employeeId;
    private String f_name;
    private String l_name;
    private String birthday;
    @Ignore
    private int age;
    private String avatarLink;
    /*@Embedded
    private List<Specialty> specialty;*/
    private final static long serialVersionUID = -8824149947485321362L;

    public Employee(int employeeId, String f_name, String l_name, String birthday, String avatarLink) {
        this.employeeId = employeeId;
        this.f_name = f_name;
        this.l_name = l_name;
        this.birthday = birthday;
        this.avatarLink = avatarLink;
        //this.specialty = specialty;
    }

    @Ignore
    public Employee() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    /*public List<Specialty> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(List<Specialty> specialty) {
        this.specialty = specialty;
    }*/
}