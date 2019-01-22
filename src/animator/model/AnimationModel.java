package cs5004.animator.model;

import java.awt.Color;
import java.util.List;
import java.util.Map;

/**
 * This interface contains all methods the model of animation should contain.
 */
public interface AnimationModel {

  /**
   * Create a shape with the given different kinds of parameters.
   *
   * @param name      the name of the shape
   * @param shape     the shape to be added
   * @param startTime the start time of this shape appearance
   * @param endTime   the end time of this shape disappearance
   * @throws IllegalArgumentException if the shape to be created already exists
   */
  void createShape(String name, Shape shape, float startTime, float endTime)
          throws IllegalArgumentException;

  /**
   * Change the position of a certain shape with the given name.
   *
   * @param name       the name of the shape to be moved
   * @param startPoint the x distance to be moved per unit time
   * @param endPoint   the y distance to be moved per unit time
   * @param startTime  the time to begin moving
   * @param endTime    the time to end moving
   * @throws IllegalArgumentException if the shapes to be moved does not exist,
   *                                  or this operation is not consistent with
   *                                  the former operations
   */
  void move(String name, Point startPoint, Point endPoint, float startTime, float endTime)
          throws IllegalArgumentException;

  /**
   * Change the size of a certain shape with the given name.
   *
   * @param name      the name of the shape to be scaled
   * @param oldXSize  the old x dimension of the shape
   * @param oldYSize  the old y dimension of the shape
   * @param newXSize  the new x dimension of the shape
   * @param newYSize  the new y dimension of the shape
   * @param startTime the time to start changing
   * @param endTime   the time to end
   * @throws IllegalArgumentException if the shapes to be scaled does not exist,
   *                                  or this operation is not consistent
   *                                  with the former operations
   */
  void scaling(String name, float oldXSize, float oldYSize, float newXSize, float newYSize,
               float startTime, float endTime) throws IllegalArgumentException;

  /**
   * Change the color of a certain shape with the given name.
   *
   * @param name       the name of the shape to be changed
   * @param startColor the initial color of that shape
   * @param endColor   the end color of that shape
   * @param startTime  the time to begin changing
   * @param endTime    the time to end this operation
   * @throws IllegalArgumentException if the shapes to be changed does not exist,
   *                                  or this operation is not consistent
   *                                  with the former operations
   */
  void changeColor(String name, Color startColor, Color endColor, float startTime, float endTime)
          throws IllegalArgumentException;

  /**
   * Get all shapes exist in this animation.
   *
   * @return all shapes exist on this animation
   */
  Map<String, Shape> getAllShape();

  /**
   * Get all operations exist in this animation, in an ascending order.
   *
   * @return all operations exist in this animation
   */
  List<Operation> getAllOperations();

  /**
   * Get all operations with their related shapes.
   *
   * @return all operations with their related shapes in a map
   */
  Map<String, List<Operation>> getAllShapeOperations();


}
