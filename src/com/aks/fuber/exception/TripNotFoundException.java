/*
 * Class: TripNotFoundException
 *
 * Created on May 2, 2019
 *
 */
package com.aks.fuber.exception;

public class TripNotFoundException extends Exception
{
    private static final long serialVersionUID = 4032149035929129780L;

    public TripNotFoundException(String message)
    {
        super(message);
    }
}
