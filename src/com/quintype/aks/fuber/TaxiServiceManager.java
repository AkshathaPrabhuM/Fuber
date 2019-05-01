/*
 * Class: TaxiServiceManager
 * 
 * Created on Apr 30, 2019
 * 
 * (c) Copyright Lam Research Corporation, unpublished work, created 2019
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Lam Research Corporation
 * 4000 N. First Street
 * San Jose, CA
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
