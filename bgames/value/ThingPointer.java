package bgames.value;

public class ThingPointer implements Value {
  private final String id;
  
  public ThingPointer(String id) {
    this.id = id;
  }
  
  public String getId() {
    return id;
  }
  public FieldPointer getField(String name) {
    return new FieldPointer(id, name);
  }
  
  @Override
  public String toString() {
    return "&" + id;
  }
}
