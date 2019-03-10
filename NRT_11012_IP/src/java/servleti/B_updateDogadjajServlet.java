
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
public class B_updateDogadjajServlet extends HttpServlet {

    String odgovor="<p>nista<p>";
    String potvrda="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet B_updateDogadjajServlet</title>"); 
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
        
        Date danas=new Date();
        potvrda="<p>danasnje vreme: "+danas+"</p><hr>";
        
        int ID=0;
        
        Date vremeOdrzavanja=new Date();
        
                                
        boolean jesteIstekao=false;
                
        ArrayList<Integer> nizID=new ArrayList<Integer>();
        odgovor+="<h3>Lista dogadjaja koji nisu do sad provereni:</h3>";
        potvrda+="<ul> <p>Lista isteklih dogadjaja:</p>";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            
            Statement upit=con.createStatement();
            ResultSet rez=upit.executeQuery("SELECT * FROM dogadjaj WHERE istekao=0");
            
            while(rez.next()){
                ID=rez.getInt("ID");
                
                vremeOdrzavanja=rez.getTimestamp("vremeOdrzavanja");
                
                odgovor+="<p>procitao sam iz baze: ID "+ID+" vremeOdrzavanja="+vremeOdrzavanja+"</p>";
                
                jesteIstekao=vremeOdrzavanja.before(danas);
                odgovor+="<h3>da li je vremeO pre danas: "+jesteIstekao+"</h3><hr>";
                if(jesteIstekao==true){
                    potvrda+="<li><p>ID="+ID+" vreme odrzavanja="+vremeOdrzavanja+"</p></li>";
                    nizID.add(ID);
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
        potvrda+="</ul>";
        
        odgovor+="<p>izdvojio sam ID u niz</p>";
        for(int i=0;i<nizID.size();i++){
            odgovor+="<p>"+nizID.get(i)+"</p>";
        }
        
        int x=nizID.size();
  
        if(x>0){
            odgovor+="<h3>istekla su "+x+" dogadjaja</h3>";
            for(int i=0;i<nizID.size();i++){
            int ovajID=nizID.get(i);
                try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            
                Statement upit=con.createStatement();
                upit.executeUpdate("UPDATE dogadjaj SET istekao=1 WHERE ID="+ovajID+"");
                potvrda+="<h3>uspesno ste UPDATE-ovali dogadjaj sa ID= "+ovajID+"</p><hr>";

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
        } else{
            potvrda="<h3>Nema promena</h3>";
        }
        odgovor+="<a href=\"javascript: history.go(-1)\">OK</a>";
        
        
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
