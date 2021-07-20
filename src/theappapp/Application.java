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
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

/**
 * 
 */
public abstract class Application implements Serializable
{
    private String name; //full name of application (uni/scholarship/common app name)
    private String nickname; //name for display in tasks, etc, must have few chars
    private String description; //optional description, users can enter links, address, etc. for own convenience
    private ArrayList<Task> tasks; //list of tasks corresponding to this application

    /**
     * Constructor with no parameters, creates new empty object
     */
    public Application()
    {
        name = null;
        nickname = null;
        description = null;
        tasks = null;
    }

    /**
     * Constructor for objects of class Application.
     * Using polymorphism (method overloading) to provide 2 constructors.
     */
    public Application(String name, String nickname, String description)
    {
        this.name = name;
        this.nickname = nickname;
        this.description = description;
        tasks = new ArrayList<Task>();
    }
    /**
     * returns name of application
    */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * 
     */
    public ArrayList<Task> getAllTasks()
    {
        return this.tasks;
    }
    
    /**
     * mutator method for name field
     * @param newName 
     */
    public void setName(String newName)
    {
        this.name = newName;
    }
    
    /**
     * returns nickname of application
     * @return 
     */
    public String getNickname()
    {
        return this.nickname;
    }
    
    /**
    * mutator method for nickname field
     * @param newNickname 
     */
    public void setNickname(String newNickname)
    {
        this.nickname = newNickname;
    }
    
    /**
     * returns description
     * @return 
     */
    public String getDescription()
    {
        return this.description;
    }
    
    /**
     * mutator method for description field
     * @param newDesc 
     */
    public void setDesription(String newDesc)
    {
        this.description = newDesc;
    }
    
    /**
     * Returns number of incomplete tasks (that are not overdue).
     * @return 
     */
    public int countCurrentTasks()
    {
        int n = 0;
        Date today = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 0, 0); //Date represents today
        for(int i = 0; i<this.tasks.size(); i++)
        {
            if (!this.tasks.get(i).getCompletedStatus() && !this.tasks.get(i).getDate().before(today))
            {
                n++;
            }
        }
        return n;
    }
    
    /**
     * Returns list of incomplete tasks (that are not overdue) sorted chronologically(nearest first).
     * @return 
     */
    public ArrayList<Task> getCurrentTasks()
    {
        ArrayList<Task> taskList = new ArrayList<>();
        Date today = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 0, 0); //Date represents today
        taskList.add(null);
        
        for(int i = 0; i<this.tasks.size(); i++)
        {
            if (!this.tasks.get(i).getDate().before(today) && !this.tasks.get(i).getCompletedStatus())
            {
                int n=0;
                while (taskList.get(n)!=null && this.tasks.get(i).getDate().after(taskList.get(n).getDate()))
                {
                    n++;
                }
                taskList.add(n,this.tasks.get(i));
            } 
            
        }
        return taskList;
    }
    
    /**
     * returns number of completed tasks
     * @return 
     */
    public int countCompletedTasks()
    {
        int n = 0;
        Date today = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 0, 0); //Date represents today
        for(int i = 0; i<this.tasks.size(); i++)
        {
            if (this.tasks.get(i).getCompletedStatus())
            {
                n++;
            }
        }
        return n;
    }
    
    /**
     * Returns list of completed tasks in reverse chronological order(most recent first).
     * @return 
     */
    public ArrayList<Task> getCompletedTasks()
    {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(null);
        
        for(int i = 0; i<this.tasks.size(); i++)
        {
            if (this.tasks.get(i).getCompletedStatus())
            {
                int n=0;
                while (taskList.get(n)!=null && this.tasks.get(i).getDate().before(taskList.get(n).getDate()))
                {
                    n++;
                }
                taskList.add(n,this.tasks.get(i));
            }
        }
        return taskList;
    }
    
    /**
     * returns number of overdue tasks
     * @return 
     */
    public int countOverdueTasks()
    {
        int n = 0;
        Date today = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 0, 0); //Date represents today
        for(int i = 0; i<this.tasks.size(); i++)
        {
            if (!this.tasks.get(i).getCompletedStatus() && this.tasks.get(i).getDate().before(today))
            {
                n++;
            }
        }
        return n;
    }
    
    /**
     * Returns list of overdue tasks sorted chronologically(oldest first).
     * @return 
     */
    public ArrayList<Task> getOverdueTasks()
    {
        ArrayList<Task> taskList = new ArrayList<>();
        Date today = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 0, 0); //Date represents today
        taskList.add(null);
        
        for(int i = 0; i<this.tasks.size(); i++)
        {
            if (!this.tasks.get(i).getCompletedStatus() && this.tasks.get(i).getDate().before(today))
            {
                int n=0;
                while (taskList.get(n)!=null && this.tasks.get(i).getDate().after(taskList.get(n).getDate()))
                {
                    n++;
                }
                taskList.add(n,this.tasks.get(i));
            }
        }
        return taskList;
    }
    
    public void addTask (Task newTask)
    {
        tasks.add(newTask);
        
    }
    
    public void delete(Task t)
    {
        this.tasks.remove(t);
    }

    /**
     * Overrides toString(), returns nickname entered by user instead.
     * @return 
     */
    @Override
    public String toString() 
    { 
        if(this.nickname.equals(null))
        {
            return this.name; //if user has not entered a nickname, the application's name is used instead
        }
        else
        {
            return this.nickname; //returns nickname in place of automatically generated String representation of object

        }
    } 
    
    //uni methods
    public abstract String getCommonApp();
    public abstract void setCommonApp(String newApp);
    
    //specialapp methods
    public abstract Date getDate();
    public abstract void setDate(Date newDate);   
    public abstract String getType();   
    public abstract void setType(String newType);
}

