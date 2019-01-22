package cs5004.animator.view;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.Operation;
import cs5004.animator.model.Shape;

/**
 * This class represents a text view with the destination output and the shapes and operations
 * appeared in the animation.
 */
public class AnimationTextView implements AnimationView {
  private Appendable outPut;
  private List<Operation> listOfOperation;
  private Map<String, Shape> mapOfShape;

  /**
   * Creates a text view with the given parameters.
   *
   * @param out             the destination of the output
   * @param listOfOperation all operations in this animation in a
   *                        chronologically ascending order
   * @param mapOfShape      all shapes appear in this animation with their respective names
   */
  AnimationTextView(Appendable out, List<Operation> listOfOperation,
                    Map<String, Shape> mapOfShape) {
    outPut = out;
    this.listOfOperation = listOfOperation;
    this.mapOfShape = mapOfShape;
  }

  @Override
  public void outPut()
          throws IOException {
    outPut.append("Shapes:\n");
    for (Operation aOperationList : listOfOperation) {
      outPut.append(aOperationList.goOperate(mapOfShape.get(aOperationList.getNameInOperation()),
              "text"));
    }

  }
}
