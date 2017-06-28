package bgames;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

import bgames.other.ParseState;

public class Bgames {
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");  
    System.out.flush();
  }
  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.format("usage: java bgames.Bgames <input_file>\n");
      return;
    }
    FileReader file = null;
    try {
      file = new FileReader(args[0]);
    }
    catch (FileNotFoundException exc) {
      System.out.format("Specified file doesn't exist.\n");
      return;
    }
    Scanner sc = new Scanner(file);
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      builder.append(sc.nextLine());
      builder.append("\n");
    }
    ParseState text = new ParseState(builder.toString());
    World world = World.parse(text);
    if (world != null) {
      clearScreen();
      System.out.format("%s\n", world.toString());
    }
    else {
      System.out.format("Syntax error in specified file.\n");
      return;
    }
    
    Scanner user = new Scanner(System.in);
    while (user.hasNextLine()) {
      String command = user.nextLine();
      if (command.equals("")) {
        World newWorld = world.next();
        if (newWorld == world) {
          break;
        }
        world = newWorld;
        clearScreen();
        System.out.format("%s\n", world.toString());
      }
    }
  }
}
