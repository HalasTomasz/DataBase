package Menu;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class SQLConnector {
	
	public static final String URL = "jdbc:mysql://localhost:3306/invoice";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "Wars";
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	Statement stmt;
	CallableStatement callStatement;
	ResultSet res;
	ArrayList<Object[]> listEX;
	Object[][] tmp;
	Object pa;
	int counter;
	int output;
	
	private int numberOfColumn=-1;
	private int sizeOfrow=-1;
	Connection conn;
	private static SQLConnector sqlConnector;
	
	private SQLConnector(){
		init();
	}
	private void init(){
		openConnection();
	}
	public static SQLConnector getInstance() {
		
		if (sqlConnector == null) {
			synchronized(Guide.class) {
				if (sqlConnector == null) {
					sqlConnector = new SQLConnector();
				}
			}
		}
		return sqlConnector;
	}
	
	public void openConnection() {
			try{
				System.out.println("Connecting to a selected database...");
				//Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(URL,  USERNAME, PASSWORD);
				System.out.println("Connected database successfully...");
				//String testquery = "INSERT INTO klienci(nazwa, nipklienta, adres, email, telefon)VALUES (\"asd\", \"1234567890\",\"adress\", \"emaill\", \"123456789\")";
				//stmt.executeUpdate(testquery);
			} catch(Exception se){
				se.printStackTrace();
			}
	}
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException throwables) {
			System.out.println("dont worry");
		}
	}
	
	public String[] selectColumns(String tableName){
	
		try {
			stmt = conn.createStatement();
			String testquery = "SELECT * FROM " + tableName;
			res = stmt.executeQuery(testquery);
			ResultSetMetaData rsmd = res.getMetaData();
			numberOfColumn = rsmd.getColumnCount();
			 String[] columnNames = new String[numberOfColumn];
			for (int i = 1; i <= numberOfColumn; i++) {
				columnNames[i-1]  = rsmd.getColumnName(i);
				//String dataTypeOfColumn = rsmd.getColumnTypeName(i);
				//System.out.println(columnName + " has data type " + dataTypeOfColumn);
			}
			return  columnNames;
		}
		catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}
	
	public Object[][] select(String tableName) {
		try{
			Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM " +tableName;
			ResultSet resultSet  = statement.executeQuery(query);
			
			if (resultSet != null) {
				resultSet.last();
				sizeOfrow = resultSet.getRow();
				resultSet.first();
			}
			tmp = new Object[sizeOfrow][numberOfColumn];
			int j=0;
			do{
			for(int i=0; i <numberOfColumn;i++) {
				assert resultSet != null;
				tmp[j][i] = resultSet.getObject(i+1);
			}
			j++;
			}while (Objects.requireNonNull(resultSet).next());
			
			//if (tableName == "firma") {
				//String testquery = "SELECT * FROM firma";
				//tmp = new Object[6];
					//res = stmt.executeQuery(testquery);
					//while(res.next()){
						//Retrieve by column name
						//tmp[0] = res.getObject("firmaid");
						//tmp[1] = res.getInt("pracownikid");
					 ///tmp[2] = res.getString("nazwafirmy");
					 //	tmp[3] = res.getString("adresfirmy");
						//tmp[4] = res.getString("nipfirmy");
						//tmp[5] = res.getString("telefon");
						
						
					
			//	}
			//else if (tableName == "faktura"){
					//String testquery = "SELECT * FROM faktura";
					//tmp = new Object[6];
					//res = stmt.executeQuery(testquery);
					//while(res.next()){
						//Retrieve by column name
						//tmp[0] = res.getObject("fakutraid");
						//tmp[1] = res.getInt("firmaid");
						//tmp[2] = res.getString("klientid");
						//tmp[3] = res.getString("calkowitakwotabrutto");
						//tmp[4] = res.getString("calkowitakwotanetto");
						//tmp[5] = res.getString("Data");
						//listEX.add(tmp);
						
				//	}
			//	else if (tableName == "klienci"){
					//String testquery = "SELECT * FROM klienci";
					//tmp = new Object[6];
					//res = stmt.executeQuery(testquery);
					//while(res.next()){
						//Retrieve by column name
					//	tmp[0] = res.getObject("klientid");
						//tmp[1] = res.getInt("nazwa");
						//tmp[2] = res.getString("nipklienta");
						///tmp[3] = res.getString("adres");
						//tmp[4] = res.getString("email");
						//tmp[5] = res.getString("telefon");
						//listEX.add(tmp);
				//	}
				//else if (tableName == "pracownicy"){
					//String testquery = "SELECT * FROM pracownicy";
					//tmp = new Object[3];
					//res = stmt.executeQuery(testquery);
					//while(res.next()){
						//Retrieve by column name
						//tmp[0] = res.getObject("pracownikid");
						//tmp[1] = res.getInt("login");
						//tmp[2] = res.getString("haslo");
						//tmp[3] = res.getString("imie");
						//tmp[4] = res.getString("nazwisko");
						//tmp[5] = res.getString("uprawnienia");
						//listEX.add(tmp);
				//	}
				//else if (tableName == "produkty") {
				
				//int j = 0;
				//do {
				//	//Retrieve by column name
				///	tmp[j][0] = resultSet.getInt("ProduktID");
				///	tmp[j][1] = resultSet.getString("NazwaProduktu");
				///	tmp[j][2] = resultSet.getInt("Cena");
				//	tmp[j][3] = resultSet.getString("Jednostka");
				//	tmp[j][4] = resultSet.getString("Podatek");
				//	j++;
				//	//System.out.println(tmp[0]+" "+tmp[1]+" "+ tmp[2]+" "+tmp[3]+" "+tmp[4] );
					//listEX.add(tmp);
					//}while (resultSet.next());
				//}
				//else if (tableName == "produktynafakturze") {
					//String testquery = "SELECT * FROM pracownicy";
					//tmp = new Object[8];
					//res = stmt.executeQuery(testquery);
					//while (res.next()) {
						//Retrieve by column name
						//tmp[0] = res.getObject("pozycjaid");
						//tmp[1] = res.getInt("produktid");
						//tmp[2] = res.getString("fakturaid");
						//tmp[3] = res.getString("cena");
						//tmp[4] = res.getString("iloscproduktu");
						//tmp[5] = res.getString("jednostka");
						//tmp[6] = res.getString("kwotanetto");
						//tmp[7] = res.getString("kwotabrutto");
						//listEX.add(tmp);
					//}
				//}*/
			
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			return tmp;
		}
	
	public int dodajFaktura(int firmaid, int klientid, String date){
		output = 0;
		try {
			callStatement = conn.prepareCall("{ call dodajFaktura(? ,? , CAST(? AS DATE) , ?) }");
			callStatement.setInt(1, firmaid);
			callStatement.setInt(2, klientid);
			callStatement.setDate(3, Date.valueOf(date));
			callStatement.registerOutParameter(4, Types.INTEGER);
			callStatement.execute();
			output = callStatement.getInt(4);
			callStatement.close();
		} catch (SQLException | IllegalArgumentException throwables ) {
			return output;
		}
		return output;
	}
	public int dodajFirma(int pracownikid, String nazwafirmy, String adresfirmy, String nipfirmy, String telefon){
		output = 0;
		try {
			callStatement = conn.prepareCall("{ call dodajFirma(? ,? , ?, ?, ? , ?) }");
			callStatement.setInt(1, pracownikid);
			callStatement.setString(2, nazwafirmy);
			callStatement.setString(3, adresfirmy);
			callStatement.setString(4, nipfirmy);
			callStatement.setString(5, telefon);
			callStatement.registerOutParameter(6, Types.INTEGER);
			callStatement.execute();
			output = callStatement.getInt(6);
			callStatement.close();
			
		} catch (SQLException throwables) {
			return output;
		}
		return output;
	}
	public int dodajProdukt(String nazwaproduktuu, Double cenaa, String jednostkaa, double podatekk){
		output = 0;
		try {
			callStatement = conn.prepareCall("{ call dodajProdukt(? ,? , ?, ?, ?) }");
			callStatement.setString(1, nazwaproduktuu);
			callStatement.setDouble(2, cenaa);
			callStatement.setString(3, jednostkaa);
			callStatement.setDouble(4, podatekk);
			callStatement.registerOutParameter(5, Types.INTEGER);
			callStatement.execute();
			output = callStatement.getInt(5);
			callStatement.close();
			
		} catch (SQLException throwables) {
			return output;
		}
		return output;
	}
	public int dodajPracownik(String login, String haslo, String imie, String nazwisko, int uprawnienia){
		output = 0;
		try {
			callStatement = conn.prepareCall("{ call dodajPracownik(? ,? , ?, ?, ? , ?) }");
			callStatement.setString(1, login);
			callStatement.setString(2, haslo);
			callStatement.setString(3, imie);
			callStatement.setString(4, nazwisko);
			callStatement.setInt(5, uprawnienia);
			callStatement.registerOutParameter(6, Types.INTEGER);
			callStatement.execute();
			output = callStatement.getInt(6);
			callStatement.close();
			
		} catch (SQLException throwables) {
			return output;
		}
		return output;
	}
	
	public int dodajListaProdukty(int produktidd, int fakturaidd, int iloscproduktuu){
		output = 0;
		try {
			callStatement = conn.prepareCall("{ call dodajListaProdukty(? ,? , ?, ?) }");
			callStatement.setInt(1, produktidd);
			callStatement.setInt(2, fakturaidd);
			callStatement.setInt(3, iloscproduktuu);
			callStatement.registerOutParameter(4, Types.INTEGER);
			callStatement.execute();
			output = callStatement.getInt(4);
			callStatement.close();
			
		} catch (SQLException throwables) {
			return output;
		}
		return output;
	}
	
	public int dodajKlient(String nazwaa, String nipklientaa, String adress, String emaill, String telefonn){
		output = 0;
		try {
			callStatement = conn.prepareCall("{ call dodajKlient(? ,? , ?, ?, ?, ?) }");
			callStatement.setString(1, nazwaa);
			callStatement.setString(2, nipklientaa);
			callStatement.setString(3, adress);
			callStatement.setString(4, emaill);
			callStatement.setString(5, telefonn);
			callStatement.registerOutParameter(6, Types.INTEGER);
			callStatement.execute();
			output = callStatement.getInt(6);
			callStatement.close();
		} catch (SQLException throwables) {
			System.out.println("aaaaaaaa");
			return output;
		}
		System.out.println("goofd");
		return output;
	}
	
	public String logowanie(String loginn, String hasloo){
		String output_str = "-1";
		output = 0;
		try {
			callStatement = conn.prepareCall("{ call logowanie(? ,? , ?) }");
			callStatement.setString(1, loginn);
			callStatement.setString(2, hasloo);
			callStatement.registerOutParameter(3, Types.VARCHAR);
			callStatement.execute();
			output_str = callStatement.getString(3);
			callStatement.close();
		} catch (SQLException throwables) {
			return output_str;
		}
		return output_str;
	}
		
		public int usunKlient(int id){
			output = 0;
			try {
				callStatement = conn.prepareCall("{ call usunKlient(? ,?) }");
				callStatement.setInt(1, id);
				callStatement.registerOutParameter(2, Types.INTEGER);
				callStatement.execute();
				output = callStatement.getInt(2);
				callStatement.close();
			} catch (SQLException throwables) {
				return output;
			}
			return output;
		}
		
		public int usunProdukt(int id){
			output = 0;
			try {
				callStatement = conn.prepareCall("{ call usunProdukt(? ,?) }");
				
				callStatement.setInt(1, id);
				callStatement.registerOutParameter(2, Types.INTEGER);
				callStatement.execute();
				output = callStatement.getInt(2);
				callStatement.close();
			} catch (SQLException throwables) {
				return output;
			}
			return output;
		}
		
		public int usunFirma(String  nip){
			output = 0;
			try {
				callStatement = conn.prepareCall("{ call usunFirma(? ,?) }");
				callStatement.setString(1, nip);
				callStatement.registerOutParameter(2, Types.INTEGER);
				callStatement.execute();
				output = callStatement.getInt(2);
				callStatement.close();
			} catch (SQLException throwables) {
				return output;
			}
			return output;
		}
		
		public int usunFaktura(int id){
			output = 0;
			try {
				callStatement = conn.prepareCall("{ call usunFaktura(? ,?) }");
				callStatement.setInt(1, id);
				callStatement.registerOutParameter(2, Types.INTEGER);
				callStatement.execute();
				output = callStatement.getInt(2);
				callStatement.close();
			} catch (SQLException throwables) {
				return output;
			}
			return output;
		}
		
		public int usunPracownik(int id){
			output = 0;
			try {
				callStatement = conn.prepareCall("{ call usunPracownik(? ,?) }");
				callStatement.setInt(1, id);
				callStatement.registerOutParameter(2, Types.INTEGER);
				callStatement.execute();
				output = callStatement.getInt(2);
				callStatement.close();
			} catch (SQLException throwables) {
				return output;
			}
			return output;
		}
		
		
		public int usunListaProdukty(int id){
			output = 0;
			try {
				callStatement = conn.prepareCall("{ call usunListaProdukty(? ,?) }");
				callStatement.setInt(1, id);
				callStatement.registerOutParameter(2, Types.INTEGER);
				callStatement.execute();
				output = callStatement.getInt(2);
				callStatement.close();
			} catch (SQLException throwables) {
				return output;
			}
			return output;
		}
		
		public int poprawFaktura(int id,  String date){
			output = 0;
			try {
				callStatement = conn.prepareCall("{ call poprawFaktura(?,  CAST(? AS DATE),  ?) }");
				callStatement.setInt(1, id);
				callStatement.setDate(2, Date.valueOf(date));
				callStatement.registerOutParameter(3, Types.INTEGER);
				callStatement.execute();
				output = callStatement.getInt(3);
				callStatement.close();
			} catch (SQLException throwables) {
				return output;
			}
			return output;
		}
		public int poprawFirma(String nazwafirmy,String adresfirmy, String nipfirmy, String telefon){
			output = 0;
			try {
				callStatement = conn.prepareCall("{ call poprawFirma( ? , ?, ?, ? , ?) }");
				callStatement.setString(1, nazwafirmy);
				callStatement.setString(2, adresfirmy);
				callStatement.setString(3, nipfirmy);
				callStatement.setString(4, telefon);
				callStatement.registerOutParameter(5, Types.INTEGER);
				callStatement.execute();
				output = callStatement.getInt(5);
				callStatement.close();
				
			} catch (SQLException throwables) {
				return output;
			}
			return output;
		}
		public int poprawProdukt(int id, String nazwaproduktuu, Double cenaa, String jednostkaa, double podatekk){
			output = 0;
			try {
				callStatement = conn.prepareCall("{ call poprawProdukt(?, ? ,? , ?, ?, ?) }");
				callStatement.setInt(1, id);
				callStatement.setString(2, nazwaproduktuu);
				callStatement.setDouble(3, cenaa);
				callStatement.setString(4, jednostkaa);
				callStatement.setDouble(5, podatekk);
				callStatement.registerOutParameter(6, Types.INTEGER);
				callStatement.execute();
				output = callStatement.getInt(6);
				callStatement.close();
				
			} catch (SQLException throwables) {
				return output;
			}
			return output;
		}
		public int poprawPracownik(int id, String login, String haslo, String imie, String nazwisko, int uprawnienia){
			output = 0;
			
			try {
				callStatement = conn.prepareCall("{ call poprawPracownik(?, ? ,? , ?, ?, ? , ?) }");
				callStatement.setInt(1, id);
				callStatement.setString(2, login);
				callStatement.setString(3, haslo);
				callStatement.setString(4, imie);
				callStatement.setString(5, nazwisko);
				callStatement.setInt(6, uprawnienia);
				callStatement.registerOutParameter(7, Types.INTEGER);
				callStatement.execute();
				output = callStatement.getInt(7);
				callStatement.close();
				
			} catch (SQLException throwables) {
			
				return output;
			}
			return output;
		}
	
	public int poprawListaProdukty(int id, String nazwaa, double cenaa, int iloscproduktuu, String jednosktaa, double podatek){
		output = 0;
		try {
			callStatement = conn.prepareCall("{ call poprawListaProdukty(?, ? ,? , ?, ?, ?,?) }");
			callStatement.setInt(1, id);
			callStatement.setString(2, nazwaa);
			callStatement.setDouble(3, cenaa);
			callStatement.setInt(4, iloscproduktuu);
			callStatement.setString(5, jednosktaa);
			callStatement.setDouble(6, podatek);
			callStatement.registerOutParameter(7, Types.INTEGER);
			callStatement.execute();
			output = callStatement.getInt(7);
			callStatement.close();
			
		} catch (SQLException throwables) {
			return output;
		}
		return output;
	}
		
		public int poprawKlient(int id, String nazwaa, String nipklientaa, String adress, String emaill,String telefon){
			output = 0;
			try {
				callStatement = conn.prepareCall("{ call poprawKlient(?, ? ,? , ?, ?, ?, ?) }");
				callStatement.setInt(1, id);
				callStatement.setString(2, nazwaa);
				callStatement.setString(3, nipklientaa);
				callStatement.setString(4, adress);
				callStatement.setString(5, emaill);
				callStatement.setString(6, telefon);
				callStatement.registerOutParameter(7, Types.INTEGER);
				callStatement.execute();
				output = callStatement.getInt(7);
				callStatement.close();
			} catch (SQLException throwables) {
				return output;
			}
			return output;
		}
		
		public int makebackup(){
			// Runtime.getRuntime().exec("mysqldump -h127.0.0.1 -P3306 -uroot -p invoice>C:\\Users\\karol\\Desktop\\backup.sql"); //nie wiem czy podwojne ukoscniki
				try {
					Process runtimeProcess;
					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					String fileName = "Daily_DB_Backup"; // default file name
					String folderPath = "D:\\backups";
					File f1 = new File(folderPath);
					f1.mkdir(); // create folder if not exist
					String saveFileName = fileName+ ".sql";
					String savePath = folderPath + File.separator + saveFileName;
					runtimeProcess = Runtime.getRuntime().exec("mysqldump -u root -pWars --databases invoice --routines -r D:\\backups\\backup.sql"); //nie wiem czy podwojne ukoscniki
					runtimeProcess.waitFor();
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
					return 0;
				}
			
			return  1;
			}
	
		public int restorebackup(){
			String dbUserName = "root";
			String dbPassword = "Wars";
			String source = "D:\\backups\\backup.sql";
			String[] restoreCmd = new String[]{"mysql ", "--user=" + dbUserName, "--password=" + dbPassword, "-e", "source " + source};
			Process runtimeProcess;
			try {
			runtimeProcess = Runtime.getRuntime().exec(restoreCmd);
			int processComplete = runtimeProcess.waitFor();
			if (processComplete == 0) {
				System.out.println("Restored successfully!");
				return 1;
			} else {
				System.out.println("Could not restore the backup!");
				return 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
		}
	}

