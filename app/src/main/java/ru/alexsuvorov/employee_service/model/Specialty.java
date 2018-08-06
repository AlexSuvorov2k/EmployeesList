package ru.alexsuvorov.employee_service.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

//Generated by http://www.jsonschema2pojo.org/
//@Entity(indices = {@Index(value = {"specId", "specName"}, unique = false)})
@Entity(foreignKeys = @ForeignKey(entity = Employee.class,
        parentColumns = {"employeeId"},
        childColumns = {"specId"},
        onUpdate = CASCADE,
        onDelete = CASCADE))
public class Specialty implements Serializable {

    @PrimaryKey
    private int specId;
    private String specName;
    private int employeeId;
    private final static long serialVersionUID = 4288061416169200241L;

    public Specialty(int specId, String specName) {
        this.specId = specId;
        this.specName = specName;
    }

    @Ignore
    public Specialty() {
    }

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specialtyId) {
        this.specId = specialtyId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String name) {
        this.specName = name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialty specialty = (Specialty) o;
        if (specId != specialty.specId) return false;
        return specName != null ? specName.equals(specialty.specName) : specialty.specName == null;
    }
    @Override
    public int hashCode() {
        int result = specId;
        result = 31 * result + (specName != null ? specName.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "Specialty{" +
                "specId=" + specId +
                ", specName='" + specName + '\'' +
                '}';
    }*/
}