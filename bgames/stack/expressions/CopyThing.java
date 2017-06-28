package bgames.stack.expressions;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.value.ThingPointer;
import bgames.trie.Trie;
import bgames.trie.FindOrCreate;
import bgames.trie.Assign;
import bgames.thing.Thing;
import bgames.other.ParseState;

public class CopyThing implements Expression {
  private final ThingPointer pointer;
  
  public CopyThing(ThingPointer pointer) {
    this.pointer = pointer;
  }
  
  private class State extends StackState {
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      String name = String.valueOf(outside.getThings().getMaxId());
      Thing preimage = outside.getThings().getThing(pointer);
      FindOrCreate<Thing> procedure = new FindOrCreate<Thing>(name,
                                      new Assign<Thing>(preimage));
      Trie<Thing> root = outside.getThings().getRoot();
      root = procedure.apply(root);
      outside.setThings(outside.getThings().setRoot(root));
      return owner.pop(new ThingPointer(name), outside);
    }
  }
  @Override
  public State getState() {
    return new State();
  }
  
  public static CopyThing parse(ParseState text) {
    int backup = text.getPosition();
    if (!text.read("new")) {
      return null;
    }
    String name = text.readName();
    if (name == null) {
      text.setPosition(backup);
      return null;
    }
    return new CopyThing(new ThingPointer(name));
  }
}
