package Menu;

public class Guide {
	
	public int x=0,y=0;
	public boolean changer=false;
	public int Id;
	public Object[][] OurFirm;
	public boolean vision=false;
	public  String EmployID;
	public int invoiceID=-1;
	private static Guide guide;
	public String invoice="";
	private Guide(){
		init();
	}
	private void init(){
	}
	
	public static Guide getInstance() {
		
		if (guide == null) {
			synchronized(Guide.class) {
				if (guide == null) {
					guide = new Guide();
				}
			}
		}
		return guide;
	}
	
	public void changeValuves(int x, int y){
		this.x = x;
		this.y = y;
		this.changer=true;
	}
	
	protected int getID(){
		return Id;
	}
	
	public void setFirm(Object[][] firm){
		this.OurFirm=firm;
	}
	
	public String employFirm(){
		for (Object[] objects : OurFirm)
			for (int j = 0; j < OurFirm[0].length; j++) {
				if ((EmployID.equals(objects[j].toString()))) {
					return objects[0].toString();
				}
			}
		return null;
	}
	
	public void preapreInvoice(String invoice){
		this.invoice=this.invoice+invoice;
	}
	public String retrunInvoice(){
		return this.invoice;
	}
	
	public void setId(int id){
		this.invoiceID=id;
	}
	
	public int giveId(){
		return this.invoiceID;
	}
}
