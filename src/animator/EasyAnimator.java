package cs5004.animator;

import java.io.StringReader;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.controller.AnimationControllerImpl;

/**
 * The entry of this whole program.
 */
public final class EasyAnimator {
  /**
   * Give arguments and control to the controller.
   *
   * @param args the arguments given by user.
   */
  public static void main(String[] args) {

    String inPut = String.join(" ", args);
    Readable in = new StringReader(inPut);
    AnimationController controller = new AnimationControllerImpl(in);
    controller.goControl();

  }
}
