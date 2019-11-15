import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMain {
	String loggedinUser = "";
	String toWrite = "";
	
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
		    
		    if (ulevel.equals("1") || ulevel.equals("2") )
		    {
		    	writeCred = uname + "," + pwd + "," + ulevel + ",5";
		    }
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
	
	public void delTour() 
	{
		JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 20));
	    
	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("Search Tour Name", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);
	    
	    JPanel delTourMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField tourname = new JTextField();
	    delTourMenu.add(tourname);
	    panel.add(delTourMenu, BorderLayout.CENTER);
	    
	    int check = JOptionPane.showConfirmDialog(null, panel, "Delete Tour", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    
	    String tname = tourname.getText();
	    
	    if (check == JOptionPane.OK_OPTION)
	    {
	    	Verification verify = new Verification();
	    	boolean result = verify.deletetour(tname);
	    	
	    	if (result)
	    	{
		    	JOptionPane.showMessageDialog(null, "Tour successfully deleted!");
		    	toWrite = loggedinUser + ", has deleted the tour, " + tname;
				verify.updateRecords(toWrite);
		    }
		    else 
		    {
		    	JOptionPane.showMessageDialog(null, "Invalid tour name!");
		    }
	    	
	    }
	    
	}
	
	public void viewTourRec() 
	{
	    JPanel oripanel = new JPanel(new BorderLayout(5, 5));
	    oripanel.setPreferredSize(new Dimension(300, 40));
	    
	    JPanel leftlabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    leftlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    leftlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    oripanel.add(leftlabel, BorderLayout.WEST);
	    JPanel rightlabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    rightlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    rightlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    oripanel.add(rightlabel, BorderLayout.EAST);
	    
	    JPanel viewMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    JButton viewAllbtn = new JButton("View all records");
	    viewMenu.add(viewAllbtn);
	    JButton viewSpecificbtn = new JButton("View specific tour record");
	    viewMenu.add(viewSpecificbtn);
	    
	    oripanel.add(viewMenu, BorderLayout.CENTER);
	    Verification verify = new Verification();
	    
	    viewAllbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	JPanel viewallpanel = new JPanel(new BorderLayout(5, 5));
	        	viewallpanel.setPreferredSize(new Dimension(500, 500));
	    	    
	    	    JPanel viewallLabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    	    viewallLabel.add(new JLabel("Record data:", SwingConstants.RIGHT));
	    	    viewallpanel.add(viewallLabel, BorderLayout.WEST);
	    	    
	    	    JPanel mainAllLabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    	    JTextArea DisplayRecord = new JTextArea();
	    	    
	    	    //pull records out
	    	    ArrayList<String> allrecords = verify.PullAllRecords();
	    	    for (String tmpdata : allrecords)
	    	    {
	    	    	DisplayRecord.append(tmpdata+"\n");
	    	    }
	    	    DisplayRecord.setEditable(false);

	    	    mainAllLabel.add(new JScrollPane(DisplayRecord), BorderLayout.PAGE_START);
	    	    viewallpanel.add(mainAllLabel, BorderLayout.CENTER);
	    	    JOptionPane.showOptionDialog(null, viewallpanel, "View All Records", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
	        }
	    });
	    
	    viewSpecificbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	JPanel viewSpanel = new JPanel(new BorderLayout(5, 5));
	        	viewSpanel.setPreferredSize(new Dimension(300, 20));
	    	    
	    	    JPanel viewSLabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    	    viewSLabel.add(new JLabel("Search for tour", SwingConstants.RIGHT));
	    	    viewSpanel.add(viewSLabel, BorderLayout.WEST);
	    	    
	    	    JPanel mainSpanel = new JPanel(new GridLayout(0, 1, 2, 2));
	    	    JTextField tourname = new JTextField();
	    	    mainSpanel.add(tourname);
	    	    viewSpanel.add(mainSpanel, BorderLayout.CENTER);
	    	    int check = JOptionPane.showConfirmDialog(null, viewSpanel, "Search for tour record", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    	    
	    	    if (check == JOptionPane.OK_OPTION)
	    	    {
	    	    	String tname = tourname.getText();
	    	    	if (!tname.equals("") || !tname.isEmpty())
	    	    	{
	    	    		 ArrayList<String> tourAL = verify.RecordCheck(tname);
	    	    		 
	    	    		 if (!tourAL.isEmpty())
	    	    		 {
	    	    			JPanel viewallpanel = new JPanel(new BorderLayout(5, 5));
	    	 	        	viewallpanel.setPreferredSize(new Dimension(500, 500));
	    	 	    	    
	    	 	    	    JPanel viewallLabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    	 	    	    viewallLabel.add(new JLabel("Record data:", SwingConstants.RIGHT));
	    	 	    	    viewallpanel.add(viewallLabel, BorderLayout.WEST);
	    	 	    	    
	    	 	    	    JPanel mainAllLabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    	 	    	    JTextArea DisplayRecord = new JTextArea();
	    	 	    	    
	    	 	    	    //pull records out
	    	 	    	    for (String tmpdata : tourAL)
	    	 	    	    {
	    	 	    	    	DisplayRecord.append(tmpdata+"\n");
	    	 	    	    }
	    	 	    	    DisplayRecord.setEditable(false);
	    	 	    	    
	    	 	    	    mainAllLabel.add(new JScrollPane(DisplayRecord), BorderLayout.PAGE_START);
	    	 	    	    viewallpanel.add(mainAllLabel, BorderLayout.CENTER);
	    	 	    	    JOptionPane.showOptionDialog(null, viewallpanel, "View specific records", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
	    	    		 }
	    	    		 else 
	    	    		 {
	    	    			 JOptionPane.showMessageDialog(null, "Invalid tour name!");
	    	    		 }
	    	    	}
	    	    	else 
	    	    	{
	    	    		JOptionPane.showMessageDialog(null, "Tour name must not be empty!");
	    	    	}
	    	    }
	        
	        }
	    });
	    JOptionPane.showOptionDialog(null, oripanel, "View Tour Records", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
	}
	
	public void showAdminMain (String loginname)
	{
		loggedinUser = loginname;
	    JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 100));

	    JPanel leftlabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    leftlabel.add(new JLabel("          ", SwingConstants.RIGHT));
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
	    JButton viewrecbtn = new JButton("View Tour Modification Record");
	    adminMenu.add(viewrecbtn);
	    
	    createuserbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	createUser();
	        }
	    });
	    
	    deluserbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	DelUser();
	        }
	    });
	    
	    moduserbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	modUser();
	        }
	    });
	    
	    deltourbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	delTour();
	        }
	    });
	    
	    viewrecbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	viewTourRec();
	        	//delTour();
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
