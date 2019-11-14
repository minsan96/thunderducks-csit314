import java.io.*;
import java.util.*;

public class Verification {

	public String credfile = "DuckTour/src/textdata/Credentials.txt";
	public String userInfofile = "DuckTour/src/textdata/UserInfo.txt";
	public String tourInfofile = "DuckTour/src/textdata/TourInfo.txt";
	//public String credHeader = "Username:Password:PriorityLevel";
	public String userLevel;
	
	//checks credentials for login
    public boolean verifylogin (String username, String password) {
    	boolean result = false;
    	Openfile Filereader = new Openfile();
        ArrayList<String> credAL = Filereader.read(credfile);
        
        for (String fdata : credAL)
        {
			String[] creddata = fdata.split(",");
			/*
			for (String s : creddata)
			{
				System.out.println(s);
			}
			*/
			if (username.equals(creddata[0]) && password.equals(creddata[1])){
				result = true;
				userLevel = creddata[2];
				break;
			}
			else {
				result = false;
			}
		}
    	return result;
    }
    
    //gets the user priority level
    public String getUserLevel()
    {
    	String result = userLevel;
    	return result;
    }
    
    //check if username exist
    public boolean checkusername (String username) {
    	boolean result = false;
    	Openfile Filereader = new Openfile();
        ArrayList<String> credAL = Filereader.read(credfile);
        
        for (String fdata : credAL)
        {
			String[] creddata = fdata.split(",");
			if (username.equals(creddata[0])){
				result = true;
				break;
			}
			else {
				result = false;
			}
		}
    	return result;
    }
    
    public boolean checktourname (String tourname) {
    	boolean result = false;
    	Openfile Filereader = new Openfile();
        ArrayList<String> tourAL = Filereader.read(tourInfofile);
        
        for (String fdata : tourAL)
        {
			String[] tourdata = fdata.split(",");
			if (tourname.equals(tourdata[0])){
				result = true;
				break;
			}
			else {
				result = false;
			}
		}
    	return result;
    }
    
    public void deleteUser (String username)
    {
    	Openfile Filereader = new Openfile();
        ArrayList<String> credAL = Filereader.read(credfile);
        ArrayList<String> tmp = new ArrayList<String>();
        //tmp.add(credHeader);
        for (String fdata : credAL)
        {
			String[] creddata = fdata.split(",");
			if (!username.equals(creddata[0])){
				tmp.add(fdata);
			}
		}
        Filereader.writeAL(credfile, tmp);
    }

    public void modUser (String username, String userlevel)
    {
    	Openfile Filereader = new Openfile();
        ArrayList<String> credAL = Filereader.read(credfile);

        for (int i = 0; i < credAL.size();i++)
        {
        	String tmp = credAL.get(i);
        	String[] creddata = tmp.split(",");
        	
        	if (username.equals(creddata[0])){
				//creddata[2] = userlevel;
				credAL.set(i, creddata[0] + "," + creddata[1] + "," + userlevel);
			}
        	
        }
        Filereader.writeAL(credfile, credAL);
    }
    
    public boolean deletetour(String username, String tname) 
    {
    	boolean result = false;
    	Openfile Filereader = new Openfile();
        ArrayList<String> tourAL = Filereader.read(tourInfofile);
        ArrayList<String> tmp = new ArrayList<String>();
        
        for (String fdata : tourAL)
        {
			String[] creddata = fdata.split(",");
			
			if (username.equals(creddata[0]) && tname.equals(creddata[1]))
			{
				result = true;
			}
			
			else
			{
				tmp.add(fdata);
			}

		}
        Filereader.writeAL(tourInfofile, tmp);
    	
    	return result;
    }
    
    public boolean deletetour(String tname) 
    {
    	boolean result = false;
    	Openfile Filereader = new Openfile();
        ArrayList<String> tourAL = Filereader.read(tourInfofile);
        ArrayList<String> tmp = new ArrayList<String>();
        
        for (String fdata : tourAL)
        {
			String[] tourdata = fdata.split(",");
			if(tourdata.length > 1 )
			{
				if (tname.equals(tourdata[1]))
				{
					result = true;
				}
				else
				{
					tmp.add(fdata);
				}
			}
				else
				{
					tmp.add(fdata);
				}

		}
        
        Filereader.writeAL(tourInfofile, tmp);
        
        return result;
    }
    
    public ArrayList<String> checkTourForMod(String username, String tname)
    {
    	boolean result = false;
    	Openfile Filereader = new Openfile();
        ArrayList<String> tourAL = Filereader.read(tourInfofile);
        ArrayList<String> tmp = new ArrayList<String>();
        
        for (String fdata : tourAL)
        {
			String[] creddata = fdata.split(",");
			
			if (username.equals(creddata[0]) && tname.equals(creddata[1]))
			{
				tmp.add(fdata);
			}

		}
    	return tmp;
    }
    
    public void updateTour(String tname, String data)
    {
    	Openfile Filereader = new Openfile();
        ArrayList<String> tourAL = Filereader.read(tourInfofile);

        for (int i = 0; i < tourAL.size(); i++)
        {
        	String tmp = tourAL.get(i);
        	String[] tourdata = tmp.split(",");
        	if(tourdata.length > 1 )
        	{
	        	if (tname.equals(tourdata[1])){
	        		System.out.println(i+"here ");
	        		tourAL.set(i, data);
				}
        	}
        	
        }
        Filereader.writeAL(tourInfofile, tourAL);
    }
    
    public String getCred()
    {
    	return credfile;
    }
    
    public String getUInfo()
    {
    	return userInfofile;
    }
    
    public String getTInfo()
    {
    	return tourInfofile;
    }
    
}
