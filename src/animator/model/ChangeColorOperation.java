package cs5004.animator.model;


import java.awt.Color;

/**
 * This class represents a change color operation with the name of targeted shape,
 * the type of this operation and the start and end time plus its extra
 * own parameters the start color and the end color.
 */
public class ChangeColorOperation extends AbstractOperation {
  private Color startColor;
  private Color endColor;

  /**
   * Construct an object of ChangeColorOperation with the given parameters.
   *
   * @param name       the name of the shape this operation applies to
   * @param type       the type of this operation
   * @param startColor the start color of the shape
   * @param endColor   the end color of the shape
   * @param startTime  the start time of this operation
   * @param endTime    the end time of this operation
   */
  ChangeColorOperation(String name, String type, Color startColor, Color endColor,
                       float startTime, float endTime) {
    super(startTime, endTime, type, name);
    this.startColor = startColor;
    this.endColor = endColor;
  }

  @Override
  public String goOperate(Shape operateShape, String outType) {
    return operateShape.changeColor(startColor, endColor,
            super.getStartTime(), super.getEndTime(), outType);
  }
}
