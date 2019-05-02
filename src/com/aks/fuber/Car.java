/*
 * Class: Car
 *
 * Created on Apr 30, 2019
 *
 */
package com.aks.fuber;

public class Car extends Vehicle
{

    private Location location;

    private Status status;

    public Car(String licenseNo, Location sourceLocation)
    {
        super(licenseNo);
        this.location = sourceLocation;
        this.status = Status.AVAILABLE;
    }

    public Location getLocation()
    {
        return this.location;
    }

    public Status getStatus()
    {
        return this.status;
    }

    public void setLocation(Location sourceLocation)
    {
        this.location = sourceLocation;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

}
