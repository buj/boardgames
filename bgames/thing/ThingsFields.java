package bgames.thing;

import java.util.function.Function;

import bgames.trie.Trie;
import bgames.field.Field;

public class ThingsFields implements Function<Thing, Thing> {
  private final Function<Trie<Field>, Trie<Field>> nextFunction;
  
  public ThingsFields(Function<Trie<Field>, Trie<Field>> nextFunction) {
    this.nextFunction = nextFunction;
  }
  
  @Override
  public Thing apply(Thing thing) {
    return new Thing(nextFunction.apply(thing.getFields()));
  }
} 
