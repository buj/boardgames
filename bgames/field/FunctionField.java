package bgames.field;

import bgames.stack.controls.Block;
import bgames.other.NameList;
import bgames.other.ParseState;

public class FunctionField extends Field {
  private final NameList names;
  private final Block block;
  
  public FunctionField(String id, NameList names, Block block) {
    super(id);
    this.names = names;
    this.block = block;
  }
  
  public NameList getNames() {
    return names;
  }
  public Block getBlock() {
    return block;
  }
  
  public static FunctionField parse(ParseState text) {
    int backup = text.getPosition();
    String id = text.readName();
    if (id == null) {
      return null;
    }
    NameList names = NameList.parse(text);
    if (names == null) {
      text.setPosition(backup);
      return null;
    }
    Block block = Block.parse(text);
    if (block == null) {
      text.setPosition(backup);
      return null;
    }
    return new FunctionField(id, names, block);
  }
  
  @Override
  public String toString() {
    return id + " " + names.toString() + " " + block.toString();
  }
}
