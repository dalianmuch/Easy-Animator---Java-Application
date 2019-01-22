package cs5004.animator.model;

/**
 * This class represents an object of abstract operation with the given the parameters
 * shared commonly among different operations. Also this class implements the Comparable interface
 * to make sure operations can be sorted in a chronologically ascending order.
 */
abstract class AbstractOperation implements Operation, Comparable<Operation> {

  private float startTime;
  private float endTime;
  private String typeOfOperation;
  private String nameOfShape;

  /**
   * Construct an object of abstract shape with the given parameters.
   *
   * @param startTime       the start time of this operation
   * @param endTime         the end time of this operation
   * @param typeOfOperation the type of this operation
   * @param nameOfShape     the name of the shape which this operation applies to
   */
  AbstractOperation(float startTime, float endTime, String typeOfOperation,
                    String nameOfShape) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.typeOfOperation = typeOfOperation;
    this.nameOfShape = nameOfShape;
  }

  @Override
  public float getStartTime() {
    return startTime;
  }

  @Override
  public float getEndTime() {
    return endTime;
  }

  @Override
  public String getTypeOfOperation() {
    return typeOfOperation;
  }

  @Override
  public String getNameInOperation() {
    return nameOfShape;
  }

  @Override
  public int compareTo(Operation o) {
    if (startTime - o.getStartTime() > 0) {
      return 1;
    } else if (startTime - o.getStartTime() == 0) {
      return 0;
    } else {
      return -1;
    }
  }

  @Override
  public String endSvg(Shape operationShape) {
    return operationShape.endSvg();
  }


}
