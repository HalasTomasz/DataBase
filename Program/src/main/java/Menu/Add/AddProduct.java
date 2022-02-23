package Menu.Add;

import Menu.Guide;
import Menu.SQLConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProduct extends JPanel implements ActionListener {
	
	private enum Actions{
		Add, Back
	}
	protected JFrame jFrame = new JFrame();
	JButton Add = new JButton("Dodaj");
	JButton Back = new JButton("Powrot");
	
	JLabel Name = new JLabel("Podaj nazwe produktu:");
	JLabel Price = new JLabel("Podaj cene produktu:");
	JLabel Unit = new JLabel("Podaj jednostke produktu:");
	JLabel Tax = new JLabel("Podaj podatek:");
	
	JTextField  NameProduct  = new JTextField();
	JTextField PriceProduct  = new JTextField();
	JTextField UnitProduct  = new JTextField();
	JTextField TaxProduct  = new JTextField();
	
	public AddProduct(){
		setLayout(new GridLayout(5,2));
		
		add(Name,BorderLayout.WEST);
		add(NameProduct,BorderLayout.EAST);
		add(Price,BorderLayout.WEST);
		add(PriceProduct,BorderLayout.EAST);
		add(Unit,BorderLayout.WEST);
		add(UnitProduct,BorderLayout.EAST);
		add(Tax,BorderLayout.WEST);
		add(TaxProduct,BorderLayout.EAST);
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
				if (!(NameProduct.getText().isEmpty() || PriceProduct.getText().isEmpty() || UnitProduct.getText().isEmpty() || TaxProduct.getText().isEmpty())) {
					if (SQLConnector.getInstance().dodajProdukt(NameProduct.getText(), Double.parseDouble(PriceProduct.getText()), UnitProduct.getText(), Double.parseDouble(TaxProduct.getText())) == 0) {
						JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(jFrame, "Operacja powiodla sie");
						Guide.getInstance().changeValuves(0, 4);
						NameProduct.setText("");
						PriceProduct.setText("");
						UnitProduct.setText("");
						TaxProduct.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
				}
			}catch (Exception en){
				JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			Guide.getInstance().changeValuves(0,4);
			NameProduct.setText("");
			PriceProduct.setText("");
			UnitProduct.setText("");
			TaxProduct.setText("");
		}
	}
}
