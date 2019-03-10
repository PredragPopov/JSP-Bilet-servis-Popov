
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import klase.*;
/**
 *
 * @author POPOV
 */
public class RezervisiServlet extends HttpServlet {

    String odgovor="<p>nista se nije desilo</p>";
    String potvrda="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>RezervisiServlet</title>");  
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css.css\">");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div id=\"heder\" name=\"heder\">");
            out.println("<h3>Projekat iz predmeta Internet Programiranje</h3>");
            out.println("<h3>REGISTRACIJA</h3>");
            out.println("<a href=\"javascript: history.go(-1)\" id=\"nazad\" name=\"nazad\"><button>Nazad</button></a>");
            out.println("</div>");
            
            out.println("<div id=\"sadrzaj\" name=\"sadrzaj\">");
            out.println(potvrda);
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
        PrintWriter out=response.getWriter();
        HttpSession sesija=request.getSession(true);
        
        String korisnik=request.getParameter("frmRPkorisnik");
        String nazivD=request.getParameter("frmRPnazivD");
        String mesto=request.getParameter("frmRPmesto");
        String vreme=request.getParameter("frmRPvreme");
        String sifraKat=request.getParameter("frmRPIDkat");
        int idKatRez=Integer.parseInt(sifraKat);
        String brUl=request.getParameter("frmRPbrUlaznica");
        int brojUl=Integer.parseInt(brUl);
        String ukupnaCena=request.getParameter("frmRPukupnaCena");
        String dogadjajID=request.getParameter("dogadjajID");
        
        
        //danasnji dan
        Date vremeRez=new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

        //zaproveru
        odgovor="<p>Iz forme: <p>";
        odgovor+="<p>korisnik "+korisnik+"</p>";
        odgovor+="<p>nazivD "+nazivD+"</p>";
        odgovor+="<p>mesto "+mesto+"</p>";
        odgovor+="<p>vreme "+vreme+"</p>";
        odgovor+="<p>sifra kategorije "+sifraKat+"</p>";
        odgovor+="<p>broj ulaznica "+brUl+"</p>";
        odgovor+="<p>ukupna cena "+ukupnaCena+"</p>";
        odgovor+="<p>trenutno vreme rez: "+vremeRez+"</p><br>";
        odgovor+="<p>FORMATIRANOtrenutno vreme rez: "+ft.format(vremeRez)+"</p><br>";
        
        //upisujem rezervaciju u bazu podataka
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            odgovor+="<p>konektovao sam se na bazu</p>";
            String upitTxt="INSERT INTO rezervacije (korisnik,dogadjajID,dogadjajNaziv,mesto,vremeOdrzavanja,katID,brUl,ukupnaCena,vremeRez,kupljena,istekla) VALUES ('"+korisnik+"','"+dogadjajID+"','"+nazivD+"','"+mesto+"','"+vreme+"','"+sifraKat+"','"+brUl+"','"+ukupnaCena+"','"+ft.format(vremeRez)+"','0','0')";
            
            odgovor+="<h3>SQL upit</h3><br><p>"+upitTxt+"</p>";
            Statement upit=con.createStatement();
            upit.executeUpdate(upitTxt);
            
            potvrda="<p>uspesno ste rezervisali! :D </p>";
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
        odgovor+="<hr>";
        //izcitavam iz tabele rezervacija rezID koji saljem korisniku
        int IDrez=0;
        try{
            odgovor+="<p>usao sam u try </p>";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            odgovor+="<p>konektovao sam se </p>";
            Statement upit2=con2.createStatement();
            odgovor+="<p>SQL: SELECT * FROM rezervacije WHERE korisnik='"+korisnik+"' AND vremeRez='"+ft.format(vremeRez)+"'</p>";
            ResultSet rez2=upit2.executeQuery("SELECT * FROM rezervacije WHERE korisnik='"+korisnik+"' AND vremeRez='"+ft.format(vremeRez)+"'");
            while(rez2.next()){
                IDrez=rez2.getInt("ID");
                potvrda+="<p>SIFRA VASE REZERVACIJE JE: "+IDrez+" </p>";
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
        odgovor+="<hr>";
       
        //treba da nadjem DOGADJAJ za koji je REZ i procitam REZERVISANE
        int pozicija=0;//pozicija u stringu idKat i rezervisane
        int rezUPDATE=0;
        String rezNOVO="";
        try{
            odgovor+="<p>usao sam u try </p>";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con3=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            odgovor+="<p>konektovao sam se na bazu </p>";
            Statement upit3=con3.createStatement();
            odgovor+="<p>SQL UPIT   SELECT * FROM dogadjaj WHERE ID="+dogadjajID+"</p>";
            ResultSet rez3=upit3.executeQuery("SELECT * FROM dogadjaj WHERE ID="+dogadjajID+"");
            while(rez3.next()){
                odgovor+="<p>citam iz tabele</p>";
                String kategorije=rez3.getString("kategorije");
                odgovor+="<p>kategorije "+kategorije+" </p>";
                String[] nizKat=kategorije.split("/");
                
                String rezervisane=rez3.getString("rezervisane");
                odgovor+="<p>rezervisane "+rezervisane+" </p>";
                
                String[] nizRez=rezervisane.split("/");
                
                //u nizu ID kategorija trazim mesto na kome je IDkat za moju rez
                
                for(int i=0;i<nizKat.length;i++){
                    String idSTEK=nizKat[i];
                    int idTEK=Integer.parseInt(idSTEK.trim()); //imam int tekucu ID kategorije
                    if(idTEK==idKatRez){
                        pozicija=i;
                    }
                }
                odgovor+="<p>pozicija "+pozicija+" </p>";
                String rezStaroS=nizRez[pozicija]; //na toj poziciji uzimam broj vec rezervisanih
                int rezStaro=Integer.parseInt(rezStaroS);
                rezUPDATE=rezStaro+brojUl; //na stare rez dodao broj iz tekuce REZ i to upisujem u bazu
                
                
                odgovor+="<p>bilo je "+rezStaro+"rezervacija, + "+brojUl+" = "+rezUPDATE+" </p>";
                
                //pravim String rezervisanih koji upisujem u bazu
                int xx=nizRez.length-1;
                odgovor+="<p>duzina niza je "+xx+" </p>";
                odgovor+="<p>NOVI STRING: "+rezNOVO+" </p>";
                
                if(pozicija==0){ //ako je promena na nultoj poziciji
                    odgovor+="<p>prvi if</p>";
                    rezNOVO=rezUPDATE+"/";
                    odgovor+="<p>NOVI STRING: "+rezNOVO+" </p>";
                    for(int i=1;i<nizRez.length-1;i++){
                      rezNOVO+=nizRez[i]+"/";
                      odgovor+="<p>NOVI STRING: "+rezNOVO+" </p>";
                    }
                    rezNOVO+=nizRez[xx]; //izdvojeno da za poslednju POZ stavi samo broj ne i /
                    odgovor+="<p>NOVI STRING: "+rezNOVO+" </p>";
                    
                } else if(pozicija==xx){ //promena na poslednjoj poziciji
                    odgovor+="<p>drugi if</p>";
                    rezNOVO=nizRez[0]+"/";
                    odgovor+="<p>NOVI STRING: "+rezNOVO+" </p>";
                    for(int i=1;i<nizRez.length-1;i++){
                      rezNOVO+=nizRez[i]+"/";
                      odgovor+="<p>NOVI STRING: "+rezNOVO+" </p>";
                    }
                    rezNOVO+=rezUPDATE;
                    odgovor+="<p>NOVI STRING: "+rezNOVO+" </p>";
                    
                }else{ //ako promena nije na nultoj poziciji ni na poslednjoj
                    odgovor+="<p>treci if</p>";
                    rezNOVO=nizRez[0]+"/";
                    odgovor+="<p>NOVI STRING: "+rezNOVO+" </p>";
                    for(int i=1;i<nizRez.length-1;i++){
                        if(i==pozicija){
                            rezNOVO+=rezUPDATE+"/";
                            odgovor+="<p>NOVI STRING: "+rezNOVO+" </p>";
                        }else{
                            rezNOVO+=nizRez[i]+"/";
                            odgovor+="<p>NOVI STRING: "+rezNOVO+" </p>";
                        }
                    }
                    rezNOVO+=nizRez[xx];
                    odgovor+="<p>NOVI STRING: "+rezNOVO+" </p>";
                }
                
                 odgovor+="<p>novi string: |"+rezNOVO+"| </p>";
                odgovor+="<a href='korisnik.jsp'>pocetna</a>";
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
        
        //UPDATE DOGADJAJU BROJ REZERVISANIH KARATA
        odgovor+="<hr>";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con4=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            Statement upit4=con4.createStatement();
            upit4.executeUpdate("UPDATE dogadjaj SET rezervisane='"+rezNOVO+"' WHERE ID="+dogadjajID+"");
            odgovor+="<p>uspesno ste UPDATE dogadjaj kolona REZERVISANE </p>";
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
