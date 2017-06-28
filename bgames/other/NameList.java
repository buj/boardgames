package bgames.other;

import java.util.ArrayList;

public class NameList {
  private final String[] names;
  
  public NameList(String[] names) {
    this.names = names;
  }
  
  public int getLength() {
    return names.length;
  }
  public boolean inRange(int i) {
    return (i >= 0) && (i < names.length);
  }
  public String get(int i) {
    if (inRange(i)) {
      return names[i];
    }
    return null;
  }
  
  public static NameList parse(ParseState text) {
    int backup = text.getPosition();
    if (!text.read("(")) {
      return null;
    }
    ArrayList<String> list = new ArrayList<>();
    if (!text.read(")")) {
      while (true) {
        String name = text.readName();
        if (name == null) {
          text.setPosition(backup);
          return null;
        }
        list.add(name);
        if (text.read(")")) {
          break;
        }
        if (!text.read(",")) {
          text.setPosition(backup);
          return null;
        }
      }
    }
    return new NameList(list.toArray(new String[0]));
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("(");
    for (int i = 0; i < names.length; i++) {
      builder.append(names[i]);
      if (i != names.length - 1) {
        builder.append(", ");
      }
    }
    builder.append(")");
    return builder.toString();
  }
}
