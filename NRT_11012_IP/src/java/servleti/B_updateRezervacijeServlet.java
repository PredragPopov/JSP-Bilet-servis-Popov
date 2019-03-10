
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import klase.*;
/**
 *
 * @author POPOV
 */
public class B_updateRezervacijeServlet extends HttpServlet {

    String odgovor="<p>nista</p>";
    String potvrda="";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet B_updateRezervacijeServlet</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css.css\">");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div id=\"heder\" name=\"heder\">");
            out.println("<h3>Projekat iz predmeta Internet Programiranje</h3>");
            out.println("<h3>UPDATE DATABASE</h3>");
            out.println("<a href=\"javascript: history.go(-1)\" id=\"nazad\" name=\"nazad\"><button>Nazad</button></a>");
            out.println("</div>");
            
            out.println("<div id=\"sadrzaj\" name=\"sadrzaj\">");
            out.println(potvrda);
            out.println("<a href='blagajnik.jsp'><button>Pocetna strana</button></a>");
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
        
        java.util.Date danas=new java.util.Date();
        odgovor="<p>danasnje vreme: "+danas+"</p><hr>";
        
        ArrayList<Integer> nizIDrez=new ArrayList<Integer>();
        ArrayList<String> nizKorisnik=new ArrayList<String>();
        //izcitavam i pamtim samo ID za istekle rez i korisnickaImena za te rez
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            
            Statement upit=con.createStatement();
            ResultSet rez=upit.executeQuery("SELECT * FROM rezervacije WHERE kupljena=0 AND istekla=0");
            
            while(rez.next()){
                int ID=rez.getInt("ID");
                String korisnik=rez.getString("korisnik");
                Date vremeOdrzavanja=rez.getTimestamp("vremeOdrzavanja");
                Date vremeRez=rez.getTimestamp("vremeRez");
                
                odgovor+="<p>procitao sam iz baze: ID |"+ID+"| korisnik: |"+korisnik+"| vremeOdrzavanja=|"+vremeOdrzavanja+"| vremeRez: |"+vremeRez+"|</p>";
                
                Date vremeRez48h=new Date();
                int x=vremeRez.getDate();
                int y=x+2;
                vremeRez48h=vremeRez;
                vremeRez48h.setDate(y);
                odgovor+="<p> vreme rez + 2 dana je: |"+vremeRez48h+"|</p>";
                
                boolean isteklaRez=vremeRez48h.before(danas);
                odgovor+="<p>da li je istekla rez (vremeRez+2dana)before danas: <h3>"+isteklaRez+"</h3></p>";
                
                boolean istekaoD=vremeOdrzavanja.before(danas);
                odgovor+="<p>da li je prosao dogadjaj: <h3>"+istekaoD+"</h3></p><hr>";
                
                
                
                if(istekaoD==true){
                    nizIDrez.add(ID);
                }else if(isteklaRez==true){
                    potvrda+="<p>Istekla rezervacija ID= "+ID+" korisnika: "+korisnik+"</p>";
                    nizIDrez.add(ID);
                    nizKorisnik.add(korisnik);
                }
                
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
        
        odgovor+="<p>izdvojio sam ID u niz</p>";
        for(int i=0;i<nizIDrez.size();i++){
            odgovor+="<p>"+nizIDrez.get(i)+"</p>";
        }
        
        odgovor+="<hr>";
        
        odgovor+="<p>izdvojio sam korisnike u niz</p>";
        for(int i=0;i<nizKorisnik.size();i++){
            odgovor+="<p>"+nizKorisnik.get(i)+"</p>";
        }
        
        odgovor+="<hr>";
        
        //setujem sve rezervacije sa zapamcenim ID da su istekle
        int z1=nizIDrez.size();
        if(z1>0){
            for(int i=0;i<nizIDrez.size();i++){
            int ovajID=nizIDrez.get(i);
                try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            
                Statement upit=con.createStatement();
                upit.executeUpdate("UPDATE rezervacije SET istekla=1 WHERE ID="+ovajID+"");
                potvrda+="<h3>uspesno ste UPDATE-ovali rezervaciju sa ID= "+ovajID+"</p><hr>";

                }catch(SQLException e){
                     out.println("Greska: " + e);
                }catch(Exception e){
                    out.println("Greska: " + e);
                }  
            
            }
        }else{
            potvrda+="<p>NEMA REZERVACIJA KOJE TREBA SETOVATI DA SU ISTEKLE</p><hr>";
        }
        
        odgovor+="<p>izdvojio sam korisnike u niz</p>";
        potvrda+="<p>Korisnici koje treba blokirati: ";
        for(int i=0;i<nizKorisnik.size();i++){
            odgovor+="<p>"+nizKorisnik.get(i)+"</p>";
            potvrda+="<p>"+nizKorisnik.get(i)+"</p>";
        }
        potvrda+="<hr>";
        
        
        // RAD SA KORISNICIMA
        // prvo cita korisnike koji nisu vec blokirani, menja im broj isteklih rezervacija, a ako je veci od 3 blokira ih
        int z2=nizKorisnik.size();
        if(z2>0){
            odgovor+="<p>usao sam u setovanje korisnika</p>";
            for(int i=0;i<nizKorisnik.size();i++){
                String korisnickoIme=nizKorisnik.get(i);
                int istekleRez=0;
                //prvo cita vec postojeci broj losih rez
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            
                        Statement upit=con.createStatement();
                        ResultSet rez=upit.executeQuery("SELECT * FROM korisnik WHERE korisnickoIme='"+korisnickoIme+"' AND blokiran=0");
                        while(rez.next()){
                            istekleRez=rez.getInt("istekleRez");
                            istekleRez+=1;
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
                    odgovor+="<hr><p>za korisnika= "+korisnickoIme+" istekle rez="+istekleRez+"</p><hr>" ;
                //setuje samo promenu broja isteklih rezervacija
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
                        
                        Statement upit=con.createStatement();
                        upit.executeUpdate("UPDATE korisnik SET istekleRez="+istekleRez+" WHERE korisnickoIme='"+korisnickoIme+"'");
                        odgovor+="<h3>uspesno ste UPDATE-ovali istekleRez="+istekleRez+" za korisnickoIme= "+korisnickoIme+"</p><hr>";

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
                    
                //ako korisnik ima vise od 2 istekleRez setovati da je BLOKIRAN
                if(istekleRez>2){
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            
                        Statement upit=con.createStatement();
                        upit.executeUpdate("UPDATE korisnik SET blokiran=1 WHERE korisnickoIme='"+korisnickoIme+"'");
                        potvrda+="<h3>uspesno ste blokirali  korisnika= "+korisnickoIme+"</p><hr>";

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
                
                
            }
        }else{
            potvrda+="<p>NEMA KORISNIKA KOJE TREBA KAZNITI</p>";
        }
        
        odgovor+="<a href=\"javascript: history.go(-1)\">OK</a>";
        
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
