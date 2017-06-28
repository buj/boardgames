package bgames.thing;

import java.util.function.Function;
import java.util.ArrayList;

import bgames.trie.Trie;
import bgames.trie.FindOrCreate;
import bgames.trie.Assign;
import bgames.field.Field;
import bgames.field.ValueField;
import bgames.other.ParseState;
import bgames.stack.controls.Block;

public class Thing {
  private final String id;
  private final Trie<Field> fields;
  
  public Thing(String id, Trie<Field> fields) {
    this.id = id;
    this.fields = fields;
  }
  
  public String getId() {
    return id;
  }
  public Thing setId(String id) {
    return new Thing(id, this.fields);
  }
  
  public Trie<Field> getFields() {
    return fields;
  }
  public Thing setFields(Trie<Field> fields) {
    if (fields == this.fields) {
      return this;
    }
    return new Thing(this.id, fields);
  }
  
  public static class GetFields implements Function<Thing, Thing> {
    private final Function<Trie<Field>, Trie<Field>> nextFunction;
    
    public GetFields(Function<Trie<Field>, Trie<Field>> nextFunction) {
      this.nextFunction = nextFunction;
    }
    
    @Override
    public Thing apply(Thing thing) {
      return thing.setFields(nextFunction.apply(thing.fields));
    }
  }
  

  public static Thing parse(ParseState text) {
    int backup = text.getPosition();
    String id = text.readName();
    if (id == null) {
      return null;
    }
    if (!text.read("{")) {
      text.setPosition(backup);
      return null;
    }
    Trie<Field> result = new Trie<>();
    while (true) {
      if (text.read("}")) {
        break;
      }
      Field field = Field.parse(text);
      if (field == null) {
        text.setPosition(backup);
        return null;
      }
      FindOrCreate<Field> procedure = new FindOrCreate<Field>(field.getId(),
                                      new Assign<Field>(field));
      result = procedure.apply(result);
    }
    return new Thing(id, result);
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("&");
    builder.append(id);
    builder.append(":\n");
    ArrayList<String> list = fields.collect();
    for (int i = 0; i < list.size(); i++) {
      Field field = fields.get(list.get(i));
      if (field instanceof ValueField) {
        builder.append(Block.indent(field.toString()));
      }
    }
    return builder.toString();
  }
}
