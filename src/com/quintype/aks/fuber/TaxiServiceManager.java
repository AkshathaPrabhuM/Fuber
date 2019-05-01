/*
 * Class: TaxiServiceManager
 * 
 * Created on Apr 30, 2019
 * 
 */
package com.quintype.aks.fuber;

import java.util.HashMap;
import java.util.Map;

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

    public void assignCars(float x, float y)
    {
        // Find closest car.

        String licenseNumber = "";
        Car cab = availableCars.remove(licenseNumber);
        assignedCars.put(licenseNumber, cab);
    }

    public void addCars(Car car)
    {
        this.availableCars.put(car.getLicenseNumber(), car);
    }
}
