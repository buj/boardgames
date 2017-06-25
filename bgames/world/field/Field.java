package bgames.world.field;

public abstract class Field {
  private final int priority;
  
  public Field(int priority) {
    this.priority = priority;
  }
  public int getPriority() {
    return priority;
  }
}
