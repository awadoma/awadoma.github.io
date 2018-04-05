
package Entity;

import company.Tools;
import javax.swing.JTable;


public class Employee implements MainData{
  
    private int EmpNo;
    private String EmpName;
    private String Address;
    private double Salary;
    private String HiringDate;
    private String BirthDate;
    private int DeptNo;

    public int getEmpNo() {
        return EmpNo;
    }

    public void setEmpNo(int EmpNo) {
        this.EmpNo = EmpNo;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String EmpName) {
        this.EmpName = EmpName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public String getHiringDate() {
        return HiringDate;
    }

    public void setHiringDate(String HiringDate) {
        this.HiringDate = HiringDate;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String BirthDate) {
        this.BirthDate = BirthDate;
    }

    public int getDeptNo() {
        return DeptNo;
    }

    public void setDeptNo(int DeptNo) {
        this.DeptNo = DeptNo;
    }

    @Override
    public void add() {
        String strinsert =  "insert into employee values("
                + EmpNo + ","
                +"'"+ EmpName +"',"
                + "'"+ Address +"',"
                + Salary + ","
                +"'" + HiringDate +"',"
                + "'" + BirthDate +"',"
                + DeptNo +")";
                 boolean isAdd = db.goo.runNonQuery(strinsert);
       if(isAdd){
          Tools.msgBox("Employee Is Added");
       }
    }

    @Override
    public void update() {
         String strupdate = "update employee set "
                 + "EmpName='" + EmpName +"',"
                 + "Address='" + Address +"',"
                 + "Salary=" + Salary + ","
                 + "HiringDate='" + HiringDate + "',"
                 + "BirthDate='" + BirthDate + "',"
                 + "DeptNo=" + DeptNo 
                 + " where EmpNo=" + EmpNo;
          boolean isupdate = db.goo.runNonQuery(strupdate);
       if(isupdate){
          Tools.msgBox("Employee Is Updated");
    }
    }

    @Override
    public void delete() {
         String strDelete = "delete from employee"
                 + " where EmpNo=" + EmpNo;
          boolean isDelete = db.goo.runNonQuery(strDelete);
       if(isDelete){
          Tools.msgBox("Employee Is Deleted");
    }
    }

    @Override
    public String getAutoNumber() {
        return db.goo.getAutoNumber("Employee","EmpNo");
    }

    @Override
    public void getAllRows(JTable table) {
        db.goo.fillToJTable("employee_Data", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String strSelect = "select * from employee_data " 
                  + " where Number=" + EmpNo;
        db.goo.fillToJTable(strSelect, table);
    }

    @Override
    public void getCustomRows(String statement, JTable table) {
        db.goo.fillToJTable(statement, table);
    }

    @Override
    public String getValueByName(String name) {
        String strSelect = "select EmpNo from employee" +
                 " where EmpName='" + name + "'";
        String strVal = db.goo.getTableData(strSelect).Items[0][0].toString();
        return strVal;
    }

    @Override
    public String getNameByValue(String value) {
         String strSelect = "select EmpName from employee"
                + " where EmpNo=" + value;
          String strName = (String)db.goo.getTableData(strSelect).Items[0][0];
          return strName;
    }
    
    }
