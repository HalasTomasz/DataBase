package Menu.Change;


import Menu.Guide;
import Menu.SQLConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangeProductList extends ChangeProduct {

	public ChangeProductList(){
	}
	
	@Override
	protected void setColumns(){
		this.columnNames= SQLConnector.getInstance().selectColumns("produktynafakturze");
	}
	
	@Override
	protected void setData() {
		if (dataInside == null) {
			dataInside = SQLConnector.getInstance().select("produktynafakturze");
		} else {
			dataInside = SQLConnector.getInstance().select("produktynafakturze");
			for (int i = 0; i < dataInside.length; i++) {
				for (int j = 0; j < dataInside[0].length; j++) {
					defaultTableModel.setValueAt(dataInside[i][j], i, j);
				}
			}
		}
	}
	@Override
	protected void setTabel(){
		this.jTable= new JTable(defaultTableModel){
			@Override
			public boolean isCellEditable(int row, int col) {
				return col != 0 && col != 1 && col != 2 && col != 8 && col != 9;
			}
		};
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(Actions.Change.name())) {
			String[] data;
			for (int i = 0; i < X.size(); i++) {
				data=X.get(i);
				try{
					
					if(SQLConnector.getInstance().poprawListaProdukty(Integer.parseInt(data[0]),data[3],Double.parseDouble(data[4]),Integer.parseInt(data[5]),data[7],Double.parseDouble(data[6])) ==0){
						JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(jFrame, "Operacja powiodla sie");
					}
				}
				catch (Exception enn){
					JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
				}
			}
			X.clear();
			Guide.getInstance().changeValuves(0,1);
		}
		else{
			X.clear();
			Guide.getInstance().changeValuves(0,6);
		}
	}
}
