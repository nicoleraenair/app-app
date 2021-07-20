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


public class Task implements Serializable
{
    private String description;
    private Date dueDate;
    private boolean isComplete;
    private String category;

    /**
     * 
     */
    public Task()
    {
        description = null;
        dueDate = null;
        isComplete = false;
        category = null;
    }
    
    public Task(String task, Date date, String category)
    {
        description = task;
        dueDate = date;
        isComplete = false;
        this.category = category;
    }
    
    /**
     * accessor method for task description
     * @return description of task
     */
    public String getTask()
    {
        return this.description;
    }
    
    /**
     * mutator method for task description
     * @param newTask 
     */
    public void setTask(String newTask)
    {
        this.description = newTask;
    }
    
    public Date getDate()
    {
        return this.dueDate;
    }
    
    public void setDate (Date newDate)
    {
        this.dueDate = newDate;
    }
    
    public boolean getCompletedStatus()
    {
        return this.isComplete;
    }
    
    public void setStatus(boolean complete)
    {
        this.isComplete = complete;
    }
    
    public void markAsComplete()
    {
        setStatus(true);
    }
    
    public void markAsIncomplete()
    {
        setStatus(false);
    }
    
    public String getCategory()
    {
        return this.category;
    }
    
    public void setCategory(String cat)
    {
        this.category = cat;
    }
}
