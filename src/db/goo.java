
package db;

import company.Tools;
import company.Tools.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class goo {
 
   private static String url = "";
   private static Connection con;
 
   private static void setUrL(){
       url = "jdbc:mysql://localhost:3306/company"
               + "?useUnicode=true&characterEncoding=UTF-8";
   }
    
    private static void setConnection(){
       try {
           setUrL();
           
           con = DriverManager.getConnection(url, "root","");
       } catch (SQLException ex) {
           Tools.msgBox(ex.getMessage());
       }
        
    }
    
  
    public static boolean checkUserAndPass(String username, String password){
       try {
           setConnection();
           Statement stmt = con.createStatement();
           String strCheck = "select * from users where " + "username='" + username 
                   + "' and " + "password='" + password + "'";
           stmt.executeQuery(strCheck);
           while(stmt.getResultSet().next()){
               return true;
           }
           con.close();           
       } catch (SQLException ex) {
           Tools.msgBox(ex.getMessage());
       }
       return false;    
    }
    //methode pour insert, delete, update
    public static boolean runNonQuery(String sqlStatement){
        try{
           setConnection();
           Statement stmt = con.createStatement();
           stmt.execute(sqlStatement);
            con.close();
            return true;
        }
        
        catch(SQLException ex){
            Tools.msgBox(ex.getMessage());
            return false;
        }
    }
    
    
 public static String getAutoNumber(String tableName, String columnName) {
     try{
           setConnection();
           Statement stmt = con.createStatement();
           String strAuto = "select max(" + columnName + ")+1 as autonum" + " from " + tableName;
           stmt.executeQuery(strAuto);
          String Num =" ";
          while(stmt.getResultSet().next()){
              Num = stmt.getResultSet().getString("autonum");
             
          }
           con.close();
           if(Num == null || "".equals(Num)) {
               return "1";
           }else{
               return Num;
           }
        }
        
        catch(SQLException ex){
            Tools.msgBox(ex.getMessage());
           return "0";
        }
 }  
 //جملة استعلام
 public static Table getTableData(String statement){
     
     Tools t = new Tools();
     
      try{
    setConnection(); 
    Statement stmt = con.createStatement();
    ResultSet rs;
    rs = stmt.executeQuery(statement);
    ResultSetMetaData rsmd = rs.getMetaData();
    int c = rsmd.getColumnCount();
    Table table = t.new Table(c);
    while(rs.next()){
        Object row[] = new Object[c]; 
        for(int i =0; i < c; i++){//ملئ الصفوف
          row[i] = rs.getString(i+1);
        }
        table.AddNewRow(row);   
    }
    con.close();
    return table;
      }
    catch(SQLException ex){
            Tools.msgBox(ex.getMessage());
              return t.new Table(0);
          
        }
 }
 
 public static void fillCombo(String tableName, String columnName, JComboBox combo){
   try{
    setConnection(); 
    Statement stmt = con.createStatement();
    ResultSet rs;
   String strSelect = "select " + columnName + " from " + tableName;
   rs = stmt.executeQuery(strSelect);
   rs.last();
   int c = rs.getRow();
   rs.beforeFirst();
   String values[] = new String[c];
   int i =0;
   while(rs.next()){
       values[i] = rs.getString(1);
       i++;   
   }
   con.close();
   combo.setModel(new DefaultComboBoxModel(values));
   
   } 
   catch(SQLException ex){
            Tools.msgBox(ex.getMessage());     
          
        }
 }
 
 
 public static void fillToJTable(String tableNameOrSelectStatement, JTable table ){
   
     
     try{
        setConnection(); 
    Statement stmt = con.createStatement();
    ResultSet rs;  
    String Spart = tableNameOrSelectStatement.substring(0,7).toLowerCase();
    String strSelect;
    if("select ".equals( Spart )){
      strSelect = tableNameOrSelectStatement; 
    }else{
    strSelect = "select * from " + tableNameOrSelectStatement;    
    }
    rs = stmt.executeQuery(strSelect);
    ResultSetMetaData rsmd = rs.getMetaData();
    int c = rsmd.getColumnCount();
    DefaultTableModel model = (DefaultTableModel)table.getModel();
    Vector row = new Vector();
    model.setRowCount(0);
    while(rs.next()){
        row = new Vector(c);
        for(int i = 1; i<= c; i++){
            row.add(rs.getString(i));
        }
        model.addRow(row);
    }
    if(table.getColumnCount() != c){
        Tools.msgBox("JTable Column Count Not Equal To Query Columns Count ");
    }
    con.close();
    
     }
      catch(SQLException ex){
            Tools.msgBox(ex.getMessage());     
          
        }
 }
 
}

   

