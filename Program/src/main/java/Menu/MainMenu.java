package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener {

		private enum Actions{
		Create, Remake, Delete, Check, Add, Backup,Logout
		}
		
		int Id = Guide.getInstance().getID();
		
	public MainMenu(){
		setLayout(new GridLayout(7, 1));
		setButtons();
	}
	
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
	}
	
	private void setButtons(){
		 JButton create = new JButton();
		 JButton Remake  = new JButton();
		 JButton delete  = new JButton();
		 JButton check = new JButton();
		 JButton add  = new JButton();
		 JButton backUp = new JButton();
		 JButton logOut = new JButton();
		 
		 create.setText("Utworz Fakture");
		 check.setText("Wyswietl");
		 add.setText("Dodaj");
		 delete.setText("Usun");
		 Remake.setText("Popraw");
		 backUp.setText("Utworz Backup");
		 logOut.setText("Wylacz");
		
		 create.setActionCommand(Actions.Create.name());
		 create.addActionListener(this);
		 Remake.setActionCommand(Actions.Remake.name());
		 Remake.addActionListener(this);
		 check.setActionCommand(Actions.Check.name());
		 check.addActionListener(this);
		 add.setActionCommand(Actions.Add.name());
		 add.addActionListener(this);
		 delete.setActionCommand(Actions.Delete.name());
		 delete.addActionListener(this);
		 backUp.setActionCommand(Actions.Backup.name());
		 backUp.addActionListener(this);
		 logOut.setActionCommand(Actions.Logout.name());
		 logOut.addActionListener(this);
		 
		 add(create);
		 add(check);
		 if(Id>1) {
			 add(add);
			 add(delete);
			 add(Remake);
		 }
		 add(backUp);
		 add(logOut);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getActionCommand().equals(Actions.Create.name())){
			Guide.getInstance().changeValuves(0,2);
		}
		else if(evt.getActionCommand().equals(Actions.Remake.name())){
			Guide.getInstance().changeValuves(0,6);
		}
		else if(evt.getActionCommand().equals(Actions.Check.name())){
			Guide.getInstance().changeValuves(0,3);
		}
		else if(evt.getActionCommand().equals(Actions.Delete.name())){
			Guide.getInstance().changeValuves(0,5);
		}
		else if(evt.getActionCommand().equals(Actions.Add.name())){
			Guide.getInstance().changeValuves(0,4);
		}
		else if(evt.getActionCommand().equals(Actions.Backup.name())){
			Guide.getInstance().changeValuves(0,7);
		}
		else if(evt.getActionCommand().equals(Actions.Logout.name())){
			Guide.getInstance().changeValuves(0,0);
		}
	}
}
