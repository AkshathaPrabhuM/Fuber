/*
 * Class: IncorrectLicenceNumber
 *
 * Created on May 2, 2019
 *
 */
package com.aks.fuber.exception;

public class IncorrectLicenseNumberException extends Exception
{
    private static final long serialVersionUID = -3351743239805926942L;

    public IncorrectLicenseNumberException(String message)
    {
        super(message);
    }
}
