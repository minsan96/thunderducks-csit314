import java.io.*;
import java.util.*;

public class Verification {

	public String credfile = "DuckTour/src/textdata/Credentials.txt";
	public String userInfofile = "DuckTour/src/textdata/UserInfo.txt";
	public String tourInfofile = "DuckTour/src/textdata/TourInfo.txt";
	public String tourRecfile = "DuckTour/src/textdata/TourRecords.txt";
	public String paymntRecfile = "DuckTour/src/textdata/PaymentRecords.txt";
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
    
    //check if tourname has been used
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
    
    //delete user function
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
    
    //modify user function
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
    
    //delete tour function for local guide while checking their usernames
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
    
    //overloading the deletetour function for admin to delete 
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
    
    //write the updates to the file for updates
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
	        		//System.out.println(i+"here ");
	        		tourAL.set(i, data);
				}
        	}
        	
        }
        Filereader.writeAL(tourInfofile, tourAL);
    }
    
    //check the available username for rating
    public String checkOtherUname(String username)
    {
    	String result = "";
    	Openfile Filereader = new Openfile();
        ArrayList<String> tourAL = Filereader.read(credfile);
        
        for (String fdata : tourAL)
        {
			String[] creddata = fdata.split(",");
			
			if (username.equals(creddata[0]))
			{
				result = fdata;
			}

		}
    	return result;
    }
    
    //function to write the update to the rating
    public void rateUser(String olddata, String newdata)
    {
    	Openfile Filereader = new Openfile();
        ArrayList<String> credAL = Filereader.read(credfile);

        for (int i = 0; i < credAL.size(); i++)
        {
        	String tmp = credAL.get(i);
        	
        	if (tmp.equals(olddata))
        	{
        		credAL.set(i, newdata);
        	}
        	
        }
        Filereader.writeAL(credfile, credAL);
    }
    
    //adding new string to the records file
    public void updateRecords(String data)
    {
    	Openfile Filereader = new Openfile();
    	Filereader.writeString(tourRecfile, data);
        //ArrayList<String> credAL = Filereader.read(credfile);
    }
    
    //pull tour records out
    public ArrayList<String> PullAllRecords()
    {
    	Openfile Filereader = new Openfile();
        ArrayList<String> recordAL = Filereader.read(tourRecfile);
        ArrayList<String> tmp = new ArrayList<String>();
        
        //strip away the first line
        for (int i = 1; i < recordAL.size(); i++)
        {
        	tmp.add(recordAL.get(i));
        }
        return tmp;
    }
    
    //retrieves all data specific to the tour
    public ArrayList<String> RecordCheck(String tname)
    {
    	Openfile Filereader = new Openfile();
        ArrayList<String> tourAL = Filereader.read(tourRecfile);
        ArrayList<String> tmp = new ArrayList<String>();
        
        for (String fdata : tourAL)
        {
			String[] tourdata = fdata.split(", ");
			if(tourdata.length > 1 )
	        	{
				if (tname.equals(tourdata[2]))
				{
					tmp.add(fdata);
				}
        	}

		}
    	return tmp;
    }
    
    //pull payment record
    public ArrayList<String> pullPaymentRecords(String username, String tname)
    {
    	Openfile Filereader = new Openfile();
    	ArrayList<String> tourAL = Filereader.read(tourInfofile);
        ArrayList<String> recordAL = Filereader.read(paymntRecfile);
        ArrayList<String> tmp = new ArrayList<String>();
        boolean check = false;
        
        for (String tdata : tourAL)
        {
        	String[] tourdata = tdata.split(",");
			if(tourdata.length > 1 )
	        	{
				if (username.equals(tourdata[0]) && tname.equals(tourdata[1]))
				{
					check = true;
					break;
				}
        	}
        }
        
        if (check)
        {
        	for (String paymntdata : recordAL)
            {
    			String[] pdata = paymntdata.split(", ");
    			if(pdata.length > 1 )
    	        	{
    				if (tname.equals(pdata[1]))
    				{
    					tmp.add(paymntdata);
    				}
            	}

    		}
        }
        
        return tmp;
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
    
    public String recInfo()
    {
    	return tourRecfile;
    }
    
    //pull tour info
    public ArrayList<String> pullTourInfo()
    {
    	Openfile Filereader = new Openfile();
    	ArrayList<String> tourAL = Filereader.read(tourInfofile);
        ArrayList<String> tmp = new ArrayList<String>();
        
        for (String tdata : tourAL)
        {
        	String[] tourdata = tdata.split(",");
			if(tourdata.length > 1 )
	        {
				tmp.add(tdata);
        	}
        }
        
        return tmp;
    }
}
