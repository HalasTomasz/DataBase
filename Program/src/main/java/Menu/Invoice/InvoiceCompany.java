package Menu.Invoice;

import Menu.Find.FindOurCompany;
import Menu.Guide;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class InvoiceCompany extends FindOurCompany {
	public InvoiceCompany() {
		done();
     }
     
	protected void done()
	{
		jTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				
				if (mouseEvent.getClickCount() == 2 && jTable.getSelectedRow() != -1) {
					Guide.getInstance().preapreInvoice(dataInside[jTable.getSelectedRow()][0].toString());
					Guide.getInstance().changeValuves(2,1);
				}
			}
		});
	}
}
