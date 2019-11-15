import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocalMain {

	String loggedinUser = "";
	String toWrite = "";
	
	public void createTour()
	{
		JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 150));
	    
	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("Enter Tour Name", SwingConstants.RIGHT));
	    label.add(new JLabel("Country", SwingConstants.RIGHT));
	    label.add(new JLabel("Location", SwingConstants.RIGHT));
	    label.add(new JLabel("Enter Tour Description", SwingConstants.RIGHT));
	    label.add(new JLabel("Date (DD-MM-YYYY)", SwingConstants.RIGHT));
	    label.add(new JLabel("Time (HHmmH, e.g 1930H)", SwingConstants.RIGHT));
	    label.add(new JLabel("Price $", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);
	    
	    JPanel createTourMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField tourname = new JTextField();
	    createTourMenu.add(tourname);
	    JTextField country = new JTextField();
	    createTourMenu.add(country);
	    JTextField location = new JTextField();
	    createTourMenu.add(location);
	    JTextField tourdesc = new JTextField();
	    createTourMenu.add(tourdesc);
	    JTextField date = new JTextField();
	    createTourMenu.add(date);
	    JTextField time = new JTextField();
	    createTourMenu.add(time);
	    JTextField price = new JTextField();
	    createTourMenu.add(price);
	    panel.add(createTourMenu, BorderLayout.CENTER);
	    
	    int check = JOptionPane.showConfirmDialog(null, panel, "Create Tour", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    
	    String tname = tourname.getText();
	    String ctry = country.getText();
	    String loc = location.getText();
	    String tdesc = tourdesc.getText();
	    String tdate = date.getText();
	    String ttime = time.getText();
	    String tprice = price.getText();
	    
	    Verification verify = new Verification();
	    if (check == JOptionPane.OK_OPTION)
	    {
		    if (verify.checktourname(tname) && !tname.isEmpty())
		    {
		    	JOptionPane.showMessageDialog(null, "Tour name had been used!");
		    }
		    else if(tname.isEmpty() || ctry.isEmpty() || loc.isEmpty() || tdesc.isEmpty() || tdate.isEmpty() || ttime.isEmpty() || tprice.isEmpty())
		    {
		    	JOptionPane.showMessageDialog(null, "All the fields must not be blank!");
		    }
		    else if (verify.checktourname(tname) == false)
		    {
			    String writeData = loggedinUser + "," + tname + "," + ctry + "," + loc + "," + tdesc + "," + tdate + "," + ttime + "," + tprice;
			    //String writeData = loggedinUser + "," + uname + "," + pwd + "," + priorityLevel + "," + gender + "," + birthdate + "," + email + "," + contact;
			    
			    Openfile fileOperator = new Openfile ();
			    fileOperator.writeString(verify.getTInfo(), writeData);
			    JOptionPane.showMessageDialog(null, "Tour successfully created");
			    toWrite = loggedinUser + ", has created the tour, " + tname;
			    verify.updateRecords(toWrite);
		    }
		    else 
		    {
		    	System.out.println("Error in creating tour guide");
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
	    	boolean result = verify.deletetour(loggedinUser, tname);
	    	
	    	if (result)
	    	{
		    	JOptionPane.showMessageDialog(null, "Tour successfully deleted!");
		    	toWrite = loggedinUser + ", has deleted the tour, " + tname;
				verify.updateRecords(toWrite);
		    }
		    else 
		    {
		    	JOptionPane.showMessageDialog(null, "You do not have the right to delete the tour! / Invalid tour name!");
		    }
	    	
	    }
	    
	}
	
	public void modTour() 
	{
		JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 20));
	    
	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("Search Tour Name", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);
	    
	    JPanel modTourMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField tourname = new JTextField();
	    modTourMenu.add(tourname);
	    panel.add(modTourMenu, BorderLayout.CENTER);
	    
	    int check = JOptionPane.showConfirmDialog(null, panel, "Search for tour to modify", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    
	    String tname = tourname.getText();
	    
	    if (check == JOptionPane.OK_OPTION)
	    {
	    	Verification verify = new Verification();
	    	ArrayList<String> result = verify.checkTourForMod(loggedinUser, tname);
	    	
	    	if (!result.isEmpty())
	    	{
	    		String currentval = result.get(0);
	    		String[] splitval = currentval.split(",");
	    		
	    		JPanel newpanel = new JPanel(new BorderLayout(5, 5));
	    	    panel.setPreferredSize(new Dimension(300, 130));
	    	    
	    	    JPanel newlabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    	    newlabel.add(new JLabel("Country", SwingConstants.RIGHT));
	    	    newlabel.add(new JLabel("Location", SwingConstants.RIGHT));
	    	    newlabel.add(new JLabel("Enter Tour Description", SwingConstants.RIGHT));
	    	    newlabel.add(new JLabel("Date (DD-MM-YYYY)", SwingConstants.RIGHT));
	    	    newlabel.add(new JLabel("Time (HHmmH, e.g 1930H)", SwingConstants.RIGHT));
	    	    newlabel.add(new JLabel("Price $", SwingConstants.RIGHT));
	    	    newpanel.add(newlabel, BorderLayout.WEST);
	    	    
	    	    JPanel updTourMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    	    JTextField country = new JTextField(splitval[2]);
	    	    updTourMenu.add(country);
	    	    JTextField location = new JTextField(splitval[3]);
	    	    updTourMenu.add(location);
	    	    JTextField tourdesc = new JTextField(splitval[4]);
	    	    updTourMenu.add(tourdesc);
	    	    JTextField date = new JTextField(splitval[5]);
	    	    updTourMenu.add(date);
	    	    JTextField time = new JTextField(splitval[6]);
	    	    updTourMenu.add(time);
	    	    JTextField price = new JTextField(splitval[7]);
	    	    updTourMenu.add(price);
	    	    newpanel.add(updTourMenu, BorderLayout.CENTER);
	    	    
	    	    int check1 = JOptionPane.showConfirmDialog(null, newpanel, "Modify Tour", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    	    if (check1 == JOptionPane.OK_OPTION)
	    	    {
		    	    String ctry = country.getText();
		    	    String loc = location.getText();
		    	    String tdesc = tourdesc.getText();
		    	    String tdate = date.getText();
		    	    String ttime = time.getText();
		    	    String tprice = price.getText();
		    	    
		    	    String writeData = loggedinUser + "," + tname + "," + ctry + "," + loc + "," + tdesc + "," + tdate + "," + ttime + "," + tprice;
		    	    
		    		verify.updateTour(tname, writeData);
		    	    JOptionPane.showMessageDialog(null, "Tour successfully updated!");
		    	    toWrite = loggedinUser + ", has updated the tour, " + tname;
					verify.updateRecords(toWrite);
	    	    }
		    }
		    else 
		    {
		    	JOptionPane.showMessageDialog(null, "You do not have the right to update the tour! / Invalid tour name!");
		    }
	    	
	    }
	}
	
	public void viewPayment() 
	{
    	JPanel searchPaymentpanel = new JPanel(new BorderLayout(5, 5));
    	searchPaymentpanel.setPreferredSize(new Dimension(300, 20));
	    
	    JPanel searchPaymntLabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    searchPaymntLabel.add(new JLabel("Search for tour", SwingConstants.RIGHT));
	    searchPaymentpanel.add(searchPaymntLabel, BorderLayout.WEST);
	    
	    JPanel searchPaymnttxtfield = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField tourname = new JTextField();
	    searchPaymnttxtfield.add(tourname);
	    searchPaymentpanel.add(searchPaymnttxtfield, BorderLayout.CENTER);
	    int check = JOptionPane.showConfirmDialog(null, searchPaymentpanel, "Search for tour payment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    
	    Verification verify = new Verification();
	    
	    if (check == JOptionPane.OK_OPTION)
	    {
	    	String tname = tourname.getText();
	    	if (!tname.equals("") || !tname.isEmpty())
	    	{
	    		 ArrayList<String> tourAL = verify.pullPaymentRecords(loggedinUser, tname);
	    		 
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
	    			 JOptionPane.showMessageDialog(null, "You do not have the right! / Invalid tour name!");
	    		 }
	    	}
	    	else 
	    	{
	    		JOptionPane.showMessageDialog(null, "Tour name must not be empty!");
	    	}
	    }
	}
	
	public void rateOthers()
	{
		JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 20));
	    
	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("Search username", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);
	    
	    JPanel rateOtherMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField otherusername = new JTextField();
	    rateOtherMenu.add(otherusername);
	    panel.add(rateOtherMenu, BorderLayout.CENTER);
	    
	    int check = JOptionPane.showConfirmDialog(null, panel, "Rate other users", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    
	    String OtherUname = otherusername.getText();
	    
	    if (check == JOptionPane.OK_OPTION)
	    {
	    	if (OtherUname.equals(loggedinUser))
	    	{
	    		JOptionPane.showMessageDialog(null, "You cannot give ratings to yourself!");
	    	}
	    	
	    	else 
	    	{
	    		Verification verify = new Verification();
	    		String otheruserinfo = verify.checkOtherUname(OtherUname);
	    		
	    		if (!otheruserinfo.equals("") || !otheruserinfo.isEmpty())
	    		{
	    			String[] userdata = otheruserinfo.split(",");
	    			
	    			JPanel newpanel = new JPanel(new BorderLayout(5, 5));
	    			newpanel.setPreferredSize(new Dimension(300, 90));
	    		    
	    		    JPanel newlabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    		    newlabel.add(new JLabel("Username", SwingConstants.RIGHT));
	    		    newlabel.add(new JLabel("Current Rating", SwingConstants.RIGHT));
	    		    newlabel.add(new JLabel("Your Rating", SwingConstants.RIGHT));
	    		    //newlabel.add(new JLabel("1 is the worst rating and 5 is the best rating", SwingConstants.RIGHT));
	    		    newpanel.add(newlabel, BorderLayout.WEST);
	    		    
	    		    JPanel OtherUserInfoMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    		    JTextField OTHusername = new JTextField(userdata[0]);
	    		    OTHusername.setEditable(false);
	    		    OtherUserInfoMenu.add(OTHusername);
	    		    JTextField OTHuserrating = new JTextField(userdata[3]);
	    		    OTHuserrating.setEditable(false);
	    		    OtherUserInfoMenu.add(OTHuserrating);
	    		    
	    		    String[] ratinglevel = {"1" , "2", "3", "4", "5"};
	    		    final JComboBox<String> RL = new JComboBox<String>(ratinglevel);
	    		    OtherUserInfoMenu.add(RL);
	    		    
	    		    newpanel.add(OtherUserInfoMenu, BorderLayout.CENTER);
	    		    
	    		    JPanel endlabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    		    endlabel.add(new JLabel("1 is the worst rating and 5 is the best rating", SwingConstants.CENTER));
	    		    newpanel.add(endlabel, BorderLayout.PAGE_END);
	    		    
	    		    int check1 = JOptionPane.showConfirmDialog(null, newpanel, "Rate other users", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    		    
	    		    String nrating = RL.getSelectedItem().toString();
	    		    
	    		    if (check1 == JOptionPane.OK_OPTION)
	    		    {
	    		    	if (!nrating.isEmpty() || !nrating.equals(""))
	    		    	{
		    		    	double currRating = Double.valueOf(userdata[3]);
		    		    	//currRating = Math.round(currRating * 10 )/ 10.0;
		    		    	double nRating = Float.valueOf(nrating);
		    		    	double avgRating = (currRating + nRating) / 2;
		    		    	avgRating = Math.round(avgRating * 10 )/ 10.0;
		    		    	
		    		    	String writedata = userdata[0] + "," + userdata[1] + "," + userdata[2] + "," + String.valueOf(avgRating);
		    		    	
		    		    	verify.rateUser(otheruserinfo, writedata);
		    		    	
		    		    	JOptionPane.showMessageDialog(null, "Successfully rated the user! The user's rating is now: " + String.valueOf(avgRating));
	    		    	}
	    		    	else
	    		    	{
	    		    		JOptionPane.showMessageDialog(null, "You must provide a rating!");
	    		    	}
	    		    }
	    			
	    		}
	    		else 
	    		{
	    			JOptionPane.showMessageDialog(null, "Invalid username!");
	    		}
	    	}
	    }
	}
	
	public void showMain (String loginusername)
	{
		loggedinUser = loginusername;
		
		JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 90));

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
	    JPanel localMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    JButton createtourbtn = new JButton("Create Local Tour");
	    localMenu.add(createtourbtn);
	    JButton deltourbtn = new JButton("Delete Local Tour");
	    localMenu.add(deltourbtn);
	    JButton modtourbtn = new JButton("Modify Local Tour");
	    localMenu.add(modtourbtn);
	    JButton viewpaymentbtn = new JButton("View Payment Records");
	    localMenu.add(viewpaymentbtn);
	    JButton rateuserstbtn = new JButton("Rate Others");
	    localMenu.add(rateuserstbtn);
	    
	    createtourbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	createTour();
	        }
	    });
	    
	    deltourbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	delTour();
	        }
	    });
	    
	    modtourbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	modTour();
	        }
	    });
	    
	    viewpaymentbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	viewPayment();
	        }
	    });
	    
	    rateuserstbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	rateOthers();
	        }
	    });
	    
	    panel.add(localMenu, BorderLayout.CENTER);
	    int check = JOptionPane.showOptionDialog(null, panel, "Local Guide", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);

	    /*
	    if (check == JOptionPane.CLOSED_OPTION)
	    {
	    	LoginPage LP = new LoginPage();
	    	LP.showLogin();
	    }
	    */
	}
	
}
