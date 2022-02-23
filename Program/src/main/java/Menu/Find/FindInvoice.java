package Menu.Find;

import Menu.Guide;
import Menu.SQLConnector;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FindInvoice extends FindProduct {
	private final ProductList productList = new ProductList();
	
	public FindInvoice(){
	seter();
	}
	
	@Override
	protected void setColumns(){
		this.columnNames= SQLConnector.getInstance().selectColumns("faktura");
	}
	
	@Override
	protected void setData() {
		if (dataInside == null) {
			dataInside = SQLConnector.getInstance().select("faktura");
		} else {
			dataInside = SQLConnector.getInstance().select("faktura");
			defaultTableModel.setRowCount(0);
			for (int i = 0; i < dataInside.length; i++) {
				defaultTableModel.addRow(dataInside[i]);
			}
		}
	}
	
	protected void seter(){
		jTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() == 2 && jTable.getSelectedRow() != -1) {
					Guide.getInstance().changeValuves(3,5);
				}
			}
		});
	}
}
