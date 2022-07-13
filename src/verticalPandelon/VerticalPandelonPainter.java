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
		PandelonPainter.setSize(800, 700);
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
	
	int minDistance = 0;	//FromFoundationToHeavier
	int maxDistance = 500;	//FromFoundationToHeavier
	
	boolean isMovingUp = false;
	int animationShift = minDistance;

	int xStart = 300, yStart = 100, foundationWidth = 70;
	int foundationX2 = foundationWidth + xStart;
	int heavierWidth = 30, heavierHeight = 100;
	// *Counting variables
	int fastenX = xStart + (foundationWidth / 2), fastenYEnd = yStart,

			heavierX = fastenX - (heavierWidth / 2);

	public void update(Graphics g) {
		// public void paint(Graphics g) {
		int w = getSize().width, h = getSize().height;
		BufferedImage bi = (BufferedImage) createImage(w, h);
		Graphics2D big = bi.createGraphics(); // 3
		///////////
		Line2D.Double fasten = new Line2D.Double(fastenX, yStart, fastenX, fastenYEnd + animationShift);
		Rectangle heavier = new Rectangle (heavierX, fastenYEnd + animationShift, heavierWidth, heavierHeight);
		//changing
		GeneralPath main = new GeneralPath(fasten);
		main.append(heavier, false);
		
		big.draw(main);
		//not changing
		Line2D.Double foundation = new Line2D.Double(xStart, yStart, foundationX2, yStart);
		GeneralPath firstLine = new GeneralPath(foundation);
		
		big.draw(firstLine);
		////////////////////////////////////////
		g.drawImage(bi, 0, 0, this);
	}
	public void go() throws Exception{
// //		minDistance += 30;		//початок координат не знаходиться у Фреймі
// //		maxDistance += 30;
		
		while(true) {
			if(isMovingUp) {
				while (animationShift > minDistance) {
					repaint();                    
					Thread.sleep(5);
					animationShift--;
				}
				isMovingUp = false;
			}else {
				while (animationShift < maxDistance) {
					repaint();                    
					Thread.sleep(3);
					animationShift++;
				}
				isMovingUp = true;
			}
		}
	}
}
