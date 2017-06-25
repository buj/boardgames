package bgames.world.stack;

import bgames.trie.Trie;
import bgames.world.Thing;
import bgames.world.World;
import bgames.value.Value;

public abstract class Stack {
  protected final Stack below;
  protected final Trie<Value> nameValues;
  protected final String returnPoint;
  
  public Stack(Stack below, Trie<Value> nameValues, String returnPoint) {
    this.below = below;
    this.nameValues = nameValues;
    this.returnPoint = returnPoint;
  }
  
  public Stack getBelow() {
    return below;
  }
  public Trie<Value> getNameValues() {
    return nameValues;
  }
  public String getReturnPoint() {
    return returnPoint;
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
