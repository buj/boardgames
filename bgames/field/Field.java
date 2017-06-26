package bgames.field;

public abstract class Field {
  protected final Field nested;
  protected final int priority;
  protected final boolean isTemp;
  
  public Field(Field nested, int priority, boolean isTemp) {
    this.nested = nested;
    this.priority = priority;
    this.isTemp = isTemp;
  }
  
  public Field getNested() {
    return nested;
  }  
  public int getPriority() {
    return priority;
  }
  public boolean isTemporary() {
    return isTemp;
  }
}
