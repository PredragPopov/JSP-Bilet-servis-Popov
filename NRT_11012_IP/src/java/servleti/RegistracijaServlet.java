
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;;

/**
 *
 * @author POPOV
 */
public class RegistracijaServlet extends HttpServlet {

    String odgovor="<h1>NISTA SE NIJE DESILO</h1>";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistracijaServlet</title>"); 
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
        
        //preuzimanje podataka iz forme sa jsp
        String korisnik=request.getParameter("korisnicko_ime");
        String lozinka=request.getParameter("lozinka");
        String tip=request.getParameter("tip");
        String ime=request.getParameter("ime");
        String prezime=request.getParameter("prezime");
        String adresa=request.getParameter("adresa");
        String telefon=request.getParameter("tel");
        String ePosta=request.getParameter("Eposta");
        
        
        String upisUBazu="";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            
            //provera da li korisnickoIme vec postoji u bazi
            Statement upit=con.createStatement();
            ResultSet rez=upit.executeQuery("SELECT * FROM korisnik WHERE korisnickoIme='"+korisnik+"'");
            if(rez.next()){
                odgovor="<h1>Korisnicko ime koje ste izabrali vec postoji !!! Odaberite drugo ime :)</h1>";
                
            } else{
                
                //upis novih podataka u bazu/tabela=registracija
                Statement upit2=con.createStatement();
                upisUBazu="INSERT INTO registracija VALUES('"+korisnik+"','"+lozinka+"','"+tip+"','"+ime+"','"+prezime+"','"+adresa+"','"+telefon+"','"+ePosta+"')";
                upit2.executeUpdate(upisUBazu);
                odgovor="<p>korisnicko ime: "+korisnik+"</p>";
                //odgovor+="<br>";
                odgovor+="<p>Tip korisnika: "+tip+"</p>";
                //odgovor+="<br>";
                odgovor+="<p>ime: "+ime+"</p>";
                //odgovor+="<br>";
                odgovor+="<p>prezime: "+prezime+"</p>";
                //odgovor+="<br>";
                odgovor+="<p>adresa: "+adresa+"</p>";
                //odgovor+="<br>";
                odgovor+="<p>telefon: "+telefon+"</p>";
                //odgovor+="<br>";
                odgovor+="<p>ePosta: "+ePosta+"</p>";
                odgovor+="<br><hr><br>";
                
                odgovor+="<h3>Uspesno ste se upisali, vasa registracija ceka odobrenje administratora</h3>";
                
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
