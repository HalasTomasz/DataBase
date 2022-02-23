package Menu.Invoice;

import Menu.Find.FindProduct;
import Menu.Guide;
import Menu.SQLConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class InvoiceProduct extends FindProduct {
	
	protected ArrayList<String> chosenProduct = new ArrayList<>();
	private final JLabel data = new JLabel("Ilosc: ");
	private final JTextField many = new JTextField();
	private JPanel jPanel;
	private JFrame jFrame;
	public InvoiceProduct() {
		setter();
		done();
	}
	void setter(){
		jFrame=new JFrame();
		jPanel = new JPanel(new GridLayout(1,2));
		jPanel.add(data, BorderLayout.EAST);
		jPanel.add(many, BorderLayout.WEST);
		jFrame.add(jPanel);
	}

	void done()
	{
		jTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				
				if (mouseEvent.getClickCount() == 2 && jTable.getSelectedRow() != -1) {
					
					JOptionPane.showMessageDialog(jFrame,jPanel,"Ilosc", JOptionPane.PLAIN_MESSAGE );
					if(!(many.getText().isEmpty())) {
						if(SQLConnector.getInstance().dodajListaProdukty(Integer.parseInt(dataInside[jTable.getSelectedRow()][0].toString()),Guide.getInstance().giveId(),Integer.parseInt(many.getText())) == 0){
							System.out.println("ERROR");
						}
						else{
							System.out.println("Udalo sie");
						}
					}
					else{
						System.out.println("EROOR");
					}
				
				}
				if(SwingUtilities.isRightMouseButton(mouseEvent)) {
				
					Guide.getInstance().changeValuves(0,1);
				}
			}
		});
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Guide.getInstance().changeValuves(0,1);
	}
}
