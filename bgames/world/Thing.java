package bgames.world;

import bgames.trie.Trie;

public class Thing {
  private final Trie<FieldBag> bags;
  
  public Thing(Trie<FieldBag> bags) {
    this.bags = bags;
  }
}
