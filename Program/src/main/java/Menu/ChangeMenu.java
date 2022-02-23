package Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangeMenu extends FindMenu {
	
	public ChangeMenu(){
	}
	@Override
	protected void addButtons(JButton invoice, JButton product, JButton client, JButton Worker, JButton company){
		add(invoice);
		add(product);
		add(client);
		if(Id==3){
			add(Worker);
			add(company);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals(Actions.Invoice.name())) {
			Guide.getInstance().changeValuves(6,0);
		} else if (evt.getActionCommand().equals(Actions.Product.name())) {
			Guide.getInstance().changeValuves(6,1);
		} else if (evt.getActionCommand().equals(Actions.Client.name())) {
			Guide.getInstance().changeValuves(6,2);
		} else if (evt.getActionCommand().equals(Actions.Woker.name())) {
			Guide.getInstance().changeValuves(6,3);
		} else if (evt.getActionCommand().equals(Actions.Company.name())) {
			Guide.getInstance().changeValuves(6,4);
		}
		else if (evt.getActionCommand().equals(Actions.Back.name())){
			Guide.getInstance().changeValuves(0,1);
		}
	}
}
