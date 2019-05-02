/*
 * Class: Vehicle
 * 
 * Created on Apr 30, 2019
 * 
 */
package com.aks.fuber;

public class Vehicle
{
    private String licenseNumber;

    private int capacity;

    private String model;

    private String make;

    private int manufacturedYear;

    private Color color;

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

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

}
