
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import klase.*;

/**
 *
 * @author POPOV
 */
public class PromenaLozinkeServlet extends HttpServlet {

    String odgovor="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PromenaLozinkeServlet</title>");
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
            out.println("<a href='POCETNA.jsp'><button>Pocetna strana</button></a>");
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
        PrintWriter out=response.getWriter();
        
        HttpSession sesija=request.getSession(true);
        
        Korisnik korisnik=(Korisnik) sesija.getAttribute("korisnik");
        
        odgovor="<p>SESIJA korisnicko ime :"+korisnik.getKorisnickoIme()+"</p><br>";
        String korisnickoIme=korisnik.getKorisnickoIme();
        odgovor+="<p>SESIJA lozinka:"+korisnik.getLozinka()+"</p>";
        odgovor+="<br>";
                
        String staraLozinka=request.getParameter("staraLozinka");
        String novaLozinka=request.getParameter("novaLozinka");
        
        odgovor+="<p>FORMA stara lozinka"+staraLozinka+"</p><br>";
        odgovor+="<p>FORMA nova lozinka"+novaLozinka+"</p><br>";
        
        boolean uslov=novaLozinka.equals(staraLozinka);
        odgovor+="<p>"+uslov+"</p>";
        
        if(uslov==false){
            odgovor+="<p>promenicu lozinke</p><br>";
            
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
            Statement upit=con.createStatement();
            odgovor+="<br>";
            odgovor+="<p>konektovao sam se na bazu</p><br>";
            //upit za promenu lozinka UPDATE
            
            upit.executeUpdate("UPDATE korisnik SET lozinka='"+novaLozinka+"' WHERE korisnickoIme='"+korisnickoIme+"'");
            
            odgovor="<p>vasa lozinka je uspesno promenjena</p><br>";
            sesija.removeAttribute("korisnik");
            odgovor+="<p>vratite se na LogIn stranu i ulogujte se sa novom lozinkom </p>";
            
            
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
            
        } else {
            odgovor="<p>GRESKA, uneli ste iste lozinke!!!</p><br>";
            odgovor+="<a href='promenaLozinke.jsp'>vrati se na formu za promenu lozinke</a>";
        }
        
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
