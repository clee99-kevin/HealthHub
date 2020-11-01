import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/Login")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");


		pw.println("<div class='6u'><section><header><h2>Log In</h2></header>");
		if (request.getParameter("correct") != null) {
			pw.print("<h4 style='color:red'>Please check your username, password and user type!</h4>");
		}
		pw.println("<form method='post' action='Home'>");
		pw.println("<table style='width:100%'><tr style='border-bottom:5px '><td><h3>Username</h3></td><td>");
		
		pw.println("<input type='text' name='username' value='' class='input' required></input></td></tr>");
		pw.println("<tr><td><h3>Password</h3></td><td>");
		pw.println("<input type='password' name='password' value='' class='input' required></input></td></tr>");
		pw.println("<tr><td></td>");
		pw.println("<td><input type='submit' name='action' class='btnbuy' value='Login'style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input></td></tr><tr><strong>");
		pw.println("<a class='' href='Signup'style='float: right;height: 20px margin: 20px;'> New User? Registerhere!</a>");
		pw.println("</strong></td></tr>");
		pw.println("</table></form></section></div>");

		utility.printHtml("Footer.html");

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("user").equals("Create")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String usertype = request.getParameter("usertype");
			String lat="";
			String longt="";
			ArrayList<String> res = ApiUtilities.getLatLongPositions(address);
			lat = res.get(0);
			longt = res.get(1);
			// try {
			// 	lat = ApiUtilities.getLatLongPositions(address).get(0);
			// 	longt = ApiUtilities.getLatLongPositions(address).get(1);
			// } catch (Exception e) {
			// 	System.out.println("Not Working: " + e.getMessage() );
			// }

			System.out.println("Create : "+username +":" + password +":"+email+ ":"+address+":" + usertype);
			System.out.println("Lat : " + lat + "  Longt : " + longt);
			MySqlDataStoreUtilities.insertUser( username, password, email, usertype, address, lat, longt, address);
		} else if ( request.getParameter("user").equals("Modify")) {
			int userId = 3;
			String password = request.getParameter("password");
			String address = request.getParameter("address");
			String lat="";
			String longt="";
			ArrayList<String> res = ApiUtilities.getLatLongPositions(address);
			lat = res.get(0);
			longt = res.get(1);
			System.out.println("Update : " + password +":" +address);
			MySqlDataStoreUtilities.updateUser(userId, password, address, lat, longt, address);

		}
		

		doGet(request, response);
	}

}
