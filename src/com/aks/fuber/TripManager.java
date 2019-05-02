/*
 * Class: TripManager
 *
 * Created on May 2, 2019
 *
 */
package com.aks.fuber;

import java.util.List;

import com.aks.fuber.exception.IncorrectLicenceNumber;

public class TripManager
{
    private static TripManager INSTANCE;

    public static TripManager getInstance()
    {
        if (TripManager.INSTANCE == null)
        {
            TripManager.INSTANCE = new TripManager();
        }
        return TripManager.INSTANCE;
    }

    private List<Trip> trips;

    private TripManager()
    {
        // Do nothing here
    }

    public void startTrip(String licenseNumber) throws IncorrectLicenceNumber
    {
        Trip trip = new Trip(licenseNumber);

        Car car = TaxiServiceManager.getInstance().getCar(licenseNumber);
        if ((car == null) || (car.getStatus() != Status.ASSIGNED))
        {
            throw new IncorrectLicenceNumber("Cannot start the trip, Invalid License Number");
        }

        trip.setStartLocation(car.getLocation());

        trip.startTrip();
    }

}
