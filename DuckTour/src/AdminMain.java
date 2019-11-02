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
	    }
	    else 
	    {
	    	System.out.println("Error in create user");
	    }
	    
	}
	
	public void showAdminMain (){
		
	    JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 50));

	    JPanel leftlabel = new JPanel(new GridLayout(0, 1, 2, 2));
	    leftlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    leftlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    leftlabel.add(new JLabel("          ", SwingConstants.RIGHT));
	    panel.add(leftlabel, BorderLayout.WEST);
	    
	    JPanel rightlabel = new JPanel(new GridLayout(0, 1, 2, 2));
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
	    
	    createuserbtn.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	createUser();
	        }
	    });
	    
	    panel.add(adminMenu, BorderLayout.CENTER);
	    
	    JOptionPane.showConfirmDialog(null, panel, "Admin Page", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    
	    //Verification verifyuser = new Verification();
	}
}
