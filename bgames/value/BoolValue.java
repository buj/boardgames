package bgames.value;

public class BoolValue implements Value {
  private boolean value;
  
  public BoolValue(boolean value) {
    this.value = value;
  }
  public boolean getValue() {
    return value;
  }
}
