/*
 * Class: Location
 *
 * Created on Apr 30, 2019
 *
 */
package com.aks.fuber.impl.cab;

public class Location
{

    public static Location create(double latitude, double longitude)
    {
        return new Location(latitude, longitude);
    }

    private double latitude;

    private double longitude;

    private Location(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double distance(Location other)
    {
        double x = this.latitude - other.latitude;

        double y = this.longitude - other.longitude;

        return Math.sqrt(Math.abs((x * x) + (y * y)));
    }

    public double getLatitude()
    {
        return this.latitude;
    }

    public double getLongititude()
    {
        return this.longitude;
    }
}
