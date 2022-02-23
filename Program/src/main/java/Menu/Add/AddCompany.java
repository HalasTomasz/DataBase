package Menu.Add;

import Menu.Guide;
import Menu.SQLConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCompany extends JPanel implements ActionListener {
	
	private enum Actions{
		Add, Back
	}
	protected JFrame jFrame = new JFrame();
	JButton Add = new JButton("Dodaj");
	JButton Back = new JButton("Powrot");
	
	JLabel Worker = new JLabel("PracownikID: ");
	JLabel Name = new JLabel("Nazwa firmy: ");
	JLabel Address = new JLabel("Adress: ");
	JLabel NIP = new JLabel("NIP :");
	JLabel Phone = new JLabel("Telefon:");
	
	JTextField WorkerFirm = new JTextField();
	JTextField NameFirm = new JTextField();
	JTextField AddressFirm = new JTextField();
	JTextField NIPFirm = new JTextField();
	JTextField PhoneFirm = new JTextField();
	
	public AddCompany(){
		setLayout(new GridLayout(6,2));
		
		add(Worker,BorderLayout.WEST);
		add(WorkerFirm,BorderLayout.EAST);
		add(Name,BorderLayout.WEST);
		add(NameFirm,BorderLayout.EAST);
		add(Address,BorderLayout.WEST);
		add(AddressFirm,BorderLayout.EAST);
		add(NIP,BorderLayout.WEST);
		add(NIPFirm,BorderLayout.EAST);
		add(Phone,BorderLayout.WEST);
		add(PhoneFirm,BorderLayout.EAST);
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
				if(!(NameFirm.getText().isEmpty() || AddressFirm.getText().isEmpty() ||NIPFirm.getText().isEmpty() || PhoneFirm.getText().isEmpty() || WorkerFirm.getText().isEmpty())) {
					if( SQLConnector.getInstance().dodajFirma(Integer.parseInt(WorkerFirm.getText()),NameFirm.getText(), AddressFirm.getText(),NIPFirm.getText(), PhoneFirm.getText()) == 0){
						JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
					}
				else{
					JOptionPane.showMessageDialog(jFrame, "Operacja powiodla sie");
					Guide.getInstance().changeValuves(0,4);
					WorkerFirm.setText("");
					NameFirm.setText("");
					AddressFirm.setText("");
					NIPFirm.setText("");
					PhoneFirm.setText("");
				}
			}
			else{
					JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
			}
		}catch (Exception en){
				JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
		}
		}
		else{
			Guide.getInstance().changeValuves(0,4);
			NameFirm.setText("");
			AddressFirm.setText("");
			NIPFirm.setText("");
			PhoneFirm.setText("");
		}
	}
}
