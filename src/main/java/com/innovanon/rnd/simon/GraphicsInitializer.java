/**
 * 
 */
package com.innovanon.rnd.simon;

import java.awt.Graphics;

import com.innovanon.rnd.ri.consumers.Initializer;
import com.innovanon.rnd.ri.functions.YInstantiator;
/**
 * @author gouldbergstein
 *
 */
public class GraphicsInitializer implements Initializer<Class<Graphics>,Graphics> {

	/* (non-Javadoc)
	 * @see java.util.function.Consumer#accept(java.lang.Object)
	 */
	@Override
	public void accept(Graphics t) {
		
		//t.setColor(c);
		//t.setFont(font);
		//t.setXORMode(c1);
		//
		//t.draw3DRect(x, y, width, height, raised);
		//t.drawArc(x, y, width, height, startAngle, arcAngle);
		// //t.drawBytes(data, offset, length, x, y);
		//t.drawChars(data, offset, length, x, y);
		//t.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
		//t.drawLine(x1, y1, x2, y2);
		//t.drawOval(x, y, width, height);
		//t.drawPolygon(p);
		//t.drawPolyline(xPoints, yPoints, nPoints);
		//t.drawRect(x, y, width, height);
		//t.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
		//t.drawString(str, x, y);
		//t.drawString(iterator, x, y);
		//
		//t.fill3DRect(x, y, width, height, raised);
		//t.fillArc(x, y, width, height, startAngle, arcAngle);
		//t.fillOval(x, y, width, height);
		//t.fillPolygon(xPoints, yPoints, nPoints);
		//t.fillRect(x, y, width, height);
		//t.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
		//
		//t.clearRect(x, y, width, height);
		//t.copyArea(x, y, width, height, dx, dy);
		//
		//	// TODO recursive
		//t.create(x, y, width, height);
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Graphics t) {
		// TODO Auto-generated method stub

		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.innovanon.rnd.yfunc.Delegator#setDelegate(com.innovanon.rnd.yfunc.Delegator)
	 */
	@Override
	public void setDelegate(YInstantiator<Class<Graphics>, Graphics> delegate) {
		// TODO Auto-generated method stub

		throw new UnsupportedOperationException();
	}
	
}
