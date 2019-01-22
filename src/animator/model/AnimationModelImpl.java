package cs5004.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cs5004.animator.util.TweenModelBuilder;

/**
 * This class represents an model of this animation storing all shapes appear in this animation and
 * all valid operations categorized by names of different shapes.
 */
public final class AnimationModelImpl implements AnimationModel {

  private Map<String, Shape> shapes;
  private Map<String, List<Operation>> operationOfShapes;

  /**
   * Create an empty object of the model.
   */
  public AnimationModelImpl() {
    shapes = new HashMap<>();
    operationOfShapes = new HashMap<>();
  }

  @Override
  public void createShape(String name, Shape shape, float startTime, float endTime) {
    if (operationOfShapes.get(name) == null) {
      List<Operation> list = new ArrayList<>();
      Operation addOperation = new AddOperation(name, "create", startTime, endTime);
      list.add(addOperation);
      operationOfShapes.put(name, list);
      shapes.put(name, shape);
    } else {
      throw new IllegalArgumentException("Cannot create the identical shape twice.");
    }
  }

  @Override
  public void move(String name, Point startPoint, Point endPoint, float startTime, float endTime)
          throws IllegalArgumentException {
    if (operationOfShapes.get(name) == null) {
      throw new IllegalArgumentException("Cannot move a non-existent shape");
    } else if (isValid("move", name, startTime, endTime)) {
      operationOfShapes.get(name).add(new MoveOperation("move", name,
              startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY(),
              startTime, endTime));
    } else {
      throw new IllegalArgumentException("This move operation is not consistent with the former"
              + "operations");
    }
  }

  @Override
  public void scaling(String name, float oldXSize, float oldYSize, float newXSize, float newYSize,
                      float startTime, float endTime)
          throws IllegalArgumentException {
    if (operationOfShapes.get(name) == null) {
      throw new IllegalArgumentException("Cannot scale a non-existent shape");
    } else if (isValid("scale", name, startTime, endTime)) {
      operationOfShapes.get(name).add(new ScalingOperation("scale", name, startTime, endTime,
              oldXSize, oldYSize, newXSize, newYSize));
    } else {
      throw new IllegalArgumentException("This scaling operation is not consistent with the former"
              + "operations");
    }

  }

  @Override
  public void changeColor(String name, Color startColor, Color endColor,
                          float startTime, float endTime) {
    if (operationOfShapes.get(name) == null) {
      throw new IllegalArgumentException("Cannot change the color of a non-existent shape");
    } else if (isValid("change-color", name, startTime, endTime)) {
      operationOfShapes.get(name).add(new ChangeColorOperation(name, "change-color",
              startColor, endColor, startTime, endTime));
    } else {
      throw new IllegalArgumentException("This change-color operation is not "
              + "consistent with the former operations");
    }
  }

  @Override
  public Map<String, Shape> getAllShape() {
    Map<String, Shape> result = new HashMap<>();
    Set<String> keySet = shapes.keySet();
    for (String key : keySet) {
      result.put(key, shapes.get(key));
    }
    return result;
  }

  @Override
  public List<Operation> getAllOperations() {
    List<Operation> result = new ArrayList<>();
    Collection<List<Operation>> valueSet = operationOfShapes.values();
    for (List<Operation> operationList : valueSet) {
      result.addAll(operationList);
    }
    Collections.sort(result);
    return result;
  }

  @Override
  public Map<String, List<Operation>> getAllShapeOperations() {
    Map<String, List<Operation>> result = new HashMap<>();
    Set<String> keySet = operationOfShapes.keySet();
    for (String key : keySet) {
      List<Operation> temp = new ArrayList<>(operationOfShapes.get(key));
      result.put(key, temp);
    }
    return result;
  }


