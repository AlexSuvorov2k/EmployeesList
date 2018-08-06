package ru.alexsuvorov.employee_service.model;

import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Generated by http://www.jsonschema2pojo.org/
public class ResponseModel implements Serializable {

    @SerializedName("response")
    @Expose
    private ArrayList<Employee> employees = new ArrayList<>();
    private final static long serialVersionUID = 5750681858598462605L;

    public ResponseModel() {
    }

    @Ignore
    public ResponseModel(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getResponse() {
        return employees;
    }

    public void setResponse(ArrayList<Employee> employees) {
        this.employees = employees;
    }

}