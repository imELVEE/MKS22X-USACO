import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class USACO{
  private static int[][] lake;
  private static int[] start = new int[4];
  private static String[] instructions;

  public static int bronze(String filename) throws FileNotFoundException{
    makeLake(filename);
    System.out.println(theLake());
    return -1;
  }


  private static void makeLake(String filename) throws FileNotFoundException{
    //get the first line
    String startIns = "";
    File text = new File(filename);
    Scanner info = new Scanner(text);
    if (info.hasNextLine()){
      startIns = info.nextLine();
    }

    //make the first line more easily accessible
    String p = "";
    int at = 0;
    for (int params = 0 ; params < startIns.length() ; params++){
      if (startIns.charAt(params) == ' '){
        start[at] = Integer.parseInt(p);
        at++;
        p = "";
      }
      else{
        p += startIns.charAt(params);
      }
    }

    //make the grid into a 2d array
    lake = new int[start[0]][start[1]];
    int c = 0;
    for (int i = 0 ; i < lake.length && info.hasNextLine() ; i++){
      String line = info.nextLine();
      String num = "";
      for (int ch = 0 ; ch < line.length() ; ch++){
        if (line.charAt(ch) == ' '){
          lake[i][c] = Integer.parseInt(num);
          num = "";
          c++;
        }
        else{
          num += line.charAt(ch);
        }
      }
      lake[i][c] = Integer.parseInt(num);
      c = 0;
    }

    //get the list of instructions
    instructions = new String[start[3]];
    for (int i = 0 ; i < instructions.length && info.hasNextLine() ; i++){
      String line = info.nextLine();
      instructions[i] = line;
    }
  }

  //RECYCLED KNIGHTSBOARD TOSTRING
  private static String theLake(){
    String lakeString = "";
    for (int r = 0 ; r < lake.length ; r++){
      for (int c = 0 ; c < lake[r].length ; c++){
        if (lake[r][c] == 0)
          lakeString += "  _";
        else
          if (lake[r][c] > 9){
            lakeString += " " + lake[r][c];
          }
          else{
            lakeString += "  " + lake[r][c];
          }
      }
      lakeString += "\n";
    }
    return lakeString;
  }

}