  /**
   * Determine whether the given operation on the targeted shape is valid.
   *
   * @param typeOperation the type of this current operation
   * @param nameOfShape   the name of the targeted shape
   * @param startTime     the start time of this operation
   * @param endTime       the end time of this operation
   * @return true if this operation is valid, otherwise false
   */
  private boolean isValid(String typeOperation, String nameOfShape,
                          float startTime, float endTime) {
    boolean result = true;
    List<Operation> listOfOperation = operationOfShapes.get(nameOfShape);
    for (Operation aListOfOperation : listOfOperation) {
      if (aListOfOperation.getTypeOfOperation().equals("create")) {
        if (startTime < aListOfOperation.getStartTime()
                || endTime > aListOfOperation.getEndTime()) {
          result = false;
          break;
        }
      }
      if (aListOfOperation.getTypeOfOperation().equals(typeOperation)) {
        if (isOverlap(aListOfOperation.getStartTime(), aListOfOperation.getEndTime(),
                startTime, endTime)) {
          result = false;
          break;
        }
      }
    }
    return result;
  }

  /**
   * Determine whether the two given time intervals overlap.
   *
   * @param startTime1 the start time of the first time interval
   * @param endTime1   the end time of the first time interval
   * @param startTime2 the start time of the second time interval
   * @param endTime2   the end time of the second time interval
   * @return true if the the given two time intervals overlap, otherwise false
   */
  private boolean isOverlap(float startTime1, float endTime1,
                            float startTime2, float endTime2) {
    boolean result = (startTime2 <= endTime2 && endTime2 <= startTime1)
            || (startTime2 >= endTime1 && endTime2 >= startTime2);
    return !result;
  }


  /**
   * An adapter class. Adapts the AnimationModel Interface to the AnimationFileReader
   * Interface.
   */
  public static final class Builder implements TweenModelBuilder<AnimationModel> {
    private AnimationModel model;
    private int speedTempo;

    /**
     * Construct an object of the model builder with the given speed of animation.
     *
     * @param speed the given speed of animation, ticks per second
     */
    public Builder(int speed) {
      this.model = new AnimationModelImpl();
      this.speedTempo = speed;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addOval(String name,
                                                     float cx, float cy, float xRadius,
                                                     float yRadius, float red, float green,
                                                     float blue, int startOfLife, int endOfLife) {
      Point location = new Point(cx, cy);
      float startTime = (float) startOfLife / speedTempo;
      float endTime = (float) endOfLife / speedTempo;
      Shape oval = new Oval(location, new Color(red, green, blue), name, xRadius, yRadius);
      model.createShape(name, oval, startTime, endTime);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addRectangle(String name,
                                                          float lx, float ly, float width,
                                                          float height, float red, float green,
                                                          float blue, int startOfLife,
                                                          int endOfLife) {
      Point location = new Point(lx, ly);
      Shape rectangle = new Rectangle(location, new Color(red, green, blue), name, width, height);
      float startTime = (float) startOfLife / speedTempo;
      float endTime = (float) endOfLife / speedTempo;
      model.createShape(name, rectangle, startTime, endTime);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addMove(String name, float moveFromX, float moveFromY,
                                                     float moveToX, float moveToY, int startTime,
                                                     int endTime) {
      Point startPoint = new Point(moveFromX, moveFromY);
      Point endPoint = new Point(moveToX, moveToY);
      float start = (float) startTime / speedTempo;
      float end = (float) endTime / speedTempo;
      model.move(name, startPoint, endPoint, start, end);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addColorChange(String name, float oldR, float oldG,
                                                            float oldB, float newR, float newG,
                                                            float newB, int startTime,
                                                            int endTime) {
      Color oldColor = new Color(oldR, oldG, oldB);
      Color newColor = new Color(newR, newG, newB);
      float start = (float) startTime / speedTempo;
      float end = (float) endTime / speedTempo;
      model.changeColor(name, oldColor, newColor, start, end);

      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addScaleToChange(String name, float fromSx,
                                                              float fromSy, float toSx, float toSy,
                                                              int startTime, int endTime) {
      float start = (float) startTime / speedTempo;
      float end = (float) endTime / speedTempo;
      model.scaling(name, fromSx, fromSy, toSx, toSy, start, end);
      return this;
    }

    @Override
    public AnimationModel build() {
      return model;
    }
  }
}
