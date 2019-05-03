/*
 * Class: TripManager
 *
 * Created on May 2, 2019
 *
 */
package com.aks.fuber.impl;

import java.util.HashMap;
import java.util.Map;

import com.aks.fuber.exception.IncorrectLicenseNumberException;
import com.aks.fuber.exception.TripNotFoundException;
import com.aks.fuber.impl.cab.Cab;
import com.aks.fuber.impl.cab.Location;
import com.aks.fuber.impl.cab.Status;
import com.aks.fuber.impl.trip.Trip;
import com.aks.fuber.impl.trip.TripStatus;

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
        this.trips = new HashMap<String, Trip>();
    }

    public void createTrip(String licenseNumber, boolean isColorPref)
    {
        Trip trip = new Trip(licenseNumber);
        trip.setIsColorPreferredTrip(isColorPref);
        this.trips.put(licenseNumber, trip);
    }

    public void startTrip(String licenseNumber) throws IncorrectLicenseNumberException
    {
        Cab cab = CabManager.getInstance().getCab(licenseNumber);
        if ((cab == null) || (cab.getStatus() != Status.ASSIGNED))
        {
            throw new IncorrectLicenseNumberException("Cannot start the trip, Invalid License Number");
        }

        Trip trip = this.trips.get(licenseNumber);

        trip.setStartLocation(cab.getLocation());
        trip.startTrip();
    }

    public double stopTrip(String licenseNumber, double destX, double destY) throws Exception
    {
        Trip trip = this.trips.remove(licenseNumber);

        if ((trip == null) || (trip.getTripStatus() != TripStatus.TRIP_STARTED))
        {
            throw new TripNotFoundException("Invalid license Number, Trip not located!");
        }

        Cab cab = CabManager.getInstance().getCab(licenseNumber);
        if ((cab == null) || (cab.getStatus() != Status.ASSIGNED))
        {
            throw new IncorrectLicenseNumberException("Cannot end the trip, Invalid License Number");
        }

        Location location = Location.create(destX, destY);
        trip.setEndLocation(location);

        trip.endTrip();

        TaxiServiceManager.getInstance().reassignCabs(licenseNumber, location);

        return trip.getCost();
    }

}
