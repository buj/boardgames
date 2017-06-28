package bgames;

import bgames.field.Field;
import bgames.thing.Thing;
import bgames.thing.ThingTrie;
import bgames.stack.Stack;
import bgames.stack.OutsideWorld;
import bgames.stack.controls.Block;
import bgames.stack.controls.ThingBlock;
import bgames.stack.expressions.Expression;
import bgames.stack.expressions.Constant;
import bgames.stack.expressions.FunctionCall;
import bgames.stack.expressions.ExpressionList;
import bgames.stack.Executable;
import bgames.other.ParseState;
import bgames.trie.Trie;
import bgames.trie.FindOrCreate;
import bgames.trie.Assign;
import bgames.value.ThingPointer;

public class World {
  private final World previous;
  private final Stack stack;
  private final ThingTrie things;
  
  public World(World previous, Stack stack, ThingTrie things) {
    this.previous = previous;
    this.stack = stack;
    this.things = things;
  }
  
  public World getPrevious() {
    return previous;
  }
  public Stack getStack() {
    return stack;
  }
  public ThingTrie getThings() {
    return things;
  }
  public World next() {
    if (stack == null) {
      return this;
    }
    OutsideWorld outside = new OutsideWorld(this);
    Stack newStack = stack.next(outside);
    return new World(this, newStack, outside.getThings());
  }
  
  public static Stack getBottom() {
    Constant global = new Constant(new ThingPointer(""));
    Executable[] steps = new Executable[1];
    steps[0] = new FunctionCall("main", new ExpressionList(new Expression[0]));
    Block block = new Block(steps);
    return new Stack(new ThingBlock(global, block).getState());
  }
  public static World parse(ParseState text) {
    Trie<Field> global = new Trie<>();
    Trie<Thing> things = new Trie<>();
    while (!text.atEnd()) {
      Field field = Field.parse(text);
      if (field == null) {
        Thing thing = Thing.parse(text);
        if (thing == null) {
          return null;
        }
        else {
          FindOrCreate<Thing> procedure = new FindOrCreate<Thing>(thing.getId(),
                                          new Assign<Thing>(thing));
          things = procedure.apply(things);
        }
      }
      else {
        FindOrCreate<Field> procedure = new FindOrCreate<Field>(field.getId(),
                                        new Assign<Field>(field));
        global = procedure.apply(global);
      }
    }
    FindOrCreate<Thing> addGlobal = new FindOrCreate<Thing>("",
                                    new Assign<Thing>(new Thing("", global)));
    things = addGlobal.apply(things);
    return new World(null, getBottom(), new ThingTrie(things));
  }
}
