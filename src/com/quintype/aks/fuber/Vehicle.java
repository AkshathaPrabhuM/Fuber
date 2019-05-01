/*
 * Class: Vehicle
 * 
 * Created on Apr 30, 2019
 * 
 */
package com.quintype.aks.fuber;

public class Vehicle
{
    private String licenseNumber;

    private int capacity;

    private String model;

    private String make;

    private int manufacturedYear;

    private String color;
    
    public Vehicle(String licenseNo)
    {
        this.licenseNumber = licenseNo;
    }

    public String getLicenseNumber()
    {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber)
    {
        this.licenseNumber = licenseNumber;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public int getManufacturedYear()
    {
        return manufacturedYear;
    }

    public void setManufacturedYear(int manufacturedYear)
    {
        this.manufacturedYear = manufacturedYear;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

}
