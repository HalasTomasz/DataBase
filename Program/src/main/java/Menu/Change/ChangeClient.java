package Menu.Change;

import Menu.Guide;
import Menu.SQLConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangeClient extends ChangeProduct {
	
	public ChangeClient() {
	}
	
	@Override
	protected void setColumns(){
		this.columnNames= SQLConnector.getInstance().selectColumns("klienci");
	}
	
	@Override
	protected void setData() {
		if (dataInside == null) {
			dataInside = SQLConnector.getInstance().select("klienci");
		} else {
			dataInside = SQLConnector.getInstance().select("klienci");
			for (int i = 0; i < dataInside.length; i++) {
				for (int j = 0; j < dataInside[0].length; j++) {
					defaultTableModel.setValueAt(dataInside[i][j], i, j);
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(Actions.Change.name())) {
			String[] data;
			for (int i = 0; i < X.size(); i++) {
				data=X.get(i);
				try {
					if (SQLConnector.getInstance().poprawKlient(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5]) == 0) {
						JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
					} else {
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
