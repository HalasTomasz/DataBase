package Menu;

import javax.swing.*;
import java.awt.*;

public class Logging extends JFrame {
	
	JPanel jPanel = new JPanel(new GridLayout(2, 1));
	JLabel labelLogin = new JLabel("Login: ");
	JLabel labelPassword = new JLabel("Haslo: ");
	JTextField login = new JTextField();
	JPasswordField password = new JPasswordField();
	String userName,pass;
	
	public Logging(){
		jPanel.add(labelLogin);
		jPanel.add(login);
		jPanel.add(labelPassword);
		jPanel.add(password);
		JOptionPane.showMessageDialog(this, jPanel, "Logowanie", JOptionPane.PLAIN_MESSAGE );
		Checklogin();
	}
	
	/**
	 * pracownik = 1
	 * kiero = 2
	 * admin = 3
	 */
	private void Checklogin(){
		userName = login.getText();
		pass = String.valueOf(password.getPassword());
	
		String[] userinfo = SQLConnector.getInstance().logowanie(userName,pass).split("\\|");
	
		int data = Integer.parseInt(userinfo[0]);
		
		if(data==-1){
			do{
			JOptionPane.showMessageDialog(this, jPanel, "Logowanie", JOptionPane.PLAIN_MESSAGE );
			userName = login.getText();
			pass = password.getText();
			userinfo = SQLConnector.getInstance().logowanie(userName,pass).split("\\|");
			data = Integer.parseInt(userinfo[0]);
			}while (data==-1);
		}
		System.out.println("Login: " +userName+" "+pass);
		Guide.getInstance().EmployID=userinfo[1];
		Guide.getInstance().Id=Integer.parseInt(userinfo[0]);
		Guide.getInstance().changeValuves(0,1);
		Guide.getInstance().vision=true;
	}
}
