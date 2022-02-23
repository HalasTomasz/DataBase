package Menu.Find;

import Menu.Guide;
import Menu.SQLConnector;

public class FindOurCompany extends FindProduct {
	
	@Override
	protected void setColumns(){
		this.columnNames= SQLConnector.getInstance().selectColumns("firma");
	}
	
	@Override
	protected void setData() {
		if (dataInside == null) {
			dataInside = SQLConnector.getInstance().select("firma");
			Guide.getInstance().setFirm(dataInside);
		} else {
			dataInside = SQLConnector.getInstance().select("firma");
			Guide.getInstance().setFirm(dataInside);
			defaultTableModel.setRowCount(0);
			for (int i = 0; i < dataInside.length; i++) {
				defaultTableModel.addRow(dataInside[i]);
			}
		}
	}
}
