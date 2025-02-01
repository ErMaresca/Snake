package snake;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.GC;

public class Gioco {
	private Display display;
	private Canvas canvas;
	private GC gc;
	
	private boolean collisione = false;
	private int punteggio = 0;
	
	private Cibo cibo;
	
	public Gioco(Display display, Canvas canvas) {
		this.display = display;
		this.canvas = canvas;
		this.gc = new GC(canvas);
		this.cibo = new Cibo(display);
	}
	
	public void aggiornaPosizioneCibo() {
		cibo.posizionaCibo();
	}
}
