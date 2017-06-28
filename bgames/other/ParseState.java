package bgames.other;

public class ParseState {
  private String text;
  private int pos;
  
  public ParseState(String text) {
    this.text = text;
    this.pos = 0;
  }
  
  public boolean atEnd() {
    passWhite();
    return pos == text.length();
  }
  public boolean inRange(int i) {
    return (i >= 0) && (i < text.length());
  }
  public int getPosition() {
    return pos;
  }
  public void setPosition(int i) {
    pos = i;
  }
  private static boolean isWhitespace(Character ch) {
    return (ch.equals(' ')) || (ch.equals('\n'));
  }
  private void passWhite() {
    while (inRange(pos) && isWhitespace(text.charAt(pos))) {
      pos += 1;
    }
  }
  private int remainingChars() {
    return text.length() - pos;
  }
  public boolean read(String name) {
    int backup = pos;
    passWhite();
    if (name.length() > remainingChars()) {
      pos = backup;
      return false;
    }
    if (!text.substring(pos, pos + name.length()).equals(name)) {
      pos = backup;
      return false;
    }
    pos += name.length();
    return true;
  }
  public int readUint() {
    int backup = pos;
    passWhite();
    StringBuilder builder = new StringBuilder();
    while (inRange(pos)) {
      char ch = text.charAt(pos);
      if (!Character.isDigit(ch)) {
        break;
      }
      builder.append(ch);
      pos += 1;
    }
    if (builder.length() == 0) {
      pos = backup;
      return -1;
    }
    return Integer.parseInt(builder.toString());
  }
  public String readName() {
    int backup = pos;
    passWhite();
    StringBuilder builder = new StringBuilder();
    while (inRange(pos)) {
      char ch = text.charAt(pos);
      if (!Character.isLetter(ch)) {
        break;
      }
      builder.append(ch);
      pos += 1;
    }
    if (builder.length() == 0) {
      pos = backup;
      return null;
    }
    return builder.toString();
  }
}
