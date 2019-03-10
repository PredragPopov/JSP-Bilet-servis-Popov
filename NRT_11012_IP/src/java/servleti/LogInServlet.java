
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

import klase.*;

/**
 *
 * @author POPOV
 */
public class LogInServlet extends HttpServlet {

    String odgovor="";
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
            out.println("<h3>LOG IN</h3>");
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
        PrintWriter out = response.getWriter();
        
        HttpSession sesija=request.getSession(true);
        
        //uzimanje podataka iz forme
        String formaKorisnik=request.getParameter("korisnickoIme");
        String formaLozinka=request.getParameter("lozinka");
        odgovor+="<p>iz forme korisnik "+formaKorisnik+"</p>";
        odgovor+="<p>lozinka "+formaLozinka+"</p>";
        String sledeca="";
        boolean logovan=false;
        try{
            //konekcija na bazu
            odgovor+="<p>usao sam u TRY</p>";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
            odgovor+="<p>konektovao sam se na bazu</p>";
            Statement st=con.createStatement();
            odgovor+="<p>SQL UPIT: SELECT * FROM korisnik WHERE korisnickoIme='"+formaKorisnik+"' and lozinka='"+formaLozinka+"' and blokiran=0</p>";
            ResultSet rs=st.executeQuery("SELECT * FROM korisnik WHERE korisnickoIme='"+formaKorisnik+"' and lozinka='"+formaLozinka+"' and blokiran=0");
            boolean nadjen=false;
            
            //izcitavanje podataka iz tabele u bazi
            while(rs.next()){
                String korisnickoIme=rs.getString("korisnickoIme");
                String lozinka=rs.getString("lozinka");
                String tip=rs.getString("tip");
                int sifraMesta=rs.getInt("sifraMesta");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
                String adresa=rs.getString("adresa");
                String telefon=rs.getString("telefon");
                String ePosta=rs.getString("ePosta");
                int istekleRez=rs.getInt("istekleRez");
                // BOOLEAN BLOKIRAN TREBA DA IDE U UPIT KAO USLOV !!!
                
                //popunjavamo objekat Korisnik//
                Korisnik k=new Korisnik();
                k.setKorisnickoIme(korisnickoIme);
                k.setLozinka(lozinka);
                k.setTip(tip);
                k.setSifraMesta(sifraMesta);
                k.setIme(ime);
                k.setPrezime(prezime);
                k.setAdresa(adresa);
                k.setTelefon(telefon);
                k.setePosta(ePosta);
                k.setIstekleRez(istekleRez);
                
                odgovor+="<p>korisnik: "+k+"</p>";
                // DA LI U SESIJU STAVLJATI CELI BEAN OBJEKAT KLASE, ILI DEO PO DEO ?????? ///
                
                if(korisnickoIme.equals(formaKorisnik) && lozinka.equalsIgnoreCase(formaLozinka)){
                    odgovor+="<p>dobrodosli "+korisnickoIme+" tip: "+tip+"</p>";
                    nadjen=true;
                    
                    //na osnovu tipa korisnika prosledjujemo na odgovarajuci JSP
                    switch(tip){
                        case "korisnik":
                            sesija.setAttribute("korisnik", k);
                            int xyz=1;
                            sesija.setAttribute("tip",xyz);
                            sledeca="korisnik.jsp";
                            break;
                            
                        case "blagajnik":
                            sesija.setAttribute("korisnik", k);
                            sledeca="blagajnik.jsp";
                            break;
                            
                        case "admin":
                            sesija.setAttribute("korisnik", k);
                            odgovor="<h3>CASE ADMIN</h3>";
                            sledeca="A_radnaMestaServlet";
                            break;
                    }
                RequestDispatcher dispatcher = request.getRequestDispatcher(sledeca);
                dispatcher.forward(request, response);    
                } 
            }
            
            //ako nema podudaranja u bazi nije registrovan ili ceka na odobrenje admina
            if(nadjen==false){
               odgovor="<h3>niste registrovani! Molimo vas popunite formular za registraciju. </h3>";
               
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
