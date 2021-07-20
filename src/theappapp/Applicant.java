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
 * Write a description of class Applicant here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Applicant implements Serializable
{
    private ArrayList<Task> allTasks;
    private ArrayList<Application> allApps;//list of objects from Applicant class and subclasses
    
    public Applicant()
    {
        allTasks = new ArrayList<>();
        allApps = new ArrayList<>();
    }
    
    public ArrayList<Task> getTasks()
    {
        return this.allTasks;
    }
    
    public ArrayList<Application> getApps()
    {
        return this.allApps;
    }
    
    public void addTask(String task, Date date, String category)
    {
        Task newTask = new Task(task, date, category);
        getCatFromName(category).addTask(newTask);
        allTasks.add(newTask);
    }
    
    /**
     * returns University or SpecialApp object with name matching the String parameter
     * @param catName
     * @return 
     */
    public Application getCatFromName(String catName)
    {
        int n = 0;
        while(n<allApps.size() && !allApps.get(n).getName().equalsIgnoreCase(catName)) 
        {
            n++;//searches arraylist for object matching name parameter
        }
        
        if (n==allApps.size())
        {
            return null; //returns null if no objects matched name parameter
        }
        else
        {
            return allApps.get(n);//returns match if found
        }
    }
    /**
     * University object is added to Application ArrayList.
     * @param name name of university to be added to applicant
     * @param nickname nickname of university for convenient display
     * @param description any additional info about university
     * @param commonApp name of existing common application that corresponds to the university
     */
    public void addUni(String name, String nickname, String description, String commonApp)
    {
        allApps.add(new University(name, nickname, description, commonApp));
        //creates new University object by calling University constructor
        //University object treated as  parent class Application in order to be added to Application ArrayList
    }
 
    public void addApp(String name, String nickname, String description, Date dueDate)
    {
        allApps.add(new SpecialApp(name, nickname, description, dueDate, "Common Application"));
    }
    
    public void addScholarship(String name, String nickname, String description, Date dueDate)
    {
        allApps.add(new SpecialApp(name, nickname, description, dueDate, "Scholarship"));
    }
    
    /**
     * Returns list of incomplete tasks (that are not overdue) sorted chronologically(nearest first).
     * @return 
     */
    public ArrayList<Task> getCurrentTasks()
    {
        ArrayList<Task> taskList = new ArrayList<>(); //creates empty arraylist to store current tasks
        Date today = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 0, 0); //Date represents today
        taskList.add(null); //helps avoid nullPointerException
        
        for(int i = 0; i<this.allTasks.size(); i++)//loops through list of all tasks
        {
            if (!this.allTasks.get(i).getCompletedStatus() && !this.allTasks.get(i).getDate().before(today))
            //if task is incomplete and not overdue (due before today), then it is a current task
            {
                /**
                 * inserts task in list of current tasks in chronological order
                 */
                int n=0; //default point of insertion is first index
                while (taskList.get(n)!=null && this.allTasks.get(i).getDate().after(taskList.get(n).getDate()))
                {
                    n++; //finds point of insertion for task based on due date
                }
                taskList.add(n,this.allTasks.get(i)); //adds task to list at appropriate point of insertion
            }
        }
        
        taskList.remove(null); //removes null object added for purpose of avoiding exception
        
        return taskList; //returns list produced of current tasks
    }
    
    /**
     * Returns list of completed tasks in reverse chronological order(most recent first).
     * @return 
     */
    public ArrayList<Task> getCompletedTasks()
    {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(null);
        
        for(int i = 0; i<this.allTasks.size(); i++)
        {
            if (this.allTasks.get(i).getCompletedStatus())
            {
                int n=0;
                while (taskList.get(n)!=null && this.allTasks.get(i).getDate().before(taskList.get(n).getDate()))
                {
                    n++;
                }
                taskList.add(n,this.allTasks.get(i));
            }
        }
        
        taskList.remove(null);
        return taskList;
    }
    
    /**
     * Returns list of overdue tasks sorted chronologically(oldest first).
     */
    public ArrayList<Task> getOverdueTasks()
    {
        ArrayList<Task> taskList = new ArrayList<Task>();
        Date today = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 0, 0); //Date represents today
        taskList.add(null);
        
        for(int i = 0; i<this.allTasks.size(); i++)
        {
            if (!this.allTasks.get(i).getCompletedStatus() && this.allTasks.get(i).getDate().before(today))
            {
                int n=0;
                while (taskList.get(n)!=null && this.allTasks.get(i).getDate().after(taskList.get(n).getDate()))
                {
                    n++;
                }
                taskList.add(n,this.allTasks.get(i));
            }
        }
        taskList.remove(null);
        return taskList;
    }
    
    public ArrayList<Application> getUnis()
    {
        ArrayList<Application> uniList = new ArrayList<Application>(); //creats empty list to store uni objects
        uniList.add(null);
        for(int i = 0; i<this.allApps.size(); i++)//loops through application list to find unis
        {
            if (this.allApps.get(i) instanceof University) //checks for object type
            {
                /**
                 * inserts uni objects into uni list
                 */
                
                uniList.add(this.allApps.get(i)); //adds uni to list
            }
        }
        return uniList; //returns list of all university objects
    }
    
    public ArrayList<Application> getCommonApps()
    {
        ArrayList<Application> appList = new ArrayList<Application>();
        appList.add(null);
        for(int i = 0; i<this.allApps.size(); i++)
        {
            if (this.allApps.get(i) instanceof SpecialApp && this.allApps.get(i).getType().equals("Common Application"))
            {
                int n=0;
                while (appList.get(n)!=null && this.allApps.get(i).getDate().after(appList.get(n).getDate()))
                {
                    n++;
                }
                
                Application s = this.allApps.get(i);
                
                appList.add(n,s);
            }
        }
        return appList;
    }
    
    public ArrayList<Application> getScholarships()
    {
        ArrayList<Application> scholarList = new ArrayList<Application>();
        scholarList.add(null);
        for(int i = 0; i<this.allApps.size(); i++)
        {
            if (this.allApps.get(i) instanceof SpecialApp && this.allApps.get(i).getType().equals("Scholarship"))
            {
                int n=0;
                while (scholarList.get(n)!=null && this.allApps.get(i).getDate().after(scholarList.get(n).getDate()))
                {
                    n++;
                }
                
                Application s = this.allApps.get(i);
                
                scholarList.add(n,s);
            }
        }
        return scholarList;
    }
    /**
     * 
     * @param nameOrNickname
     * @return list of search results (unis with names or nicknames that contain search term)
     */
    public ArrayList<University> searchUnis(String nameOrNickname)
    {
        String searchTerm = nameOrNickname.toLowerCase(); //converts term to lower case so Strings can be compared
        ArrayList<University> results = new ArrayList<University>(); //creates arraylist to which search results are added
        for (int i=0; i<this.allApps.size(); i++)
        {
            if(allApps.get(i) instanceof University  //only checks university objects
                    && (allApps.get(i).getName().toLowerCase().contains(searchTerm) //if uni name (in lower case) is a match
                    || allApps.get(i).getNickname().toLowerCase().contains(searchTerm))) //or if nickname is a match
            {
                results.add((University)allApps.get(i)); //if uni is a match to search term it is added to search result list
            }
        }
        return results; //search results are returned
    }
    
    public void delete(Application a)
    {
        if (a instanceof University)
        {
            University.numUnis --;
        }
        for (int i = 0; i<a.getAllTasks().size(); i++)
        {
            allTasks.remove(a.getAllTasks().get(i));
        }
        allApps.remove(a);

    }
    
    public void delete(Task t)
    {
        allTasks.remove(t);
        getCatFromName(t.getCategory()).delete(t);
    }
    /**
     * for testing
     * @param y
     * @param m
     * @param d
     * @return 
     */
    public Date makeDate(int y, int m, int d)
    {
        Date newDate = new Date(y,m,d);
        return newDate;
    }
    
    public int countCurrentTasks()
    {
        int n = 0;
        Date today = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 0, 0); //Date represents today
        System.out.println(today);
        for(int i = 0; i<this.allTasks.size(); i++)
        {
            if (!this.allTasks.get(i).getCompletedStatus() && !this.allTasks.get(i).getDate().before(today))
            {
                n++;
            }
        }
        return n;
    }
    
    public int countCompletedTasks()
    {
        int n = 0;
        for(int i = 0; i<this.allTasks.size(); i++)
        {
            if (this.allTasks.get(i).getCompletedStatus())
            {
                n++;
            }
        }
        return n;
    }
    
    public int countOverdueTasks()
    {
        int n = 0;
        Date today = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 0, 0); //Date represents today
        for(int i = 0; i<this.allTasks.size(); i++)
        {
            if (!this.allTasks.get(i).getCompletedStatus() && this.allTasks.get(i).getDate().before(today))
            {
                n++;
            }
        }
        return n;
    }
}



