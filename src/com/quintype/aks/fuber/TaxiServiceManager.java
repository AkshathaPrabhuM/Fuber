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
import java.util.stream.Collectors;

import com.quintype.aks.fuber.exception.CabNotFoundException;
import com.quintype.aks.fuber.logger.Logger;

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

    private Map<String, Car> assignedCars;

    private Map<String, Car> availableCars;

    private TaxiServiceManager()
    {
        this.availableCars = new HashMap<String, Car>();
        this.assignedCars = new HashMap<String, Car>();
    }

    public void addCars(Car car)
    {
        this.availableCars.put(car.getLicenseNumber(), car);
    }

    public String assignCars(double sourceX, double sourceY) throws CabNotFoundException
    {
        // Find closest car.
        if (!this.isCarsAvailable())
        {
            throw new CabNotFoundException("Cabs not available at the moment! Please try again later");
        }

        Car closestCar = null;
        double minDistance = Double.MAX_VALUE;
        for (Car car : this.availableCars.values())
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

        this.availableCars.remove(closestCar.getLicenseNumber());
        this.assignedCars.put(closestCar.getLicenseNumber(), closestCar);

        return closestCar.getLicenseNumber();
    }

    public String assignCars(double sourceX, double sourceY, Color colorPref) throws CabNotFoundException
    {
        if (colorPref == null)
        {
            Logger.getInstance().log("Color not provided. Any available cab will be returned shortly");
            this.assignCars(sourceX, sourceY);
        }

        if (!this.isCarsAvailable(colorPref))
        {
            throw new CabNotFoundException("Cabs not available in " + colorPref + "! Please try again later!");
        }

        Car closestCar = null;
        double minDistance = Double.MAX_VALUE;
        for (Car car : this.availableCars.values())
        {
            if (car.getColor().equals(colorPref))
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
        }

        this.availableCars.remove(closestCar.getLicenseNumber());
        this.assignedCars.put(closestCar.getLicenseNumber(), closestCar);

        return closestCar.getLicenseNumber();
    }

    public List<Car> getAvailableCars()
    {
        return (List<Car>) this.availableCars.values();
    }

    public boolean isCarsAvailable()
    {
        return this.availableCars.size() > 0;
    }

    public boolean isCarsAvailable(Color colorPref)
    {
        if (!this.isCarsAvailable())
        {
            return false;
        }

        return this.availableCars.values().stream().filter(x -> x.getColor().equals(colorPref))
            .collect(Collectors.toList()).size() > 0;
    }
}
