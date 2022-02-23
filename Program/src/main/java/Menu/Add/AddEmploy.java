package Menu.Add;

import Menu.Guide;
import Menu.SQLConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmploy extends JPanel implements ActionListener {
	private enum Actions{
		Add, Back
	}
	protected JFrame jFrame = new JFrame();
	JButton Add = new JButton("Dodaj");
	JButton Back = new JButton("Powrot");
	
	
	JLabel Login = new JLabel("Podaj login: ");
	JLabel Password = new JLabel("Haslo: ");
	JLabel Name = new JLabel("Imie: ");
	JLabel Surrname = new JLabel("Nazwisko: ");
	JLabel Privliage = new JLabel("Uprawnienia: ");
	
	
	
	JTextField LoginEmploy = new JTextField();
	JTextField PasswordEmploy = new JTextField();
	JTextField NameEmploy = new JTextField();
	JTextField SurrnameEmploy = new JTextField();
	JTextField PrivliageEmploy = new JTextField();

	
	public AddEmploy(){
		setLayout(new GridLayout(6,2));
		add(Login,BorderLayout.WEST);
		add(LoginEmploy,BorderLayout.EAST);
		add(Password,BorderLayout.WEST);
		add(PasswordEmploy,BorderLayout.EAST);
		add(Name,BorderLayout.WEST);
		add(NameEmploy,BorderLayout.EAST);
		add(Surrname,BorderLayout.WEST);
		add(SurrnameEmploy,BorderLayout.EAST);
		add(Privliage,BorderLayout.WEST);
		add(PrivliageEmploy,BorderLayout.EAST);
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
			try {
				if (!(LoginEmploy.getText().isEmpty() || PasswordEmploy.getText().isEmpty() || NameEmploy.getText().isEmpty() || SurrnameEmploy.getText().isEmpty() || PrivliageEmploy.getText().isEmpty())) {
					if (SQLConnector.getInstance().dodajPracownik(LoginEmploy.getText(), PasswordEmploy.getText(), NameEmploy.getText(), SurrnameEmploy.getText(), Integer.parseInt(PrivliageEmploy.getText())) == 0) {
						JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(jFrame, "Operacja powiodla sie");
						Guide.getInstance().changeValuves(0, 4);
						LoginEmploy.setText("");
						PasswordEmploy.setText("");
						NameEmploy.setText("");
						SurrnameEmploy.setText("");
						PrivliageEmploy.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch (Exception en){
				JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
		}
		}
		else{
			Guide.getInstance().changeValuves(0,4);
			LoginEmploy.setText("");
			PasswordEmploy.setText("");
			NameEmploy.setText("");
			SurrnameEmploy.setText("");
			PrivliageEmploy.setText("");
		}
	}
}
