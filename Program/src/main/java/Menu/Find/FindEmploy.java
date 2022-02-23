package Menu.Find;

import Menu.SQLConnector;

public class FindEmploy extends FindProduct {
	
	@Override
	protected void setColumns(){
		this.columnNames= SQLConnector.getInstance().selectColumns("pracownicy");
	}
	
	@Override
	protected void setData() {
		if (dataInside == null) {
			dataInside = SQLConnector.getInstance().select("pracownicy");
		} else {
			dataInside = SQLConnector.getInstance().select("pracownicy");
			defaultTableModel.setRowCount(0);
			for (int i = 0; i < dataInside.length; i++) {
				defaultTableModel.addRow(dataInside[i]);
			}
		}
	}
}
