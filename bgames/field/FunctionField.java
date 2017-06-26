package bgames.field;

import bgames.other.NameList;

public class FunctionField extends Field {  
  public FunctionField(Field nested, int priority, boolean isTemp) {
    super(nested, priority, isTemp);
  }
}
