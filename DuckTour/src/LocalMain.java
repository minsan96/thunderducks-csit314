import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocalMain {

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
			    String writeData = tname + "," + ctry + "," + loc + "," + tdesc + "," + tdate + "," + ttime + "," + tprice;
			    //String writeData = uname + "," + pwd + "," + priorityLevel + "," + gender + "," + birthdate + "," + email + "," + contact;
			    
			    Openfile fileOperator = new Openfile ();
			    fileOperator.writeString(verify.getTInfo(), writeData);
			    JOptionPane.showMessageDialog(null, "Tour successfully created");
		    }
		    else 
		    {
		    	System.out.println("Error in creating tour guide");
		    }
	    }

	}
	
	public void showMain ()
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
	    JPanel localMenu = new JPanel(new GridLayout(0, 1, 2, 2));
	    JButton createtourbtn = new JButton("Create Local Tour");
	    localMenu.add(createtourbtn);
	    JButton deltourbtn = new JButton("Delete Local Tour");
	    localMenu.add(deltourbtn);
	    JButton modtourbtn = new JButton("Modify Local Tour");
	    localMenu.add(modtourbtn);
	    JButton viewpaymentbtn = new JButton("View Payment Records");
	    localMenu.add(viewpaymentbtn);
	    
	    createtourbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	createTour();
	        }
	    });
	    
	    modtourbtn .addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	//DelUser();
	        }
	    });
	    
	    modtourbtn .addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	//modUser();
	        }
	    });
	    
	    panel.add(localMenu, BorderLayout.CENTER);
	    int check = JOptionPane.showOptionDialog(null, panel, "Local Guide", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
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
