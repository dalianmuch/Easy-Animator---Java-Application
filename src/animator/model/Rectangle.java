package cs5004.animator.model;

import java.awt.Color;

/**
 * This class represents a rectangle with the given width, height and the type of itself.
 */
public class Rectangle extends AbstractShape {

  private double width;
  private double height;
  private String type;

  /**
   * Construct an object of AbstractShape with the given location, color, name, width and height.
   *
   * @param location the given location of this object
   * @param color    the given color of this object
   * @param name     the given name of this object
   * @param width    the given width of this object
   * @param height   the given height of this object
   */
  public Rectangle(Point location, Color color, String name, double width, double height) {
    super(location, color, name);
    this.width = width;
    this.height = height;
    this.type = "rectangle";
  }

  @Override
  public String addShape(float startTime, float endTime, String outType) {
    String result = "";
    if (outType.equals("text")) {
      result = result.concat(String.format("Name: %s\n", name));
      result = result.concat(String.format("Type: %s\n", type));
      result = result.concat(String.format("Min corner: (%.1f,%.1f), Width: %.1f, Height: %.1f, "
                      + "Color: (%.1f,%.1f,%.1f)\n", location.getX(), location.getY(), width,
              height, (float) color.getRed() / 255, (float) color.getGreen() / 255,
              (float) color.getBlue() / 255));
      result = result.concat(String.format("Appears at t=%.1fs\n", startTime));
      result = result.concat(String.format("Disappears at t=%.1fs\n", endTime));
      result = result.concat("\n");
    } else if (outType.equals("svg")) {
      result = result.concat(String.format("<rect id=\"%s\" x=\"%.1f\" y=\"%.1f\" width=\"%.1f\" "
                      + "height=\"%.1f\" fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\" >\n", name,
              location.getX(), location.getY(), width, height, color.getRed(), color.getGreen(),
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
      result = result.concat(String.format("Shape %s scales from Width: %.1f, Height: %.1f "
                      + "to Width: %.1f, Height: %.1f from t=%.1fs to t=%.1fs\n", name, fromSx,
              fromSy, toSx, toSx, startTime, endTime));
      result = result.concat("\n");
    } else if (outType.equals("svg")) {
      result = result.concat(String.format("<animate attributeType=\"xml\" begin=\""
                      + "base.begin+%.1fs\" dur=\"%.1fs\" attributeName=\"width\" from=\"%.1f\" "
                      + "to=\"%.1f\" fill=\"freeze\" />\n", startTime, endTime - startTime,
              fromSx, toSx));
      result = result.concat(String.format("<animate attributeType=\"xml\" begin=\""
                      + "base.begin+%.1fs\" dur=\"%.1fs\" attributeName=\"height\" from=\"%.1f\" "
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
                      + "base.begin+%.1fs\" dur=\"%.1fs\" attributeName=\"x\" from=\"%.1f\" "
                      + "to=\"%.1f\" fill=\"freeze\" />\n", startTime, endTime - startTime,
              startPoint.getX(), endPoint.getX()));
      result = result.concat(String.format("<animate attributeType=\"xml\" begin=\""
                      + "base.begin+%.1fs\" dur=\"%.1fs\" attributeName=\"y\" from=\"%.1f\" "
                      + "to=\"%.1f\" fill=\"freeze\" />\n", startTime, endTime - startTime,
              startPoint.getY(), endPoint.getY()));
      return result;
    }
  }

  @Override
  public String endSvg() {
    String result = "";
    result = result.concat(String.format("<animate attributeType=\"xml\" begin=\"base.begin\" "
                    + "dur=\"1ms\" attributeName=\"x\" to=\"%.1f\" fill=\"freeze\" />\n",
            location.getX()));
    result = result.concat(String.format("<animate attributeType=\"xml\" begin=\"base.begin\" "
                    + "dur=\"1ms\" attributeName=\"y\" to=\"%.1f\" fill=\"freeze\" />\n",
            location.getY()));
    result = result.concat(String.format("<animate attributeType=\"xml\" begin=\"base.begin\" "
            + "dur=\"1ms\" attributeName=\"width\" to=\"%.1f\" fill=\"freeze\" />\n", width));
    result = result.concat(String.format("<animate attributeType=\"xml\" begin=\"base.begin\" "
            + "dur=\"1ms\" attributeName=\"height\" to=\"%.1f\" fill=\"freeze\" />\n", height));
    result = result.concat(String.format("<animate attributeType=\"css\" begin=\"base.begin\" "
                    + "dur=\"1ms\" attributeName=\"fill\" "
                    + "to=\"rgb(%d,%d,%d)\" fill=\"freeze\" />\n",
            color.getRed(), color.getGreen(), color.getBlue()));
    result = result.concat("</rect>\n\n");
    return result;
  }
}
