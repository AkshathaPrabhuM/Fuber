/*
 * Class: Vehicle
 *
 * Created on Apr 30, 2019
 *
 */
package com.aks.fuber.impl.cab;

public class Vehicle
{
    private int capacity;

    private Color color;

    private String licenseNumber;

    private int manufacturedYear;

    private String model;

    public Vehicle(String licenseNo)
    {
        this.licenseNumber = licenseNo;
    }

    public int getCapacity()
    {
        return this.capacity;
    }

    public Color getColor()
    {
        return this.color;
    }

    public String getLicenseNumber()
    {
        return this.licenseNumber;
    }

    public int getManufacturedYear()
    {
        return this.manufacturedYear;
    }

    public String getModel()
    {
        return this.model;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public void setLicenseNumber(String licenseNumber)
    {
        this.licenseNumber = licenseNumber;
    }

    public void setManufacturedYear(int manufacturedYear)
    {
        this.manufacturedYear = manufacturedYear;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

}
