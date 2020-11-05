import java.sql.*;
import java.util.*;
import java.time.*;

public class MySqlDataStoreUtilities {
    static Connection conn = null;

    public static void getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project_database?autoReconnect=true&useSSL=false", "root", "yzhu90");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTable() {
        try {

            getConnection();
            conn.prepareStatement(
                    "create table Registration(userId integer NOT NULL AUTO_INCREMENT,userName varchar(40),password varchar(40),email varchar(60),usertype varchar(40),address varchar(60),locationLat varchar(20),locationLong varchar(20),location  varchar(30),Primary key(userId));")
                    .execute();
            conn.prepareStatement(
                    "Create table Appointment(bookId integer NOT NULL AUTO_INCREMENT,providerId integer,customerId integer,appointmentDate date,appointmentTime time, primary key(bookId));")
                    .execute();
            conn.prepareStatement(
                    "Create table Doctordetails(postId integer NOT NULL AUTO_INCREMENT,doctorId integer,realName varchar(30),department varchar(40),address varchar(60),locationLat varchar(20),locationLong varchar(20),location  varchar(30),OpenTime varchar(20), closeTime varchar(20), postTime date,Primary key(postId));")
                    .execute();
            conn.prepareStatement(
                    "create table Message (messageId integer NOT NULL AUTO_INCREMENT, customerId integer, providerId integer, message varchar(1000), primary key(messageId));")
                    .execute();
            conn.prepareStatement(
                    "Create view process as Select messageId, userName as 'CustomerName', providerId, message  from Message inner join Registration on Message.customerId = Registration.userId;")
                    .execute();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insertDefaultData() {
        try {

            getConnection();
            conn.prepareStatement(
                    "INSERT INTO Registration ( userName, password, email, usertype, address, locationLat, locationLong, location) VALUES ('Tom','tom','tom@gmail.com','customer','215 West Washington, W Washington St, Chicago, IL 60606','41.8828584','-87.6363466','Chicago, IL'),"
                            + "('Bill', 'bill','bill@gmail.com','doctor','3000 N Halsted St Ste 720, Chicago, IL 60657','41.9367044','-87.6519388','Chicago, IL'),"
                            + "('Lily','lily','lily@gmail.com','customer','255 King St, San Francisco, CA 94107','37.776453','-122.3956391','San Francisco, CA'),"
                            + "('Jason','jason','jason@gmail.com','customer','1700 Newbury Park Dr, San Jose, CA 95133','37.3649833','-121.8693459','San Jose, CA'),"
                            + "('Ming','ming','ming@gmail.com','doctor','909 Hyde St #432, San Francisco, CA 94109','37.789371','-122.4194441','San Francisco, CA'),"
                            + "('Justin','justin','justin@gmail.com','customer','101 Colorado St, Austin, TX 78701','30.2641372','-97.7477867','Austin, TX'),"
                            + "('Brynna','brynna ','brynna@gmail.com','doctor','2911 Medical Arts St Building #10, Austin, TX 78705','30.2880745','-97.7295075','Austin, TX'),"
                            + "('Gina','gina','tina@gmail.com','customer','2829 27th St W, Williston, ND 58801','48.1700719','-103.6650649','Williston, ND'),"
                            + "('Ryan ','ryan ','ryan@gmail.com','doctor','1321 W Dakota Pkwy, Williston, ND 58801','48.1559559','-103.6530379','Williston, ND'),"
                            + "('David','david','david@gmail.com','customer','111 N Wabash Ave STE 1412, Chicago, IL 60602','41.8834746','-87.6279434','Chicago, IL'),"
                            + "('Milton','milton','milton@gmail.com','doctor','210 East 64th Street, 7th Floor, New York City, NY 10065','40.7640723','-73.9655038','New York City, NY'),"
                            + "('David','david','david@gmail.com','doctor',' 111 N Wabash Ave STE 1412, Chicago, IL 60602','41.8834746','-87.6279434','Chicago, IL');")
                    .execute();

            conn.prepareStatement(
                    "INSERT INTO Doctordetails ( doctorId, realName, department, address, locationLat, locationLong, location, OpenTime,closeTime,postTime)"
                            + "VALUES (2,'Dr. Sue Kafali-Nazarof','gynecologic & cosmetic surgeon','3000 N Halsted St Ste 720, Chicago, IL 60657','41.9367044','-87.6519388','Chicago, IL','09:00:00','17:00:00','2020-08-30'),"
                            + "(5,'Dr. Ming Li Tsang','Family Medicine','909 Hyde St #432, San Francisco, CA 94109','37.789371','-122.4194441','San Francisco, CA','08:00:00','17:30:00','2020-09-03'),"
                            + "(9,'Dr. Ryan M Siewert','Family Medicine','1321 W Dakota Pkwy, Williston, ND 58801','48.1559559','-103.6530379','Williston, ND','09:30:00','17:30:00','2020-09-05'),"
                            + "(7,'Dr. Brynna Connor','Family Medicine','2911 Medical Arts St Building #10, Austin, TX 78705','30.2880745','-97.7295075','Austin, TX','08:00:00','17:30:00','2020-09-05'),"
                            + "(11,'Dr. Milton Waner','hemangiomas and vascular malformations','210 East 64th Street, 7th Floor, New York City, NY 10065','40.7640723','-73.9655038','New York City, NY','08:15:00','16:30:00','2020-09-06'),"
                            + "(12,'Dr. David J. Benditzson',' Primary Care','111 N Wabash Ave STE 1412, Chicago, IL 60602','41.8834746','-87.6279434','Chicago, IL','09:30:00','16:30:00','2020-09-01'),"
                            + "(13,'Dr. Shirish N. Shah',' Cardiology ','5600 W Addison St Ste 400, Chicago, IL 60634','41.9460911','-87.7693311','Chicago, IL','09:00:00','17:30:00','2020-09-12'),"
							+ "(14,'Dr. Adrian Ma',' Cardiovascular ','5200 Jose Figueres Ave #215, San Jose, CA 95116','37.3615718','-121.8524164','San Jose, CA','08:00:00','15:30:00','2020-09-15'),"
							+ "(15,'Dr. Russell O. Schub','Gastroenterology','8875 Centre Park Dr suite d, Columbia, MD 21045','39.2289939','-76.8156736','Columbia, MD','10:00:00','16:00:00','2020-09-20'),"
							+ "(16,'Dr. March E. Seabrook','Gastroenterology','131 Summer Pl Dr, West Columbia, SC 29169','34.0042347','-81.1231826','West Columbia, SC','08:00:00','17:00:00','2020-09-22'),"
							+ "(17,'Dr. Mark W. Galland','Orthopedics','11200 Gov Manly Way #309, Raleigh, NC 27614','35.9477509','-78.543784','Raleigh, NC','09:00:00','17:30:00','2020-09-27'),"
							+ "(18,'Dr. Jon J. Warner','Orthopedics','55 Fruit St, Boston, MA 02114','42.362706','-71.0708467','Boston, MA','08:30:00','16:30:00','2020-09-28'),"
							+ "(19,'Dr. Peter Steinberg','Urology','330 Brookline Ave, Boston, MA 02215','42.3398798','-71.106747','Boston, MA','08:30:00','16:30:00','2020-10-08')," 
							+ "(20,'Dr. Robert Oates','Urology','725 Albany St #3b, Boston, MA 02118','42.3348727','-71.0743435','Boston, MA','09:30:00','18:30:00','2020-10-09'),"
							+ "(21,'Dr. Edward Zoltan','Urology','175 Remsen St 12th floor, Brooklyn, NY 11201','40.6934999','-73.9935536','Brooklyn, NY','08:30:00','18:00:00','2020-10-11')," 
							+ "(22,'Dr. Ken Moadel','Ophthalmology','110 E 40th St Floor 6, New York, NY 10016','40.7504117','-73.980187','New York, NY','08:30:00','17:00:00','2020-10-12')," 
							+ "(23,'Dr. Jacqueline Wong','Ophthalmology','945 Hildebrand Ln NE, Bainbridge Island, WA 98110','47.6336187','-122.5199271','Bainbridge Island, WA','09:00:00','17:00:00','2020-10-15')," 
							+ "(24,'Dr. John H. Wilkins','Ophthalmology','9155 SW Barnes Rd #430, Portland, OR 97225','45.510411','-122.772308','Portland, OR','09:00:00','14:00:00','2020-10-16');")
                    .execute();

            conn.prepareStatement("INSERT INTO Appointment (providerId, customerId, appointmentDate, appointmentTime)"
                    + "VALUES (2,1,'2020-10-20','2020-10-20 10:00:00')," + "(2,10,'2020-10-07','2020-10-07 15:00:00'),"
                    + "(5,4,'2020-10-31','2020-10-31 09:30:00')," + "(5,3,'2020-11-01','2020-11-01 13:45:00'),"
                    + "(2,1,'2020-11-20','2020-11-20 10:00:00')," + "(7,6,'2020-10-15','2020-10-15 10:15:00'),"
                    + "(7,6,'2020-11-20','2020-11-20 10:30:00')," + "(5,3,'2020-10-06','2020-10-06 09:20:00'),"
                    + "(5,10,'2020-11-06','2020-11-06 14:30:00')," + "(5,8,'2020-10-30','2020-10-30 09:20:00');")
                    .execute();

            conn.prepareStatement("insert into Message ( customerId, providerId)" 
                    +"values (1, 2),"
                    +"(10, 2),"
                    +"(4, 5),"
                    +"(3, 5),"
                    +"(1, 2),"
                    +"(6, 7),"
                    +"(6, 7),"
                    +"(3, 5),"
                    +"(10, 5),"
                    +"(8, 5);")
                    .execute();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void insertUser(String userName,String  password, String email,String usertype,String address,String locationLat,String locationLong,String location)
	{
		try
		{	

			getConnection();
			String insertIntoRegister = "INSERT INTO Registration(userName, password, email, usertype, address, locationLat, locationLong, location) "
			+ "VALUES (?,?,?,?,?,?,?,?);";	
					
			PreparedStatement pst = conn.prepareStatement(insertIntoRegister);
			pst.setString(1,userName);
            pst.setString(2,password);
            pst.setString(3,email);
            pst.setString(4,usertype);
            pst.setString(5,address);
            pst.setString(6,locationLat);
            pst.setString(7,locationLong);
			pst.setString(8,location);

			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println("insertUser error: "+e.getMessage());

		}	
	}
	
	public static HashMap<String,User> selectUser()
	{	
		HashMap<String,User> hm=new HashMap<String,User>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  Registration";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	User user = new User(rs.getInt("userId"),rs.getString("userName"),rs.getString("password"),rs.getString("email"), rs.getString("usertype"), rs.getString("address"),rs.getString("locationLat"),rs.getString("locationLong"),rs.getString("location"));
				hm.put(rs.getString("username"), user);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return hm;			
	}

    public static void updateUser(int userId, String  password,String address,String locationLat,String locationLong,String location)
	{
		try
		{	

			getConnection();
			String insertIntoRegister = "Update Registration set password = ? , address = ? , locationLat = ? , locationLong = ? , location = ? where userid = ? ";	
            
            //Update Registration set password = 'newPassword' , address = '25910 Celtic Terrace Dr' , locationLat = 30 , locationLong = 50 , location = '25910 Celtic Terrace Dr' where userid = 3;

					
			PreparedStatement pst = conn.prepareStatement(insertIntoRegister);
			pst.setString(1,password);
            pst.setString(2,address);
            pst.setString(3,locationLat);
            pst.setString(4,locationLong);
            pst.setString(5,location);
            pst.setInt(6,userId);


			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println("Update error: "+e.getMessage());

		}	
    }
    
    public static void insertDoctor(int doctorId,String realName,String department,String address,String locationLat,String locationLong,String location,String openTime,String closeTime, String postTime)
	{
		try
		{	

			getConnection();
			String insertIntoDoctor = "INSERT INTO Doctordetails( doctorId, realName, department, address, locationLat, locationLong, location, OpenTime,closeTime,postTime) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?);";	
					
			PreparedStatement pst = conn.prepareStatement(insertIntoDoctor);
			pst.setInt(1,doctorId);
            pst.setString(2,realName);
            pst.setString(3,department);
            pst.setString(4,address);
            pst.setString(5,locationLat);
            pst.setString(6,locationLong);
            pst.setString(7,location);
			pst.setString(8,openTime);
            pst.setString(9,closeTime);
            pst.setString(10,postTime);
			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}	
    }
    
    public static void insertAppointment(int providerId,int  customerId,String appointmentDate,String appointmentTime)
	{
		try
		{	

			getConnection();
			String insertAppointment = "INSERT INTO Appointment(providerId, customerId, appointmentDate, appointmentTime) "
			+ "VALUES (?,?,?,?);";	
					
			PreparedStatement pst = conn.prepareStatement(insertAppointment);
			pst.setInt(1,providerId);
			pst.setInt(2,customerId);
            pst.setString(3,appointmentDate);
            pst.setString(4,appointmentTime);

			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}	
    }
    
    public static void insertMessage(int customerId,int providerId)
	{
		try
		{	

			getConnection();
			String insertMessage = "INSERT INTO Message( customerId, providerId) "
			+ "VALUES (?,?);";	
					
			PreparedStatement pst = conn.prepareStatement(insertMessage);
			pst.setInt(1,customerId);
			pst.setInt(2,providerId);

			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}	
    }

    public static void insertBook(int providerId,int  customerId,String appointmentDate,String appointmentTime) {
        insertAppointment(providerId,customerId,appointmentDate,appointmentTime);
        insertMessage(customerId, providerId);
    }
    

    public static void updateMessage(int messageId, String message)
	{	
		try
		{	

			getConnection();
			String updateMessage = "Update Message Set message=? where messageId=?";
					
			PreparedStatement pst = conn.prepareStatement(updateMessage);
			pst.setString(1,message);
			pst.setInt(2,messageId);

			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}				
    }


    public static ArrayList<Book> viewBook()
	{	
		ArrayList<Book> books = new ArrayList<>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectBookQuery="Select messageId, CustomerName, userName as 'ProviderName', usertype, appointmentDate, appointmentTime, message from process inner join Registration on providerId = Registration.userId join Appointment on messageId = bookId ;";
			ResultSet rs = stmt.executeQuery(selectBookQuery);
			while(rs.next())
			{	
                Book book = new Book(rs.getInt("messageId"),rs.getString("CustomerName"),rs.getString("ProviderName"),rs.getString("usertype"), rs.getString("appointmentDate"),rs.getString("appointmentTime"),rs.getString("message"));
				books.add(book);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return books;			
    }
    
    public static ArrayList<Book> viewCustomerBook(int userId)
	{	
		ArrayList<Book> books = new ArrayList<>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectBookQuery="Select messageId, CustomerName, userName as 'ProviderName',usertype, appointmentDate, appointmentTime, message from process inner join Registration on providerId = Registration.userId join Appointment on messageId = bookId where customerId ="+ userId;
			ResultSet rs = stmt.executeQuery(selectBookQuery);
			while(rs.next())
			{	
                Book book = new Book(rs.getInt("messageId"),rs.getString("CustomerName"),rs.getString("ProviderName"),rs.getString("usertype"), rs.getString("appointmentDate"),rs.getString("appointmentTime"),rs.getString("message"));
				books.add(book);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return books;			
    }
    
    public static void removeBook(int id )
	{	
		try
		{	

			getConnection();
			String removeBookQuery = "Delete from message where messageId=? ";	
			PreparedStatement pst = conn.prepareStatement(removeBookQuery);
			pst.setInt(1,id);
            pst.execute();
            
            removeBookQuery = "Delete from Appointment where bookId=? ";	
			PreparedStatement pst2 = conn.prepareStatement(removeBookQuery);
			pst2.setInt(1,id);
			pst2.execute();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}				
	}
    /**************************************************GET 10 Doctors******************************************************/
public static ArrayList<DoctorDetail> getTenDoctor()
{	
	ArrayList<DoctorDetail> list=new ArrayList<DoctorDetail>();

	try 
	{
		getConnection();
		
		Statement stmt=conn.createStatement();
		String getTenDoctorDetails="SELECT * FROM Doctordetails";
		ResultSet rs = stmt.executeQuery(getTenDoctorDetails);
		/*int size =0;
		if (rs != null) {
			System.out.println("!");
			System.out.println("rs is not Null");
			System.out.println("rs has row: " + size);
			rs.last();    // moves cursor to the last row
			size = rs.getRow(); // get row id 
			rs.beforeFirst();
			System.out.println("rs has row1: " + size);
		}*/

		/*public DoctorDetail(int id, int doctorId,
                String realName, String department,
                String password, String usertype,
                String lat, String longt,
                String address, String location,
                String openTime, String closeTime,
                LocalDate postTime) */
		while(rs.next())
		{	
			System.out.println("Wrong");
				DoctorDetail doctorDetail = 
				new DoctorDetail(rs.getInt("postId"),
				rs.getInt("doctorId"),
				rs.getString("realName"),
				rs.getString("department"),
				rs.getString("usertype"),	
				rs.getString("locationLat"),rs.getString("locationLong"),
				rs.getString("address"),rs.getString("location"),rs.getString("openTime"),
				rs.getString("closeTime"),rs.getDate("postTime").toLocalDate());

			list.add(doctorDetail);
		}
	}
	catch(Exception e)
	{
	}
	System.out.println(list.toString());
	System.out.println("list.size in mysqllist = "+list.size());
	
	return list;
}	
}