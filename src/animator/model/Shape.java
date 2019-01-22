package cs5004.animator.model;

import java.awt.Color;

/**
 * This interface represents all methods that shape should contain.
 * And outputs the final output text result.
 */
public interface Shape {

  /**
   * Change the current location to the given x and y.
   *
   * @param startPoint the given start location
   * @param endPoint   the given end location
   * @param startTime  the given start time
   * @param endTime    the given end time
   * @param outType    the given format of the output
   * @return the result string
   */
  String move(Point startPoint, Point endPoint, float startTime, float endTime, String outType);

  /**
   * Change the current color to the given color.
   *
   * @param startColor the given start color
   * @param endColor   the given end color
   * @param startTime  the given start time
   * @param endTime    the given end time
   * @param outType    the given format of the output
   * @return the result string
   */
  String changeColor(Color startColor, Color endColor, float startTime,
                     float endTime, String outType);


  /**
   * Create a new shape with the start time and end time.
   *
   * @param startTime the given time to appear
   * @param endTime   the given time to disappear
   * @param outType   the given format of the output
   * @return the result string
   */
  String addShape(float startTime, float endTime, String outType);

  /**
   * Scale the size of the shape.
   *
   * @param fromSx    the start x extent of this shape
   * @param fromSy    the start y extent of this shape
   * @param toSx      then end x extent of this shape
   * @param toSy      then end y extent of this shape
   * @param startTime the time given to start
   * @param endTime   the given time to end
   * @param outType   the given format of the output
   * @return the result string
   */
  String scaling(float fromSx, float fromSy, float toSx, float toSy,
                 float startTime, float endTime, String outType);

  /**
   * Get the ending text in the svg file.
   *
   * @return the ending text in the svg file
   */
  String endSvg();
}
