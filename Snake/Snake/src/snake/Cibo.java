package snake;

import java.util.Random;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.GC;

public class Cibo implements Runnable{
	Random random = new Random();
	
	private boolean fineGioco = false;
	
	private Display display;
	private Canvas canvas;
	
	private final int LARGHEZZA = 30;
	private final int ALTEZZA = 30;
	
	private int posizioneX;
	private int posizioneY; 
	
	private boolean ciboMangiato = false;
	private ImageData dettagliImmagine;
	
	private Image immagineCibo;
	
	public Cibo(Display display, Canvas canvas) {
		this.display = display;
		this.canvas = canvas;
		this.dettagliImmagine = new ImageData("immagini/pizza.JPG");
		this.immagineCibo = new Image(display, dettagliImmagine.scaledTo(25, 25));
		generaPosizioneCibo();
	}
	
	public int getPosizioneX() {
		return posizioneX;
	}

	public void setPosizioneX(int posizioneX) {
		this.posizioneX = posizioneX;
	}

	public int getPosizioneY() {
		return posizioneY;
	}

	public void setPosizioneY(int posizioneY) {
		this.posizioneY = posizioneY;
	}

	public boolean isFineGioco() {
		return fineGioco;
	}

	public void setFineGioco(boolean fineGioco) {
		this.fineGioco = fineGioco;
	}

	@Override
    public void run() {
		while(!fineGioco) {
			if(ciboMangiato) {
				try {
					generaPosizioneCibo();
					ciboMangiato = false;
				}
				catch(Exception e) {
	                Thread.currentThread().interrupt();
	                return;
	            }
			}
			
			try {
				Thread.sleep(500);
			}
			catch(Exception e){
				Thread.currentThread().interrupt();
                return;
			}
		}
	}
	
	private void generaPosizioneCibo() {
		posizioneX = random.nextInt(LARGHEZZA)*25;
		posizioneY = random.nextInt(ALTEZZA)*25;
	}
	
	public void posizionaCibo(GC gc) {		
		gc.drawImage(immagineCibo, posizioneX, posizioneY);
	}
	
}