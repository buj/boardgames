package bgames.other;

public class FieldPointer {
  private final String thingId, fieldId;
  
  public FieldPointer(String thingId, String fieldId) {
    this.thingId = thingId;
    this.fieldId = fieldId;
  }
  public FieldPointer(String fieldId) {
    this("", fieldId);
  }
  
  public String getThingId() {
    return thingId;
  }
  public String getFieldId() {
    return fieldId;
  }
}
