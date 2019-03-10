
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
import klase.Registracija;
/**
 *
 * @author POPOV
 */
public class A_reg2Servlet extends HttpServlet {

    String odgovor="<p>nista se nije desilo</p>";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet A_reg2Servlet</title>"); 
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css.css\">");
            out.println("</head>");
            out.println("<body>");
            
             out.println("<div id=\"heder\" name=\"heder\">");
            out.println("<h3>Projekat iz predmeta Internet Programiranje</h3>");
            out.println("<h3>ADMIN</h3>");
            out.println("<a href=\"javascript: history.go(-1)\" id=\"nazad\" name=\"nazad\"><button>Nazad</button></a>");
            out.println("</div>");
            
            out.println("<div id=\"sadrzaj\" name=\"sadrzaj\">");
            out.println(odgovor);
            out.println("<a href='admin.jsp'><button>Pocetna strana</button></a>");
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
        
        String izborS=request.getParameter("izbor");
        int izbor=Integer.parseInt(izborS.trim());
        String korisnickoIme=request.getParameter("korisnickoIme");
                String lozinka=request.getParameter("lozinka");
                String tip=request.getParameter("tip");
                String sifraMesta=request.getParameter("sifraMesta");
                String ime=request.getParameter("ime");
                String prezime=request.getParameter("prezime");
                String adresa=request.getParameter("adresa");
                String telefon=request.getParameter("telefon");
                String ePosta=request.getParameter("ePosta");
        odgovor="<p>podaci iz forme: |"+korisnickoIme+"| |"+lozinka+"| |"+tip+"| |"+sifraMesta+"| |"+ime+"| |"+prezime+"| |"+adresa+"| |"+telefon+"| |"+ePosta+"| </p><hr>";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            Statement upit1=con1.createStatement();
            odgovor+="<p>SQL UPIT : DELETE FROM registracija WHERE korisnickoIme='"+korisnickoIme+"'</p><hr>";
            upit1.executeUpdate("DELETE FROM registracija WHERE korisnickoIme='"+korisnickoIme+"'");
            odgovor+="<p> uspesno ste obrisali registraciju</p><hr>";
  
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
        
        if(izbor==0){
            odgovor="<p> uspesno ste odbili korisnika: "+korisnickoIme+"</p>";
        } else if(izbor==1){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
                Statement upit2=con2.createStatement();
                odgovor+="<p>SQL UPIT : \"INSERT INTO korisnik(korisnickoIme,lozinka,tip,sifraMesta,ime,prezime,adresa,telefon,ePosta) VALUES('"+korisnickoIme+"','"+lozinka+"','"+tip+"','"+sifraMesta+"','"+ime+"','"+prezime+"','"+adresa+"','"+telefon+"','"+ePosta+"')</p><hr>";
                upit2.executeUpdate("INSERT INTO korisnik(korisnickoIme,lozinka,tip,sifraMesta,ime,prezime,adresa,telefon,ePosta) VALUES('"+korisnickoIme+"','"+lozinka+"','"+tip+"','"+sifraMesta+"','"+ime+"','"+prezime+"','"+adresa+"','"+telefon+"','"+ePosta+"')" );
            
  
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
            odgovor="<p>uspesno ste dodali korisnikaL "+korisnickoIme+" u tebelu KORISNIK</p>";
        }
        
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
