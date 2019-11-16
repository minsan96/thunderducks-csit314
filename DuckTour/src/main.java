import java.io.*;

public class main {
	public static void main(String[] args) throws IOException
	{
		LoginPage loginDisplay = new LoginPage();
		loginDisplay.showLogin();
		String username = loginDisplay.getUsername();
		//System.out.println("From main "+username);
		String userlevel = loginDisplay.getUserLevel();
		
		if (userlevel.equals("0"))
		{
			AdminMain admMain = new AdminMain();
			admMain.showAdminMain(username);
		}
		
		if (userlevel.equals("1"))
		{
			LocalMain locMain = new LocalMain();
			locMain.showMain(username);
			
		}
		
		if (userlevel.equals("2"))
		{
			TouristMain tourstMain = new TouristMain();
			tourstMain.showTouristMain(username);
		}
		
	}
}
