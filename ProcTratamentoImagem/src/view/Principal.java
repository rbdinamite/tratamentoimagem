package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import pdi.Processamento;
import utils.Funcoes;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseAdapter;

public class Principal extends Shell {
	private CLabel lblImagem1;
	private Button btnImagem1;
	private Button btnImagem2;
	private Label lblNewLabel;
	private Label lblR;
	private Label lblB;
	private Label lblG;
	private ScrolledComposite scrolledComposite;
	private ScrolledComposite scrolledComposite_2;
	private CLabel lblImagem3;
	private Label lblCor;
	private Label lblPos;
	ArrayList<String> listaCordenadas = new ArrayList<String>(); 
	static Funcoes funcoes;
	private ScrolledComposite scrolledComposite_1;
	private CLabel lblImagem2;
	private Button btnMediana2;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Principal shell = new Principal(display);
			funcoes = new Funcoes(shell);
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
		
		CTabItem acaoFiltro = new CTabItem(tabFolder, SWT.NONE);
		acaoFiltro.setText("Filtro");
		
		Composite compositeFiltro = new Composite(tabFolder, SWT.NONE);
		acaoFiltro.setControl(compositeFiltro);
		
		Button btnMediana1 = new Button(compositeFiltro, SWT.NONE);
		btnMediana1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Processamento proc = new Processamento();
				try {
					System.out.println("Arquivo -> "+funcoes.getFilePath1());
					BufferedImage mediana = proc.filtroMediana(1, ImageIO.read(new File(funcoes.getFilePath1())));
					ImageIO.write(mediana, "jpg", new File("images/_mediana.jpg"));
					Image Image3 = new Image(null, "images/_mediana.jpg");
					funcoes.setImage3(Image3);
					funcoes.abreImagem(3, lblImagem3);
				} catch (IOException e) {
					System.out.println("Erro iniciar função de filtro");
					e.printStackTrace();
				}
			}
		});
		btnMediana1.setBounds(10, 22, 168, 32);
		btnMediana1.setText("Fun\u00E7\u00E3o mediana - Imagem 1");
		
		btnMediana2 = new Button(compositeFiltro, SWT.NONE);
		btnMediana2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//APLICA FUNÇÃO MEDIANA NA IMAGEM 2
			}
		});
		btnMediana2.setText("Fun\u00E7\u00E3o mediana - Imagem 2");
		btnMediana2.setBounds(10, 60, 168, 32);
		
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
				funcoes.abreImagem(1,lblImagem1);
			}
		});
		btnImagem1.setBounds(10, 160, 75, 25);
		btnImagem1.setText("Imagem 1");
		
		btnImagem2 = new Button(composite, SWT.NONE);
		btnImagem2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				funcoes.abreImagem(2,lblImagem2);
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
		
		scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 191, 287, 241);
		lblImagem1 = new CLabel(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(lblImagem1);
		scrolledComposite.setMinSize(lblImagem1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		lblImagem1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				String texto = lblPos.getText()+","+lblR.getText().substring(3)+","+lblG.getText().substring(3)+","+lblB.getText().substring(3);
				listaCordenadas.add(texto);
				System.out.println("add -> "+texto);
			}
		});
		scrolledComposite.setMinSize(lblImagem1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		lblImagem1.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				funcoes.capturaPixel(1,arg0.x, arg0.y, lblPos, lblCor, lblR, lblG, lblB);
			}
		});
		
		scrolledComposite_1 = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_1.setBounds(303, 191, 287, 241);
		
		lblImagem2 = new CLabel(scrolledComposite_1, SWT.NONE);
		scrolledComposite_1.setContent(lblImagem2);
		scrolledComposite_1.setMinSize(lblImagem2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		lblImagem2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				String texto = lblPos.getText()+","+lblR.getText().substring(3)+","+lblG.getText().substring(3)+","+lblB.getText().substring(3);
				listaCordenadas.add(texto);
				System.out.println("add -> "+texto);
			}
		});
		scrolledComposite.setMinSize(lblImagem1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		lblImagem2.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				funcoes.capturaPixel(2,arg0.x, arg0.y, lblPos, lblCor, lblR, lblG, lblB);
			}
		});
		
		scrolledComposite_2 = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_2.setBounds(655, 191, 287, 241);
		
		lblImagem3 = new CLabel(scrolledComposite_2, SWT.BORDER);
		lblImagem3.setText("");
		scrolledComposite_2.setContent(lblImagem3);
		scrolledComposite_2.setMinSize(lblImagem3.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		lblCor = new Label(composite, SWT.NONE);
		lblCor.setBounds(585, 160, 55, 25);
		
		lblPos = new Label(composite, SWT.NONE);
		lblPos.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblPos.setBounds(655, 165, 75, 20);
		
		Label lblResultado = new Label(composite, SWT.NONE);
		lblResultado.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblResultado.setBounds(828, 160, 89, 25);
		lblResultado.setText("Resultado");
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
