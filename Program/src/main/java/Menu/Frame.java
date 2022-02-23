package Menu;

import Menu.Add.AddClient;
import Menu.Add.AddCompany;
import Menu.Add.AddEmploy;
import Menu.Add.AddProduct;
import Menu.Change.*;
import Menu.Delete.*;
import Menu.Find.*;
import Menu.Invoice.InvoiceClient;
import Menu.Invoice.InvoiceCompany;
import Menu.Invoice.InvoiceProduct;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Frame  {
	
	private final JFrame frame = new JFrame();
	private final JPanel[][] panels = new JPanel[7][8];
	private int [] sizesX = new int[8];
	private int [] sizesY = new int[9];

	public Frame() throws InterruptedException {
		addMenu();
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start();
	}
	private void start() throws InterruptedException {
		while(true) {
			TimeUnit.SECONDS.sleep(1);
			if (Guide.getInstance().changer) {
				clearFrame();
				if(Guide.getInstance().x==0 && Guide.getInstance().y==0) {
					frame.setVisible(false);
					System.exit(0);
				}
				else if (Guide.getInstance().x==0 && Guide.getInstance().y==2){
					new NewInvoice();
				}
				else {
					frame.setSize(sizesX[Guide.getInstance().x],sizesY[Guide.getInstance().x]);
					Guide.getInstance().changer = false;
					frame.add(panels[Guide.getInstance().x][Guide.getInstance().y]);
				}
				frame.revalidate();
			}
		}
	}
	private void addMenu()  {
		
		sizesX= new int[]{500, 500, 500, 700, 700, 700,700};
		sizesY= new int[]{500, 500, 500, 800, 300, 800,800};
		
		panels[3][0]= new FindInvoice();
		panels[3][1]= new FindProduct();
		panels[3][2]= new FindClient();
		panels[3][3]= new FindEmploy();
		panels[3][4]= new FindOurCompany();
		panels[3][5]= new ProductList();
		
		panels[0][1]= new MainMenu();
		panels[0][3]= new FindMenu();
		panels[0][4]= new AddMenu();
		panels[0][5]= new DeleteMenu();
		panels[0][6]= new ChangeMenu();
		panels[0][7]= new Backup();
		
		panels[2][0]= new InvoiceCompany();
		panels[2][1]= new InvoiceClient();
		panels[2][2]= new InvoiceProduct();
		
		panels[4][0]= new AddProduct();
		panels[4][1]= new AddClient();
		panels[4][2]= new AddEmploy();
		panels[4][3]= new AddCompany();
		
		panels[5][0]= new DeleteInvoice();
		panels[5][1]= new DeleteProduct();
		panels[5][2]= new DeleteClient();
		panels[5][3]= new DeleteEmploy();
		panels[5][4]= new DeleteOurCompany();
		panels[5][5]= new DeleteProductList();
		
		panels[6][0]= new ChangeInvoice();
		panels[6][1]= new ChangeProduct();
		panels[6][2]= new ChangeClient();
		panels[6][3]= new ChangeEmploy();
		panels[6][4]= new ChangeOurCompany();
		panels[6][5]= new ChangeProductList();
	}
	public void clearFrame() {
		frame.getContentPane().removeAll();
		frame.revalidate();
		frame.repaint();
	}
}
