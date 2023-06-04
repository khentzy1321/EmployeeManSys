package com.example.employeemanagementsystem;

import javafx.beans.property.*;

public class Employee {
    private final StringProperty fullName;
    private final StringProperty address;
    private final StringProperty phone;
    private final DoubleProperty salary;
    private final StringProperty department;

    public Employee(String fullName, String address, String phone, double salary, String department) {
        this.fullName = new SimpleStringProperty(fullName);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.salary = new SimpleDoubleProperty(salary);
        this.department = new SimpleStringProperty(department);
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public double getSalary() {
        return salary.get();
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }

    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }
}
