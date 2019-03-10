
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
public class B_dodajDServlet extends HttpServlet {

    String odgovor="<p>nista</p>";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>DodajDogadjajServlet</title>"); 
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css.css\">");
            out.println("</head>");
            out.println("<body>");
            
            
            out.println("<div id=\"heder\" name=\"heder\">");
            out.println("<h3>Projekat iz predmeta Internet Programiranje</h3>");
            out.println("<h3>BLAGAJNIK</h3>");
            out.println("<a href=\"javascript: history.go(-1)\" id=\"nazad\" name=\"nazad\"><button>Nazad</button></a>");
            out.println("</div>");
            
             out.println("<div id=\"sadrzaj\" name=\"sadrzaj\">");
            out.println(odgovor);
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
        PrintWriter out = response.getWriter();
        HttpSession sesija=request.getSession(true);
        
        String naziv=request.getParameter("naziv");
        String mesto=request.getParameter("mesto");
        String vremeOdrzavanja=request.getParameter("vremeOdrzavanja");
        String opis=request.getParameter("opis");
        String datoteka=request.getParameter("datoteka");
        
        //pravi string koji sadrzi IDkategorija u formatu za bazu
        String[] odabrani=request.getParameterValues("check");
        String tmp="";
        String prodateTMP="";
        
        for(int i=0;i<odabrani.length;i++){
            tmp+=odabrani[i].trim()+"/";
            prodateTMP+="0/";
        odgovor+="<p> string values:" +odabrani[i]+" </p><br>";
        }
        
        String katIDS="";
        String prodate="";
        for(int i=0;i<tmp.length()-1;i++){
            if(tmp.charAt(i)!=' '){
                katIDS+=tmp.charAt(i);
            }
        }
        for(int i=0;i<prodateTMP.length()-1;i++){
            prodate+=prodateTMP.charAt(i);
        }
       
        
        odgovor+="<p>iz forme:"+naziv+" "+mesto+" "+vremeOdrzavanja+" "+opis+" "+datoteka+" kategorije: |"+tmp+"| kategorije: |"+katIDS+"| prodate: |"+prodateTMP+"| prodate: |"+prodate+"|</p>";
        
        //za KATEGORIJE imam MESTO preko njega da uzmem sve maxUl i saberem u maxUl za DOGADJAJ
        int maxUlD=0;
        
         try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
            Statement upit=con.createStatement();
            ResultSet rez=upit.executeQuery("SELECT * FROM kategorije WHERE mesto='"+mesto.trim()+"'");
            
            while(rez.next()){
                int IDtek=rez.getInt("ID");
                int brojMesta=rez.getInt("maxUl");
                for(int i=0;i<odabrani.length;i++){
                    String z=odabrani[i].trim();
                    int y=Integer.parseInt(z);
                    if(IDtek==y){
                        maxUlD+=brojMesta;
                        odgovor+="<p> brojMesta:" +brojMesta+" </p><br>";
                    }
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
        odgovor+="<p> maxUlD:" +maxUlD+" </p><br>";
        
        //upisati dogadjaj u bazu podataka
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
            Statement upit2=con2.createStatement();
            odgovor+="<p> SQL UPIT za nov dogadjaj<br> INSERT INTO dogadjaj(naziv,mesto,vremeOdrzavanja,opis,datoteka,kategorije,prodate,rezervisane,maxUl) VALUES('"+naziv+"','"+mesto+"','"+vremeOdrzavanja+"','"+opis+"','"+datoteka+"','"+katIDS+"','"+prodate+"','"+prodate+"','"+maxUlD+"')</p><br>";
            upit2.executeUpdate("INSERT INTO dogadjaj(naziv,mesto,vremeOdrzavanja,opis,datoteka,kategorije,prodate,rezervisane,maxUl) VALUES('"+naziv+"','"+mesto+"','"+vremeOdrzavanja+"','"+opis+"','"+datoteka+"','"+katIDS+"','"+prodate+"','"+prodate+"','"+maxUlD+"')");
            odgovor="<h3>uspesno dodat dogadjaj</h3>";
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
