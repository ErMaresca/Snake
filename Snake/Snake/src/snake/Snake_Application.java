package snake;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.ColorDescriptor;
import org.eclipse.swt.graphics.RGB;

public class Snake_Application {
	private Gioco gioco;
	
	private Display display;
	private Shell shell;
	private LocalResourceManager localResourceManager;
	private Color sfondo; // colore sfondo
	private Canvas canvas;
	private GridData gridData; // caratteristiche griglia
	private Label lblPunteggio; // punteggio utente
	private Label lblSnake; // titolo
	
	private final int NUM_CELLE = 20;
	private final int DIMENSIONE_CELLE = 25;
	
	private final int LARGHEZZA = 30;	// larghezza campo di gioco (in celle)
	private final int ALTEZZA = 30;		// altezza campo di gioco (in celle)
	
	private int[][] campo = new int[LARGHEZZA][ALTEZZA]; // griglia di gioco
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Snake_Application window = new Snake_Application();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		createResourceManager();
		shell.setSize(1280,960);
		shell.setText("Snake");
		
		sfondo = new Color(display,101, 67, 33);
		shell.setBackground(sfondo);
		shell.setLayout(new GridLayout(1, false));
		
		
		lblSnake = new Label(shell, SWT.CENTER);
		lblSnake.setBackground(localResourceManager.create(ColorDescriptor.createFrom(new RGB(101, 67, 33))));
		lblSnake.setFont(localResourceManager.create(FontDescriptor.createFrom("Viner Hand ITC", 28, SWT.BOLD)));
		lblSnake.setText("SNAKE");
		gridData = new GridData(SWT.CENTER, SWT.CENTER, true, false);
		lblSnake.setLayoutData(gridData);
		
		// label punteggio
		lblPunteggio = new Label(shell, SWT.NONE);
		lblPunteggio.setAlignment(SWT.CENTER);
		
		canvas = new Canvas(shell, SWT.NONE);
		canvas.setBackground(localResourceManager.create(ColorDescriptor.createFrom(new RGB(101, 67, 33))));
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		canvas.addPaintListener(new PaintListener() {
		        public void paintControl(PaintEvent e) {
		            creaGriglia();
		        }
		    });
	}
	
	private void createResourceManager() {
		localResourceManager = new LocalResourceManager(JFaceResources.getResources(),shell);
	}
	
	private void creaGriglia() {
	    int canvasWidth = canvas.getClientArea().width;
	    int canvasHeight = canvas.getClientArea().height;

	    int gridWidth = LARGHEZZA * DIMENSIONE_CELLE;
	    int gridHeight = ALTEZZA * DIMENSIONE_CELLE;

	    int marginX = (canvasWidth - gridWidth) / 2;
	    int marginY = (canvasHeight - gridHeight) / 2;

	    GC gc = new GC(canvas);
	    gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));

	    for (int i = 0; i < LARGHEZZA; i++) {
	        for (int j = 0; j < ALTEZZA; j++) {
	            gc.drawRectangle(marginX + i * DIMENSIONE_CELLE, marginY + j * DIMENSIONE_CELLE, DIMENSIONE_CELLE, DIMENSIONE_CELLE);
	        }
	    }
	    gc.dispose();
	}
}
