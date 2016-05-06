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
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import pdi.Processamento;
import utils.Funcoes;

public class Principal extends Shell {
	private CLabel lblImagem1;
	private Button btnImagem1;
	private Button btnImagem2;
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
	private Button btnMedia1;
	private Button btnMedia2;
	private Composite compositeFiltro;
	private Combo comboTipoConsulta;
	private Text txtPropR;
	private Text txtPropG;
	private Text txtPropB;
	private Label lblErro;
	private CTabItem acaoLimiarizacao;
	private Composite composite_2;
	private CTabItem acaoNegativa;
	private Composite composite_3;
	private Button btnNegativaImagem1;
	private Button btnNegativaImagem2;
	private Button btnHistograma1;
	private Button btnHistograma2;
	private Button btnEqualizarImagem;
	private Button btnEqualizarImagem_1;
	private Button btnAdicao;
	private Button btnSubtracao;
	private Spinner txImg1;
	private Spinner txImg2;
	private CTabItem transparencia;
	private Composite composite_7;
	private Label label_4;
	private Slider transpImagem1;
	private Label label_5;
	private Slider transpImagem2;
	private Label label_6;
	private Label label_7;
	private Label label_8;
	private Label label_9;
	int x1=0,x2=0,y1=0,y2=0; // INDICES UTILIZADOS NO DESENHO DE FORMAS
	private Button btnSimplesImagem1Desenho;
	private Button btnSimplesImagem2_Desenho;
	private CTabItem tbtmInverso;
	private Composite composite_8;
	private Button btnInversao1;
	private Button btnInversao2;
	private Spinner nColunas;
	private Button btnInversaoDesenho1;
	private Button btnInversaoDesenho2;
	private CTabItem tbtmAnliseDeQuadrado;
	private Composite composite_10;
	private Button btnAnalisarImagem1;
	private Button btnAnalisarImagem2;
	private Label lblRespostaAnalise;
	

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
		setImage(SWTResourceManager.getImage("images/icon.jpg"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(0, 0, 952, 442);
		
		CTabFolder tabFolder = new CTabFolder(composite, SWT.BORDER);
		tabFolder.setBounds(10, 10, 932, 144);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem acaoFiltro = new CTabItem(tabFolder, SWT.NONE);
		acaoFiltro.setText("Filtro");
		
		compositeFiltro = new Composite(tabFolder, SWT.NONE);
		acaoFiltro.setControl(compositeFiltro);
		
		Button btnMediana1 = new Button(compositeFiltro, SWT.NONE);
		btnMediana1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath1() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						BufferedImage mediana = proc.filtroMediana(comboTipoConsulta.getSelectionIndex()+1, ImageIO.read(new File(funcoes.getFilePath1())), false);
						ImageIO.write(mediana, "jpg", new File("images/_mediana.jpg"));
						Image Image3 = new Image(null, "images/_mediana.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de filtro");
						e.printStackTrace();
					}
				}
			}
		});
		btnMediana1.setBounds(10, 22, 168, 32);
		btnMediana1.setText("Fun\u00E7\u00E3o mediana - Imagem 1");
		
		btnMediana2 = new Button(compositeFiltro, SWT.NONE);
		btnMediana2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath2() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath2());
						BufferedImage mediana = proc.filtroMediana(comboTipoConsulta.getSelectionIndex()+1, ImageIO.read(new File(funcoes.getFilePath2())),false);
						ImageIO.write(mediana, "jpg", new File("images/_mediana.jpg"));
						Image Image3 = new Image(null, "images/_mediana.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de filtro");
						e.printStackTrace();
					}
				}
			}
		});
		btnMediana2.setText("Fun\u00E7\u00E3o mediana - Imagem 2");
		btnMediana2.setBounds(10, 60, 168, 32);
		
		btnMedia1 = new Button(compositeFiltro, SWT.NONE);
		btnMedia1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath1() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						BufferedImage mediana = proc.filtroMediana(comboTipoConsulta.getSelectionIndex()+1, ImageIO.read(new File(funcoes.getFilePath1())), true);
						ImageIO.write(mediana, "jpg", new File("images/_mediana.jpg"));
						Image Image3 = new Image(null, "images/_mediana.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de filtro");
						e.printStackTrace();
					}
				}
			}
		});
		btnMedia1.setText("Fun\u00E7\u00E3o media - Imagem 1");
		btnMedia1.setBounds(194, 22, 168, 32);
		
		btnMedia2 = new Button(compositeFiltro, SWT.NONE);
		btnMedia2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath2() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath2());
						BufferedImage mediana = proc.filtroMediana(comboTipoConsulta.getSelectionIndex()+1, ImageIO.read(new File(funcoes.getFilePath2())),true);
						ImageIO.write(mediana, "jpg", new File("images/_mediana.jpg"));
						Image Image3 = new Image(null, "images/_mediana.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de filtro");
						e.printStackTrace();
					}
				}
			}
		});
		btnMedia2.setText("Fun\u00E7\u00E3o media - Imagem 2");
		btnMedia2.setBounds(194, 60, 168, 32);
		
		comboTipoConsulta = new Combo(compositeFiltro, SWT.NONE);
		comboTipoConsulta.setItems(new String[] {"3X3", "2X2 Diagonal", "2X2"});
		comboTipoConsulta.setToolTipText("");
		comboTipoConsulta.setBounds(440, 42, 99, 23);
		comboTipoConsulta.select(0);
		
		Label lblTipoDeConsulta = new Label(compositeFiltro, SWT.NONE);
		lblTipoDeConsulta.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblTipoDeConsulta.setBounds(432, 10, 134, 26);
		lblTipoDeConsulta.setText("Tipo de consulta:");
		
		CTabItem acaoEscalaCinza = new CTabItem(tabFolder, SWT.NONE);
		acaoEscalaCinza.setText("Escala de Cinza");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		acaoEscalaCinza.setControl(composite_1);
		
		Button btnSimplesImagem1 = new Button(composite_1, SWT.NONE);
		btnSimplesImagem1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath1() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						BufferedImage escalaCinza = proc.filtroEscalaCinza(1, ImageIO.read(new File(funcoes.getFilePath1())),0,0,0);
						ImageIO.write(escalaCinza, "jpg", new File("images/_escalaCinzaSimples.jpg"));
						Image Image3 = new Image(null, "images/_escalaCinzaSimples.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de escala de cinza");
						e.printStackTrace();
					}
				}
			}
		});
		btnSimplesImagem1.setText("Simples - Imagem 1");
		btnSimplesImagem1.setBounds(10, 10, 168, 32);
		
		Button btnSimplesImagem2 = new Button(composite_1, SWT.NONE);
		btnSimplesImagem2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath2() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath2());
						BufferedImage escalaCinza = proc.filtroEscalaCinza(1, ImageIO.read(new File(funcoes.getFilePath2())),0,0,0);
						ImageIO.write(escalaCinza, "jpg", new File("images/_escalaCinzaSimples.jpg"));
						Image Image3 = new Image(null, "images/_escalaCinzaSimples.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de escala de cinza");
						e.printStackTrace();
					}
				}
			}
		});
		btnSimplesImagem2.setText("Simples - Imagem 2");
		btnSimplesImagem2.setBounds(10, 55, 168, 32);
		
		Button btnPonderadaImagem1 = new Button(composite_1, SWT.NONE);
		btnPonderadaImagem1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (Integer.parseInt(txtPropR.getText())+
								Integer.parseInt(txtPropG.getText())+Integer.parseInt(txtPropB.getText()) != 100) {
					lblErro.setText("ERRO: A SOMA DEVE SER IGUAL A 100!");
				} else if (funcoes.getFilePath1() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						BufferedImage escalaCinza = proc.filtroEscalaCinza(2, ImageIO.read(new File(funcoes.getFilePath1())),Integer.parseInt(txtPropR.getText()),
								Integer.parseInt(txtPropG.getText()),Integer.parseInt(txtPropB.getText()));
						ImageIO.write(escalaCinza, "jpg", new File("images/_escalaCinzaPonderada.jpg"));
						Image Image3 = new Image(null, "images/_escalaCinzaPonderada.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de escala de cinza");
						e.printStackTrace();
					}
				}
			}
		});
		btnPonderadaImagem1.setText("Ponderada - Imagem 1");
		btnPonderadaImagem1.setBounds(212, 10, 168, 32);
		
		Button btnPonderadaImagem2 = new Button(composite_1, SWT.NONE);
		btnPonderadaImagem2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (Integer.parseInt(txtPropR.getText())+
						Integer.parseInt(txtPropG.getText())+Integer.parseInt(txtPropB.getText()) != 100) {
			lblErro.setText("ERRO: A SOMA DEVE SER IGUAL A 100!");
		} else if (funcoes.getFilePath2() != null) {
			Processamento proc = new Processamento();
			try {
				System.out.println("Arquivo -> "+funcoes.getFilePath2());
				BufferedImage escalaCinza = proc.filtroEscalaCinza(2, ImageIO.read(new File(funcoes.getFilePath2())),Integer.parseInt(txtPropR.getText()),
						Integer.parseInt(txtPropG.getText()),Integer.parseInt(txtPropB.getText()));
				ImageIO.write(escalaCinza, "jpg", new File("images/_escalaCinzaPonderada.jpg"));
				Image Image3 = new Image(null, "images/_escalaCinzaPonderada.jpg");
				funcoes.setImage3(Image3);
				funcoes.abreImagem(3, lblImagem3);
			} catch (IOException e) {
				System.out.println("Erro iniciar função de escala de cinza");
				e.printStackTrace();
			}
		}
			}
		});
		btnPonderadaImagem2.setText("Ponderada - Imagem 2");
		btnPonderadaImagem2.setBounds(212, 55, 168, 32);
		
		Label lblProporcao = new Label(composite_1, SWT.NONE);
		lblProporcao.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblProporcao.setBounds(435, 10, 142, 23);
		lblProporcao.setText("Propor\u00E7\u00E3o (Ponderada)");
		
		txtPropR = new Text(composite_1, SWT.BORDER);
		txtPropR.setText("40");
		txtPropR.setBounds(445, 39, 32, 23);
		
		txtPropG = new Text(composite_1, SWT.BORDER);
		txtPropG.setText("30");
		txtPropG.setBounds(496, 39, 32, 23);
		
		txtPropB = new Text(composite_1, SWT.BORDER);
		txtPropB.setText("30");
		txtPropB.setBounds(545, 39, 32, 23);
		
		Label lblr = new Label(composite_1, SWT.NONE);
		lblr.setBounds(455, 64, 25, 23);
		lblr.setText("%R");
		
		Label lblg = new Label(composite_1, SWT.NONE);
		lblg.setText("%G");
		lblg.setBounds(506, 64, 25, 23);
		
		Label lblb = new Label(composite_1, SWT.NONE);
		lblb.setText("%B");
		lblb.setBounds(552, 64, 25, 23);
		
		lblErro = new Label(composite_1, SWT.NONE);
		lblErro.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblErro.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblErro.setBounds(435, 86, 347, 23);
		
		btnSimplesImagem1Desenho = new Button(composite_1, SWT.NONE);
		btnSimplesImagem1Desenho.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath1() != null && x1 > 0 && y1 > 0 && x2 > 0 && y2 > 0) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						BufferedImage escalaCinza = proc.filtroEscalaCinzaComDesenho(ImageIO.read(new File(funcoes.getFilePath1())),x1,x2,y1,y2);
						ImageIO.write(escalaCinza, "jpg", new File("images/_escalaCinzaSimplesComDesenho.jpg"));
						Image Image3 = new Image(null, "images/_escalaCinzaSimplesComDesenho.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de escala de cinza com desenho");
						e.printStackTrace();
					}
				}
			}
		});
		btnSimplesImagem1Desenho.setText("Simples - Imagem 1 (Com desenho)");
		btnSimplesImagem1Desenho.setBounds(614, 10, 202, 32);
		
		btnSimplesImagem2_Desenho = new Button(composite_1, SWT.NONE);
		btnSimplesImagem2_Desenho.setText("Simples - Imagem 2 (Com desenho)");
		btnSimplesImagem2_Desenho.setBounds(614, 55, 202, 32);
		
		acaoLimiarizacao = new CTabItem(tabFolder, SWT.NONE);
		acaoLimiarizacao.setText("Limiariza\u00E7\u00E3o");
		
		composite_2 = new Composite(tabFolder, SWT.NONE);
		acaoLimiarizacao.setControl(composite_2);
		
		Slider sliderImagem1 = new Slider(composite_2, SWT.NONE);
		sliderImagem1.setMaximum(265);
		sliderImagem1.setSelection(100);
		sliderImagem1.addDragDetectListener(new DragDetectListener() {
			public void dragDetected(DragDetectEvent arg0) {
				if (funcoes.getFilePath1() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						BufferedImage escalaCinza = proc.filtroLimiarizacao(ImageIO.read(new File(funcoes.getFilePath1())),sliderImagem1.getSelection());
						System.out.println(sliderImagem1.getSelection());
						ImageIO.write(escalaCinza, "jpg", new File("images/_limiaziracao.jpg"));
						Image Image3 = new Image(null, "images/_limiaziracao.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de escala de cinza");
						e.printStackTrace();
					}
				}
			}
		});
		
		sliderImagem1.setBounds(47, 66, 170, 17);
		
		Slider sliderImagem2 = new Slider(composite_2, SWT.NONE);
		sliderImagem2.setMaximum(255);
		sliderImagem2.setSelection(100);
		sliderImagem2.addDragDetectListener(new DragDetectListener() {
			public void dragDetected(DragDetectEvent arg0) {
				if (funcoes.getFilePath2() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath2());
						BufferedImage escalaCinza = proc.filtroLimiarizacao(ImageIO.read(new File(funcoes.getFilePath2())),sliderImagem2.getSelection());
						ImageIO.write(escalaCinza, "jpg", new File("images/_limiaziracao.jpg"));
						Image Image3 = new Image(null, "images/_limiaziracao.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de escala de cinza");
						e.printStackTrace();
					}
				}
			}
		});

		sliderImagem2.setBounds(329, 66, 170, 17);
		
		Label lblImagem = new Label(composite_2, SWT.NONE);
		lblImagem.setAlignment(SWT.CENTER);
		lblImagem.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblImagem.setBounds(47, 33, 166, 27);
		lblImagem.setText("Imagem 1");
		
		Label lblImagem_1 = new Label(composite_2, SWT.NONE);
		lblImagem_1.setText("Imagem 2");
		lblImagem_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblImagem_1.setAlignment(SWT.CENTER);
		lblImagem_1.setBounds(333, 33, 166, 27);
		
		Label label = new Label(composite_2, SWT.NONE);
		label.setBounds(47, 89, 18, 15);
		label.setText("0");
		
		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setText("0");
		label_1.setBounds(329, 89, 18, 15);
		
		Label label_2 = new Label(composite_2, SWT.NONE);
		label_2.setText("255");
		label_2.setBounds(206, 89, 24, 15);
		
		Label label_3 = new Label(composite_2, SWT.NONE);
		label_3.setText("255");
		label_3.setBounds(491, 89, 24, 15);
		
		acaoNegativa = new CTabItem(tabFolder, SWT.NONE);
		acaoNegativa.setText("Negativa");
		
		composite_3 = new Composite(tabFolder, SWT.NONE);
		acaoNegativa.setControl(composite_3);
		
		btnNegativaImagem1 = new Button(composite_3, SWT.NONE);
		btnNegativaImagem1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath1() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						BufferedImage escalaCinza = proc.filtroNegativa(ImageIO.read(new File(funcoes.getFilePath1())));
						ImageIO.write(escalaCinza, "jpg", new File("images/_negativa.jpg"));
						Image Image3 = new Image(null, "images/_negativa.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de escala de cinza");
						e.printStackTrace();
					}
				}
			}
		});
		btnNegativaImagem1.setText("Negativa - Imagem 1");
		btnNegativaImagem1.setBounds(10, 34, 168, 32);
		
		btnNegativaImagem2 = new Button(composite_3, SWT.NONE);
		btnNegativaImagem2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath2() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath2());
						BufferedImage escalaCinza = proc.filtroNegativa(ImageIO.read(new File(funcoes.getFilePath2())));
						ImageIO.write(escalaCinza, "jpg", new File("images/_negativa.jpg"));
						Image Image3 = new Image(null, "images/_negativa.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de escala de cinza");
						e.printStackTrace();
					}
				}
			}
		});
		btnNegativaImagem2.setText("Negativa - Imagem 2");
		btnNegativaImagem2.setBounds(210, 34, 168, 32);
		
		CTabItem histograma = new CTabItem(tabFolder, SWT.NONE);
		histograma.setText("Histograma");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		histograma.setControl(composite_4);
		
		btnHistograma1 = new Button(composite_4, SWT.NONE);
		btnHistograma1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Processamento proc = new Processamento();
				try {
					proc.filtroHistograma(ImageIO.read(new File(funcoes.getFilePath1())));
				} catch (Exception e) {
					System.out.println("Erro ao criar histograma");
					e.printStackTrace();
				}
			}
		});
		btnHistograma1.setText("Histograma - Imagem 1");
		btnHistograma1.setBounds(10, 10, 168, 32);
		
		btnHistograma2 = new Button(composite_4, SWT.NONE);
		btnHistograma2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Processamento proc = new Processamento();
				try {
					proc.filtroHistograma(ImageIO.read(new File(funcoes.getFilePath2())));
				} catch (Exception e) {
					System.out.println("Erro ao criar histograma");
					e.printStackTrace();
				}
			}
		});
		btnHistograma2.setText("Histograma - Imagem 2");
		btnHistograma2.setBounds(10, 61, 168, 32);
		
		CTabItem filtroEqualizacao = new CTabItem(tabFolder, SWT.NONE);
		filtroEqualizacao.setText("Equalizacao");
		
		Composite composite_5 = new Composite(tabFolder, SWT.NONE);
		filtroEqualizacao.setControl(composite_5);
		
		btnEqualizarImagem = new Button(composite_5, SWT.NONE);
		btnEqualizarImagem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath1() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						BufferedImage equalizacao = proc.filtroEqualizacao(ImageIO.read(new File(funcoes.getFilePath1())));
						ImageIO.write(equalizacao, "jpg", new File("images/_equalizacao1.jpg"));
						Image Image3 = new Image(null, "images/_equalizacao1.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de equalizacao");
						e.printStackTrace();
					}
				}
			}
		});
		btnEqualizarImagem.setText("Equalizar - Imagem 1");
		btnEqualizarImagem.setBounds(10, 10, 168, 32);
		
		btnEqualizarImagem_1 = new Button(composite_5, SWT.NONE);
		btnEqualizarImagem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath2() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath2());
						BufferedImage equalizacao = proc.filtroEqualizacao(ImageIO.read(new File(funcoes.getFilePath2())));
						ImageIO.write(equalizacao, "jpg", new File("images/_equalizacao2.jpg"));
						Image Image3 = new Image(null, "images/_equalizacao2.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de equalizacao");
						e.printStackTrace();
					}
				}
			}
		});
		btnEqualizarImagem_1.setText("Equalizar - Imagem 2");
		btnEqualizarImagem_1.setBounds(10, 58, 168, 32);
		
		CTabItem adicaoSubtracao = new CTabItem(tabFolder, SWT.NONE);
		adicaoSubtracao.setText("Adi\u00E7\u00E3o e Subtra\u00E7\u00E3o");
		
		Composite composite_6 = new Composite(tabFolder, SWT.NONE);
		adicaoSubtracao.setControl(composite_6);
		
		Label lblTaxaImg = new Label(composite_6, SWT.NONE);
		lblTaxaImg.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblTaxaImg.setBounds(10, 21, 81, 27);
		lblTaxaImg.setText("Taxa IMG 1:");
		
		Label lblTaxaImg_1 = new Label(composite_6, SWT.NONE);
		lblTaxaImg_1.setText("Taxa IMG 2:");
		lblTaxaImg_1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblTaxaImg_1.setBounds(10, 65, 81, 27);
		
		txImg1 = new Spinner(composite_6, SWT.BORDER);
		txImg1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				txImg2.setSelection(100-txImg1.getSelection());
			}
		});
		txImg1.setSelection(50);
		txImg1.setBounds(93, 21, 47, 22);
		
		txImg2 = new Spinner(composite_6, SWT.BORDER);
		txImg2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				txImg1.setSelection(100-txImg2.getSelection());
			}
		});
		txImg2.setSelection(50);
		txImg2.setBounds(93, 64, 47, 22);
		
		btnAdicao = new Button(composite_6, SWT.NONE);
		btnAdicao.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath1() != null && funcoes.getFilePath2() != null) {
					Processamento proc = new Processamento();
					try {
						BufferedImage equalizacao = proc.filtroAdicaoSubtracao(ImageIO.read(new File(funcoes.getFilePath1())), ImageIO.read(new File(funcoes.getFilePath2())), 
								true, txImg1.getSelection(), txImg2.getSelection());
						ImageIO.write(equalizacao, "jpg", new File("images/_adicao.jpg"));
						Image Image3 = new Image(null, "images/_adicao.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de adicao");
						e.printStackTrace();
					}
				} else {
					System.out.println("ERRO: SELECIONE DUAS IMAGENS!");
				}
			}
		});
		btnAdicao.setBounds(204, 21, 120, 34);
		btnAdicao.setText("Adi\u00E7\u00E3o");
		
		btnSubtracao = new Button(composite_6, SWT.NONE);
		btnSubtracao.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath1() != null && funcoes.getFilePath2() != null) {
					Processamento proc = new Processamento();
					try {
						BufferedImage equalizacao = proc.filtroAdicaoSubtracao(ImageIO.read(new File(funcoes.getFilePath1())), ImageIO.read(new File(funcoes.getFilePath2())), 
								false, txImg1.getSelection(), txImg2.getSelection());
						ImageIO.write(equalizacao, "jpg", new File("images/_subtracao.jpg"));
						Image Image3 = new Image(null, "images/_subtracao.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de subtracao");
						e.printStackTrace();
					}
				} else {
					System.out.println("ERRO: SELECIONE DUAS IMAGENS!");
				}
			}
		});
		btnSubtracao.setText("Subtra\u00E7\u00E3o");
		btnSubtracao.setBounds(204, 58, 120, 34);
		
		transparencia = new CTabItem(tabFolder, SWT.NONE);
		transparencia.setText("Transpar\u00EAncia");
		
		composite_7 = new Composite(tabFolder, SWT.NONE);
		transparencia.setControl(composite_7);
		
		label_4 = new Label(composite_7, SWT.NONE);
		label_4.setText("Imagem 1");
		label_4.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		label_4.setAlignment(SWT.CENTER);
		label_4.setBounds(46, 33, 166, 27);
		
		transpImagem1 = new Slider(composite_7, SWT.NONE);
		transpImagem1.addDragDetectListener(new DragDetectListener() {
			public void dragDetected(DragDetectEvent arg0) {
				if (funcoes.getFilePath1() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						BufferedImage escalaCinza = proc.filtroTransparencia(ImageIO.read(new File(funcoes.getFilePath1())),transpImagem1.getSelection());
						System.out.println(transpImagem1.getSelection());
						ImageIO.write(escalaCinza, "jpg", new File("images/_transparencia.jpg"));
						Image Image3 = new Image(null, "images/_transparencia.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de escala de cinza");
						e.printStackTrace();
					}
				}
			}
		});
		transpImagem1.setMaximum(110);
		transpImagem1.setSelection(50);
		transpImagem1.setBounds(42, 66, 170, 17);
		
		label_5 = new Label(composite_7, SWT.NONE);
		label_5.setText("Imagem 2");
		label_5.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		label_5.setAlignment(SWT.CENTER);
		label_5.setBounds(278, 33, 166, 27);
		
		transpImagem2 = new Slider(composite_7, SWT.NONE);
		transpImagem2.addDragDetectListener(new DragDetectListener() {
			public void dragDetected(DragDetectEvent arg0) {
				if (funcoes.getFilePath2() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath2());
						BufferedImage escalaCinza = proc.filtroTransparencia(ImageIO.read(new File(funcoes.getFilePath2())),transpImagem2.getSelection());
						System.out.println(transpImagem2.getSelection());
						ImageIO.write(escalaCinza, "jpg", new File("images/_transparencia.jpg"));
						Image Image3 = new Image(null, "images/_transparencia.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de escala de cinza");
						e.printStackTrace();
					}
				}
			}
		});
		transpImagem2.setMaximum(110);
		transpImagem2.setSelection(50);
		transpImagem2.setBounds(274, 66, 170, 17);
		
		label_6 = new Label(composite_7, SWT.NONE);
		label_6.setText("0");
		label_6.setBounds(39, 89, 18, 15);
		
		label_7 = new Label(composite_7, SWT.NONE);
		label_7.setText("100");
		label_7.setBounds(204, 89, 18, 15);
		
		label_8 = new Label(composite_7, SWT.NONE);
		label_8.setText("0");
		label_8.setBounds(271, 89, 18, 15);
		
		label_9 = new Label(composite_7, SWT.NONE);
		label_9.setText("100");
		label_9.setBounds(435, 89, 18, 15);
		
		tbtmInverso = new CTabItem(tabFolder, SWT.NONE);
		tbtmInverso.setText("Invers\u00E3o");
		
		composite_8 = new Composite(tabFolder, SWT.NONE);
		tbtmInverso.setControl(composite_8);
		
		btnInversao1 = new Button(composite_8, SWT.NONE);
		btnInversao1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath1() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						BufferedImage inversao = proc.filtroInversao(ImageIO.read(new File(funcoes.getFilePath1())));
						ImageIO.write(inversao, "jpg", new File("images/_inversao.jpg"));
						Image Image3 = new Image(null, "images/_inversao.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de inversão da imagem");
						e.printStackTrace();
					}
				}
			}
		});
		btnInversao1.setText("Invers\u00E3o - Imagem 1");
		btnInversao1.setBounds(10, 10, 168, 32);
		
		btnInversao2 = new Button(composite_8, SWT.NONE);
		btnInversao2.setText("Invers\u00E3o - Imagem 2");
		btnInversao2.setBounds(10, 60, 168, 32);
		
		btnInversaoDesenho1 = new Button(composite_8, SWT.NONE);
		btnInversaoDesenho1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath1() != null && x1 > 0 && y1 > 0 && x2 > 0 && y2 > 0) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						BufferedImage inversao = proc.filtroInversaoComDesenho(ImageIO.read(new File(funcoes.getFilePath1())),x1,x2,y1,y2);
						ImageIO.write(inversao, "jpg", new File("images/_inversaoComDesenho1.jpg"));
						Image Image3 = new Image(null, "images/_inversaoComDesenho1.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de inversão com desenho");
						e.printStackTrace();
					}
				}
			}
		});
		btnInversaoDesenho1.setText("Imagem 1 (Com desenho)");
		btnInversaoDesenho1.setBounds(204, 10, 202, 32);
		
		btnInversaoDesenho2 = new Button(composite_8, SWT.NONE);
		btnInversaoDesenho2.setText("Imagem 2 (Com desenho)");
		btnInversaoDesenho2.setBounds(204, 60, 202, 32);
		
		CTabItem tbtmZebrado = new CTabItem(tabFolder, SWT.NONE);
		tbtmZebrado.setText("Zebrado");
		
		Composite composite_9 = new Composite(tabFolder, SWT.NONE);
		tbtmZebrado.setControl(composite_9);
		
		Button btnZebradoImagem1 = new Button(composite_9, SWT.NONE);
		btnZebradoImagem1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath1() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						BufferedImage zebrada = proc.filtroZebrada(ImageIO.read(new File(funcoes.getFilePath1())),nColunas.getSelection());
						ImageIO.write(zebrada, "jpg", new File("images/_zebrada1.jpg"));
						Image Image3 = new Image(null, "images/_zebrada1.jpg");
						funcoes.setImage3(Image3);
						funcoes.abreImagem(3, lblImagem3);
					} catch (IOException e) {
						System.out.println("Erro iniciar função de zebrar imagem");
						e.printStackTrace();
					}
				}
			}
		});
		btnZebradoImagem1.setText("Zebrado - Imagem 1");
		btnZebradoImagem1.setBounds(10, 10, 168, 32);
		
		Button btnZebradoImagem2 = new Button(composite_9, SWT.NONE);
		btnZebradoImagem2.setText("Zebrado - Imagem 2");
		btnZebradoImagem2.setBounds(10, 64, 168, 32);
		
		nColunas = new Spinner(composite_9, SWT.BORDER);
		nColunas.setPageIncrement(1);
		nColunas.setMaximum(50);
		nColunas.setSelection(5);
		nColunas.setBounds(265, 39, 47, 22);
		
		Label lblNDeColunas = new Label(composite_9, SWT.NONE);
		lblNDeColunas.setText("N\u00BA de colunas");
		lblNDeColunas.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblNDeColunas.setBounds(241, 10, 97, 27);
		
		tbtmAnliseDeQuadrado = new CTabItem(tabFolder, SWT.NONE);
		tbtmAnliseDeQuadrado.setText("An\u00E1lise de Quadrado");
		
		composite_10 = new Composite(tabFolder, SWT.NONE);
		tbtmAnliseDeQuadrado.setControl(composite_10);
		
		btnAnalisarImagem1 = new Button(composite_10, SWT.NONE);
		btnAnalisarImagem1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (funcoes.getFilePath1() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						lblRespostaAnalise.setText(proc.analiseQuadrado(ImageIO.read(new File(funcoes.getFilePath1()))));
					} catch (IOException e) {
						System.out.println("Erro iniciar função de zebrar imagem");
						e.printStackTrace();
					}
				}
			}
		});
		btnAnalisarImagem1.setText("Analisar - Imagem 1");
		btnAnalisarImagem1.setBounds(10, 10, 168, 32);
		
		btnAnalisarImagem2 = new Button(composite_10, SWT.NONE);
		btnAnalisarImagem2.setText("Analisar - Imagem 2");
		btnAnalisarImagem2.setBounds(10, 64, 168, 32);
		
		lblRespostaAnalise = new Label(composite_10, SWT.NONE);
		lblRespostaAnalise.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblRespostaAnalise.setBounds(283, 64, 255, 32);
		
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
		scrolledComposite.setMinSize(lblImagem1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		lblImagem1.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				funcoes.capturaPixel(1,arg0.x, arg0.y, lblPos, lblCor, lblR, lblG, lblB);
			}
		});
		lblImagem1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				x1=e.x; y1=e.y;
			}
			@Override
			public void mouseUp(MouseEvent e) {
				// USO NO INFORMATIVO DE RGB (ADICIONANDO AO ARRAY)
				String texto = lblPos.getText()+","+lblR.getText().substring(3)+","+lblG.getText().substring(3)+","+lblB.getText().substring(3);
				listaCordenadas.add(texto);
				System.out.println("add -> "+texto);
				// ------------------------------------------------
				x2=e.x; y2=e.y;
				if (funcoes.getFilePath1() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath1());
						BufferedImage desenhoForma = proc.desenhaForma(ImageIO.read(new File(funcoes.getFilePath1())),x1,x2,y1,y2, "poligono");
						ImageIO.write(desenhoForma, "jpg", new File("images/_quadrado.jpg"));
						Image Image3 = new Image(null, "images/_quadrado.jpg");
						funcoes.setImage1(Image3);
						funcoes.abreImagemDesenho(1, lblImagem1);
					} catch (IOException e2) {
						System.out.println("Erro desenhar forma");
						e2.printStackTrace();
					}
				}
			}
		});
		
		scrolledComposite_1 = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_1.setBounds(303, 191, 287, 241);
		
		
		lblImagem2 = new CLabel(scrolledComposite_1, SWT.NONE);
		scrolledComposite_1.setContent(lblImagem2);
		scrolledComposite_1.setMinSize(lblImagem2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setMinSize(lblImagem1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		lblImagem2.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				funcoes.capturaPixel(2,arg0.x, arg0.y, lblPos, lblCor, lblR, lblG, lblB);
			}
		});
		lblImagem2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				x1=e.x; y1=e.y;
			}
			@Override
			public void mouseUp(MouseEvent e) {
				// USO NO INFORMATIVO DE RGB (ADICIONANDO AO ARRAY)
				String texto = lblPos.getText()+","+lblR.getText().substring(3)+","+lblG.getText().substring(3)+","+lblB.getText().substring(3);
				listaCordenadas.add(texto);
				System.out.println("add -> "+texto);
				// ------------------------------------------------
				x2=e.x; y2=e.y;
				if (funcoes.getFilePath2() != null) {
					Processamento proc = new Processamento();
					try {
						System.out.println("Arquivo -> "+funcoes.getFilePath2());
						BufferedImage desenhoForma = proc.desenhaForma(ImageIO.read(new File(funcoes.getFilePath2())),x1,x2,y1,y2, "quadrado");
						ImageIO.write(desenhoForma, "jpg", new File("images/_quadrado.jpg"));
						Image Image3 = new Image(null, "images/_quadrado.jpg");
						funcoes.setImage2(Image3);
						funcoes.abreImagemDesenho(2, lblImagem2);
					} catch (IOException e2) {
						System.out.println("Erro desenhar forma");
						e2.printStackTrace();
					}
				}
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
