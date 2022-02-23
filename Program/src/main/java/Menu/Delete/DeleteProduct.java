package Menu.Delete;

import Menu.Find.FindProduct;
import Menu.Guide;
import Menu.SQLConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DeleteProduct extends FindProduct {
	public DeleteProduct(){
		done();
	}
	private void done()
	{
		jTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() == 2 && jTable.getSelectedRow() != -1) {
					if(SQLConnector.getInstance().usunProdukt(Integer.parseInt(dataInside[jTable.getSelectedRow()][0].toString())) ==0){
						JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(jFrame, "Operacja powiodla sie");
						Guide.getInstance().changeValuves(0,5);
					}
				}
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Guide.getInstance().changeValuves(0,5);
	}
}
