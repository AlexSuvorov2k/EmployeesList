package ru.alexsuvorov.employee_service.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class EmployeeToSpecialty {
    @Embedded
    public Employee employee;

    @Relation(parentColumn = "id", entityColumn = "specId", entity = Specialty.class)
    public List<Specialty> specialties;

}