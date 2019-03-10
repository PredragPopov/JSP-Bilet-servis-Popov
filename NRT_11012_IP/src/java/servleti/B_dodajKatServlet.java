
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import klase.*;
/**
 *
 * @author POPOV
 */
public class B_dodajKatServlet extends HttpServlet {

    String odgovor="<p>nista</p>";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet B_dodajKatServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet B_dodajKatServlet at " + request.getContextPath() + "</h1>");
            out.println(odgovor);
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesija=request.getSession(true);
        
        String mesto=request.getParameter("noviKmesto");
        String tipSed=request.getParameter("noviKtipS");
        String maxUl=request.getParameter("noviKmaxUl");
        String cenaUl=request.getParameter("noviKcenaUl");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
            Statement upit=con.createStatement();
            odgovor+="<p>SQL UPIT:  INSERT INTO kategorije(mesto,tipSedista,maxUl,cenaUl) VALUES('"+mesto+"','"+tipSed+"','"+maxUl+"','"+cenaUl+"')</p>";
            upit.executeUpdate("INSERT INTO kategorije(mesto,tipSedista,maxUl,cenaUl) VALUES('"+mesto.trim()+"','"+tipSed+"','"+maxUl.trim()+"','"+cenaUl.trim()+"')");
            odgovor+="<p>uspesno dodato</p>";
        }catch(SQLException e){
            //out.println("Greska: " + e);
            sesija.setAttribute("greska",e.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("greske.jsp");
            dispatcher.forward(request, response);
        }catch(Exception e){
            //out.println("Greska: " + e);
            sesija.setAttribute("greska",e.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("greske.jsp");
            dispatcher.forward(request, response);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("B_citajKategorijeServlet");
        dispatcher.forward(request, response);
        
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
