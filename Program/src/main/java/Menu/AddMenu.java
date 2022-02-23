package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddMenu extends FindMenu {
	
	public AddMenu(){
		setLayout(new GridLayout(5, 1));
	}
	@Override
	protected void addButtons(JButton invoice, JButton product, JButton client, JButton Worker, JButton company){
		add(product);
		add(client);
		if(Id==3){
			add(Worker);
			add(company);
		}
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals(Actions.Product.name())) {
			Guide.getInstance().changeValuves(4,0);
		} else if (evt.getActionCommand().equals(Actions.Client.name())) {
			Guide.getInstance().changeValuves(4,1);
		} else if (evt.getActionCommand().equals(Actions.Woker.name())) {
			Guide.getInstance().changeValuves(4,2);
		} else if (evt.getActionCommand().equals(Actions.Company.name())) {
			Guide.getInstance().changeValuves(4,3);
		}
		else if (evt.getActionCommand().equals(Actions.Back.name())){
			Guide.getInstance().changeValuves(0,1);
		}
	}
}
