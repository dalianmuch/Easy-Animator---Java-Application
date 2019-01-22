package cs5004.animator.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.Operation;
import cs5004.animator.model.Shape;
import cs5004.animator.util.AnimationFileReader;
import cs5004.animator.view.AbstractView;
import cs5004.animator.view.AnimationView;

/**
 * Implements the controller part with the given type of input.
 */
public class AnimationControllerImpl implements AnimationController {
  private Readable in;

  /**
   * Constructs an object of the controller with the given input source.
   * @param in the given input source
   */
  public AnimationControllerImpl(Readable in) {
    this.in = in;
  }

  @Override
  public void goControl() {
    Scanner scan = new Scanner(in);
    List<String> input = new ArrayList<>();
    int speed = 1;
    String inputFile = "";
    String typeView = "";
    String outputFile = "";
    int mandatoryIfFlag = 0;
    int mandatoryIvFlag = 0;
    PrintStream outPut = System.out;
    while (scan.hasNext()) {
      input.add(scan.next());
    }
    for (int i = 0; i < input.size(); i++) {
      switch (input.get(i)) {
        case "-if":
          try {
            inputFile = input.get(i + 1);
            AnimationModelImpl.Builder modelBuilder = new AnimationModelImpl.Builder(speed);
            new AnimationFileReader().readFile(inputFile, modelBuilder);
            i++;
            mandatoryIfFlag++;
          } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The name of the animation file is invalid",
                    "Arguments error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
          }
          break;
        case "-iv":
          try {
            if (input.get(i + 1).equals("text") || (input.get(i + 1).equals("svg"))) {
              typeView = input.get(i + 1);
              i++;
              mandatoryIvFlag++;
            } else {
              JOptionPane.showMessageDialog(null, "The type of view is invalid",
                      "Arguments error", JOptionPane.ERROR_MESSAGE);
              System.exit(1);
            }
          } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The type of view is invalid",
                    "Arguments error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
          }
          break;
        case "-speed":
          try {
            speed = Integer.valueOf(input.get(i + 1));
            i++;
          } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The type of speed is invalid",
                    "Arguments error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
          }
          break;
        case "-o":
          try {
            outputFile = input.get(i + 1);
            if (outputFile.contains(".txt")) {
              if (!typeView.equals("text")) {
                JOptionPane.showMessageDialog(null, "The type of output file should be consistent "
                                + "with the type of view",
                        "Arguments error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
              }
            } else if (outputFile.contains(".svg")) {
              if (!typeView.equals("svg")) {
                JOptionPane.showMessageDialog(null, "The type of output file should be consistent "
                                + "with the type of view",
                        "Arguments error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
              }
            } else if (!outputFile.equals("out")) {
              JOptionPane.showMessageDialog(null, "The name of output file is invalid",
                      "Arguments error", JOptionPane.ERROR_MESSAGE);
              System.exit(1);
            }
            i++;
          } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The name of output file is invalid",
                    "Arguments error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
          }
          break;
        default:
          JOptionPane.showMessageDialog(null, "You have entered an invalid input",
                  "Arguments error",
                  JOptionPane.ERROR_MESSAGE);
          System.exit(1);
      }
    }
    if (!(mandatoryIfFlag == 1 && mandatoryIvFlag == 1)) {
      JOptionPane.showMessageDialog(null, "The number of -if and -iv is invalid",
              "Arguments error", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    } else {
      if (outputFile.contains(".svg") || outputFile.contains(".txt")) {
        AnimationModelImpl.Builder modelBuilder = new AnimationModelImpl.Builder(speed);
        try {
          AnimationModel model1 = new AnimationFileReader().readFile(inputFile,
                  modelBuilder);
          Map<String, Shape> mapOfShape = model1.getAllShape();
          List<Operation> listOfOperation = model1.getAllOperations();
          Map<String, List<Operation>> allShapeOperations = model1.getAllShapeOperations();
          outPut = new PrintStream(new FileOutputStream(new File(outputFile), true));
          printOut(outPut, typeView, listOfOperation, mapOfShape, allShapeOperations);
          // This following operation is really important for print string to a file.
          outPut.flush();
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "The name of the animation file is invalid",
                  "Arguments error", JOptionPane.ERROR_MESSAGE);
          System.exit(1);
        }

      } else {
        AnimationModelImpl.Builder modelBuilder = new AnimationModelImpl.Builder(speed);
        try {
          AnimationModel model1 = new AnimationFileReader().readFile(inputFile,
                  modelBuilder);
          Map<String, Shape> mapOfShape = model1.getAllShape();
          List<Operation> listOfOperation = model1.getAllOperations();
          Map<String, List<Operation>> allShapeOperations = model1.getAllShapeOperations();
          printOut(outPut, typeView, listOfOperation, mapOfShape, allShapeOperations);
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "The name of the animation file is invalid",
                  "Arguments error", JOptionPane.ERROR_MESSAGE);
          System.exit(1);
        }
      }
    }
  }

  /**
   * Creates different views with the given type of view and print out the final output text.
   *
   * @param outPut             the destination where the output goes
   * @param typeView           the type of view to be created
   * @param listOfOperation    all operations in this animation in a
   *                           chronologically ascending order
   * @param mapOfShape         all shapes appear in this animation with their respective names
   * @param allShapeOperations A table contains all shapes with their individual set of operations
   *                           applying to them
   */
  private void printOut(Appendable outPut, String typeView, List<Operation> listOfOperation,
                        Map<String, Shape> mapOfShape,
                        Map<String, List<Operation>> allShapeOperations) {
    try {
      if (typeView.equals("svg")) {
        AnimationView svgView = AbstractView.createView("svg", outPut, mapOfShape,
                allShapeOperations, listOfOperation);
        svgView.outPut();
      } else {
        AnimationView textView = AbstractView.createView("text", outPut, mapOfShape,
                allShapeOperations, listOfOperation);
        textView.outPut();
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "The name of output file is invalid",
              "Arguments error", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
  }
}
