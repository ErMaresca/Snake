package snake;

import java.util.Random;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.GC;

public class Cibo implements Runnable{
	Random random = new Random();
	
	private Display display;
	private Canvas canvas;
	private GC gc;
	private ImageData dettagliImmagine;
	private Image immagineCibo;
	
	private boolean fineGioco = false;
	
	private final int LARGHEZZA = 30;
	private final int ALTEZZA = 30;
	
	private int posizioneX;
	private int posizioneY; 
	
	private boolean ciboMangiato = false;
	
	  public Cibo(Display display) {
	        this.display = display;
	        this.dettagliImmagine = new ImageData("immagini/pizza.JPG");
	        this.immagineCibo = new Image(display, dettagliImmagine.scaledTo(25, 25));
	        generaCordinateCibo();
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
					generaCordinateCibo();
					posizionaCibo();
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
	
	private void generaCordinateCibo() {
		posizioneX = random.nextInt(LARGHEZZA)*25;
		posizioneY = random.nextInt(ALTEZZA)*25;
	}
	
	public void posizionaCibo() {		
		gc.drawImage(immagineCibo, posizioneX, posizioneY);
	}
	
}