import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TouristMain {
	String loggedinUser = "";
	ArrayList<String> tourlist = new ArrayList<String>();
	
	public void showTouristMain (String loginname)
	{
		Verification verify = new Verification();
		tourlist = verify.pullTourInfo();
		
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
	    JButton viewtourbtn = new JButton("View Tours");
	    adminMenu.add(viewtourbtn);
	    JButton myprofilebtn = new JButton("View my profile");
	    adminMenu.add(myprofilebtn);
	    JButton ratebtn = new JButton("Rate other Tourists / Guide");
	    adminMenu.add(ratebtn);
	    
	    viewtourbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	viewTours();
	        }
	    });
	    
	    myprofilebtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	myprofile();
	        }
	    });
	    
	    ratebtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	rateOthers();
	        }
	    });
	    
	    panel.add(adminMenu, BorderLayout.CENTER);
	    int check = JOptionPane.showOptionDialog(null, panel, "Admin", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);

	}
	
	public void viewTours() 
	{
		JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(600, 300));
	    
	    JPanel leftlabel = new JPanel(new GridBagLayout());
	    leftlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    panel.add(leftlabel, BorderLayout.WEST);
	    
	    JPanel rightlabel = new JPanel(new GridBagLayout());
	    rightlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    panel.add(rightlabel, BorderLayout.EAST);
	    
	    //code start
	    JPanel adminMenu = new JPanel(new GridBagLayout());
	    JButton viewtourbtn = new JButton("View Tours");
	    adminMenu.add(viewtourbtn);
	    panel.add(adminMenu, BorderLayout.CENTER);
	    JOptionPane.showOptionDialog(null, panel, "View All Records", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
	}
	
	public void myprofile() 
	{
		JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 140));
	    
	    JPanel leftlabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    leftlabel.add(new JLabel("Username", SwingConstants.RIGHT));
	    leftlabel.add(new JLabel("Gender", SwingConstants.RIGHT));
	    leftlabel.add(new JLabel("Date Of Birth", SwingConstants.RIGHT));
	    leftlabel.add(new JLabel("Email Address", SwingConstants.RIGHT));
	    leftlabel.add(new JLabel("Contact Number", SwingConstants.RIGHT));
	    leftlabel.add(new JLabel("Current Rating", SwingConstants.RIGHT));
	    leftlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    panel.add(leftlabel, BorderLayout.WEST);
	    
	    //code start
	    Verification verify = new Verification();
	    String myInfo = verify.pullMyInfo(loggedinUser);
	    String[] mydata = myInfo.split(",");
	    String myCurrRating = verify.checkOtherUname(loggedinUser);
	    String[] myRatingdata = myCurrRating.split(",");
	    
	    JPanel adminMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField myuname = new JTextField(mydata[0]);
	    myuname.setEditable(false);
	    adminMenu.add(myuname);
	    JTextField mygender = new JTextField(mydata[3]);
	    mygender.setEditable(false);
	    adminMenu.add(mygender);
	    JTextField myDOB = new JTextField(mydata[4]);
	    myDOB.setEditable(false);
	    adminMenu.add(myDOB);
	    JTextField myemail = new JTextField(mydata[5]);
	    myemail.setEditable(false);
	    adminMenu.add(myemail);
	    JTextField mycontact = new JTextField(mydata[6]);
	    mycontact.setEditable(false);
	    adminMenu.add(mycontact);
	    JTextField myrating = new JTextField(myRatingdata[3]);
	    myrating.setEditable(false);
	    adminMenu.add(myrating);
	    JButton SignedUpTourbtn = new JButton("List of signed up tours");
	    adminMenu.add(SignedUpTourbtn);
	    panel.add(adminMenu, BorderLayout.CENTER);
	    
	    SignedUpTourbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	ArrayList<String> SignedUpAL = verify.pullSignedUpTour(loggedinUser);
	    		 
	    		 if (!SignedUpAL.isEmpty())
	    		 {
	    			JPanel viewallpanel = new JPanel(new BorderLayout(5, 5));
	 	        	viewallpanel.setPreferredSize(new Dimension(450, 200));
	 	    	    
	 	    	    JPanel viewallLabel = new JPanel(new GridLayout(0, 1, 2, 2));
	 	    	    viewallLabel.add(new JLabel("List of signed up tour:", SwingConstants.RIGHT));
	 	    	    viewallpanel.add(viewallLabel, BorderLayout.WEST);
	 	    	    
	 	    	    JPanel mainAllLabel = new JPanel(new GridLayout(0, 1, 2, 2));
	 	    	    JTextArea DisplayRecord = new JTextArea();
	 	    	    
	 	    	    //pull records out
	 	    	    for (String tmpdata : SignedUpAL)
	 	    	    {
	 	    	    	String[] newdata = tmpdata.split(", ");
	 	    	    	String commonstr = "You have signed up and paid for the tour, " + newdata[2];
	 	    	    	DisplayRecord.append(commonstr+"\n");
	 	    	    }
	 	    	    DisplayRecord.setEditable(false);
	 	    	    
	 	    	    mainAllLabel.add(new JScrollPane(DisplayRecord), BorderLayout.PAGE_START);
	 	    	    viewallpanel.add(mainAllLabel, BorderLayout.CENTER);
	 	    	    JOptionPane.showOptionDialog(null, viewallpanel, "List of Signed Up Tour", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
	    		 }
	    		 else 
	    		 {
	    			 JOptionPane.showMessageDialog(null, "You have not signed up for any tour!");
	    		 }
	        }
	    });
	    
	    JOptionPane.showOptionDialog(null, panel, "My Profile", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
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
}
