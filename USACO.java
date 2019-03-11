import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class USACO{
  private static int[][] lake;
  private static int[] start = new int[4];
  private static int[][] EZinstructions;

  public static int bronze(String filename) throws FileNotFoundException{
    makeLake(filename);
    stomp();
    fillLake();
    return volume();
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
    start[at] = Integer.parseInt(p);

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
    String[] instructions = new String[start[3]];
    for (int i = 0 ; i < instructions.length && info.hasNextLine() ; i++){
      String line = info.nextLine();
      instructions[i] = line;
    }

    fixINSTRO(instructions);
  }

  //RECYCLED KNIGHTSBOARD TOSTRING : for testing purposes/debugging
  private static String theLake(){
    String lakeString = "";
    for (int r = 0 ; r < lake.length ; r++){
      for (int c = 0 ; c < lake[r].length ; c++){
        if (lake[r][c] <= 0)
          lakeString += " __";
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
    return lakeString.substring(0,lakeString.length()-1);
  }

  //make the instructions easier to access
  private static void fixINSTRO(String[] instructions){
    EZinstructions = new int[instructions.length][3];
    for (int l = 0 ; l < instructions.length ; l++){
      String p = "";
      int at = 0;
      for (int params = 0 ; params < instructions[l].length() ; params++){
        if (instructions[l].charAt(params) == ' '){
          EZinstructions[l][at] = Integer.parseInt(p);
          at++;
          p = "";
        }
        else{
          p += instructions[l].charAt(params);
        }
      }
      EZinstructions[l][at] = Integer.parseInt(p);
    }
  }

  private static void stomp(){
    int[][] around = new int[][] {{0,0}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};

    //follow each instruction: find the biggest in the 3x3, change it and anything bigger than the desired value
    for (int i = 0 ; i < EZinstructions.length ; i++){
      int[] threex3 = new int[9];
      int[] one = EZinstructions[i];
      for (int cow = 0 ; cow < around.length ; cow++){
        if (one[0] + around[cow][0] > -1 && one[0] + around[cow][0] < lake.length && one[1] + around[cow][1] > -1 && one[1] + around[cow][1] < lake[0].length){
          threex3[cow] = lake[one[0]+around[cow][0]][one[1]+around[cow][1]];
        }
      }
      int changed = maximum(threex3) - one[2];
      for (int cow = 0 ; cow < around.length ; cow++){
        if (one[0] + around[cow][0] > -1 && one[0] + around[cow][0] < lake.length && one[1] + around[cow][1] > -1 && one[1] + around[cow][1] < lake[0].length){
          if (lake[one[0]+around[cow][0]][one[1]+around[cow][1]] > changed){
            lake[one[0]+around[cow][0]][one[1]+around[cow][1]] = changed;
          }
        }
      }
    }

  }

  private static int maximum(int[] numbers){
    int ans = numbers[0];
    for (int i = 0 ; i < numbers.length ; i++){
      ans = Math.max(ans,numbers[i]);
    }
    return ans;
  }

  private static void fillLake(){
    for (int r = 0 ; r < lake.length ; r++){
      for (int c = 0 ; c < lake[r].length ; c++){
        lake[r][c] = start[2] - lake[r][c];
      }
    }
  }

  private static int volume(){
    int depth = 0;
    for (int r = 0 ; r < lake.length ; r++){
      for (int c = 0 ; c < lake[r].length ; c++){
        if (lake[r][c] > 0){
          depth += lake[r][c];
        }
      }
    }
    return depth * 72 * 72;
  }

//--------------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------------
  private static int[] inf = new int[3];
  private static char[][] pasture;
  private static int[] startCo = new int[2];
  private static int[] endCo = new int[2];

  public static int silver(String filename) throws FileNotFoundException{
    dimension(filename);
    return wander();
  }

  //set up all the variables
  private static void dimension(String filename) throws FileNotFoundException{
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
        inf[at] = Integer.parseInt(p);
        at++;
        p = "";
      }
      else{
        p += startIns.charAt(params);
      }
    }
    inf[at] = Integer.parseInt(p);

    //make the pasture
    pasture = new char[inf[0]][inf[1]];
    for (int i = 0 ; i < pasture.length && info.hasNextLine() ; i++){
      String line = info.nextLine();
      for (int c = 0 ; c < line.length() ; c++){
        pasture[i][c] = line.charAt(c);
      }
    }

    //get the start and ending coordinates
    String coordinates = info.nextLine();
    String coord = "";
    int fosho = 0;
    for (int co = 0 ; co < coordinates.length() ; co++){
      if (coordinates.charAt(co) == ' '){
        if (fosho <= 1){
          startCo[fosho] = Integer.parseInt(coord);
          fosho++;
          coord = "";
        }
        else{
          endCo[fosho-2] = Integer.parseInt(coord);
          fosho++;
          coord = "";
        }
      }
      else{
        coord += coordinates.charAt(co);
      }
    }
    endCo[fosho-2] = Integer.parseInt(coord);
  }

  //for testing
  private static String thePAST(){
    String pastureString = "";
    for (int r = 0 ; r < pasture.length ; r++){
      for (int c = 0 ; c < pasture[r].length ; c++){
        pastureString += pasture[r][c];
      }
      pastureString += "\n";
    }
    return pastureString.substring(0,pastureString.length()-1);
  }

  private static int wander(){
    int[][] pastureSolved = new int[inf[0]][inf[1]];
    int[][] pastureSolved2 = new int[inf[0]][inf[1]];
    int steps = inf[2];
    int[][] directions = new int[][] {{-1,0}, {0,1}, {1,0}, {0,-1}};

    //set up pastureSolved
    pastureSolved[startCo[0]-1][startCo[1]-1] = 1;
    pastureSolved2[startCo[0]-1][startCo[1]-1] = 1;
    for (int r = 0 ; r < pasture.length ; r++){
      for (int c = 0 ; c < pasture[r].length ; c++){
        if (pasture[r][c] == '*'){
          pastureSolved[r][c] = -1;
          pastureSolved2[r][c] = -1;
        }
      }
    }


    for (int taken = 0 ; taken < steps ; taken++){
    for (int r = 0 ; r < pastureSolved.length ; r++){
      for (int c = 0 ; c < pastureSolved[r].length ; c++){

        if (pastureSolved[r][c] > 0){
          pastureSolved2[r][c] = 0;

          //in all four directions
          for (int d = 0 ; d < directions.length ; d++){
            //checks for out of bounds
            if (r+directions[d][0] > -1 && r+directions[d][0] < pastureSolved.length && c+directions[d][1] > -1 && c+directions[d][1] < pastureSolved[r].length){
              //checks for trees
              if (pastureSolved[r+directions[d][0]][c+directions[d][1]] != -1){
                pastureSolved2[r+directions[d][0]][c+directions[d][1]] += pastureSolved[r][c];
              }
            }
          }

        }

      }
    }

    //copy over from 2 to 1
    for (int r = 0 ; r < pasture.length ; r++){
      for (int c = 0 ; c < pasture[r].length ; c++){
          pastureSolved[r][c] = pastureSolved2[r][c];
      }
    }
    }

    return pastureSolved[endCo[0]-1][endCo[1]-1];
  }

}
