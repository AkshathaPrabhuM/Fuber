/*
 * Class: TripManager
 *
 * Created on May 2, 2019
 *
 */
package com.aks.fuber;

import java.util.Map;

import com.aks.fuber.exception.CabNotFoundException;
import com.aks.fuber.exception.IncorrectLicenceNumber;
import com.aks.fuber.exception.TripNotFoundException;

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

    private Map<String, Trip> trips;

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

        this.trips.put(licenseNumber, trip);

        trip.setStartLocation(car.getLocation());
        trip.startTrip();
    }

    public void stopTrip(String licenseNumber, double destX, double destY)
            throws TripNotFoundException, CabNotFoundException
    {
        Trip trip = this.trips.get(licenseNumber);

        if ((trip == null) || (trip.getTripStatus() != TripStatus.TRIP_STARTED))
        {
            throw new TripNotFoundException("Invalid license Number, Trip not located!");
        }

        Location location = new Location(destX, destY);
        trip.setEndLocation(location);

        trip.endTrip();

        TaxiServiceManager.getInstance().reassignCars(licenseNumber);

    }

}
