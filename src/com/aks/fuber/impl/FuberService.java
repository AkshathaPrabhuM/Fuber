/*
 * Class: FuberService
 *
 * Created on May 2, 2019
 *
 */
package com.aks.fuber.impl;

import com.aks.fuber.IFuberService;
import com.aks.fuber.impl.cab.Cab;
import com.aks.fuber.impl.cab.Color;
import com.aks.fuber.impl.cab.Location;
import com.aks.fuber.impl.cab.Status;

public class FuberService implements IFuberService
{

    /* (non-Javadoc)
     * @see com.aks.fuber.IFuberService#makeCabOnline(java.lang.String, double, double)
     */
    public void makeCabOnline(String licenseNumber, double latitude, double longitude)
    {
        Location currentLocation = Location.create(latitude, longitude);
        Cab cab = CabManager.getInstance().getCab(licenseNumber);
        cab.setLocation(currentLocation);
        cab.setStatus(Status.ONLINE);

        TaxiServiceManager.getInstance().addCabs(cab);
    }

    /* (non-Javadoc)
     * @see com.aks.fuber.IFuberService#registerCab(java.lang.String, com.aks.fuber.Color, java.lang.String, int, int)
     */
    public void registerCab(String licenseNumber, Color color, String model, int year, int capacity)
    {
        CabManager.getInstance().registerCab(licenseNumber, color, model, year, capacity);
    }

    /* (non-Javadoc)
     * @see com.aks.fuber.intf.IFuberService#requestCab(double, double)
     */
    public String requestCab(double sourceX, double sourceY) throws Exception
    {
        return TaxiServiceManager.getInstance().assignCab(sourceX, sourceY);
    }

    /* (non-Javadoc)
     * @see com.aks.fuber.intf.IFuberService#requestCab(double, double, com.aks.fuber.Color)
     */
    public String requestCab(double sourceX, double sourceY, Color colorPref) throws Exception
    {
        return TaxiServiceManager.getInstance().assignCab(sourceX, sourceY, colorPref);
    }

    /* (non-Javadoc)
     * @see com.aks.fuber.intf.IFuberService#startTrip(java.lang.String)
     */
    public void startTrip(String licenseNumber) throws Exception
    {
        TripManager.getInstance().startTrip(licenseNumber);
    }

    /* (non-Javadoc)
     * @see com.aks.fuber.intf.IFuberService#stopTrip(java.lang.String, double, double)
     */
    public double stopTrip(String licenseNumber, double destX, double destY) throws Exception
    {
        return TripManager.getInstance().stopTrip(licenseNumber, destX, destY);
    }

}
