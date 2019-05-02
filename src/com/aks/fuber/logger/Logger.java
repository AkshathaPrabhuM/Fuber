/*
 * Class: Logger
 *
 * Created on May 1, 2019
 *
 */
package com.aks.fuber.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger
{
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private static Logger INSTANCE;

    public static Logger getInstance()
    {
        if (Logger.INSTANCE == null)
        {
            Logger.INSTANCE = new Logger();
        }
        return Logger.INSTANCE;
    }

    private Logger()
    {
        // Do nothing here
    }

    public void log(String message)
    {
        System.out.println(Logger.formatter.format(LocalDateTime.now()) + " message");
    }

}
