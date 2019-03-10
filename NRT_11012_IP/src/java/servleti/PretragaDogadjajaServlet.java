
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
public class PretragaDogadjajaServlet extends HttpServlet {

    String odgovor="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PretragaDogadjajaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PretragaDogadjajaServlet at " + request.getContextPath() + "</h1>");
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
        PrintWriter out=response.getWriter();
        
        HttpSession sesija=request.getSession(true);
        
        //preuzimanje podataka iz forme sa jsp
        String izbor=request.getParameter("izbor");
        
        String tipS=request.getParameter("faktorX");
        int tip=Integer.parseInt(tipS.trim());
        
        // upit se razlikuje po izboru pretrage:
        String upit="";
        
        
        //String poDatumu="";
        switch(izbor){
            case "poNazivu":{
                String poNazivu=request.getParameter("FPnaziv");
                upit="SELECT * FROM dogadjaj WHERE naziv='"+poNazivu+"'";
                break;
            }
            case "poDatumu":{
                String poDatumu=request.getParameter("FPdatum");
                upit="SELECT * FROM dogadjaj WHERE vremeOdrzavanja='"+poDatumu+"'";
                break;
            }
            case "poMestu":{
                String poMestu=request.getParameter("FPmesto");
                upit="SELECT * FROM dogadjaj WHERE mesto='"+poMestu+"'";
                break;
            }
        }
        
        ArrayList<Dogadjaj> listaRez=new ArrayList<Dogadjaj>();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            Statement st=con.createStatement();
            ResultSet rez=st.executeQuery(upit);
            
            odgovor="<br><p>konektovao sam se na bazu</p>";
            //odgovor+="<br><p>pretrazujem po nazivu: "+poNazivu+"</p>";
            odgovor+="<br><p>upit: "+upit+"</p>";
            
            
            
            while(rez.next()){
                odgovor+="<br><hr><p>nov rez iz tabele</p><br>";
                int id=rez.getInt("ID");
                String naziv=rez.getString("naziv");
                String mesto=rez.getString("mesto");
                Date vremeOdrzavanja=rez.getTimestamp("vremeOdrzavanja");
                String opis=rez.getString("opis");
                String datoteka=rez.getString("datoteka");
                String kategorije=rez.getString("kategorije");
                String prodate=rez.getString("prodate");
                String rezervisane=rez.getString("rezervisane");
                int maxUl=rez.getInt("maxUl");
                odgovor+="<p> iz baze: id= "+id+" naziv= "+naziv+" mesto= "+mesto+" vreme= "+vremeOdrzavanja+" </p>";
                
                
                Dogadjaj d=new Dogadjaj();
                d.setID(id);
                d.setNaziv(naziv);
                d.setMesto(mesto);
                d.setVremeOdrzavanja(vremeOdrzavanja);
                d.setOpis(opis);
                d.setDatoteka(datoteka);
                
                ArrayList<Kategorija> listaKat=new ArrayList<Kategorija>();
                int prodateTEMP=0;
                int rezervisaneTEMP=0;
                
                //rastavljam stringove u brojeve i upisujem u dogadjaj.listaKategorija
                odgovor+="<p>kategorije string: "+kategorije+"</p>";
                odgovor+="<p>prodate string: "+prodate+"</p>";
                odgovor+="<p>rezervisane string: "+rezervisane+"</p>";
                String[] nizK=kategorije.split("/");
                String[] nizP=prodate.split("/");
                String[] nizR=rezervisane.split("/");
                
                int x=nizK.length;
                
                for(int i=0;i<x;i++){
                    String xIDS=nizK[i].trim();
                    int xID=Integer.parseInt(xIDS);
                    
                    String xPS=nizP[i].trim();
                    int xP=Integer.parseInt(xPS);
                    prodateTEMP+=xP;
                    
                    String xRS=nizR[i].trim();
                    int xR=Integer.parseInt(xRS);
                    rezervisaneTEMP+=xR;
                    
                    Kategorija kat=new Kategorija();
                    kat.setID(xID);
                    kat.setProdate(xP);
                    kat.setRezervisane(xR);
                    
                    listaKat.add(kat);
                    
                }
                odgovor+="<p>napravio sam listu kategorija: "+listaKat+"</p>";
                d.setListaKat(listaKat);
                
                //racunam koliko je preostalo ulaznica
                odgovor+="<p>prodate: "+prodateTEMP+" rezervisane: "+rezervisaneTEMP+" </p>";
                int preostale=maxUl-(prodateTEMP+rezervisaneTEMP);
                d.setPreostale(preostale);
                odgovor+="<p>preostale: "+preostale+"</p>";
                
                //racunam da li je ostalo 48h do odrzavanja dogadjaja
                Date danas=new Date();
                int dan1=danas.getDate();
                //uvecam dan za 2 sto je 48h
                int dan2=dan1+2;                
                //setujem moj objekat trenutnog vremena za +48h
                danas.setDate(dan2);
                
                boolean moze=danas.before(vremeOdrzavanja);
                
                d.setMogucaRez(moze);
                
                //dodavanje tekuceg dogadjaja u listu dogadjaja  
                
                listaRez.add(d);
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
        
        //stavljam u sesiju tip korisnika koji sam dobio iz forme request-a
        sesija.setAttribute("tip", tip);
        odgovor+="<p>saljem tip: "+tip+"</p>";
        
        //ubacuje ArrayList u sesiju da bi prosledio u .jsp
            request.setAttribute("lista_dogadjaja", listaRez);

            String adresa="listaDogadjaja.jsp";
            if(tip==0){
                adresa="gost.jsp";
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(adresa);
            dispatcher.forward(request, response);
            
            
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
