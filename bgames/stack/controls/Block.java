package bgames.stack.controls;

import java.util.ArrayList;

import bgames.stack.Executable;
import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.other.ParseState;

public class Block implements Controller {
  private final Executable[] steps;
  
  public Block(Executable[] steps) {
    this.steps = steps;
  }
  
  private class State extends StackState {
    private int step = 0;
    
    @Override
    public Stack next(Stack owner, OutsideWorld outsideWorld) {
      if (step < steps.length) {
        step += 1;
        return owner.push(new Stack(steps[step - 1].getState()));
      }
      return owner.pop();
    }
  }
  @Override
  public State getState() {
    return new State();
  }
  
  public static Block parse(ParseState text) {
    int backup = text.getPosition();
    if (!text.read("{")) {
      return null;
    }
    ArrayList<Executable> list = new ArrayList<>();
    while (true) {
      if (text.read("}")) {
        break;
      }
      Executable exec = Executable.parse(text);
      if (exec == null) {
        text.setPosition(backup);
        return null;
      }
      list.add(exec);
    }
    return new Block(list.toArray(new Executable[0]));
  }
}
