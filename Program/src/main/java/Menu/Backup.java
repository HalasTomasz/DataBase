package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Backup extends JPanel implements ActionListener {
	private final JButton create = new JButton("Utworz backup");
	private final JButton read = new JButton("Wczytaj backup");
	private final JButton back = new JButton("Powrot");
	private final JFrame jFrame = new JFrame();
	private enum Actions{
		Add,Load,Back
	}
	
	public Backup(){
		seter();
	}
	
	void seter(){
		setLayout(new GridLayout(1,2));
		add(create,BorderLayout.EAST);
		add(read,BorderLayout.CENTER);
		add(back,BorderLayout.WEST);
		
		create.setActionCommand(Actions.Add.name());
		create.addActionListener(this);
		
		read.setActionCommand(Actions.Load.name());
		read.addActionListener(this);
		
		back.setActionCommand(Actions.Back.name());
		back.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(Actions.Add.name())) {
			if(SQLConnector.getInstance().makebackup()==0){
				JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(jFrame, "Operacja powiodla sie");
			}
		} else if (e.getActionCommand().equals(Actions.Load.name())) {
		 if(SQLConnector.getInstance().restorebackup()==0){
			 JOptionPane.showMessageDialog(jFrame, "Operacja nie powiodla sie", "Ostrzezenie", JOptionPane.ERROR_MESSAGE);
		 }
		 else{
			 JOptionPane.showMessageDialog(jFrame, "Operacja powiodla sie");
		 }
		} else {
			Guide.getInstance().changeValuves(0, 1);
		}
	}
}
