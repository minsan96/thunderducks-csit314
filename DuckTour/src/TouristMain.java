import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.*;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;

public class TouristMain {
	String loggedinUser = "";
	List<String> tourlist = new ArrayList<String>();
	List<String> countries = new ArrayList<String>();
	
	public void showTouristMain (String loginname)
	{
		Verification verify = new Verification();
		tourlist = verify.pullTourInfo();
		
		for (String tdata : tourlist)
        {
			String[] tourdata = tdata.split(",");
			if(tourdata.length > 1 )
	        {
				String tcntry = tourdata[2];
				if(countries.size() == 0) {
					countries.add(tcntry);
				}
				else if(!countries.contains(tcntry)) {
					countries.add(tcntry);
				}
	        }
        }
		
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
	    JButton signupbtn = new JButton("Sign Up Tour");
	    adminMenu.add(signupbtn);
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
	    signupbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	signupTour();
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
	    JOptionPane.showOptionDialog(null, panel, "Tourists", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);

	}
	
	public void viewTours() 
	{
		Verification verify = new Verification();
		JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(800, 300));
	    JComboBox<String> cb = new JComboBox<String>();
	    //cb.setPreferredSize(new Dimension(0, 25));
	    cb.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");
	    cb.setMaximumSize( cb.getPreferredSize() );
	    cb.setModel(new DefaultComboBoxModel(countries.toArray()));
        cb.setVisible(true);
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));		
		JPanel list = new JPanel();
		list.setLayout(new BoxLayout(list, BoxLayout.PAGE_AXIS));
	    cb.addActionListener (new ActionListener () {
	        public void actionPerformed(ActionEvent e) {
	            String selectedcntry = String.valueOf(cb.getSelectedItem());
	            tourlist = verify.pullTourInfo(); 	
	            tourlist.removeIf(b -> b.contains(selectedcntry) == false);
	        	/*for (Iterator<String> it=tourlist.iterator(); it.hasNext();) {
	        		tourlist = verify.pullTourInfo();
	        	    if (!it.next().contains(selectedcntry)) {
	        	        it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
	        	    }
	        	}*/
	        	list.removeAll();
	            //list.add(cb, BorderLayout.CENTER);
	        	loadList(list);
	        	list.revalidate();
	        	list.repaint();
	        }
	    });
	    main.add(cb, BorderLayout.NORTH);
        loadList(list);

	    main.add(list,BorderLayout.CENTER);
	    panel.add(new JScrollPane(main));
        //panel.add(new JScrollPane(list));        
	    JOptionPane.showOptionDialog(null, panel, "View Tours", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
	}
	
	public void loadList(JPanel list) {
		for (String tdata : tourlist)
        {
        	String[] tourdata = tdata.split(",");
			if(tourdata.length > 1 )
	        {
				JPanel label = new JPanel();
				label.setLayout(new BoxLayout(label, BoxLayout.PAGE_AXIS));
				String tourname = tourdata[1];
				String country = tourdata[2];
				String location = tourdata[3];
				String tourdescription = tourdata[4];
				String date = tourdata[5];
				String time = tourdata[6];
				String price = tourdata[7];
				
			    label.add(new JLabel("Tour Name: " + tourname, SwingConstants.LEFT));
			    label.add(new JLabel("Country: " + country + "    Location: " + location, SwingConstants.LEFT));
			    label.add(new JLabel("Tour Description: " + tourdescription, SwingConstants.LEFT));
			    label.add(new JLabel("Date: " + date + "    Time: " + time, SwingConstants.LEFT));
			    label.add(new JLabel("<html><div>Price : " + price + "</div><br/></html>", SwingConstants.LEFT));
			    label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
			    list.add(label, BorderLayout.CENTER);
        	}
        }
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
	
	public void signupTour()
	{
		JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 150));
	    
	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("Tour Name", SwingConstants.RIGHT));
	    label.add(new JLabel("Credit Card No.", SwingConstants.RIGHT));
	    label.add(new JLabel("Expiry (MM/YY)", SwingConstants.RIGHT));
	    label.add(new JLabel("CVV", SwingConstants.RIGHT));
	    label.add(new JLabel("Name", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);
	    
	    JPanel createTourMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField tourname = new JTextField();
	    createTourMenu.add(tourname);
	    JTextField creditcard = new JTextField();
	    createTourMenu.add(creditcard);
	    JTextField expiry = new JTextField();
	    createTourMenu.add(expiry);
	    JTextField tcvv = new JTextField();
	    createTourMenu.add(tcvv);
	    JTextField txtname = new JTextField();
	    createTourMenu.add(txtname);
	    panel.add(createTourMenu, BorderLayout.CENTER);
	    
	    int check = JOptionPane.showConfirmDialog(null, panel, "Sign Up Tour", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    
	    if (check == JOptionPane.OK_OPTION)
	    {
	    	String tname = tourname.getText();
		    String ccard = creditcard.getText();
		    String exp = expiry.getText();
		    String cvv = tcvv.getText();
		    String name = txtname.getText();
			Verification verify = new Verification();
		    String tinfo = verify.pullTourInfo(tname);
		    String price = tinfo.split(",")[7];
		    if (verify.checktourname(tname) && !tname.isEmpty())
		    {
				String writeData = loggedinUser + ", has paid " + price + " for the tour, " + tname;
				
				Openfile fileOperator = new Openfile ();
				fileOperator.writeString(verify.paymntRecInfo(), writeData);
				JOptionPane.showMessageDialog(null, "Tour successfully paid and signed up!");
		    }
		    else if(tname.isEmpty() || ccard.isEmpty() || exp.isEmpty() || cvv.isEmpty() || name.isEmpty())
		    {
		    	JOptionPane.showMessageDialog(null, "All the fields must not be blank!");
		    }
		    else if (verify.checktourname(tname) == false)
		    {
		    	JOptionPane.showMessageDialog(null, "Tour name is not found!");
		    }
		    else 
		    {
		    	System.out.println("Error in creating tour guide");
		    }
	    }

	}
}
