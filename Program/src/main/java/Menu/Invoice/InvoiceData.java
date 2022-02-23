package Menu.Invoice;

import Menu.Guide;
import Menu.SQLConnector;

import javax.swing.*;
import java.awt.*;
public class InvoiceData extends JFrame  {
	
	JPanel jPanel = new JPanel(new GridLayout(1, 1));
	JLabel labeldate= new JLabel("Data: ");
	JTextField textdate = new JTextField();
	String date;
	public InvoiceData(){
		setting();
	}
	
	public void setting() {
		jPanel.add(labeldate);
		jPanel.add(textdate);
	}
	public void Logging(){
			JOptionPane.showMessageDialog(this, jPanel, "Data", JOptionPane.PLAIN_MESSAGE );
			date=textdate.getText();
	
			while(date.isEmpty()){
				JOptionPane.showMessageDialog(this, jPanel, "Data", JOptionPane.PLAIN_MESSAGE );
				date=textdate.getText();
			}
		Guide.getInstance().preapreInvoice(";"+date);
		textdate.setText("");
		String[] tmp = Guide.getInstance().retrunInvoice().split(";");
		int id = SQLConnector.getInstance().dodajFaktura(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]),tmp[2]);
		if(id==0 ){
			System.out.println("ERROR");
			Guide.getInstance().invoice="";
			Guide.getInstance().changeValuves(0,1);
		}
		else{
			Guide.getInstance().invoice="";
			Guide.getInstance().setId(id);
			System.out.println(id);
			Guide.getInstance().changeValuves(2,2);
		}
	}
}
