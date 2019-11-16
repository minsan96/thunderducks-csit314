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
	    
	    viewtourbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	viewTours();
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
	
	public void viewTours() {
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
}
