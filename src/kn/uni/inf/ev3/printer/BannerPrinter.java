/*
 * Printer.java   1.0   May 4, 2017
 *
 * Copyright (c) 2017 University of Konstanz.
 *
 * This software is the proprietary information of the above-mentioned institutions.
 * Use is subject to license terms. Please refer to the included copyright notice.
 */
package kn.uni.inf.ev3.printer;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * LeJOS printer implementation for the LEGO® Mindstorms "BANNER PRINT3R" model.
 */
public class BannerPrinter {

   /** The A motor handles the lifting and lowering of the pen. */
   private final RegulatedMotor aMotor;

   /** The B motor handles the X coordinates for the paper. */
   private final RegulatedMotor bMotor;

   /** The C motor handles the Y coordinates for the pen. */
   private final RegulatedMotor cMotor;

   /**
    * Instantiates and sets up the printer.
    */
   public BannerPrinter() {
      this.aMotor = new EV3LargeRegulatedMotor(MotorPort.A);
      this.aMotor.resetTachoCount();
      this.bMotor = new EV3LargeRegulatedMotor(MotorPort.B);
      this.bMotor.resetTachoCount();
      this.cMotor = new EV3MediumRegulatedMotor(MotorPort.C);
      this.cMotor.resetTachoCount();
      this.bMotor.synchronizeWith(new RegulatedMotor[] { this.cMotor });
   }

   /**
    * This method is the basic building block of this little application. You can tell the block to draw a
    * line to any absolute target coordinate. The {@code liftPen} parameter lifts or lowers the pen: use
    * {@code true} for lifted and {@code false} for lowered. Note this assumes that the program starts with
    * the pen in the up position! The {@code x} parameter moves the paper left and right. You can use any
    * value for {@code x}, but a reasonable character width is {@code 50}. The {@code y} parameter moves the
    * pen up and down: {@code 0} is the lowest value, and {@code 100} is the highest value.
    *
    * @param x
    *           X-coordinate of the pen (left and right)
    * @param y
    *           Y-coordinate of the pen (up and down)
    * @param liftPen
    *           {@code true} if the pen should be lifted, {@code false} otherwise
    */
   private void plotStep(final int x, final int y, final boolean liftPen) {
      this.setPenState(liftPen);
      this.bMotor.setSpeed(200);
      this.cMotor.setSpeed(750);
      final int bAngle = (int) (x * 3.6 - this.bMotor.getTachoCount());
      final int cAngle = (int) (y * 3.0 - this.cMotor.getTachoCount());
      this.bMotor.startSynchronization();
      this.bMotor.rotate(bAngle, true);
      this.cMotor.rotate(cAngle, true);
      this.bMotor.endSynchronization();
      this.bMotor.waitComplete();
      this.cMotor.waitComplete();
   }

   /**
    * Sets the state of the pen, i.e., whether it is lifted or lowered.
    *
    * @param liftPen
    *           {@code true} if the pen should be lifted, {@code false} otherwise
    */
   private void setPenState(final boolean liftPen) {
      final int angle = (liftPen ? 0 : 180) - this.aMotor.getTachoCount();
      this.aMotor.rotate(angle);
      this.aMotor.waitComplete();
   }

   /**
    * Prints a blank of 10 units and resets the coordinate system for the next letter.
    */
   private void printBlank() {
      this.bMotor.resetTachoCount();
      this.plotStep(10, 0, true);
      this.bMotor.resetTachoCount();
   }

   /**
    * Prints the letter "E".
    */
   private void printE() {
      this.plotStep(50, 100, true);
      this.plotStep(0, 100, false);
      this.plotStep(0, 0, false);
      this.plotStep(50, 0, false);
      this.plotStep(0, 50, true);
      this.plotStep(50, 50, false);
      this.printBlank();
   }

   /**
    * Prints the letter "L".
    */
   private void printL() {
      this.plotStep(0, 100, true);
      this.plotStep(0, 0, false);
      this.plotStep(50, 0, false);
      this.printBlank();
   }

   /**
    * Prints the letter "V".
    */
   private void printV() {
      this.plotStep(0, 100, true);
      this.plotStep(30, 0, false);
      this.plotStep(60, 100, false);
      this.printBlank();
   }

   /**
    * Prints the "LEGO EV3" logo.
    */
   public void print() {
      // Print the V
      this.printV();
      // Print the E
      // this.printE();
      // Print the L
      // this.printL();
   }

}

//private void plotStep(final int x, final int y, final boolean liftPen) {
//   this.setPenState(liftPen);
//   int bTacho = this.bMotor.getTachoCount();
//   int cTacho = this.cMotor.getTachoCount();
//   final int bDegrees = (int) (x * 3.6 - bTacho);
//   final int cDegrees = (int) (y * 3.1 - cTacho);
//   float bAbsDegrees = Math.abs(bDegrees);
//   float cAbsDegrees = Math.abs(cDegrees);
//   boolean bRunning = false;
//   boolean cRunning = false;
//   if (bDegrees == 0) {
//      this.bMotor.setPower(0);
//   } else {
//      this.bMotor.setPower(50);
//      if (bDegrees > 0) {
//         this.bMotor.forward();
//      } else {
//         this.bMotor.backward();
//      }
//      bRunning = true;
//   }
//   if (cDegrees == 0) {
//      this.cMotor.setPower(0);
//   } else {
//      this.cMotor.setPower(80);
//      if (cDegrees > 0) {
//         this.cMotor.forward();
//      } else {
//         this.cMotor.backward();
//      }
//      cRunning = true;
//   }
//   while (bRunning || cRunning) {
//      if (Math.abs(this.bMotor.getTachoCount() - bTacho) >= bAbsDegrees) {
//         this.bMotor.stop();
//         bRunning = false;
//      }
//      if (Math.abs(this.cMotor.getTachoCount() - cTacho) >= cAbsDegrees) {
//         this.cMotor.stop();
//         cRunning = false;
//      }
//   }
//}
