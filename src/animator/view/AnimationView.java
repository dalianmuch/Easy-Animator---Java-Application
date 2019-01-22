package cs5004.animator.view;

import java.io.IOException;

/**
 * This represents a view in this animation outputting the final text formatted result.
 */
public interface AnimationView {

  /**
   * output the animation based on the given shapes and operations.
   */
  void outPut() throws IOException;

}
