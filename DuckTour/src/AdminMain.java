import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMain {
	
	public void createUser()
	{
		JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 60));
	    
	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("Enter Username", SwingConstants.RIGHT));
	    label.add(new JLabel("Enter Password", SwingConstants.RIGHT));
	    label.add(new JLabel("Set User Level", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);
	    
	    JPanel createUserMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField username = new JTextField();
	    createUserMenu.add(username);
	    JTextField password = new JTextField();
	    createUserMenu.add(password);
	    
	    String[] userlevels = {"0" , "1" , "2"};
	    final JComboBox<String> UL = new JComboBox<String>(userlevels);
	    createUserMenu.add(UL);
	    
	    panel.add(createUserMenu, BorderLayout.CENTER);
	    
	    JOptionPane.showConfirmDialog(null, panel, "Create User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    
	    String uname = username.getText();
	    String pwd = password.getText();
	    String ulevel = UL.getSelectedItem().toString();
	    //System.out.println("from create user: " + uname + "," + pwd + "," +ulevel);
	    
	    Verification verify = new Verification();
	    if (verify.checkusername(uname) && !uname.isEmpty())
	    {
	    	JOptionPane.showMessageDialog(null, "Username had been used!");
	    }
	    else if(pwd.isEmpty())
	    {
	    	JOptionPane.showMessageDialog(null, "Password field cannot be blank!");
	    }
	    else if (verify.checkusername(uname) == false && !uname.isEmpty() && !pwd.isEmpty())
	    {
		    String writeCred = uname + "," + pwd + "," + ulevel;
		    //String writeData = uname + "," + pwd + "," + priorityLevel + "," + gender + "," + birthdate + "," + email + "," + contact;
		    
		    Openfile fileOperator = new Openfile ();
		    fileOperator.writeString(verify.getCred(), writeCred);
		    //fileOperator.writeString(verify.getUInfo(), writeData);
		    JOptionPane.showMessageDialog(null, "User successfully created");
	    }
	    else 
	    {
	    	System.out.println("Error in create user");
	    }

	}
	
	public void DelUser ()
	{
		JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 20));
	    
	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("Search for username to delete", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);
	    
	    JPanel delUserMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField username = new JTextField();
	    delUserMenu.add(username);
	    panel.add(delUserMenu, BorderLayout.CENTER);
	    
	    int check = JOptionPane.showConfirmDialog(null, panel, "Delete User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    String uname = username.getText();
	    if (check == JOptionPane.OK_OPTION)
	    {
	    	Verification verify = new Verification();
		    if (verify.checkusername(uname) && !uname.isEmpty())
		    {
		    	verify.deleteUser(uname);
		    	JOptionPane.showMessageDialog(null, "Deleted user successfully");
		    }
		    else 
		    {
		    	JOptionPane.showMessageDialog(null, "User not found in database");
		    }
	    }
	}
	
	public void modUser() 
	{
		JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 40));
	    
	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("Search username to modify", SwingConstants.RIGHT));
	    label.add(new JLabel("Set new user level", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);
	    
	    JPanel modUserMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField username = new JTextField();
	    modUserMenu.add(username);
	    String[] userlevels = {"0" , "1" , "2"};
	    final JComboBox<String> UL = new JComboBox<String>(userlevels);
	    modUserMenu.add(UL);
	    panel.add(modUserMenu, BorderLayout.CENTER);
	    
	    int check = JOptionPane.showConfirmDialog(null, panel, "Modify User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    
	    String uname = username.getText();
	    String ulevel = UL.getSelectedItem().toString();
	    //System.out.println("from create user: " + uname + "," + pwd + "," +ulevel);
	    
	    if (check == JOptionPane.OK_OPTION)
	    {
	    	Verification verify = new Verification();
		    if (verify.checkusername(uname) && !uname.isEmpty())
		    {
		    	verify.modUser(uname, ulevel);
		    	JOptionPane.showMessageDialog(null, "User level successfully changed");
		    }
		    else 
		    {
		    	JOptionPane.showMessageDialog(null, "User not found in database");
		    }
	    }
	}
	
	public void showAdminMain ()
	{
	    JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 70));

	    JPanel leftlabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    leftlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    leftlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    leftlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    leftlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    panel.add(leftlabel, BorderLayout.WEST);
	    
	    JPanel rightlabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    rightlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    rightlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    rightlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    rightlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    panel.add(rightlabel, BorderLayout.EAST);
	    
	    //code start
	    JPanel adminMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    JButton createuserbtn = new JButton("Create User");
	    adminMenu.add(createuserbtn);
	    JButton deluserbtn = new JButton("Delete User");
	    adminMenu.add(deluserbtn);
	    JButton moduserbtn = new JButton("Modify User");
	    adminMenu.add(moduserbtn);
	    JButton deltourbtn = new JButton("Delete Tour");
	    adminMenu.add(deltourbtn);
	    
	    createuserbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	createUser();
	        }
	    });
	    
	    deluserbtn .addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	DelUser();
	        }
	    });
	    
	    moduserbtn .addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	modUser();
	        }
	    });
	    
	    panel.add(adminMenu, BorderLayout.CENTER);
	    int check = JOptionPane.showOptionDialog(null, panel, "Admin", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
	    //JOptionPane.showConfirmDialog(null, panel, "Admin Page", JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    //JOptionPane.showMessageDialog(null, panel, "Admin Page", JOptionPane.PLAIN_MESSAGE);
	    /*
	    if (check == JOptionPane.CLOSED_OPTION)
	    {
	    	LoginPage LP = new LoginPage();
	    	LP.showLogin();
	    }
	    */
	}
}
