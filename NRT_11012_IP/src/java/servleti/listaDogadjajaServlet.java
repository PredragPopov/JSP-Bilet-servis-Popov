
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


public class listaDogadjajaServlet extends HttpServlet {

    String odgovor="<p>nista se nije desilo</p>";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet listaDogadjajaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listaDogadjajaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println(odgovor);
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

        String tipS=request.getParameter("tip");
        int tip=Integer.parseInt(tipS.trim());
        
        odgovor="<p>procitao iz forme: "+tip+"</p>";

        ArrayList<Dogadjaj> listaD=new ArrayList<Dogadjaj>();

        //citanje podataka iz baze podataka
        try{
            odgovor+="<p>usao sam u TRY</p>";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
            odgovor+="<p>konektovao sam se na bazu</p>";
            Statement upit=con.createStatement();
            odgovor+="<p>SQL UPIT: SELECT * FROM dogadjaj WHERE istekao=0</p>";
            ResultSet rez=upit.executeQuery("SELECT * FROM dogadjaj WHERE istekao=0");
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
                    
                    //konekcija na bazu da se izcitaju kategorije
                    String xMesto="";
                    String xTipSedista="";
                    int xMaxUl=0;
                    int xCenaUl=0;
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
                        Statement upit2=con2.createStatement();
                        ResultSet rez2=upit2.executeQuery("SELECT * FROM kategorije WHERE ID='"+xID+"'");
            
                        while(rez2.next()){
                                xMesto=rez2.getString("mesto");
                                xTipSedista=rez2.getString("tipSedista");
                                xMaxUl=rez2.getInt("maxUl");
                                xCenaUl=rez2.getInt("cenaUl");
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
                    
                    
                    Kategorija kat=new Kategorija();
                    kat.setID(xID);
                    kat.setMesto(xMesto);
                    kat.setTipSedista(xTipSedista);
                    kat.setMaxUl(xMaxUl);
                    kat.setCenaUl(xCenaUl);
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
                
                listaD.add(d);
                
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
        //stavljam listu sa dogadjajima u sesiju
        sesija.setAttribute("lista_dogadjaja", listaD);
        odgovor+="<p>saljem listu dogadjaja: "+listaD+"</p>";

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
