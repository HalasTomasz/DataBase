package Menu.Add;

import Menu.Guide;
import Menu.SQLConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClient extends JPanel implements ActionListener {
	
	private enum Actions{
		Add, Back
	}
	protected JFrame jFrame = new JFrame();
	JButton Add = new JButton("Dodaj");
	JButton Back = new JButton("Powrot");
	
	JLabel Name = new JLabel("Podaj nazwe firmy:");
	JLabel NIP = new JLabel("Podaj NIP:");
	JLabel Address = new JLabel("Podaj adres klienta:");
	JLabel Email = new JLabel("Podaj adres email:");
	JLabel Phone = new JLabel("Podaj numer telefonu:");
	
	JTextField NameClient = new JTextField();
	JTextField NIPClient = new JTextField();
	JTextField AddressClient = new JTextField();
	JTextField EmailClient = new JTextField();
	JTextField PhoneClient = new JTextField();
	
	public AddClient(){
		setLayout(new GridLayout(6,2));
		
		add(Name,BorderLayout.WEST);
		add(NameClient,BorderLayout.EAST);
		add(NIP,BorderLayout.WEST);
		add(NIPClient,BorderLayout.EAST);
		add(Address,BorderLayout.WEST);
		add(AddressClient,BorderLayout.EAST);
		add(Email,BorderLayout.WEST);
		add(EmailClient,BorderLayout.EAST);
		add(Phone,BorderLayout.WEST);
		add(PhoneClient,BorderLayout.EAST);
		add(Back,BorderLayout.EAST);
		add(Add,BorderLayout.WEST);
		
		Add.setActionCommand(Actions.Add.name());
		Add.addActionListener(this);
		Back.setActionCommand(Actions.Back.name());
		Back.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(Actions.Add.name())){
			try{
			if(!(NameClient.getText().isEmpty() || NIPClient.getText().isEmpty() || AddressClient.getText().isEmpty() || EmailClient.getText().isEmpty() || PhoneClient.getText().isEmpty() )) {
				
				if (SQLConnector.getInstance().dodajKlient(NameClient.getText(), NIPClient.getText(), AddressClient.getText(), EmailClient.getText(), PhoneClient.getText()) == 0) {
					JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(jFrame, "Operacja powiodla sie");
					Guide.getInstance().changeValuves(0, 4);
					NameClient.setText("");
					NIPClient.setText("");
					AddressClient.setText("");
					EmailClient.setText("");
					PhoneClient.setText("");
				}
				}
				else {
				JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
					}
			}catch (Exception en){
				JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
				}
		}
		else{
			Guide.getInstance().changeValuves(0,4);
			NameClient.setText("");
			NIPClient.setText("");
			AddressClient.setText("");
			EmailClient.setText("");
			PhoneClient.setText("");
		}
	}
}
