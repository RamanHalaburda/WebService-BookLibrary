package logic;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class MySqlConnection 
{    
    public static Connection conn;
    public static String query;
    public static Statement stmt;
    public static ResultSet rs;
    
    MySqlConnection() { conn = null; query = null; stmt = null; rs = null; }
    
    public void ConnectToDB() 
    {
        if(conn != null) return;
        try 
        {
            String url = "jdbc:mysql://localhost/schema_lib";
            String user = "root";
            String pass = "1656";
            conn = (Connection) DriverManager.getConnection(url, user, pass);
        } 
        catch (SQLException sqlE) { System.err.println(sqlE); }
    }
    
    public ResultSet Select()
    {
        Statement stmt = null;
        try 
        { 
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM new_table order by id;");
            return rs;
        } catch (SQLException sqlE) {System.out.println(sqlE); return null;}  
    }
    
    public void Insert(String _bookname, String _autor, String _datein, int _coll, String _datebl)
    {
        Statement stmt = null;        
        if(!_bookname.equals(""))
        {
            try 
            { 
                stmt = conn.createStatement(); 
                stmt.executeUpdate("insert into new_table set bookname = '" + _bookname
                                        + "', autor = '"         + _autor
                                        + "', datein = '"         + _datein
                                        + "', coll = '"         + _coll
                                        + "', datebl = '" +  _datebl + "';");
            } catch (SQLException sqlE) {System.err.println(sqlE);}
        }
    }
    
    public void Delete(int _id)
    {
        if(_id != 0)
        {
            Statement stmt = null;        
            try 
            {
                stmt = conn.createStatement(); 
                stmt.executeUpdate("delete from new_table where id = '" + _id + "';");
            } catch (SQLException sqlE) {System.err.println(sqlE);}
        }
    }
    
    public void Update(int _id, String _bookname, String _autor, String _datein, int _coll, String _datebl)
    {
        if(_id != 0)
        {
            Statement stmt = null;        
            try 
            { 
                stmt = conn.createStatement(); 
                stmt.executeUpdate("update new_table set bookname = '" + _bookname
                                        + "', autor = '"         + _autor
                                        + "', datein = '"         + _datein
                                        + "', coll = '"         + _coll
                                        + "', datebl = '" +  _datebl + "' where id = '" + _id + "';");
            } catch (SQLException sqlE) {System.err.println(sqlE);}
        }
    }
}
