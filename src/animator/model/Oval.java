package cs5004.animator.model;

import java.awt.Color;

/**
 * This class represents the oval with the given xRadius and yRadius and the type of itself.
 */
public class Oval extends AbstractShape {

  private double xRadius;
  private double yRadius;
  private String type;

  /**
   * Construct an object of AbstractShape with the given location, color, name, x radius and
   * y radius.
   *
   * @param location the given location of this object
   * @param color    the given color of this object
   * @param name     the given name of this object
   * @param xRadius  the given value of the x radius
   * @param yRadius  the given value of the y radius
   */
  Oval(Point location, Color color, String name, double xRadius, double yRadius) {
    super(location, color, name);
    this.xRadius = xRadius;
    this.yRadius = yRadius;
    this.type = "oval";
  }

  @Override
  public String addShape(float startTime, float endTime, String outType) {
    String result = "";
    if (outType.equals("text")) {
      result = result.concat(String.format("Name: %s\n", name));
      result = result.concat(String.format("Type: %s\n", type));
      result = result.concat(String.format("Center: (%.1f,%.1f), X radius: %.1f, "
                      + "Y radius: %.1f, Color: (%.1f,%.1f,%.1f)\n",
              location.getX(), location.getY(), xRadius,
              yRadius, (float) color.getRed() / 255, (float) color.getGreen() / 255,
              (float) color.getBlue() / 255));
      result = result.concat(String.format("Appears at t=%.1fs\n", startTime));
      result = result.concat(String.format("Disappears at t=%.1fs\n", endTime));
      result = result.concat("\n");
    } else if (outType.equals("svg")) {
      result = result.concat(String.format("<ellipse id=\"%s\" cx=\"%.1f\" cy=\"%.1f\" rx=\"%.1f\" "
                      + "ry=\"%.1f\" fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\" >\n", name,
              location.getX(), location.getY(), xRadius, yRadius, color.getRed(), color.getGreen(),
              color.getBlue()));
      result = result.concat(String.format("<set attributeType=\"xml\" attributeName=\""
              + "visibility\" to=\"visible\" begin=\"base.begin+%.1fs\" dur=\"%.1fs\" "
              + "fill=\"remove\" />\n", startTime, endTime));
    }
    return result;
  }

  @Override
  public String scaling(float fromSx, float fromSy, float toSx, float toSy,
                        float startTime, float endTime, String outType) {
    String result = "";
    if (outType.equals("text")) {
      result = result.concat(String.format("Shape %s scales from X radius: %.1f, Y radius %.1f "
                      + "to X radius: %.1f, Y radius: %.1f from t=%.1fs to t=%.1fs\n", name, fromSx,
              fromSy, toSx, toSy, startTime, endTime));
    } else if (outType.equals("svg")) {
      result = result.concat(String.format("<animate attributeType=\"xml\" begin=\""
                      + "base.begin+%.1fs\" dur=\"%.1fs\" attributeName=\"rx\" from=\"%.1f\" "
                      + "to=\"%.1f\" fill=\"freeze\" />\n", startTime, endTime - startTime,
              fromSx, toSx));
      result = result.concat(String.format("<animate attributeType=\"xml\" begin=\""
                      + "base.begin+%.1fs\" dur=\"%.1fs\" attributeName=\"ry\" from=\"%.1f\" "
                      + "to=\"%.1f\" fill=\"freeze\" />\n", startTime, endTime - startTime,
              fromSy, toSy));
    }
    return result;
  }

  @Override
  public String move(Point startPoint, Point endPoint, float startTime,
                     float endTime, String outPutType) {
    if (outPutType.equals("text")) {
      return super.move(startPoint, endPoint, startTime, endTime, outPutType);
    } else {
      String result = "";
      result = result.concat(String.format("<animate attributeType=\"xml\" begin=\""
                      + "base.begin+%.1fs\" dur=\"%.1fs\" attributeName=\"cx\" from=\"%.1f\" "
                      + "to=\"%.1f\" fill=\"freeze\" />\n", startTime, endTime - startTime,
              startPoint.getX(), endPoint.getX()));
      result = result.concat(String.format("<animate attributeType=\"xml\" begin=\""
                      + "base.begin+%.1fs\" dur=\"%.1fs\" attributeName=\"cy\" from=\"%.1f\" "
                      + "to=\"%.1f\" fill=\"freeze\" />\n", startTime, endTime - startTime,
              startPoint.getY(), endPoint.getY()));
      return result;
    }
  }

  @Override
  public String endSvg() {
    String result = "";
    result = result.concat(String.format("<animate attributeType=\"xml\" begin=\"base.begin\" "
                    + "dur=\"1ms\" attributeName=\"cx\" to=\"%.1f\" fill=\"freeze\" />\n",
            location.getX()));
    result = result.concat(String.format("<animate attributeType=\"xml\" begin=\"base.begin\" "
                    + "dur=\"1ms\" attributeName=\"cy\" to=\"%.1f\" fill=\"freeze\" />\n",
            location.getY()));
    result = result.concat(String.format("<animate attributeType=\"xml\" begin=\"base.begin\" "
            + "dur=\"1ms\" attributeName=\"rx\" to=\"%.1f\" fill=\"freeze\" />\n", xRadius));
    result = result.concat(String.format("<animate attributeType=\"xml\" begin=\"base.begin\" "
            + "dur=\"1ms\" attributeName=\"ry\" to=\"%.1f\" fill=\"freeze\" />\n", yRadius));
    result = result.concat(String.format("<animate attributeType=\"css\" begin=\"base.begin\" "
                    + "dur=\"1ms\" attributeName=\"fill\" to=\"rgb(%d,%d,%d)\" fill=\"freeze\"" +
                    " />\n",
            color.getRed(), color.getGreen(), color.getBlue()));
    result = result.concat("</ellipse>\n\n");
    return result;
  }
}
