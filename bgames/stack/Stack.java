package bgames.stack;

import bgames.trie.Trie;
import bgames.thing.Thing;
import bgames.World;
import bgames.value.Value;

public abstract class Stack {
  protected final Stack below;
  protected final Trie<Value> values;
  
  public Stack(Stack below, Trie<Value> values) {
    this.below = below;
    this.values = values;
  }
  
  public Stack getBelow() {
    return below;
  }
  public Trie<Value> getValues() {
    return values;
  }
  
  public class OutsideWorld {
    private final World world;
    private Trie<Thing> things;
    
    public OutsideWorld(World world) {
      this.world = world;
      this.things = world.getThings();
    }
    
    public World getWorld() {
      return world;
    }
    public Trie<Thing> getThings() {
      return things;
    }
    public void setThings(Trie<Thing> things) {
      this.things = things;
    }
  }
  public abstract Stack next(OutsideWorld state);
  public abstract boolean isReturnPoint();
  public abstract boolean isMajor();
}
