import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class driver{
  public static void main(String[] args){
    try{
      String beg = "testCases/makelake.";
      String in = ".in";
      String out = ".out";

      for (int i = 1 ; i < 6 ; i++){
        String num = "";
        File text = new File(beg+i+out);
        Scanner info = new Scanner(text);
        if (info.hasNextLine()){
          num = info.nextLine();
        }

        if (USACO.bronze(beg+i+in) == Integer.parseInt(num)){
          System.out.println("makelake."+i+"    PASS");
        }
        else{
          System.out.println("makelake."+i+"    FAIL");
        }
      }
    }
    catch(FileNotFoundException e){
      System.out.println(e);
    }
  }
}
