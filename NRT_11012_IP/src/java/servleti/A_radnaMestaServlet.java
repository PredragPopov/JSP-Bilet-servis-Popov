
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import klase.RadnoMesto;
/**
 *
 * @author POPOV
 */
public class A_radnaMestaServlet extends HttpServlet {

    String odgovor="<p>nista</p>";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession sesija=request.getSession(true);
        ArrayList<RadnoMesto> listaRM=new ArrayList<RadnoMesto>();
        try{
            odgovor+="<p>TRY</p>";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
            odgovor+="<p>konektovao se</p>";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM radnoMesto");
            odgovor+="<p>ima rez</p>";
            //izcitavanje podataka iz tabele u bazi
            while(rs.next()){
                int sifraMesta=rs.getInt("sifraMesta");
                String mesto=rs.getString("mesto");
                String adresa=rs.getString("adresa");
                String grad=rs.getString("grad");
                
                RadnoMesto rm=new RadnoMesto();
                rm.setSifraMesta(sifraMesta);
                rm.setMesto(mesto);
                rm.setAdresa(adresa);
                rm.setGrad(grad);
                odgovor+="<p>uzeo RadnoMesto: "+rm+"</p>";
                listaRM.add(rm);
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
          
        sesija.setAttribute("listaRadnaMesta",listaRM);
        odgovor+="<p>dodao u sesiju listuRM</p>";
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
        odgovor+="<p>prosao dispatcher</p>";
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet A_radnaMestaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet A_radnaMestaServlet at " + request.getContextPath() + "</h1>");
            out.println(odgovor);
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
            //out.println("Greska: " + e);
            sesija.setAttribute("greska",e.toString());
            //RequestDispatcher dispatcher = request.getRequestDispatcher("greske.jsp");
            dispatcher.forward(request, response);
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        odgovor+="<p>doGET</p>";
        
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        odgovor="<p>doPOST</p>";
        response.setContentType("text/html;charset=UTF-8");
        
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
