/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theappapp;

/**
 *
 * @author Nicole
 */
import java.util.Date;
import java.io.*;

/**
 * Write a description of class University here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class University extends Application implements Serializable
{
    private String commonApp;
    public static int numUnis;

    /**
     * Constructor for objects of class University
     */
    public University()
    {
        super();
        numUnis++;
        this.commonApp = null;
    }
    
    public University(String name, String nickname,String description, String commonApp)
    {
        super(name, nickname, description);
        numUnis++;
        this.commonApp = commonApp;
    }

    @Override
    public String getCommonApp()
    {
        return this.commonApp;
    }
    
    @Override
    public void setCommonApp(String newApp)
    {
        this.commonApp = newApp;
    }
    
    @Override
    public Date getDate(){return null;}
    @Override
    public void setDate(Date newDate){}
    @Override
    public String getType(){return null;}   
    @Override
    public void setType(String newType){}
}
