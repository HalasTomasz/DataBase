package Menu;

public class NewInvoice {

	public NewInvoice(){
		if(Guide.getInstance().getID()<3) {
			Guide.getInstance().preapreInvoice(Guide.getInstance().employFirm());
			Guide.getInstance().changeValuves(2,1);
		}
		else{
			Guide.getInstance().changeValuves(2,0);
		}
	}
}
