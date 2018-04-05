
package company;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class Tools {
     public static void msgBox(String message){
        JOptionPane.showMessageDialog(null, message);
        
    }
     
     public static boolean confirmMsg(String message){
        int msgC = JOptionPane.showConfirmDialog(null, message);
        if(msgC == JOptionPane.YES_OPTION ){
            return true;
        }else{
            return false;
        }
     }
    
   public static void CreateFolder(String folderName,String path) {
      File f = new File(path + "/" + folderName);
      f.mkdir();
     
  } 
   
    public static void CreateFolder(String folderName) {
      File f = new File( folderName);
      f.mkdir();
     
  } 
  
    
    public static void OpenForm(JFrame form) {
      try {
          form.setLocationRelativeTo(null);
          Image img = ImageIO.read(Tools.class.getResource("man.png"));
          form.setDefaultCloseOperation(2);
        // form.getContentPane().setBackground(java.awt.Color.white);
          form.setVisible(true);
      } catch (IOException ex) {
          msgBox(ex.getMessage());
      }
 }
    
    
    public static void ClearText(Container form){
     
    for(Component c : form.getComponents()){
        if(c instanceof JTextField){
            
        JTextField txt = (JTextField)c;
        txt.setText("");
            
        }else if(c instanceof Container){
            ClearText((Container)c);
        
    }
    }
     
 }
    
     public static void CreateEmptyFile(String fieleName){
      
      try {
          File f = new File(fieleName + ".txt");
          f.createNewFile();
      } catch (IOException ex) {
          msgBox(ex.getMessage());
      }
  }
    
    
    public static void CreateEmptyFiles(String fileNames[]){
      for( String str : fileNames){
        createEmptyFile(str);
      }
      
  }
    
    public static void CreateFile(String fileName , Object deta[]) {
      try {
          PrintWriter p = new PrintWriter(fileName +".txt");
          for(Object obj :deta ){
              p.println(obj);
              p.close();
          }
      } catch (FileNotFoundException ex) {
          msgBox(ex.getMessage());
      }
      
  }
    
    
     public static void CreateFiles(String fileames[] , Object allData[]) {
       for(int x = 0; x < fileames.length; x++){
           CreateFile(fileames[x], (Object[]) allData[x]);
           
       }
   }
     
     public static Object InputBox(String title){
       Object myobj =JOptionPane.showInputDialog(title);
       return myobj;
   }
     
      public static String getNumbers(String text){
        String val = "";
        for(char c : text.toCharArray()){
            if(c =='0'||c =='1'||c =='2'||c =='3'||c =='4'||c =='5'||c =='6'||c =='7'||c =='8'||c =='9'){
                val+=c; 
            }
        }
        return val;
    }
    //////////////////////////////////////////////////////
    
     public static int getNumbersToInteger(String text){
        String val = "";
        for(char c : text.toString().toCharArray()){
            if(c =='0'||c =='1'||c =='2'||c =='3'||c =='4'||c =='5'||c =='6'||c =='7'||c =='8'||c =='9'){
                val+=c; 
            }
        }
        return Integer.parseInt(val);
    }
     
     
     ///////////////////////////////////////////////////////////////////////////
      public static String removeNumbers(String text){
        String val = "";
        for(char c : text.toCharArray()){
            if(!(c =='0'||c =='1'||c =='2'||c =='3'||c =='4'||c =='5'||c =='6'||c =='7'||c =='8'||c =='9')){
                val+=c; 
            }
        }
        return val;
        
      } 
      /////////////////////////////////////////////////////////////
      public static void PrintScreen(String Imagename) {
      try {  
          Robot r = new Robot();
          Rectangle rec = new  Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
          BufferedImage img = r.createScreenCapture(rec);
          try {
              ImageIO.write(img,"jpg", new File(Imagename+ ".jpg"));
          } catch (IOException ex) {
              msgBox(ex.getMessage());
          }
      } catch (AWTException ex) {
           msgBox(ex.getMessage());
          
      }
      } 
      
      
      public static void PrintScreen(String ImageName,JFrame form) {
      try { 
          form.setState(1);
          Robot r = new Robot();
          Rectangle rec = new  Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
          BufferedImage img = r.createScreenCapture(rec);
          try {
              ImageIO.write(img,"jpg", new File(ImageName+ ".jpg"));
               form.setState(0);
          } catch (IOException ex) {
               msgBox(ex.getMessage());
          }
      } catch (AWTException ex) {
           msgBox(ex.getMessage());
      }
      }   

    private static void createEmptyFile(String str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
      public static class StringTools{
          private static String inverseText;
          
          public static void printCharByChar(String text){
              for(char c : text.toCharArray()){
                  System.out.println(c);
              }
          }
          
       public static void printCharByCharInverse(String text) {
           StringBuilder sb = new StringBuilder(text);
           inverseText = sb.reverse().toString();
           for(char c : inverseText.toCharArray()){
               System.out.println(c);
           }
           
       }   
    
}
      
      public class Table{
        
        public int columns;
        public Object [][] Items;
       
        public Table(int columns){
            this.columns =columns;
            Items = new Object[0][columns];
        }
       
        //////////////////////////// 
        public void AddNewRow(Object row[]){
            Object TempItems[][]= Items;//الاحتفاظ بالعناصر القديمة داخل متغير مؤقت
            Items = new Object[Items.length+1][columns];//زيادة المتغير الاساسي بعنصر اضافي
            for( int x= 0; x < TempItems.length; x++){//تعبئة العناصر القديمة في المتغير الاساسي 
              Items[x] = TempItems[x];  
            }
            Items[Items.length-1]=row;//اضافة الصف الجديد للعنصر  الاساسي 
        }
        public void printItemes(){
            
            for(Object objs[] : Items){
                for(Object obj : objs){
                  System  .out.println(obj +";");
                }
                System  .out.println();
            }
        }
        
        ///////////////////////////////////////////:
        
         public void editRow(int rowIndex, int columnIndex ,Object newData){
         
          Items[rowIndex][columnIndex]= newData;   
        }
         
         
         
         public void deletRow(int rowIndex){
           Object TempItems[][]= Items;
            Items = new Object[Items.length -1][columns];
             int y = 0;
              for(int x = 0; x < TempItems.length; x++){
   
                 if( x != rowIndex){
                     
             Items[y] = TempItems[x];
              y++;

   
   }
   }



    }
}
      
      public class MergeArray {
	public Object[]  array1;
	public Object[]  array2;
	
	public MergeArray(Object array1 [], Object array2[] ) {
	this.array1 = array1;	
	this.array2 = array2;	
	}
	
	public Object[] mergeTowArray() {
		
		int a1 = array1.length;
		int a2 = array2.length;
		Object[] arrayR = new Object[a1 + a2];
		System.arraycopy(array1, 0, arrayR, 0, a1);
		System.arraycopy(array2, 0, arrayR, a1, a2);
		
		
		
		return arrayR;
	}

}
    
}
