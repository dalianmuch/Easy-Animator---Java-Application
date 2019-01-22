package cs5004.animator.model;

/**
 * This class represents a move operation with the name of targeted shape,
 * the type of this operation and the start and end time plus its extra
 * own parameters start point and end point.
 */
public class MoveOperation extends AbstractOperation {
  private float startX;
  private float startY;
  private float endX;
  private float endY;

  /**
   * Construct an object of MoveOperation with the given parameters.
   *
   * @param startX    the position x of the start point
   * @param startY    the position y of the start point
   * @param endX      the position of x of the end point
   * @param endY      the position of y of the end Point
   * @param startTime the time node to move
   * @param endTime   the time node to end moving
   * @param name      the name of shape this operation applies to
   * @param type      the type of this operation
   */
  MoveOperation(String type, String name, float startX, float startY, float endX,
                float endY, float startTime, float endTime) {
    super(startTime, endTime, type, name);
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;
  }

  @Override
  public String goOperate(Shape operateShape, String outType) {
    Point startPoint = new Point(startX, startY);
    Point endPoint = new Point(endX, endY);
    return operateShape.move(startPoint, endPoint, super.getStartTime(),
            super.getEndTime(), outType);
  }
}
