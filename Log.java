import java.util.ArrayList;

public class Log extends ArrayList<String> {

    boolean hasNext = false;
    int x1;
    int y1;
    int x2;
    int y2;
    String str_left="";
    String str_right="";
    String str="";

   public void  addLog(String s , int x1 , int y1 , int x2 , int y2, String str_left , String str_right){
       this.add(s);
       this.x1 = x1;
       this.y1 = y1;
       this.x2 = x2;
       this.y2 = y2;
       this.str_left = str_left;
       this.str_right = str_right;
       this.hasNext = true;
   }

    public void  addLog(String s , String str){
        this.add(s);
        this.str = str;
        this.hasNext = false;
    }

}
