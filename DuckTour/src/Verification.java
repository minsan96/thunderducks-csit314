import java.io.*;
import java.util.*;

public class Verification {

	public String credfile = "src/textdata/Credentials.txt";
	public String userInfofile = "src/textdata/UserInfo.txt";
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
    
    public String getCred()
    {
    	return credfile;
    }
    
    public String getUInfo()
    {
    	return userInfofile;
    }
    
}
