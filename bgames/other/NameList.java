package bgames.other;

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
}
