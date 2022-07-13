package verticalPandelon;

import java.awt.*;
import java.awt.event.*; //or: import java.awt.event.WindowAdapter;&import java.awt.event.WindowEvent;
import java.awt.geom.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class VerticalPandelonPainter extends Frame { // Розширяє або наслідує клас Frame
//			 subclass			 superclass
	public static void main(String[] args) throws Exception {
		VerticalPandelonPainter PandelonPainter = new VerticalPandelonPainter("MyPandelon");
		PandelonPainter.setSize(800, 900);
		PandelonPainter.setVisible(true);
		PandelonPainter.go();
	}

	VerticalPandelonPainter(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});
	}

	

	// Vars for declaring
	
	int minDistance = 10;	//From Foundation To Heavier
	int maxDistance = 200;	//From Foundation To Heavier
	
	boolean isMovingUp = true;
	int animationShift = minDistance;

	int xStart = 300, yStart = 100, foundationWidth = 70;
	int foundationX2 = foundationWidth + xStart;
	int heavierWidth = 30, heavierHeight = 100;

	Color upColor = Color.green.darker();
	Color downColor = Color.red;
	// *Counting variables
	Color heavierColor = Color.red;
	Color normal = Color.black;
	int fastenX = xStart + (foundationWidth / 2), fastenYEnd = yStart,
	heavierX = fastenX - (heavierWidth / 2);

	public void update(Graphics g) {
		// public void paint(Graphics g) {
		int w = getSize().width, h = getSize().height;
		BufferedImage bi = (BufferedImage) createImage(w, h);
		Graphics2D big = bi.createGraphics(); // 3
		//! /////////
		Line2D.Double fasten = new Line2D.Double(fastenX, yStart, fastenX, fastenYEnd + animationShift);
		Rectangle heavier = new Rectangle (heavierX, fastenYEnd + animationShift, heavierWidth, heavierHeight);
		//changing

		big.setColor(heavierColor);
		big.fill(heavier);
		
		big.setColor(normal);
		big.draw(fasten);
		//not changing
		Line2D.Double foundation = new Line2D.Double(xStart, yStart, foundationX2, yStart);
		Line2D.Double minDistanceBorder = new Line2D.Double(xStart + (foundationX2 - xStart) / 4, yStart + minDistance, foundationX2 - (foundationX2 - xStart) / 4, yStart + minDistance);
		Rectangle maxDistanceBorder = new Rectangle(xStart - (foundationX2 - xStart) / 4, yStart + maxDistance + heavierHeight, 100, 10);
		
		big.draw(foundation);
		big.setColor(downColor);
		big.draw(minDistanceBorder);
		big.setColor(upColor);
		big.fill(maxDistanceBorder);
		//! //////////////////////////////////////
		g.drawImage(bi, 0, 0, this);
	}
	public void go() throws Exception{
		
		while(true) {
			if(isMovingUp) {
				heavierColor = upColor;
				while (animationShift > minDistance) {
					repaint();                    
					Thread.sleep(8);
					animationShift--;
				}
				isMovingUp = false;
			}else {
				heavierColor = downColor;
				while (animationShift < maxDistance) {
					repaint();                    
					Thread.sleep(6);
					animationShift++;
				}
				isMovingUp = true;
			}
		}
	}
}
