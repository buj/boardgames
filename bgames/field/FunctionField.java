package bgames.field;

import bgames.stack.controls.Block;
import bgames.other.NameList;

public class FunctionField implements Field {
  private final NameList names;
  private final Block block;
  
  public FunctionField(NameList names, Block block) {
    this.names = names;
    this.block = block;
  }
  
  public NameList getNames() {
    return names;
  }
  public Block getBlock() {
    return block;
  }
}
