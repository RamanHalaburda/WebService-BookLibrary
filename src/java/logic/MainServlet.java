package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("<title>Servlet MainServlet</title>");
            out.println("<style type=\"text/css\">" + "* {" +
                    "    margin: 0;" +
                    "    padding: 0;" +
                    "    font-family: \"Verdana\",\"Arial\",\"Helvetica\",sans-serif;}" +
                    "html, body {" +
                    "    height: 100%;" +
                    "    margin: 0;" +
                    "    padding: 0;" +
                    "	min-height: 100%;}" +
                    "body {" +
                    "    background-color: /*#A2000C*/#252F48;" +
                    "    background-size: cover; /* масштабируемость фона */" +
                    "    padding: 0;" +
                    "    margin: 0;}" +
                    ".page-wrapper {" +
                    "    border: solid black 1px;" +
                    "    display: flex;" +
                    "    flex-direction: column;" +
                    "    align-items: stretch;" +
                    "    justify-content: flex-start;" +
                    "    min-height: 100%; /* минимальная высота обертки */" +
                    "    padding: 0 10px; /* оставляет по 10px справа и слева, если сжать окно браузера */" +
                    "    margin:0 auto;\n" +
                    "    background-color: #476BD6/*#7AE969*/;" +
                    "    align-content: center;}" +
                    "@media (min-width: 1240px)" +
                    "{.page-wrapper{width: 1000px;}}" +
                    ".table_dark { font-family: \"Lucida Sans Unicode\", \"Lucida Grande\", Sans-Serif;  font-size: 14px;  width: 640px; text-align: left;  border-collapse: collapse;  background: #252F48;  margin: 10px;} " +
                    ".table_dark th {  color: #EDB749;  border-bottom: 1px solid #37B5A5;  padding: 12px 17px;} " +
                    ".table_dark td {  color: #CAD4D6;  border-bottom: 1px solid #37B5A5;  border-right:1px solid #37B5A5;  padding: 4px 7px;}" +
                    ".table_dark tr:last-child td {  border-bottom: none;}"+
                    ".table_dark td:last-child {  border-right: none;}"+
                    ".table_dark tr:hover td {  text-decoration: underline;} hr{  margin: 10px;}"+
                    " .txtinput{width: 99%;}</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"page-wrapper\"><center>");
            out.println("<h2>Система учёта учебной литературы приветствует вас</h2><br><h3>Приятного пользования</h3><hr>");
            out.println("<form action=\"MainServlet\" method=\"get\">");
            out.println("<select name=\"operation\" size=\"1\" >" +
                        "<option value=\"unchecked\">Не выбран</option>" +
                        "<option value=\"insert\">Добавить</option>" +
                        "<option value=\"alter\">Изменить</option>" +
                        "<option value=\"delete\">Удалить</option>" +
                        "</select>");
            out.println("<input type=\"submit\" value=\"Выполнить\" action=\"MainServlet\" method=\"get\"><br><hr>");
            out.println(" <table class=\"table_dark\" >");
            out.println("<tr><td>ИД</td><td><input placeholder=\"ИД\" type=\"text\" class=\"txtinput\" name=\"id\"></td></tr>");
            out.println("<tr><td>Наименование</td><td><input placeholder=\"Наименование\" type=\"text\" class=\"txtinput\" name=\"bookname\"></td></tr>");
            out.println("<tr><td>Автор</td><td><input placeholder=\"Автор\" type=\"text\" class=\"txtinput\" name=\"autor\"></td></tr>");
            out.println("<tr><td>Дата издания</td><td><input placeholder=\"Дата издания\" type=\"text\" class=\"txtinput\" name=\"datein\"></td></tr>");
            out.println("<tr><td>Наличие</td><td><input placeholder=\"Наличие\" type=\"text\" class=\"txtinput\" name=\"coll\"></td></tr>");
            out.println("<tr><td>Дата поступления</td><td><input placeholder=\"Дата поступления\" type=\"text\" class=\"txtinput\" name=\"datebl\"></td></tr>");
            out.println("</table></form>");
            out.println("<hr> <table class=\"table_dark\">" +
                "   <caption ><b><i><font size=\"4\">Таблица книг</font></i></b></caption>" +
                "   <tr>" +
                "    <th>ИД</th>" +
                "    <th>Наименование</th>" +
                "    <th>Автор</th>" +
                "    <th>Дата издания</th>" +
                "    <th>Наличие</th>" +
                "    <th>Дата поступления</th>" +
                "   </tr>");
            
            MySqlConnection msc = new MySqlConnection();
            msc.ConnectToDB();
            ResultSet rs = msc.Select();
            
            try 
            {
                while(rs.next())
                 {
                    out.println("<tr>");
                    Object[] data = new Object[6];
                    for(int i = 1; i <= 6; ++i)
                    {
                        out.print("<td>" + rs.getObject(i) + "</td>");
                    }                    
                    out.println("</tr>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.println("</table> <hr> <h6>Igor Minko &copy 2016</h6><br> </center></div></body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        try
        {
            String op = request.getParameter("operation");
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();          
            if(!op.equals("unchecked"))
            {
                if(op.equals("insert"))
                {
                    MySqlConnection msc = new MySqlConnection();
                    msc.ConnectToDB();
                    msc.Insert(request.getParameter("bookname"),
                               request.getParameter("autor"),
                               request.getParameter("datein"), 
                               Integer.parseInt(request.getParameter("coll")), 
                               request.getParameter("datebl"));
                }
                if(op.equals("alter"))
                {
                    MySqlConnection msc = new MySqlConnection();
                    msc.ConnectToDB();
                    msc.Update(Integer.parseInt(request.getParameter("id")),
                               request.getParameter("bookname"),
                               request.getParameter("autor"),
                               request.getParameter("datein"), 
                               Integer.parseInt(request.getParameter("coll")), 
                               request.getParameter("datebl"));
                }
                if(op.equals("delete"))
                {
                    MySqlConnection msc = new MySqlConnection();
                    msc.ConnectToDB();
                    msc.Delete(Integer.parseInt(request.getParameter("id")));
                }
            }
            processRequest(request, response);
        } catch(Exception ex) 
        {            
            System.err.print(ex);
            processRequest(request, response); 
        }
             
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
