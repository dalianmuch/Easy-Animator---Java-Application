package cs5004.animator.model;

/**
 * This class represents a scale operation with the name of targeted shape,
 * the type of this operation and the start and end time plus its extra
 * own parameters start dimension and end dimension.
 */
public class ScalingOperation extends AbstractOperation {
  private float fromSx;
  private float fromSy;
  private float toSx;
  private float toSy;

  /**
   * Construct an object of the scale operation with the given operations.
   *
   * @param type      the type of itself
   * @param name      the name of the targeted shape
   * @param startTime the start time of this operation
   * @param endTime   the end time of this operation
   * @param fromSx    the start x dimension
   * @param fromSy    the start y dimension
   * @param toSx      the end x dimension
   * @param toSy      the end y dimension
   */
  ScalingOperation(String type, String name, float startTime, float endTime,
                   float fromSx, float fromSy, float toSx, float toSy) {
    super(startTime, endTime, type, name);
    this.fromSx = fromSx;
    this.fromSy = fromSy;
    this.toSx = toSx;
    this.toSy = toSy;
  }

  @Override
  public String goOperate(Shape operateShape, String outType) {
    return operateShape.scaling(fromSx, fromSy, toSx, toSy,
            super.getStartTime(), super.getEndTime(), outType);
  }

}
