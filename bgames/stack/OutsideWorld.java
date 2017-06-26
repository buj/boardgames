package bgames.stack;

import bgames.World;
import bgames.thing.ThingTrie;

public class OutsideWorld {
  private final World world;
  private ThingTrie things;
  
  public OutsideWorld(World world) {
    this.world = world;
    this.things = world.getThings();
  }
  
  public World getWorld() {
    return world;
  }
  public ThingTrie getThings() {
    return things;
  }
  public void setThings(ThingTrie things) {
    this.things = things;
  }
}
