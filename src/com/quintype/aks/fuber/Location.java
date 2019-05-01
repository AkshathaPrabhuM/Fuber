/*
 * Class: Location
 * 
 * Created on Apr 30, 2019
 * 
 * (c) Copyright Lam Research Corporation, unpublished work, created 2019
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Lam Research Corporation
 * 4000 N. First Street
 * San Jose, CA
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
