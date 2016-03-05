package tela1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseEvent;

public class Principal extends Shell {
	private CLabel lblImagem1;
	private CLabel lblImagem2;
	private Button btnImagem1;
	private Button btnImagem2;
	private Label lblNewLabel;
	private Label lblR;
	private Label lblB;
	private Label lblG;
	private ScrolledComposite scrolledComposite;
	private ScrolledComposite scrolledComposite_1;
	private ScrolledComposite scrolledComposite_2;
	private CLabel lblImagem3;
	private Label lblCor;
	private Label lblPos;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Principal shell = new Principal(display);
			new Funcoes(shell);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public Principal(Display display) {
		super(display, SWT.SHELL_TRIM);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(0, 0, 952, 442);
		
		CTabFolder tabFolder = new CTabFolder(composite, SWT.BORDER);
		tabFolder.setBounds(10, 10, 932, 144);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setText("New Item");
		
		CTabItem tabItem_2 = new CTabItem(tabFolder, SWT.NONE);
		tabItem_2.setText("New Item");
		
		lblNewLabel = new Label(tabFolder, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		tabItem_2.setControl(lblNewLabel);
		lblNewLabel.setText("PENDENTE");
		
		CTabItem tabItem_1 = new CTabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("New Item");
		
		btnImagem1 = new Button(composite, SWT.NONE);
		btnImagem1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Funcoes.abreImagem(1,lblImagem1);
			}
		});
		btnImagem1.setBounds(10, 160, 75, 25);
		btnImagem1.setText("Imagem 1");
		
		btnImagem2 = new Button(composite, SWT.NONE);
		btnImagem2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnImagem2.setText("Imagem 2");
		btnImagem2.setBounds(303, 160, 75, 25);
		
		lblR = new Label(composite, SWT.NONE);
		lblR.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblR.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblR.setBounds(384, 160, 62, 25);
		lblR.setText("R:");
		
		lblG = new Label(composite, SWT.RIGHT);
		lblG.setAlignment(SWT.LEFT);
		lblG.setText("G:");
		lblG.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblG.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblG.setBounds(446, 160, 62, 25);
		
		lblB = new Label(composite, SWT.NONE);
		lblB.setText("B:");
		lblB.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		lblB.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblB.setBounds(517, 160, 62, 25);
		
		scrolledComposite = new ScrolledComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 191, 287, 241);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		lblImagem1 = new CLabel(scrolledComposite, SWT.BORDER);
		scrolledComposite.setContent(lblImagem1);
		//scrolledComposite.setMinSize(lblImagem1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		lblImagem1.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				int x = scrolledComposite.toControl(display.getCursorLocation()).x;
				int y = scrolledComposite.toControl(display.getCursorLocation()).y;
				Funcoes.capturaPixel(x, y, lblPos, lblCor, lblR, lblG, lblB);
			}
		});
		
		scrolledComposite_1 = new ScrolledComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_1.setExpandVertical(true);
		scrolledComposite_1.setExpandHorizontal(true);
		scrolledComposite_1.setBounds(303, 191, 287, 241);
		
		
		
		lblImagem2 = new CLabel(scrolledComposite_1, SWT.BORDER);
		lblImagem2.setText("");
		scrolledComposite_1.setContent(lblImagem2);
		scrolledComposite_1.setMinSize(lblImagem2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		scrolledComposite_2 = new ScrolledComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_2.setExpandVertical(true);
		scrolledComposite_2.setExpandHorizontal(true);
		scrolledComposite_2.setBounds(655, 191, 287, 241);
		
		lblImagem3 = new CLabel(scrolledComposite_2, SWT.BORDER);
		lblImagem3.setText("");
		scrolledComposite_2.setContent(lblImagem3);
		scrolledComposite_2.setMinSize(lblImagem3.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		lblCor = new Label(composite, SWT.NONE);
		lblCor.setBounds(585, 160, 55, 25);
		
		lblPos = new Label(composite, SWT.NONE);
		lblPos.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblPos.setText("500,500");
		lblPos.setBounds(655, 165, 75, 20);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Tratamento de Imagens");
		setSize(968, 481);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
