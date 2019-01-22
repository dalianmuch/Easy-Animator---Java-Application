package cs5004.animator.view;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cs5004.animator.model.Operation;
import cs5004.animator.model.Shape;

/**
 * This class represents a svg view with the given output destination
 * and all shapes and operations.
 */
public class AnimationSvgView implements AnimationView {
  private Appendable outPut;
  private Map<String, Shape> mapOfShape;
  private Map<String, List<Operation>> allShapeOperations;
  private List<Operation> listOfOperation;

  /**
   * Create a svg view based on the given parameters.
   *
   * @param out                the destination of the final output
   * @param listOfOperation    all operations in this animation in a
   *                           chronologically ascending order
   * @param mapOfShape         all shapes appear in this animation with their respective names
   * @param allShapeOperations A table contains all shapes with their individual set of operations
   *                           applying to them
   */
  AnimationSvgView(Appendable out,
                   Map<String, Shape> mapOfShape, Map<String,
          List<Operation>> allShapeOperations, List<Operation> listOfOperation) {
    outPut = out;
    this.mapOfShape = mapOfShape;
    this.allShapeOperations = allShapeOperations;
    this.listOfOperation = listOfOperation;
  }

  @Override
  public void outPut() throws IOException {
    float maxEndTime = 0;
    float hold = 2;
    for (Operation aListOfOperation : listOfOperation) {
      if (aListOfOperation.getEndTime() > maxEndTime) {
        maxEndTime = aListOfOperation.getEndTime();
      }
    }
    maxEndTime = maxEndTime + hold;
    outPut.append("<svg width=\"1000\" height=\"1000\" version=\"1.1\""
            + " xmlns=\"http://www.w3.org/2000/svg\">\n\n");
    outPut.append("<rect>\n\n");
    outPut.append(String.format("<animate id=\"base\" begin=\"0;base.end\" dur=\"%.1fs\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"hidden\"/>\n\n", maxEndTime));
    outPut.append("</rect>\n\n");
    Set<String> shapeSet = allShapeOperations.keySet();

    for (String aShape : shapeSet) {
      List<Operation> aListOfOperation = allShapeOperations.get(aShape);
      Collections.sort(aListOfOperation);
      int size = aListOfOperation.size();
      for (int i = 0; i < size; i++) {
        if (i <= size - 2) {
          outPut.append(aListOfOperation.get(i).goOperate(mapOfShape.get(aShape), "svg"));
        } else {
          outPut.append(aListOfOperation.get(i).goOperate(mapOfShape.get(aShape), "svg"));
          outPut.append(aListOfOperation.get(i).endSvg(mapOfShape.get(aShape)));
        }
      }
    }
    outPut.append("</svg>\n");
  }


}

