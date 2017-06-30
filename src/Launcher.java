/*
 * Launcher.java   1.0   May 4, 2017
 *
 * Copyright (c) 2017-2017 University of Konstanz.
 *
 * This software is the proprietary information of the above-mentioned institutions.
 * Use is subject to license terms. Please refer to the included copyright notice.
 */

import kn.uni.inf.ev3.printer.BannerPrinter;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

/**
 * Launcher class of the LeJOS printer implementation for the LEGO® Mindstorms "BANNER PRINT3R" model.
 */
public class Launcher {

   public static void main(final String[] args) {
      final BannerPrinter p = new BannerPrinter();
      // p.drawCircle();
      LCD.drawString("Printing done.", 0, 3);
      Delay.msDelay(3000);
   }
}
