package bgames.field;

import bgames.other.ParseState;

public abstract class Field {
  protected final String id;
  
  public Field(String id) {
    this.id = id;
  }
  
  public String getId() {
    return id;
  }
  
  public static Field parse(ParseState text) {
    Field result = ValueField.parse(text);
    if (result == null) {
      result = FunctionField.parse(text);
    }
    return result;
  }
}
