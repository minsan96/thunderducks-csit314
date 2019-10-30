import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


public class Registerpage {
	
	public void showPage()
	{
		JPanel panel = new JPanel(new BorderLayout(5, 5));
	    panel.setPreferredSize(new Dimension(300, 200));

	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("Set Username", SwingConstants.RIGHT));
	    label.add(new JLabel("Set Password", SwingConstants.RIGHT));
	    label.add(new JLabel("Date Of Birth", SwingConstants.RIGHT));
	    label.add(new JLabel("Email Address", SwingConstants.RIGHT));
	    label.add(new JLabel("Contact Number", SwingConstants.RIGHT));
	    //label.add(new JLabel("Gender", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);

	    JPanel controls = new JPanel(new GridLayout(0, 1, 3, 2));
	    JTextField username = new JTextField();
	    controls.add(username);
	    JPasswordField password = new JPasswordField();
	    controls.add(password);
	    
	    ButtonGroup bg1 = new ButtonGroup( );
		JRadioButton malebtn = new JRadioButton("M");
	    JRadioButton femalebtn = new JRadioButton("F");
		bg1.add(malebtn);
		bg1.add(femalebtn);
	    controls.add(malebtn);
	    controls.add(femalebtn);
	    
	    JTextField DOB = new JTextField("DD-MM-YYYY e.g 20-04-1965");
	    
	    DOB.addFocusListener(new FocusListener() {
	        public void focusGained(FocusEvent e) {
	            DOB.setText("");
	        }
	        public void focusLost(FocusEvent e) {
	        }
	    });
	    
	    controls.add(DOB);
	    
	    JTextField emailaddr = new JTextField();
	    controls.add(emailaddr);	    
	    JTextField contactnum = new JTextField();
	    controls.add(contactnum);
	    panel.add(controls, BorderLayout.CENTER);
	    
	    JPanel genderbtnPanel = new JPanel();
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = GridBagConstraints.RELATIVE;
	    gbc.anchor = GridBagConstraints.WEST;

	    genderbtnPanel.add(new JLabel("Gender", SwingConstants.RIGHT));
	    genderbtnPanel.add(malebtn, gbc);
	    genderbtnPanel.add(femalebtn, gbc);
	    panel.add(genderbtnPanel, BorderLayout.PAGE_END );
	    
	    JOptionPane.showConfirmDialog(null, panel, "Register", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    
	    String uname = username.getText();
	    //System.out.println(uname);
	    String pwd = new String (password.getPassword());
	    String gender = "";
	    
	    if (malebtn.isSelected())
	    {
	    	gender = malebtn.getText();
	    	//System.out.println(gender);
	    }
	    else 
	    {
	    	gender = femalebtn.getText();
	    	//System.out.println(gender);
	    }
	    
	    String birthdate = DOB.getText();
	    String email = emailaddr.getText();
	    String contact = contactnum.getText();
	    String priorityLevel = "2";
	    
	    Verification verify = new Verification();
	    if (verify.checkusername(uname) && !uname.isEmpty())
	    {
	    	JOptionPane.showMessageDialog(null, "Username had been used!");
	    }
	    else if (verify.checkusername(uname) == false && !uname.isEmpty() && !pwd.isEmpty()){
	    	if(birthdate.equals("DD-MM-YYYY e.g 20-04-1965"))
	    	{
	    		birthdate = "";
	    	}
		    String writeCred = uname + "," + pwd + "," + priorityLevel;
		    String writeData = uname + "," + pwd + "," + priorityLevel + "," + gender + "," + birthdate + "," + email + "," + contact;
		    
		    Openfile fileOperator = new Openfile ();
		    fileOperator.writeString(verify.getCred(), writeCred);
		    fileOperator.writeString(verify.getUInfo(), writeData);
	    }
	    else {
	    	JOptionPane.showMessageDialog(null, "Username/Password must not be empty!");
	    }
	}
}
