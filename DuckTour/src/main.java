import java.io.*;

public class main {
	public static void main(String[] args) throws IOException
	{
		LoginPage loginDisplay = new LoginPage();
		loginDisplay.showLogin();
		String username = loginDisplay.getUsername();
		System.out.println("From main "+username);
		
	}
}
