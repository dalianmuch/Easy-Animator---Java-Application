package cs5004.animator.model;

/**
 * This class represents an add operation with the name of targeted shape,
 * the type of this operation and the start and end time.
 */
public class AddOperation extends AbstractOperation {

  /**
   * Construct an object of the AddOperation with the given parameters.
   *
   * @param name      the name of shape this operation applies to
   * @param type      the type of this operation
   * @param startTime the start time of this operation
   * @param endTime   the end time of this operation
   */
  AddOperation(String name, String type, float startTime, float endTime) {
    super(startTime, endTime, type, name);
  }

  @Override
  public String goOperate(Shape operateShape, String outType) {
    return operateShape.addShape(super.getStartTime(), super.getEndTime(), outType);
  }

}
