package Menu.Invoice;

import Menu.Find.FindClient;
import Menu.Guide;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InvoiceClient extends FindClient {

	InvoiceData data = new InvoiceData();
	public InvoiceClient() {
		done();
	}
	
	void done()
	{
		jTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				
				if (mouseEvent.getClickCount() == 2 && jTable.getSelectedRow() != -1) {
					Guide.getInstance().preapreInvoice(";"+dataInside[jTable.getSelectedRow()][0].toString());
					data.Logging();
				}
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Guide.getInstance().changeValuves(0,1);
	}

}
