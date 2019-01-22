package cs5004.animator.model;

/**
 * This interface represents operation applied to different targeted shapes and contains
 * all methods different sub-operations should support.
 */
public interface Operation extends Comparable<Operation> {

  /**
   * Output the final text formatted result with the given targeted shape and the type of output.
   *
   * @param operationShape the given targeted shape
   * @param outType        the type of the final text formatted result
   * @return the final text formatted result
   */
  String goOperate(Shape operationShape, String outType);

  /**
   * Output the end text for the svg file with the given targeted shape.
   *
   * @param operationShape the targeted shape to be operated
   * @return the end text for the svg file
   */
  String endSvg(Shape operationShape);

  /**
   * Return the start time of this operation used to be sorted in the model.
   *
   * @return the start time of this operation
   */
  float getStartTime();

  /**
   * Return the end time of this operation used to determine the whole time bound of
   * this animation.
   *
   * @return the end time of this operation
   */
  float getEndTime();

  /**
   * Return the type of its own operation to be determined whether it is valid in model.
   *
   * @return the type of its own operation
   */
  String getTypeOfOperation();

  /**
   * Get the string name of its targeted shape to operate correctly based on a table of
   * name and shape in the view.
   *
   * @return the string name of its targeted shape.
   */
  String getNameInOperation();

}
