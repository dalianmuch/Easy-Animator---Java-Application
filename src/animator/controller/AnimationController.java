package cs5004.animator.controller;

/**
 * Interprets the arguments passed by the main() method and creates the
 * corresponding model and view. Gets key information from model and asks view to output the
 * final result based on those information.
 */
public interface AnimationController {

  /**
   * Gain the control of the whole program.
   */
  void goControl();

}
