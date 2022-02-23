package Menu.Change;

import Menu.Find.FindProduct;
import Menu.Guide;
import Menu.SQLConnector;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;


public class ChangeProduct extends FindProduct {
	
	protected ArrayList<String[]> X = new ArrayList<>();
	protected String[] tmp;
	
	
	protected enum Actions{
		Change, Back
	}
	
	public ChangeProduct() {
	}
	
	@Override
	protected void setDiffrence() {
		JButton add = new JButton("Popraw");
		setTabel();
		
		panel.setLayout(new GridLayout(0,4));
		panel.add(add, 0, 0);
		Back.setActionCommand(Actions.Back.name());
		add.setActionCommand(Actions.Change.name());
		add.addActionListener(this);
		set();
	}
	
	protected void setTabel(){
		this.jTable= new JTable(defaultTableModel){
			@Override
			public boolean isCellEditable(int row, int col) {
				return col != 0;
			}
		};
	}
	private void set() {
		jTable.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
					if(jTable.getSelectedColumn() !=-1 && jTable.getSelectedRow() != -1){
						if(jTable.getModel().getRowCount() !=0) {
							tmp = Arrays.toString(dataInside[jTable.getSelectedRow()]).split(", ");
							tmp[0] = tmp[0].replace("[","");
							tmp[tmp.length-1] = tmp[tmp.length-1].replace("]","");
							tmp[tmp.length-1] = tmp[tmp.length-1].replace(" ","");
							tmp[jTable.getSelectedColumn()] = jTable.getValueAt(jTable.getSelectedRow(),jTable.getSelectedColumn()).toString();
							X.add(tmp);
						}
				}
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(Actions.Change.name())) {
			
			String[] data;
			for (int i = 0; i < X.size(); i++) {
				data=X.get(i);
				try{
				if(SQLConnector.getInstance().poprawProdukt(Integer.parseInt(data[0]),data[1],Double.parseDouble(data[2]),data[3],Double.parseDouble(data[4])) ==0){
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
	

