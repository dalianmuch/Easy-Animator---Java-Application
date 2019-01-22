package cs5004.animator.model;


import java.awt.Color;

/**
 * This class represents an object of abstract shape with the given the parameters
 * shared commonly among different shapes. Also implements some methods shared commonly.
 */
public abstract class AbstractShape implements Shape {

  Point location;
  Color color;
  String name;

  /**
   * Construct an object of AbstractShape with the given location, color and name.
   *
   * @param location the given location of this object
   * @param color    the given color of this object
   * @param name     the given name of this object
   */
  protected AbstractShape(Point location, Color color, String name) {
    this.location = location;
    this.color = color;
    this.name = name;
  }


  @Override
  public String move(Point startPoint, Point endPoint, float startTime,
                     float endTime, String outPutType) {
    String result = "";
    if (outPutType.equals("text")) {
      result = result.concat(String.format("Shape %s moves from (%.1f,%.1f) to (%.1f,%.1f) "
                      + "from t=%.1fs to t=%.1fs\n", name,
              startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY(),
              startTime, endTime));
    }
    return result;
  }

  @Override
  public String changeColor(Color startColor, Color endColor, float startTime, float endTime,
                            String outPutType) {
    String result = "";
    if (outPutType.equals("text")) {
      result = result.concat(String.format("Shape %s changes color from (%.1f,%.1f,%.1f) "
                      + "to (%.1f,%.1f,%.1f)"
                      + " from t=%.1fs to t=%.1fs\n", name, (float) startColor.getRed() / 255,
              (float) startColor.getGreen() / 255, (float) startColor.getBlue() / 255,
              (float) endColor.getRed() / 255, (float) endColor.getGreen() / 255,
              (float) endColor.getBlue() / 255, startTime, endTime));
    } else if (outPutType.equals("svg")) {
      result = result.concat(String.format("<animate attributeName=\"fill\" "
                      + "attributeType=\"css\" from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" "
                      + "begin=\"base.begin+%.1fs\" dur=\"%.1fs\" fill=\"freeze\" />\n",
              startColor.getRed(),
              startColor.getGreen(), startColor.getBlue(), endColor.getRed(), endColor.getGreen(),
              endColor.getBlue(), startTime, endTime - startTime));
    }
    return result;
  }

}
