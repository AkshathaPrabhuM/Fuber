/*
 * Class: TaxiServiceManager
 *
 * Created on Apr 30, 2019
 *
 */
package com.aks.fuber.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.aks.fuber.exception.CabNotFoundException;
import com.aks.fuber.impl.cab.Cab;
import com.aks.fuber.impl.cab.Color;
import com.aks.fuber.impl.cab.Location;
import com.aks.fuber.impl.cab.Status;
import com.aks.fuber.logger.Logger;

public class TaxiServiceManager
{

    private static TaxiServiceManager INSTANCE;

    public static TaxiServiceManager getInstance()
    {
        if (TaxiServiceManager.INSTANCE == null)
        {
            TaxiServiceManager.INSTANCE = new TaxiServiceManager();
        }
        return TaxiServiceManager.INSTANCE;
    }

    private Map<String, Cab> assignedCabs;

    private Map<String, Cab> availableCabs;

    private TaxiServiceManager()
    {
        this.availableCabs = new HashMap<String, Cab>();
        this.assignedCabs = new HashMap<String, Cab>();
    }

    public void addCabs(Cab cab)
    {
        this.availableCabs.put(cab.getLicenseNumber(), cab);
    }

    public String assignCab(double sourceX, double sourceY) throws CabNotFoundException
    {
        // Find closest cab.
        if (!this.isCabsAvailable())
        {
            throw new CabNotFoundException("Cabs not available at the moment! Please try again later");
        }

        Cab closestCab = null;
        double minDistance = Double.MAX_VALUE;
        for (Cab cab : this.availableCabs.values())
        {
            double distance = cab.getLocation().distance(Location.create(sourceX, sourceY));
            if (distance < minDistance)
            {
                closestCab = cab;
                minDistance = distance;
            }
        }

        this.availableCabs.remove(closestCab.getLicenseNumber());
        this.assignedCabs.put(closestCab.getLicenseNumber(), closestCab);
        closestCab.setStatus(Status.ASSIGNED);

        TripManager.getInstance().createTrip(closestCab.getLicenseNumber(), false);

        return closestCab.getLicenseNumber();
    }

    public String assignCab(double sourceX, double sourceY, Color colorPref) throws CabNotFoundException
    {
        if (colorPref == null)
        {
            Logger.getInstance().log("Color not provided. Any available cab will be returned shortly");
            return this.assignCab(sourceX, sourceY);
        }

        if (!this.isCabsAvailable(colorPref))
        {
            throw new CabNotFoundException("Cabs not available in " + colorPref + "! Please try again later!");
        }

        Cab closestCab = null;
        double minDistance = Double.MAX_VALUE;
        for (Cab cab : this.availableCabs.values())
        {
            if (cab.getColor().equals(colorPref))
            {
                double distance = cab.getLocation().distance(Location.create(sourceX, sourceY));
                if (distance < minDistance)
                {
                    closestCab = cab;
                    minDistance = distance;
                }
            }
        }

        this.availableCabs.remove(closestCab.getLicenseNumber());
        this.assignedCabs.put(closestCab.getLicenseNumber(), closestCab);
        closestCab.setStatus(Status.ASSIGNED);

        TripManager.getInstance().createTrip(closestCab.getLicenseNumber(), true);

        return closestCab.getLicenseNumber();
    }

    public boolean isCabsAvailable()
    {
        return this.availableCabs.size() > 0;
    }

    public boolean isCabsAvailable(Color colorPref)
    {
        if (!this.isCabsAvailable())
        {
            return false;
        }

        return this.availableCabs.values().stream().filter(x -> x.getColor().equals(colorPref))
            .collect(Collectors.toList()).size() > 0;
    }

    public void reassignCabs(String licenseNumber, Location location) throws CabNotFoundException
    {
        if ((licenseNumber == null) || (this.assignedCabs.get(licenseNumber) == null))
        {
            throw new CabNotFoundException("");
        }

        Cab cab = this.assignedCabs.remove(licenseNumber);
        this.availableCabs.put(licenseNumber, cab);

        cab.setStatus(Status.ONLINE);
        cab.setLocation(location);
    }
}
