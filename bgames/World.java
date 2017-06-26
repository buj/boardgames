package bgames;

import bgames.thing.Thing;
import bgames.thing.ThingTrie;
import bgames.stack.Stack;

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
}
