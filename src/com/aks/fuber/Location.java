/*
 * Class: Location
 * 
 * Created on Apr 30, 2019
 * 
 */
package com.aks.fuber;

public class Location
{

    private double latitude;

    private double longitude;

    public Location(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return this.latitude;
    }

    public double getLongititude()
    {
        return this.longitude;
    }

    public void setLatitiude(double value)
    {
        this.latitude = value;
    }

    public void setLongitiude(double value)
    {
        this.longitude = value;
    }
}
