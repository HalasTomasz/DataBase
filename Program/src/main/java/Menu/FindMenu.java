package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindMenu extends JPanel implements ActionListener {
	protected int Id;
	protected JButton comeBack = new JButton("Powrot");
	
	protected enum Actions{
		Invoice, Product, Client, Woker, Company,Back
	}
	
	public FindMenu() {
		prepare();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	protected void prepare(){
		this.Id = Guide.getInstance().Id;
		setLayout(new GridLayout(6, 1));
		setButtons();
	}
	
	protected void setButtons() {
		JButton invoice = new JButton();
		JButton product = new JButton();
		JButton client = new JButton();
		JButton Worker = new JButton();
		JButton company = new JButton();
		
		invoice.setText("Faktury");
		product.setText("Produkty");
		Worker.setText("Pracownicy");
		client.setText("Klienci");
		company.setText("Firma");
		
		invoice.setActionCommand(Actions.Invoice.name());
		invoice.addActionListener(this);
		product.setActionCommand(Actions.Product.name());
		product.addActionListener(this);
		Worker.setActionCommand(Actions.Woker.name());
		Worker.addActionListener(this);
		company.setActionCommand(Actions.Company.name());
		company.addActionListener(this);
		client.setActionCommand(Actions.Client.name());
		client.addActionListener(this);
		
		addButtons(invoice,product,client,Worker,company);
		
		add(comeBack, BorderLayout.SOUTH);
		comeBack.setActionCommand(Actions.Back.name());
		comeBack.addActionListener(this);
	}
	
	protected void addButtons(JButton invoice,JButton product,JButton client,JButton Worker,JButton company){
		add(invoice);
		add(product);
		add(client);
		if (Id >= 2){
			add(Worker);
		}
		if(Id==3) {
			add(company);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals(Actions.Invoice.name())) {
			Guide.getInstance().changeValuves(3,0);
		} else if (evt.getActionCommand().equals(Actions.Product.name())) {
			Guide.getInstance().changeValuves(3,1);
		} else if (evt.getActionCommand().equals(Actions.Client.name())) {
			Guide.getInstance().changeValuves(3,2);
		} else if (evt.getActionCommand().equals(Actions.Woker.name())) {
			Guide.getInstance().changeValuves(3,3);
		} else if (evt.getActionCommand().equals(Actions.Company.name())) {
			Guide.getInstance().changeValuves(3,4);
		}
		else if (evt.getActionCommand().equals(Actions.Back.name())){
			Guide.getInstance().changeValuves(0,1);
		}
	}
}
