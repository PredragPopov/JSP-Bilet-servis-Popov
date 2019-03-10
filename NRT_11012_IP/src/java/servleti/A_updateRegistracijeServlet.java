
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import klase.Registracija;
/**
 *
 * @author POPOV
 */
public class A_updateRegistracijeServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet A_updateRegistracijeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet A_updateRegistracijeServlet at " + request.getContextPath() + "</h1>");
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
        
        ArrayList<Registracija> listaReg=new ArrayList<Registracija>();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb","root","");
            Statement upit=con.createStatement();
            ResultSet rez=upit.executeQuery("SELECT * FROM registracija");
            while(rez.next()){
                String korisnickoIme=rez.getString("korisnickoIme");
                String lozinka=rez.getString("lozinka");
                String tip=rez.getString("tip");
                String ime=rez.getString("ime");
                String prezime=rez.getString("prezime");
                String adresa=rez.getString("adresa");
                String telefon=rez.getString("telefon");
                String ePosta=rez.getString("ePosta");
                
                Registracija r=new Registracija();
                r.setKorisnickoIme(korisnickoIme);
                r.setLozinka(lozinka);
                r.setTip(tip);
                r.setIme(ime);
                r.setPrezime(prezime);
                r.setAdresa(adresa);
                r.setTelefon(telefon);
                r.setePosta(ePosta);
                
                listaReg.add(r);
                
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
        
        sesija.setAttribute("listaReg",listaReg);
        
        RequestDispatcher dispatcher=request.getRequestDispatcher("AlistaRegistracija.jsp");
        dispatcher.forward(request, response);
        
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
