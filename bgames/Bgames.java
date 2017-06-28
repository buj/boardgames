package bgames;

import java.util.Scanner;

import bgames.other.ParseState;

public class Bgames {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      builder.append(sc.nextLine());
      builder.append("\n");
    }
    ParseState text = new ParseState(builder.toString());
    World world = World.parse(text);
    if (world != null) {
      System.out.format("%s\n", world.toString());
    }
    while (true) {
      World newWorld = world.next();
      if (newWorld == world) {
        break;
      }
      world = newWorld;
      System.out.format("%s\n", world.toString());
    }
  }
}
