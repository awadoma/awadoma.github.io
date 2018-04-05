 
package Entity;

import company.Tools;
import javax.swing.JTable;


public class Department implements MainData{
   
    private int DeptNo;
    private String DeptName;
    private String Location;

    public int getDeptNo() {
        return DeptNo;
    }

    public void setDeptNo(int DeptNo) {
        this.DeptNo = DeptNo;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String DeptName) {
        this.DeptName = DeptName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    @Override
    public void add() {
       String insert =  "insert into department values("
               + DeptNo + ","
                +"'"+ DeptName + "',"
                + "'" + Location + "')" ; 
       boolean isAdd = db.goo.runNonQuery(insert);
       if(isAdd){
          Tools.msgBox("Departement Is Added");
       }
    }

    @Override
    public void update() {
        String strupdate = "update department set "
                + "DeptName='" + DeptName + "',"
                + "Location='" + Location + "' "
                + " where DeptNo=" + DeptNo;
        boolean IsUpdate = db.goo.runNonQuery(strupdate);
        if(IsUpdate){
            Tools.msgBox("Department Is Updated");
    }
    }

    @Override
    public void delete() {
         String strDelete = "delete from department"
                 + " where DeptNo=" + DeptNo;
        boolean IsDelete = db.goo.runNonQuery(strDelete);
        if(IsDelete){
            Tools.msgBox("Department Is Deleted");
        }
    }

    @Override
    public String getAutoNumber() {
        String strAuto = db.goo.getAutoNumber("department","DeptNo");
        return strAuto;
    }

    @Override
    public void getAllRows(JTable table) {
        db.goo.fillToJTable("department_data", table);
    }

    @Override
    public void getOneRow(JTable table) {
          String strSelect = "select * from department_data " 
                  + " where Department_No=" + DeptNo;
        db.goo.fillToJTable(strSelect, table);
    }

    @Override
    public void getCustomRows(String statement, JTable table) {
        db.goo.fillToJTable(statement, table);
    }

    @Override
    public String getValueByName(String name) {
         String strSelect = "select deptNo from department" +
                 " where deptName='" + name + "'";
        String strVal = (String)db.goo.getTableData(strSelect).Items[0][0];
        return strVal;
    }

    @Override
    public String getNameByValue(String value) {
        String strSelect = "select deptname from department"
                + " where deptno=" + value;
          String strName = (String)db.goo.getTableData(strSelect).Items[0][0];
          return strName;
    }

}
