import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage{
	public String userID = "";
	
	public void showLogin (){
		
	    JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 50));

	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("Username", SwingConstants.RIGHT));
	    label.add(new JLabel("Password", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);

	    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField username = new JTextField();
	    controls.add(username);
	    JPasswordField password = new JPasswordField();
	    controls.add(password);
	    panel.add(controls, BorderLayout.CENTER);
	    
	    JPanel regpanel = new JPanel (new GridLayout(2,1,2,2));
	    JButton registerbtn = new JButton("Register here");
	    regpanel.add(registerbtn);
	    
	    registerbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	            //code to excute
	        	Registerpage regDisplay = new Registerpage();
	    		regDisplay.showPage();
	            //System.out.println("Register");
	        }
	    });
	    
	    panel.add(regpanel, BorderLayout.LINE_END);
	    
	    JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    
	    String uname = username.getText();
	    String pwd = new String (password.getPassword());
	    Verification verifyuser = new Verification();
	    
	    if(verifyuser.verifylogin(uname, pwd)) 
	    {
	    	System.out.println("true");
	    	userID = uname;
	    	String ulevel = verifyuser.getUserLevel();
	    	//System.out.println(ulevel);
	    }
	    else 
	    {
	    	System.out.println("false");
	    }
	    
	}
	
	public String getUsername() {
		return userID;
	}
	
}
