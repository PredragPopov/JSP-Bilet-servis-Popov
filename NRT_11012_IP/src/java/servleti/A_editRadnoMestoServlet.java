
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import klase.*;
/**
 *
 * @author POPOV
 */
public class A_editRadnoMestoServlet extends HttpServlet {

    String potvrda="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet A_editKorisnikServlet</title>");  
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css.css\">");
            out.println("</head>");
            out.println("<body>");
            
             out.println("<div id=\"heder\" name=\"heder\">");
            out.println("<h3>Projekat iz predmeta Internet Programiranje</h3>");
            out.println("<h3>ADMIN</h3>");
            out.println("<a href=\"javascript: history.go(-1)\" id=\"nazad\" name=\"nazad\"><button>Nazad</button></a>");
            out.println("</div>");
            
            out.println("<div id=\"sadrzaj\" name=\"sadrzaj\">");
            out.println(potvrda);
            out.println("<a href='A_radnaMestaServlet'><button>Pocetna strana</button></a>");
            out.println("</div>");
            
            out.println("<div id=\"futer\" name=\"futer\">");
            out.println("<h3><pre>Predrag Popov NRT 110/12      jan.2015.</pre></h3>");
            
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
        PrintWriter out=response.getWriter();
        HttpSession sesija=request.getSession(true);
        
        String izborS=request.getParameter("faktorZ");
        int izbor=Integer.parseInt(izborS.trim());
        
        String ID=request.getParameter("rmID");
        
        String SQLupit="";
        String mestoS="";
        if(izbor==1){ //obrisi
            SQLupit="DELETE FROM radnomesto WHERE sifraMesta='"+ID+"'";
        }else if(izbor==2){ //dodaj novi
            
            String mesto=request.getParameter("mesto");
            String adresa=request.getParameter("adresa");
            String grad=request.getParameter("grad");
            mestoS=mesto;
            SQLupit="INSERT INTO radnomesto(mesto,adresa,grad) VALUES('"+mesto+"','"+adresa+"','"+grad+"')";
        }
        
        try{
                //odgovor+="<p>usao u bitan TRY</p>";
                Class.forName("com.mysql.jdbc.Driver");
                Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
                Statement upit2=con2.createStatement();
                upit2.executeUpdate(SQLupit);
                 //odgovor+="<p>izvrsio UPDATE</p>";
                if(izbor==1){
                    potvrda="<h1>Uspesno obrisano radno mesto ID="+ID+"</h1>";
                }else if(izbor==2){
                    potvrda="<h1>Uspesno dodato novo radno mesto: "+mestoS+"</h1>";
                }
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
        
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
