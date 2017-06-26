package bgames.stack;

public abstract class StackState {
  public abstract Stack next(Stack owner, OutsideWorld state);
  public boolean isReturnPoint() {
    return false;
  }
}
