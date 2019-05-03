/*
 * Class: CabManager
 *
 * Created on May 2, 2019
 *
 */
package com.aks.fuber.impl;

import java.util.HashMap;
import java.util.Map;

import com.aks.fuber.impl.cab.Cab;
import com.aks.fuber.impl.cab.Color;

public class CabManager
{
    private static CabManager INSTANCE;

    public static CabManager getInstance()
    {
        if (CabManager.INSTANCE == null)
        {
            CabManager.INSTANCE = new CabManager();
        }
        return CabManager.INSTANCE;
    }

    private Map<String, Cab> cabs;

    private CabManager()
    {
        this.cabs = new HashMap<String, Cab>();
    }

    public Cab getCab(String licenseNumber)
    {
        return this.cabs.get(licenseNumber);
    }

    public void registerCab(String licenseNumber, Color color, String model, int year, int capacity)
    {
        // Create a cab with the details
        Cab cab = new Cab(licenseNumber);
        cab.setColor(color);
        cab.setModel(model);
        cab.setManufacturedYear(year);
        cab.setCapacity(capacity);

        this.cabs.put(licenseNumber, cab);
    }
}
