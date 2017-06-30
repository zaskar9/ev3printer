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

   /** The A motor handles the Y coordinates for the pen. */
   private final RegulatedMotor aMotor;

   /** The B motor handles the lifting and lowering of the pen. */
   private final RegulatedMotor bMotor;

   /** The C motor handles the X coordinates for the paper. */
   private final RegulatedMotor cMotor;

   /**
    * Instantiates and sets up the printer.
    */
   public BannerPrinter() {
      this.aMotor = new EV3MediumRegulatedMotor(MotorPort.A);
      this.bMotor = new EV3LargeRegulatedMotor(MotorPort.B);
      this.cMotor = new EV3LargeRegulatedMotor(MotorPort.C);
      this.aMotor.synchronizeWith(new RegulatedMotor[] { this.cMotor });
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
      this.aMotor.setSpeed(420);
      this.cMotor.setSpeed(300);
      int aAngle = (int) (y * 2.8 - this.aMotor.getTachoCount());
      int cAngle = (int) (x * 3.6 - this.cMotor.getTachoCount());
      this.aMotor.startSynchronization();
      this.aMotor.rotate(aAngle, true);
      this.cMotor.rotate(cAngle, true);
      this.aMotor.endSynchronization();
      this.aMotor.waitComplete();
      this.cMotor.waitComplete();
   }

   /**
    * Sets the state of the pen, i.e., whether it is lifted or lowered.
    * 
    * @param liftPen
    *           {@code true} if the pen should be lifted, {@code false} otherwise
    */
   private void setPenState(final boolean liftPen) {
      final int z = liftPen ? 0 : 180;
      int tacho = this.bMotor.getTachoCount();
      int angle = z - tacho;
      this.bMotor.rotate(angle);
      this.bMotor.waitComplete();
   }

   /**
    * Prints the "LEGO EV3" logo.
    */
   public void printLogo() {
      // Print the L
      this.plotStep(0, 100, true);
      this.plotStep(0, 0, false);
      this.plotStep(50, 0, false);
      this.plotStep(60, 0, true);
   }

}
