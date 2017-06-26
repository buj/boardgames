package bgames.stack;

import bgames.trie.Trie;
import bgames.value.Value;

public abstract class ReturnStack extends Stack {
  protected final String returnPoint;
  
  public ReturnStack(Stack below, Trie<Value> values, String returnPoint) {
    super(below, values);
    this.returnPoint = returnPoint;
  }
  
  @Override
  public boolean isReturnPoint() {
    return true;
  }
  @Override
  public boolean isMajor() {
    return false;
  }
}
