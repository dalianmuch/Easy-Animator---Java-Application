package cs5004.animator.view;

import java.util.List;
import java.util.Map;

import cs5004.animator.model.Operation;
import cs5004.animator.model.Shape;

/**
 * This class represents an abstract view. Its goal is implementing a static factory method.
 */
public abstract class AbstractView implements AnimationView {

  /**
   * Create a corresponding view based on the given type and all related parameters.
   *
   * @param type               the type of the view to be created
   * @param out                the destination of the output
   * @param listOfOperation    all operations in this animation in a
   *                           chronologically ascending order
   * @param mapOfShape         all shapes appear in this animation with their respective names
   * @param allShapeOperations A table contains all shapes with their individual set of operations
   *                           applying to them
   * @return the targeted view
   */
  public static AnimationView createView(String type, Appendable out,
                                         Map<String, Shape> mapOfShape, Map<String,
          List<Operation>> allShapeOperations, List<Operation> listOfOperation) {
    AnimationView view = null;
    if (type.equals("text")) {
      view = new AnimationTextView(out, listOfOperation, mapOfShape);
    } else if (type.equals("svg")) {
      view = new AnimationSvgView(out, mapOfShape, allShapeOperations, listOfOperation);
    }
    return view;


  }
}
