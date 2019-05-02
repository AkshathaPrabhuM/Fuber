/*
 * Class: Trip
 *
 * Created on May 2, 2019
 *
 */
package com.aks.fuber;

import java.time.LocalDate;

public class Trip
{

    private Location endLocation;

    private String licenseNumber;

    private Location startLocation;

    private LocalDate startTime;

    private TripStatus tripStatus;

    public Trip(String licenseNumber)
    {
        this.licenseNumber = licenseNumber;
        this.tripStatus = TripStatus.NOT_STARTED;
    }

    public TripStatus getTripStatus()
    {
        return this.tripStatus;
    }

    public void setStartLocation(Location location)
    {
        this.startLocation = location;
    }

    public void startTrip()
    {
        this.startTime = LocalDate.now();
        this.tripStatus = TripStatus.TRIP_STARTED;
    }
}
