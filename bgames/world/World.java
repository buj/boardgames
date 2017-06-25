package bgames.world;

import bgames.trie.Trie;
import bgames.world.stack.Stack;

public class World {
  private final World previous;
  private final Stack stack;
  private final Trie<Thing> things;
  
  public World(World previous, Stack stack, Trie<Thing> things) {
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
  public Trie<Thing> getThings() {
    return things;
  }
}
