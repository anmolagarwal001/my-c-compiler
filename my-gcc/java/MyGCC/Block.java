package MyGCC;

import java.util.*;

public class Block {
  
  public Block parent = null;
  public LinkedList<Instruction> instructions;
  public LinkedList<Block> blocks;
  public Context myContext;

  
  public Block() {
    this.instructions = new LinkedList<Instruction>();
    this.blocks = new LinkedList<Block>();
    this.myContext = null;
  }
  
  public Block(LinkedList<Instruction> ins, LinkedList<Block> bl) {
    this.instructions = ins;
    this.blocks = bl;
    this.myContext = null;
  }
  
  public Block(Block b, Context c) {
    this.instructions = new LinkedList<Instruction>(b.instructions);
    this.blocks = new LinkedList<Block>();
    this.myContext = c;
  }
  
  public Block(Context c) {
    this.instructions = new LinkedList<Instruction>();
    this.blocks = new LinkedList<Block>();
    this.myContext = c;
  }
  
  /**
   * This functions return the highest number of parameters contained in a
   * function call of the block.
   * The value returned is <b>-1</b> if there's no call to functions in this block
   */  
  public int maxParameters(){
    int n = -1;
    for (Instruction i : instructions){
      n = Math.max(i.maxParameters(), n);
    }
    for (Block b : blocks){
      n = Math.max(b.maxParameters(), n);
    }
    return n;
  }
  
  public void pushInstruction(Instruction e) {
    if(this.instructions != null)
      if(e != null)
        this.instructions.add(e);
      else
        System.err.println("The specified instruction is null");
    else
      System.err.println("this.instructions: is null");
  }
  
  public void pushBlock(Block bl) {
    if(this.blocks != null)
      if(bl != null)
        this.blocks.add(bl);
      else
        System.err.println("The specified block is null");
    else
      System.err.println("this.blocks: is null");
  }

  public LinkedList<Instruction> getInstructions() {
    return this.instructions;
  }
  
  public LinkedList<Block> getBlocks() {
    return this.blocks;
  }
  
  public String toString(){
    StringBuffer sb = new StringBuffer();
    Instruction i;
    Iterator<Instruction> iter = instructions.iterator();
    
    while(iter.hasNext()){
      i = iter.next();
      try{
      sb.append(Instruction.instructionToAssembly(i, myContext));
      }catch(Exception e){e.printStackTrace();}
    }
    
    return sb.toString();
  }
}
