
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author POPOV
 */
public class EditKorisnikServlet extends HttpServlet {

    String odgovor="<p>nista se nije desilo</p>";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>EditProfil</title>"); 
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css.css\">");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div id=\"heder\" name=\"heder\">");
            out.println("<h3>Projekat iz predmeta Internet Programiranje</h3>");
            out.println("<h3>REGISTRACIJA</h3>");
            out.println("<a href=\"javascript: history.go(-1)\" id=\"nazad\" name=\"nazad\"><button>Nazad</button></a>");
            out.println("</div>");
            
            out.println("<div id=\"sadrzaj\" name=\"sadrzaj\">");
            out.println(odgovor);
            out.println("<a href='korisnik.jsp'><button>Pocetna strana</button></a>");
            out.println("</div>");
            
            out.println("<div id=\"futer\" name=\"futer\">");
            out.println("<h3><pre>Predrag Popov NRT 110/12      jan.2015.</pre></h3>");
            out.println("</div>");
            
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

        String korisnik=request.getParameter("korisnicko_ime");
        String ime=request.getParameter("ime");
        String prezime=request.getParameter("prezime");
        String adresa=request.getParameter("adresa");
        String tel=request.getParameter("tel");
        String Eposta=request.getParameter("Eposta");
        String faktorXS=request.getParameter("faktorX");
        int faktorX=Integer.parseInt(faktorXS.trim());
        odgovor="<p>IZ FORME korisnik "+korisnik+" ime "+ime+" "+prezime+" adresa "+adresa+" tel "+tel+" ePosta "+Eposta+"</p>";
        try{
            odgovor="<p>TRY</p>";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
            odgovor="<p>KONEKTOVAO SAM SE NA BAZU</p>";
            Statement upit=con.createStatement();
            odgovor="<p>UPDATE korisnik SET ime='"+ime+"',prezime='"+prezime+"',adresa='"+adresa+"',telefon='"+tel+",ePosta='"+Eposta+"' WHERE korisnickoIme='"+korisnik+"'</p>";
            upit.executeUpdate("UPDATE korisnik SET ime='"+ime+"',prezime='"+prezime+"',adresa='"+adresa+"',telefon='"+tel+"',ePosta='"+Eposta+"' WHERE korisnickoIme='"+korisnik+"'");
            odgovor="<h3>Uspesno ste promenili svoj profil</h3>";
            
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
