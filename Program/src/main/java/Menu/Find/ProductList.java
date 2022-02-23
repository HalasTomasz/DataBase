package Menu.Find;

import Menu.Guide;
import Menu.SQLConnector;

import java.awt.event.ActionEvent;

public class ProductList extends FindProduct{

	public ProductList(){
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
			defaultTableModel.setRowCount(0);
			for (int i = 0; i < dataInside.length; i++) {
				defaultTableModel.addRow(dataInside[i]);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Guide.getInstance().changeValuves(3,0);
	}
}
