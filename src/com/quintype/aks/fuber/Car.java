/*
 * Class: Car
 * 
 * Created on Apr 30, 2019
 * 
 */
package com.quintype.aks.fuber;

public class Car extends Vehicle
{

    private Status status;

    private Location location;

    public Car(String licenseNo, Location sourceLocation)
    {
        super(licenseNo);
        this.location = sourceLocation;
        status = Status.ASSIGNED;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public void setLocation(Location sourceLocation)
    {
        this.location = sourceLocation;
    }

    public Location getLocation()
    {
        return this.location;
    }

}
