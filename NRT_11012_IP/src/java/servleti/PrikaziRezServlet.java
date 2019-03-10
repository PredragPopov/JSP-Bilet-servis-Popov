
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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
public class PrikaziRezServlet extends HttpServlet {

    String odgovor="<p>taj sam</p>";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PrikaziRezServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PrikaziRezServlet at " + request.getContextPath() + "</h1>");
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
        odgovor+="<h3>POST</h3>";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesija=request.getSession(true);
        
        String tipS=request.getParameter("faktorX");
        int tip=Integer.parseInt(tipS.trim());
        odgovor="<br><p>tip: "+tip+"</p>";
        
        Korisnik k=(Korisnik) sesija.getAttribute("korisnik");
        String korisnikovoIme=k.getKorisnickoIme();
        odgovor="<br><p>korisnik: "+korisnikovoIme+"</p>";
        
        int IDrezProdaj=0;
        if(tip==2){
            String xxz=request.getParameter("rezID");
            
            IDrezProdaj=Integer.parseInt(xxz.trim());
            odgovor="<br><p>tip: "+tip+"</p>";
            
        }
        //za blagajnika prodaja rez kada je pogresna sifra
        boolean greskaID=true;
        
        try{
            
            //konekcija na bazu i izcitavanje svih dogadjaja koji nisu istekli
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
            odgovor+="<br><p>konektovao sam se na bazu</p>";
            Statement upit=con.createStatement();
            String SQLupit="";
            if(tip==1){
                SQLupit="SELECT * FROM rezervacije WHERE korisnik='"+korisnikovoIme+"'";
            }else if(tip==2){
                SQLupit="SELECT * FROM rezervacije WHERE ID="+IDrezProdaj+"";
            }
            ResultSet rez=upit.executeQuery(SQLupit);
            
            odgovor+="<br><p>SQL UPIT: SELECT * FROM rezervacije WHERE korisnik='"+korisnikovoIme+"'</p>";
            ArrayList<Rezervacija> listaR=new ArrayList<Rezervacija>();
            
            while(rez.next()){
                greskaID=false;
                odgovor+="<p>citam podatke iz tabele</p>";
                int ID=rez.getInt("ID");
                odgovor+="<b>dogadjajID "+ID+" </b><br>";
                String korisnik=rez.getString("korisnik");
                odgovor+="<b>korisnik "+korisnik+" </b><br>";
                int dogadjajID=rez.getInt("dogadjajID");
                odgovor+="<b>dogadjajID "+dogadjajID+" </b><br>";
                String dogadjajNaziv=rez.getString("dogadjajNaziv");
                odgovor+="<b>dogadjajNaziv "+dogadjajNaziv+" </b><br>";
                String mesto=rez.getString("mesto");
                odgovor+="<b>mesto "+mesto+" </b><br>";
                Date vremeOdrzavanja=rez.getTimestamp("vremeOdrzavanja");
                odgovor+="<b>vremeOdrzavanja "+vremeOdrzavanja+" </b><br>";
                int katID=rez.getInt("katID");
                odgovor+="<b>katID "+katID+" </b><br>";
                
                int brojUl=rez.getInt("brUl");
                odgovor+="<b>brojUl "+brojUl+" </b><br>";
                int ukupnaCena=rez.getInt("ukupnaCena");
                odgovor+="<b>ukupnaCena "+ukupnaCena+" </b><br>";
                Date vremeRez=rez.getTimestamp("vremeRez");
                odgovor+="<b>vremeRez "+vremeRez+" </b><br>";
                boolean kupljena=rez.getBoolean("kupljena");
                odgovor+="<b>kupljena "+kupljena+" </b><br>";
                boolean istekla=rez.getBoolean("istekla");
                odgovor+="<b>istekla "+istekla+" </b><br>";
                
                String tipKat="";
                int cenaUl=0;
                // preko katID pristupam ID tabele KATEGORIJE i kupim podatke za sedista
                try{
                    odgovor+="<p>TRY 2</p>";
                Class.forName("com.mysql.jdbc.Driver");
                Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
                odgovor+="<p>KONEKTOVAO 2</p>";
                Statement upit2=con2.createStatement();
                odgovor+="<p>SQL 2 : SELECT * FROM kategorije WHERE ID='"+katID+"'</p>";
                ResultSet rez2=upit2.executeQuery("SELECT * FROM kategorije WHERE ID='"+katID+"'");
                while(rez2.next()){
                    odgovor+="<p>citam podatke 2</p>";
                    tipKat=rez2.getString("tipSedista");
                    cenaUl=rez2.getInt("cenaUl");
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
                
                Rezervacija rezervacija=new Rezervacija();
                odgovor+="<p>napravio objekat REZERVACIJE</p>";
                rezervacija.setID(ID);
                rezervacija.setKorisnik(korisnik);
                rezervacija.setDogadjajID(dogadjajID);
                rezervacija.setDogadjajNaziv(dogadjajNaziv);
                rezervacija.setMesto(mesto);
                rezervacija.setVremeOdrzavanja(vremeOdrzavanja);
                rezervacija.setKatID(katID);
                
                rezervacija.setTipKat(tipKat);
                rezervacija.setCenaUl(cenaUl);
                rezervacija.setBrojUl(brojUl);
                rezervacija.setUkupnaCena(ukupnaCena);
                rezervacija.setVremeRez(vremeRez);
                rezervacija.setKupljena(kupljena);
                rezervacija.setIstekla(istekla);
                odgovor+="<p>"+rezervacija+"</p>";
                
                if(tip==2){ //tip=2 je blagajnik, znaci trazena je samo ova jedna REZ i samo nju saljem
                    
                    
                        sesija.setAttribute("rezervacija",rezervacija);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("B_rezervacija.jsp");
                        dispatcher.forward(request, response);   
                    
                }
                
                
                listaR.add(rezervacija);
                odgovor+="<p>doda sam ga u listu</p><hr>";
            }
            if(greskaID==true){
                        sesija.setAttribute("greska","Ne postoji rezervacija sa unetom sifrom!");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("greske.jsp");
                        dispatcher.forward(request, response);
                    }

            request.setAttribute("listaRez", listaR);
            odgovor+="<p>lista rezervacija u sesiji: "+listaR+"</p>";
            RequestDispatcher dispatcher = request.getRequestDispatcher("mojeRezervacije.jsp");
            dispatcher.forward(request, response);
                    
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
