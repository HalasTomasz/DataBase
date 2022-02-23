package Menu.Find;

import Menu.SQLConnector;

public class FindClient extends FindProduct{
	
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
			defaultTableModel.setRowCount(0);
			for (int i = 0; i < dataInside.length; i++) {
				defaultTableModel.addRow(dataInside[i]);
			}
		}
	}
}
