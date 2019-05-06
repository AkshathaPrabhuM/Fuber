/*
 * Class: TaxiServiceManager
 *
 * Created on Apr 30, 2019
 *
 */
package com.aks.fuber.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aks.fuber.exception.CabNotFoundException;
import com.aks.fuber.impl.cab.Cab;
import com.aks.fuber.impl.cab.Color;
import com.aks.fuber.impl.cab.Location;
import com.aks.fuber.impl.cab.Status;

public class TaxiServiceManager
{

    private static final String CABS_NOT_AVAILABLE = "Cabs not available at the moment! Please try again later";

    private static TaxiServiceManager instance;

    public static TaxiServiceManager getInstance()
    {
        if (TaxiServiceManager.instance == null)
        {
            TaxiServiceManager.instance = new TaxiServiceManager();
        }
        return TaxiServiceManager.instance;
    }

    private Map<String, Cab> assignedCabs;

    private Map<String, Cab> availableCabs;

    private TaxiServiceManager()
    {
        this.availableCabs = new HashMap<>();
        this.assignedCabs = new HashMap<>();
    }

    public void addCab(String liceneNumber) throws CabNotFoundException
    {
        // Get the cab from cab manager given the license number and add it to the cache
        Cab cab = CabManager.getInstance().getCab(liceneNumber);
        if (cab == null)
        {
            throw new CabNotFoundException("Cab not found for given license number");
        }
        this.availableCabs.put(cab.getLicenseNumber(), cab);
    }

    public String assignCab(double sourceX, double sourceY) throws CabNotFoundException
    {
        // 1. Make sure we have any cabs
        if (this.availableCabs.isEmpty())
        {
            throw new CabNotFoundException(TaxiServiceManager.CABS_NOT_AVAILABLE);
        }

        // 2. Get the closes cab by distance
        Cab closestCab = this.findClosest(Location.create(sourceX, sourceY), this.availableCabs.values());

        // 3. Validate we have valid cab
        if (closestCab == null)
        {
            throw new CabNotFoundException(TaxiServiceManager.CABS_NOT_AVAILABLE);
        }

        // 4. Mark the cab as assigned and some other book keeping
        this.markCabAssigned(closestCab, sourceX, sourceY, false);

        // 5. Return the license number of the assigned cab
        return closestCab.getLicenseNumber();
    }

    public String assignCab(double sourceX, double sourceY, Color colorPref) throws CabNotFoundException
    {
        // 1. Make sure we have any cabs
        if (this.availableCabs.isEmpty())
        {
            throw new CabNotFoundException(TaxiServiceManager.CABS_NOT_AVAILABLE);
        }

        // 2. Let us get only the cabs with the given color preference
        List<Cab> cabs = this.availableCabs.values().stream().filter(x -> x.getColor().equals(colorPref))
            .collect(Collectors.toList());

        // 3. Make sure we have any cabs
        if (cabs.isEmpty())
        {
            throw new CabNotFoundException(colorPref.toString() + " cabs not available now! Please try again later!");
        }

        // 4. Get the closes cab by distance
        Cab closestCab = this.findClosest(Location.create(sourceX, sourceY), cabs);

        // 5. Validate we have valid cab
        if (closestCab == null)
        {
            throw new CabNotFoundException(TaxiServiceManager.CABS_NOT_AVAILABLE);
        }

        // 6. Mark the cab as assigned and some other book keeping
        this.markCabAssigned(closestCab, sourceX, sourceY, true);

        // 7. Return the license number of the assigned cab
        return closestCab.getLicenseNumber();
    }

    private Cab findClosest(Location location, Collection<Cab> cabs)
    {
        // Currently linear search is implemented to find the closest cab.
        // TODO: Improve the finding closest cab algorithm
        Cab closestCab = null;
        double minDistance = Double.MAX_VALUE;
        for (Cab cab : cabs)
        {
            double distance = cab.getLocation().distance(location);
            if (distance < minDistance)
            {
                closestCab = cab;
                minDistance = distance;
            }
        }

        return closestCab;
    }

    private void markCabAssigned(Cab cab, double sourceX, double sourceY, boolean isColorPrefSet)
    {
        this.availableCabs.remove(cab.getLicenseNumber());
        this.assignedCabs.put(cab.getLicenseNumber(), cab);
        cab.setStatus(Status.ASSIGNED);
        cab.setLocation(Location.create(sourceX, sourceY));
        TripManager.getInstance().createTrip(cab.getLicenseNumber(), isColorPrefSet);
    }

    public void reassignCabs(String licenseNumber, Location location) throws CabNotFoundException
    {
        // 1. Validate the license number
        if ((licenseNumber == null) || (this.assignedCabs.get(licenseNumber) == null))
        {
            throw new CabNotFoundException("Incorrect Cab license number");
        }

        // 2. Remove from assigned cabs and make it available now.
        Cab cab = this.assignedCabs.remove(licenseNumber);
        this.availableCabs.put(licenseNumber, cab);

        // 3. Update other details on the cab.
        cab.setStatus(Status.ONLINE);
        cab.setLocation(location);
    }

    public void removeCab(String liceneNumber) throws CabNotFoundException
    {
        Cab cab = CabManager.getInstance().getCab(liceneNumber);
        if (cab == null)
        {
            throw new CabNotFoundException("Cab not found for given license number");
        }
        this.availableCabs.remove(cab.getLicenseNumber());
    }
}
