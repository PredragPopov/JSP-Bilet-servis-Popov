
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author POPOV
 */
public class A_editKorisnikServlet extends HttpServlet {

    String potvrda="";
    String odgovor="<p>nista</p>";
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
        
        String korisnickoIme=request.getParameter("korisnicko_ime");
        odgovor="<p> izbor="+izbor+" korisnikIme="+korisnickoIme+"</p>";
        
        String SQLupit="";
        boolean postoji=false;
        if(izbor==1){
            odgovor+="<p>usao u izbor 1</p>";
            String lozinka=request.getParameter("lozinka");
            String tip=request.getParameter("tip");
            String sifraMesta=request.getParameter("sifraMesta");
            String ime=request.getParameter("ime");
            String prezime=request.getParameter("prezime");
            String adresa=request.getParameter("adresa");
            String telefon=request.getParameter("tel");
            String ePosta=request.getParameter("Eposta");
                
            SQLupit="INSERT INTO korisnik(korisnickoIme,lozinka,tip,sifraMesta,ime,prezime,adresa,telefon,ePosta) VALUES('"+korisnickoIme+"','"+lozinka+"','"+tip+"','"+sifraMesta+"','"+ime+"','"+prezime+"','"+adresa+"','"+telefon+"','"+ePosta+"')";
                odgovor+="<p>SQLupit="+SQLupit+"</p>";
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
                odgovor+="<p>provera da li korisnik postoji</p>";
                //provera da li korisnickoIme vec postoji u bazi
                Statement upit=con.createStatement();
                ResultSet rez=upit.executeQuery("SELECT * FROM korisnik WHERE korisnickoIme='"+korisnickoIme+"'");
                    if(rez.next()){
                    potvrda="<h1>Korisnicko ime koje ste izabrali vec postoji !!! Odaberite drugo ime :)</h1>";
                    postoji=true;
                    odgovor+="<p>"+postoji+"</p>";
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
            
            
            
        }else if(izbor==2){
            odgovor+="<p>izbor 2</p>";
            odgovor+="<p>SQLupit="+SQLupit+"</p>";
            SQLupit="DELETE FROM korisnik WHERE korisnickoIme='"+korisnickoIme+"'";
        }
        
        if(postoji==false){
            try{
                odgovor+="<p>usao u bitan TRY</p>";
                Class.forName("com.mysql.jdbc.Driver");
                Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
                Statement upit2=con2.createStatement();
                upit2.executeUpdate(SQLupit);
                 odgovor+="<p>izvrsio UPDATE</p>";
                if(izbor==1){
                    potvrda="<h1>Uspesno dodat korisnik: "+korisnickoIme+"</h1>";
                }else if(izbor==2){
                    potvrda="<h1>Uspesno obrisan korisnik: "+korisnickoIme+"</h1>";
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
        }
        
        
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
