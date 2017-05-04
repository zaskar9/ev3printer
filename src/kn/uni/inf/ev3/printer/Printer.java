/*
 * Printer.java   1.0   May 4, 2017
 *
 * Copyright (c) 2017-2017 University of Konstanz.
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
 * LeJOS printer implementation for the LEGO® Mindstorms "BANNER PRINT3R" model. The model has three EV3
 * motors that are connected to the following ports. The first large motor that lifts and lowers the pen
 * ({@code penLifter}) is connected to port A, the second large motor that controls the paper feed
 * ({@code paperFeeder}) is connected to port B, and the medium motor that moves the pen ({@code penMover}) is
 * connected to port C.
 */
public class Printer {

   /** Motor controlling the pen movement. */
   private final RegulatedMotor penMover;

   /** Motor controlling the paper feed. */
   private final RegulatedMotor paperFeeder;

   /** Motor lifting and lowering the pen. */
   private final RegulatedMotor penLifter;

   /**
    * Instantiates and sets up the printer.
    */
   public Printer() {
      this.penLifter = new EV3LargeRegulatedMotor(MotorPort.A);
      this.paperFeeder = new EV3LargeRegulatedMotor(MotorPort.B);
      this.penMover = new EV3MediumRegulatedMotor(MotorPort.C);
   }

   /**
    * Lifts the pen and blocks until the action completes.
    */
   public void liftPen() {
      this.penLifter.rotate(-180);
   }

   /**
    * Lowers the pen and blocks until the action completes.
    */
   public void lowerPen() {
      this.penLifter.rotate(180);
   }

   /**
    * Draws a circle.
    */
   public void drawCircle() {
      this.lowerPen();
      System.out.println("Draw circle...");
      this.liftPen();
   }

}
