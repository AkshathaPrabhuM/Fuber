/*
 * Class: Location
 * 
 * Created on Apr 30, 2019
 * 
 */
package com.quintype.aks.fuber;

public class Location
{

    private float latitude;

    private float longitude;

    public Location(float latitude, float longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude()
    {
        return this.latitude;
    }

    public float getLongititude()
    {
        return this.longitude;
    }

    public void setLatitiude(float value)
    {
        this.latitude = value;
    }

    public void setLongitiude(float value)
    {
        this.longitude = value;
    }
}
