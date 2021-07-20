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
 * Write a description of class SpecialApp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpecialApp extends Application implements Serializable
{
    private Date dueDate;
    private String type;

    /**
     * Constructor for objects of class SpecialApp
     */
    public SpecialApp()
    {
        super();
        this.dueDate = null;
        this.type = null;
    }
    
    public SpecialApp(String name, String nickname, String description, Date dueDate, String type)
    {
        super(name, nickname, description);
        this.dueDate = dueDate;
        this.type = type;
    }

    @Override
    public Date getDate()
    {
        return this.dueDate;
    }
    
    @Override
    public void setDate(Date newDate)
    {
        this.dueDate = newDate;
    }
    
    @Override
    public String getType()
    {
        return this.type;
    }
    
    @Override
    public void setType(String newType)
    {
        this.type = newType;
    }

    @Override
    public String getCommonApp(){return null;}
    @Override
    public void setCommonApp(String newApp){}
}
