/*
 * Class: Trip
 *
 * Created on May 2, 2019
 *
 */
package com.aks.fuber.impl.trip;

import java.util.Calendar;

import com.aks.fuber.impl.cab.Location;

public class Trip
{

    private boolean colorPrefSet;

    private double cost;

    private Location endLocation;

    private long endTime;

    private String licenseNumber;

    private Location startLocation;

    private long startTime;

    private TripStatus tripStatus;

    public Trip(String licenseNumber)
    {
        this.licenseNumber = licenseNumber;
        this.tripStatus = TripStatus.NOT_STARTED;
    }

    public double calculateTripCost()
    {
        long minutes = ((this.endTime - this.startTime) / (60 * 1000)) % 60;
        double distance = this.startLocation.distance(this.endLocation);
        double cost = (minutes * 1) + (distance * 2);

        return this.colorPrefSet ? cost + 5 : cost;
    }

    public void endTrip()
    {
        this.endTime = Calendar.getInstance().getTimeInMillis();
        this.tripStatus = TripStatus.TRIP_ENDED;
        this.cost = this.calculateTripCost();
    }

    public double getCost()
    {
        return this.cost;
    }

    public TripStatus getTripStatus()
    {
        return this.tripStatus;
    }

    public void setEndLocation(Location location)
    {
        this.endLocation = location;
    }

    public void setIsColorPreferredTrip(boolean colorPref)
    {
        this.colorPrefSet = colorPref;
    }

    public void setStartLocation(Location location)
    {
        this.startLocation = location;
    }

    public void startTrip()
    {
        this.startTime = Calendar.getInstance().getTimeInMillis();
        this.tripStatus = TripStatus.TRIP_STARTED;
    }
}
