package cs5004.animator.model;

/**
 * This class represents a point with the given x and y.
 */
public class Point {
  private float x;
  private float y;

  /**
   * Create an object of point with the given x and y.
   *
   * @param x the value of x
   * @param y the value of y
   */
  public Point(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get the value of x.
   *
   * @return the value of x
   */
  public float getX() {
    return x;
  }

  /**
   * Get the value of y.
   *
   * @return the value of y
   */
  public float getY() {
    return y;
  }
}
