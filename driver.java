import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class driver{
  public static void main(String[] args){
    try{
      USACO.bronze("testCases/makelake.1.in");
    }
    catch(FileNotFoundException e){
      System.out.println(e);
    }
  }
}
