package bgames.stack.controls;

import bgames.stack.Executable;
import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;

public class Block implements Executable {
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
}
