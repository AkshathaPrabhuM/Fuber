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
    private static TripManager instance;

    public static TripManager getInstance()
    {
        if (TripManager.instance == null)
        {
            TripManager.instance = new TripManager();
        }
        return TripManager.instance;
    }

    private Map<String, Trip> trips;

    private TripManager()
    {
        this.trips = new HashMap<String, Trip>();
    }

    public void createTrip(String licenseNumber, boolean isColorPref)
    {
        // Create trip and add it into the cache.
        Trip trip = new Trip(licenseNumber);
        trip.setIsColorPreferredTrip(isColorPref);
        this.trips.put(licenseNumber, trip);
    }

    public void startTrip(String licenseNumber) throws IncorrectLicenseNumberException
    {
        // 1. Get the cab from the license number
        Cab cab = CabManager.getInstance().getCab(licenseNumber);

        // 2. Validate if the cab is correct and in correct status
        if ((cab == null) || (cab.getStatus() != Status.ASSIGNED))
        {
            throw new IncorrectLicenseNumberException("Cannot start the trip, Invalid License Number");
        }

        // 3. Get the trip from the local cache
        Trip trip = this.trips.get(licenseNumber);

        // 4. Set the required details in to the trip and start it.
        trip.setStartLocation(cab.getLocation());
        trip.startTrip();
    }

    public double stopTrip(String licenseNumber, double destX, double destY) throws Exception
    {
        // 1. First remove the trip from the cache.
        Trip trip = this.trips.remove(licenseNumber);

        // 2. Validate the trip
        if ((trip == null) || (trip.getTripStatus() != TripStatus.TRIP_STARTED))
        {
            throw new TripNotFoundException("Invalid license Number, Trip not located!");
        }

        // 3. Get the cab details and validate the details.
        Cab cab = CabManager.getInstance().getCab(licenseNumber);
        if ((cab == null) || (cab.getStatus() != Status.ASSIGNED))
        {
            throw new IncorrectLicenseNumberException("Cannot end the trip, Invalid License Number");
        }

        // 4. Set the end location and end the trip
        Location location = Location.create(destX, destY);
        trip.setEndLocation(location);

        trip.endTrip();

        // 5. make cab available for other customers
        TaxiServiceManager.getInstance().reassignCabs(licenseNumber, location);

        // 6. End trip will calculate the cost and store in trip, return the cost value.
        return trip.getCost();
    }

}
