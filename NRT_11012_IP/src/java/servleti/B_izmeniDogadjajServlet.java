
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
public class B_izmeniDogadjajServlet extends HttpServlet {

   String odgovor="<p>nista</p>";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>B_IzmeniDogadjajServlet</title>"); 
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
        
        String potvrda="";
        String SQLupit="";

        String faktorY=request.getParameter("faktorY");
        int izbor=Integer.parseInt(faktorY.trim());
        String idS=request.getParameter("dID");
        int ID=Integer.parseInt(idS.trim());
        
        odgovor+="<p>izbor je: "+izbor+" a ID=|"+ID+"|</p>";
        
        if(izbor==1){ //izmeni dogadjaj sa poslatim podacima
            String naziv=request.getParameter("dnaziv");
            String mesto=request.getParameter("dmesto");
            String vremeOdrzavanja=request.getParameter("dvremeOdrzavanja");
            String opis=request.getParameter("dopis");
            String datoteka=request.getParameter("ddatoteka");
            
            String istekaoS=request.getParameter("distekao");
            boolean istekaoB=Boolean.parseBoolean(istekaoS.trim());
            int istekao=9;
            if(istekaoB==true){
                istekao=1;
            }else if(istekaoB==false){
                istekao=0;
            }
            odgovor+="<p>istekaoS="+istekaoS+" istekaoB="+istekaoB+" istekaoINT="+istekao+"</p>";
            odgovor+="<p>UPDATE dogadjaj SET naziv='"+naziv+"',mesto='"+mesto+"',vremeOdrzavanja='"+vremeOdrzavanja+"',opis='"+opis+"',datoteka='"+datoteka+"',istekao="+istekao+" WHERE ID="+ID+"</p>";
            SQLupit="UPDATE dogadjaj SET naziv='"+naziv+"',mesto='"+mesto+"',vremeOdrzavanja='"+vremeOdrzavanja+"',opis='"+opis+"',datoteka='"+datoteka+"',istekao="+istekao+" WHERE ID="+ID+"";
            potvrda="<h3>Uspesno izmenjen dogadjaj sa ID="+ID+"</h3>";
            
            
            
        }else if(izbor==2){ //obrisi dogadjaj sa tim ID
            SQLupit="DELETE FROM dogadjaj WHERE ID="+ID+"";
            potvrda="<h3>Uspesno obrisan dogadjaj sa ID="+ID+"</h3>";
            
        }
        
        try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
                Statement upit2=con2.createStatement();
                upit2.executeUpdate(SQLupit);
                odgovor=potvrda;
                
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
