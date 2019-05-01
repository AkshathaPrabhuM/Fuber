/*
 * Class: TaxiServiceManager
 * 
 * Created on Apr 30, 2019
 * 
 */
package com.quintype.aks.fuber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quintype.aks.fuber.exception.CabNotFoundException;

public class TaxiServiceManager
{

    private static TaxiServiceManager INSTANCE;

    private Map<String, Car> availableCars;

    private Map<String, Car> assignedCars;

    private TaxiServiceManager()
    {
        availableCars = new HashMap<String, Car>();
        assignedCars = new HashMap<String, Car>();
    }

    public static TaxiServiceManager getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new TaxiServiceManager();
        }
        return INSTANCE;
    }

    public boolean isCarsAvailable()
    {
        return availableCars.size() > 0;
    }

    public List<Car> getAvailableCars()
    {
        return (List<Car>) availableCars.values();
    }

    public String assignCars(double sourceX, double sourceY) throws CabNotFoundException
    {
        // Find closest car.
        if (!isCarsAvailable())
        {
            throw new CabNotFoundException("Cabs not available at the moment! Please try later");
        }
        
        Car closestCar = null;
        double minDistance = Double.MAX_VALUE;
        for (Car car : availableCars.values())
        {
            double distance = DistanceCalculator.getInstance().calculateDistance(
                car.getLocation().getLatitude(),
                car.getLocation().getLongititude(),
                sourceX,
                sourceY);
            if (distance < minDistance)
            {
                closestCar = car;
                minDistance = distance;
            }
        }

        availableCars.remove(closestCar.getLicenseNumber());
        assignedCars.put(closestCar.getLicenseNumber(), closestCar);

        return closestCar.getLicenseNumber();
    }

    public void addCars(Car car)
    {
        this.availableCars.put(car.getLicenseNumber(), car);
    }
}
