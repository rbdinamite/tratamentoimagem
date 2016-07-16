package pdi;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import utils.Pixel;

/**
 * FUNÇÕES PARA TRATAMENTO DIGITAL DE IMAGEM SEM FRAMEWORK
 * @author ROBERTO ABREU BENTO
 * @version 1.0.17
 */
public class Processamento {

	// FUNÇÕES PARA CÁLCULO DE MÉDIA E MEDIANA
	public BufferedImage filtroMediana(int tipoVizinho, BufferedImage image, boolean calculaMedia) {
		WritableRaster raster = image.getRaster();
		BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		int pixels[] = new int[4];
		for (int i = 1; i < image.getWidth()-1; i++) {
			for (int j = 1; j <image.getHeight()-1; j++) {
				raster.getPixel(i,j,pixels);
				int[] medianas = verificaTipoMediana(tipoVizinho, image, i, j, calculaMedia);
				pixels[0] = medianas[0];
				pixels[1] = medianas[1];
				pixels[2] = medianas[2];
				raster.setPixel(i,j,pixels);
			}
		}
		try {
			newImg.setData(raster);
		} catch (Exception e) {
			System.out.println("Erro ao filtrar imagem");
			e.printStackTrace();
		}
		return newImg;
	}
	
	public int[] verificaTipoMediana(int tipo, BufferedImage image, int x, int y, boolean calculaMedia) {
		Pixel[][] vizinhos = null;
		switch (tipo) {
		case 1:
			vizinhos = criaMatrizVizinho3X3(x, y);
			break;
		case 2:
			vizinhos = criaMatrizVizinho2X2Diagonal(x, y);
			break;
		case 3:
			vizinhos = criaMatrizVizinho2X2(x, y);
			break;
		default:
			break;
		}
		return calculaMediana(tipo, vizinhos, image, x, y, calculaMedia);
	}
	
	public int[] calculaMediana(int tipo, Pixel[][] vizinhos, BufferedImage image, int x, int y, boolean calculaMedia) {
		WritableRaster raster = image.getRaster();
		int[] pixels = new int[4];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (vizinhos[i][j] != null) {
					raster.getPixel(vizinhos[i][j].getX(), vizinhos[i][j].getY(), pixels);
					vizinhos[i][j].setR(pixels[0]);
					vizinhos[i][j].setG(pixels[1]);
					vizinhos[i][j].setB(pixels[2]);
				}
			}
		}
		pixels[0] = retornaMediana(vizinhos, 0, tipo, calculaMedia);
		pixels[1] = retornaMediana(vizinhos, 1, tipo, calculaMedia);
		pixels[2] = retornaMediana(vizinhos, 2, tipo, calculaMedia);
		return pixels;
	}
	
	public int retornaMediana(Pixel[][] vizinhos, int indice, int tipo, boolean calculaMedia) {
		int tamVetor,cont = 0, soma = 0;
		if (tipo == 1) { 
			tamVetor = 9;
		} else {
			tamVetor = 5;
		}
		int[] valores = new int[tamVetor];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (vizinhos[i][j] != null) {
					switch (indice) {
					case 0:
						valores[cont] = vizinhos[i][j].getR();
						break;
					case 1:
						valores[cont] = vizinhos[i][j].getG();
						break;
					case 2:
						valores[cont] = vizinhos[i][j].getB();
						break;
					default:
						break;
					}
					soma+=valores[cont];
					cont++;
				}
			}
		}
		valores = ordenaVetor(valores);
		if (calculaMedia) {
			return (int)soma/valores.length;
		} else {
			return valores[(int)valores.length/2];
		}
	}
	
	public int[] ordenaVetor(int[] vetor) {
		int aux = 0;
		for (int i = 0; i < vetor.length; i++) { 
			for (int j = 0; j < vetor.length; j++) {
				if (vetor[i] < vetor[j]) { 
					aux = vetor[i]; 
					vetor[i] = vetor[j]; 
					vetor[j] = aux; 
				} 
			}
		}
		return vetor;
	}
	
	public Pixel[][] criaMatrizVizinho3X3(int x, int y) {
		Pixel[][] vizinhos = new Pixel[3][3];
		vizinhos[0][0] = new Pixel(x-1, y-1);
		vizinhos[0][1] = new Pixel(x-1, y);
		vizinhos[0][2] = new Pixel(x-1, y+1);
		vizinhos[1][0] = new Pixel(x, y-1);
		vizinhos[1][1] = new Pixel(x, y);
		vizinhos[1][2] = new Pixel(x, y+1);
		vizinhos[2][0] = new Pixel(x+1, y-1);
		vizinhos[2][1] = new Pixel(x+1, y);
		vizinhos[2][2] = new Pixel(x+1, y+1);
		return vizinhos;
	}
	
	public Pixel[][] criaMatrizVizinho2X2Diagonal(int x, int y) {
		Pixel[][] vizinhos = new Pixel[3][3];
		vizinhos[0][0] = new Pixel(x-1, y-1);
		vizinhos[0][1] = null;
		vizinhos[0][2] = new Pixel(x-1, y+1);
		vizinhos[1][0] = null;
		vizinhos[1][1] = new Pixel(x, y);
		vizinhos[1][2] = null;
		vizinhos[2][0] = new Pixel(x+1, y-1);
		vizinhos[2][1] = null;
		vizinhos[2][2] = new Pixel(x+1, y+1);
		return vizinhos;
	}
	
	public Pixel[][] criaMatrizVizinho2X2(int x, int y) {
		Pixel[][] vizinhos = new Pixel[3][3];
		vizinhos[0][0] = null;
		vizinhos[0][1] = new Pixel(x-1, y);
		vizinhos[0][2] = null;
		vizinhos[1][0] = new Pixel(x, y-1);
		vizinhos[1][1] = new Pixel(x, y);
		vizinhos[1][2] = new Pixel(x, y+1);
		vizinhos[2][0] = null;
		vizinhos[2][1] = new Pixel(x+1, y);
		vizinhos[2][2] = null;
		return vizinhos;
	}
	
	// FUNÇÕES PARA CÁLCULO DE ESCALA DE CINZA
	public BufferedImage filtroEscalaCinza(int tipo, BufferedImage image, int propR, int propG,int propB) {
		WritableRaster raster = image.getRaster();
		BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		int pixels[] = new int[4];
		for (int i = 1; i < image.getWidth()-1; i++) {
			for (int j = 1; j <image.getHeight()-1; j++) {
				raster.getPixel(i,j,pixels);
				int escalaCinza = calculaEscalaCinza(tipo, pixels, propR, propG, propB);
				pixels[0] = escalaCinza;
				pixels[1] = escalaCinza;
				pixels[2] = escalaCinza;
				raster.setPixel(i,j,pixels);
			}
		}
		try {
			newImg.setData(raster);
		} catch (Exception e) {
			System.out.println("Erro ao inserir escala de cinza na imagem");
			e.printStackTrace();
		}
		return newImg;
	}
	
	public int calculaEscalaCinza(int tipo,int[] pixels, int propR, int propG,int propB) {
		int resultado = 0;
		switch (tipo) {
		// CALCULA ESCALA SIMPLES
		case 1:
			resultado = (int) ((pixels[0]+pixels[1]+pixels[2])/3);
			break;
		//CALCULA ESCALA PONDERADA
		case 2:
			resultado = (int) (((pixels[0]*propR)+(pixels[1]*propG)+(pixels[2]*propB))/100);
			break;
		default:
			break;
		}
		return resultado;
	}
	
	/**
	 * FUNÇÃO PARA O CÁLCULO DA LIMIARIZAÇÃO
	 * @param image - ARQUIVO DA IMAGEM
	 * @param limiar - VALOR DO LIMIAR A SER UTILIZADO NO CORTE
	 * @return BufferedImage
	 */
	public BufferedImage filtroLimiarizacao(BufferedImage image, int limiar) {
		WritableRaster raster = image.getRaster();
		BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		int pixels[] = new int[4];
		for (int i = 1; i < image.getWidth()-1; i++) {
			for (int j = 1; j <image.getHeight()-1; j++) {
				raster.getPixel(i,j,pixels);
				pixels[0] = (pixels[0] <= limiar)?0:255;
				pixels[1] = (pixels[1] <= limiar)?0:255;
				pixels[2] = (pixels[2] <= limiar)?0:255;
				raster.setPixel(i,j,pixels);
			}
		}
		try {
			newImg.setData(raster);
		} catch (Exception e) {
			System.out.println("Erro ao inserir escala de cinza na imagem");
			e.printStackTrace();
		}
		return newImg;
	}
	
	// FUNÇÕES PARA O CÁLCULO DA NEGATIVA
		public BufferedImage filtroNegativa(BufferedImage image) {
			WritableRaster raster = image.getRaster();
			BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
			int pixels[] = new int[4];
			for (int i = 1; i < image.getWidth()-1; i++) {
				for (int j = 1; j <image.getHeight()-1; j++) {
					raster.getPixel(i,j,pixels);
					pixels[0] = 255-pixels[0];
					pixels[1] = 255-pixels[1];
					pixels[2] = 255-pixels[2];
					raster.setPixel(i,j,pixels);
				}
			}
			try {
				newImg.setData(raster);
			} catch (Exception e) {
				System.out.println("Erro ao inserir escala de cinza na imagem");
				e.printStackTrace();
			}
			return newImg;
		}
		
		// FUNCÃO PARA CÁLCULO DO HISTOGRAMA DA IMAGEM
		public void filtroHistograma(BufferedImage image) {
			Map<Integer, Integer> valoresR = new HashMap<Integer, Integer>();
			Map<Integer, Integer> valoresG = new HashMap<Integer, Integer>();
			Map<Integer, Integer> valoresB = new HashMap<Integer, Integer>();
			WritableRaster raster = image.getRaster();
			DefaultCategoryDataset dsR = new DefaultCategoryDataset();
			DefaultCategoryDataset dsG = new DefaultCategoryDataset();
			DefaultCategoryDataset dsB = new DefaultCategoryDataset();
			int pixels[] = new int[4];
			for (int i = 1; i < image.getWidth()-1; i++) {
				for (int j = 1; j <image.getHeight()-1; j++) {
					raster.getPixel(i,j,pixels);
					if (valoresR.get(pixels[0]) == null) {
						valoresR.put(pixels[0], 1);
					} else {
						valoresR.put(pixels[0], valoresR.get(pixels[0])+1);
					}
					if (valoresG.get(pixels[1]) == null) {
						valoresG.put(pixels[1], 1);
					} else {
						valoresG.put(pixels[1], valoresG.get(pixels[1])+1);
					}
					if (valoresB.get(pixels[2]) == null) {
						valoresB.put(pixels[2], 1);
					} else {
						valoresB.put(pixels[2], valoresB.get(pixels[2])+1);
					}
				}
			}
			System.out.println("VALORES R:");
			for (int i = 0; i < valoresR.size(); i++) {
				if (valoresR.get(i) != null) {
					dsR.addValue(valoresR.get(i), "", i+"");
					System.out.println("Píxel: "+i+" - Qt: "+valoresR.get(i));
				}
			}
			JFreeChart grafico = ChartFactory.createBarChart("Histograma", "", "", dsR);
			try {
				OutputStream arquivo = new FileOutputStream("images/graficoR.jpg");
				ChartUtilities.writeChartAsJPEG(arquivo, grafico, 657, 175);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("VALORES G:");
			for (int i = 0; i < valoresG.size(); i++) {
				if (valoresG.get(i) != null) {
					dsG.addValue(valoresG.get(i), "", i+"");
					System.out.println("Píxel: "+i+" - Qt: "+valoresG.get(i));
				}
			}
			grafico = ChartFactory.createBarChart("Histograma", "", "", dsG);
			try {
				OutputStream arquivo = new FileOutputStream("images/graficoG.jpg");
				ChartUtilities.writeChartAsJPEG(arquivo, grafico, 657, 175);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("VALORES B:");
			for (int i = 0; i < valoresB.size(); i++) {
				if (valoresB.get(i) != null) {
					dsB.addValue(valoresB.get(i), "", i+"");
					System.out.println("Píxel: "+i+" - Qt: "+valoresB.get(i));
				}
			}
			grafico = ChartFactory.createBarChart("Histograma", "", "", dsB);
			try {
				OutputStream arquivo = new FileOutputStream("images/graficoB.jpg");
				ChartUtilities.writeChartAsJPEG(arquivo, grafico, 657, 175);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// FUNCÃO PARA CÁLCULO DA EQUALIZACAO DA IMAGEM
		public BufferedImage filtroEqualizacao(BufferedImage image) {
			Map<Integer, Integer> valoresR = new HashMap<Integer, Integer>();
			Map<Integer, Integer> valoresG = new HashMap<Integer, Integer>();
			Map<Integer, Integer> valoresB = new HashMap<Integer, Integer>();
			WritableRaster raster = image.getRaster();
			int pixels[] = new int[4];
			// CALCULA O HISTROGRAMA DA IMAGEM
			for (int i = 1; i < image.getWidth()-1; i++) {
				for (int j = 1; j <image.getHeight()-1; j++) {
					raster.getPixel(i,j,pixels);
					if (valoresR.get(pixels[0]) == null) {
						valoresR.put(pixels[0], 1);
					} else {
						valoresR.put(pixels[0], valoresR.get(pixels[0])+1);
					}
					if (valoresG.get(pixels[1]) == null) {
						valoresG.put(pixels[1], 1);
					} else {
						valoresG.put(pixels[1], valoresG.get(pixels[1])+1);
					}
					if (valoresB.get(pixels[2]) == null) {
						valoresB.put(pixels[2], 1);
					} else {
						valoresB.put(pixels[2], valoresB.get(pixels[2])+1);
					}
				}
			}
			// CALCULA O HISTOGRAMA ACUMULADO
			Map<Integer, Integer> histAcumuladoR = new HashMap<Integer, Integer>();
			Map<Integer, Integer> histAcumuladoG = new HashMap<Integer, Integer>();
			Map<Integer, Integer> histAcumuladoB = new HashMap<Integer, Integer>();
			int soma = 0;
			for (int i = 0; i < 256; i++) {
				soma += (valoresR.get(i) == null) ? 0 : valoresR.get(i);
				histAcumuladoR.put(i, soma);
			}
			soma = 0;
			for (int i = 0; i < 256; i++) {
				soma += (valoresG.get(i) == null) ? 0 : valoresG.get(i);
				histAcumuladoG.put(i, soma);
			}
			soma = 0;
			for (int i = 0; i < 256; i++) {
				soma += (valoresB.get(i) == null) ? 0 : valoresB.get(i);
				histAcumuladoB.put(i, soma);
			}	
			// REALIZA O CÁLCULO DA EQUALIZAÇÃO
			BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
			int tamanhoTotal = (int) (image.getWidth()-1)*(image.getHeight()-1);
			double mediaCalculo = (255/(double)tamanhoTotal);
			for (int i = 1; i < image.getWidth()-1; i++) {
				for (int j = 1; j <image.getHeight()-1; j++) {
					raster.getPixel(i,j,pixels);
					pixels[0] = (int) Math.round(mediaCalculo*histAcumuladoR.get(pixels[0]));
					pixels[1] = (int) Math.round(mediaCalculo*histAcumuladoG.get(pixels[1]));
					pixels[2] = (int) Math.round(mediaCalculo*histAcumuladoB.get(pixels[2]));
					raster.setPixel(i,j,pixels);
				}
			}
			try {
				newImg.setData(raster);
			} catch (Exception e) {
				System.out.println("Erro ao inserir escala de cinza na imagem");
				e.printStackTrace();
			}
			return newImg;				
		}
		
		// FUNÇÕES PARA O CÁLCULO DE ADICAO E SUBTRAÇÃO
		public BufferedImage filtroAdicaoSubtracao(BufferedImage image1, BufferedImage image2, boolean soma,int txImg1, int txImg2) {
			WritableRaster raster1 = image1.getRaster();
			WritableRaster raster2 = image2.getRaster();
			int Xmaior = Math.max(image1.getWidth(),image2.getWidth());
			int Ymaior = Math.max(image1.getHeight(), image2.getHeight());
			BufferedImage newImg = new BufferedImage(Xmaior, Ymaior, BufferedImage.TYPE_INT_RGB);
			WritableRaster raster3 = newImg.getRaster();
			int pixels1[] = new int[4];
			int pixels2[] = new int[4];
			int c1 = 0,c2 = 0;
					for (int i = 1; i < Xmaior-1; i++) {
						for (int j = 1; j < Ymaior-1; j++) {
							// VERIFICA INDICE NA IMAGE 1
							if (i < image1.getWidth() && j < image1.getHeight()) {
								raster1.getPixel(i,j,pixels1);
							} else {
								pixels1[0] = 0;
								pixels1[1] = 0;
								pixels1[0] = 0;
							}
							// VERIFICA INDICE NA IMAGEM 2
							if (i < image2.getWidth() && j < image2.getHeight()) {
								raster2.getPixel(i,j,pixels2);
							} else {
								pixels1[0] = 0;
								pixels1[1] = 0;
								pixels1[0] = 0;
							}
							
							// CALCULA R
							c1 = (int) (pixels1[0]*((double) txImg1/100));
							c2 = (int) (pixels2[0]*((double) txImg2/100));
							pixels1[0] = (soma) ? c1+c2 : pixels1[0]-pixels2[0];
							pixels1[0] = (pixels1[0] < 0) ? (pixels1[0]*(-1)) : pixels1[0];
							// CALCULA G
							c1 = (int) (pixels1[1]*((double) txImg1/100));
							c2 = (int) (pixels2[1]*((double) txImg2/100));
							pixels1[1] = (soma) ? c1+c2 : pixels1[1]-pixels2[1];
							pixels1[1] = (pixels1[1] < 0) ? (pixels1[1]*(-1)) : pixels1[1];
							// CALCULA B
							c1 = (int) (pixels1[2]*((double) txImg1/100));
							c2 = (int) (pixels2[2]*((double) txImg2/100));
							pixels1[2] = (soma) ? c1+c2 : pixels1[2]-pixels2[2];
							pixels1[2] = (pixels1[2] < 0) ? (pixels1[2]*(-1)) : pixels1[2];
							
							raster3.setPixel(i,j,pixels1);
						}
					}
					try {
						newImg.setData(raster3);
					} catch (Exception e) {
						System.out.println("Erro ao inserir escala de cinza na imagem");
						e.printStackTrace();
					}
					return newImg;
				}
		
				// FUNÇÕES PARA O CÁLCULO DE TRANSPARENCIA
				public BufferedImage filtroTransparencia(BufferedImage image, int txImg) {
					WritableRaster raster = image.getRaster();
					BufferedImage newImg = new BufferedImage(image.getWidth(), image.getWidth(), BufferedImage.TYPE_INT_RGB);
					int pixels[] = new int[4];
					int c1 = 0, c2 = 0;
					int taxa = 100-txImg;
					for (int i = 1; i < image.getWidth()-1; i++) {
						for (int j = 1; j < image.getWidth()-1; j++) {
							raster.getPixel(i,j,pixels);
							// CALCULA R
							c1 = (int) (pixels[0]*((double) txImg/100));
							c2 = (int) (255*((double) taxa/100));
							pixels[0] = c1+c2;
							// CALCULA G
							c1 = (int) (pixels[1]*((double) txImg/100));
							c2 = (int) (255*((double) taxa/100));
							pixels[1] = c1+c2;
							// CALCULA B
							c1 = (int) (pixels[2]*((double) txImg /100));
							c2 = (int) (255*((double) taxa/100));
							pixels[2] = c1+c2;
							raster.setPixel(i,j,pixels);
						}
					}
					try {
						newImg.setData(raster);
					} catch (Exception e) {
						System.out.println("Erro ao inserir escala de cinza na imagem");
						e.printStackTrace();
					}
					return newImg;
				}
				
				// FUNÇÕES PARA O DESENHO DE FORMAS NA IMAGEM (QUADRADO)
				public BufferedImage desenhaForma(BufferedImage image, int x1, int x2, int y1, int y2, String tipo) {
					Graphics g = image.getGraphics();
					Graphics2D g2d = (Graphics2D) g.create();
					g2d.setStroke(new BasicStroke(4)); // 4 pixels de linha
					g2d.setColor(Color.BLACK);
					switch (tipo) {
					case "quadrado":
						g2d.drawRect(x1, y1, (Math.max(x1,x2)-Math.min(x1,x2)), (Math.max(y1,y2)-Math.min(y1,y2)));
						break;
					case "poligono":
						g2d.drawPolygon(new int[]{x1,x1,x2,x2},new int[]{y1,y2,y2,y1},4);
					default:
						break;
					}
					
					return image;
				}
				
				// FUNÇÕES PARA CÁLCULO DE ESCALA DE CINZA
				public BufferedImage filtroEscalaCinzaComDesenho(BufferedImage image, int x1, int x2, int y1, int y2) {
					WritableRaster raster = image.getRaster();
					BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
					int pixels[] = new int[4];
					int xMaior = Math.max(x1, x2);
					int xMenor = Math.min(x1, x2);
					int yMaior = Math.max(y1, y2);
					int yMenor = Math.min(y1, y2);
					for (int i = 1; i < image.getWidth()-1; i++) {
						for (int j = 1; j <image.getHeight()-1; j++) {
							if (i >= xMenor && i <= xMaior && j >= yMenor && j <= yMaior) {
								raster.getPixel(i,j,pixels);
								int escalaCinza = calculaEscalaCinza(1, pixels, 0,0,0);
								pixels[0] = escalaCinza;
								pixels[1] = escalaCinza;
								pixels[2] = escalaCinza;
								raster.setPixel(i,j,pixels);
							}
						}
					}
					try {
						newImg.setData(raster);
					} catch (Exception e) {
						System.out.println("Erro ao inserir escala de cinza na imagem");
						e.printStackTrace();
					}
					return newImg;
				}
				
				// FUNÇÕES PARA O INVERSAO DA IMAGEM
				public BufferedImage filtroInversao(BufferedImage image) {
					WritableRaster raster = image.getRaster();
					BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
					WritableRaster raster2 = newImg.getRaster();
					int pixels[] = new int[4];
					for (int i = 1; i < image.getWidth()-1; i++) {
						for (int j = 1; j <image.getHeight()-1; j++) {
							raster.getPixel(image.getWidth()-(i+1),image.getHeight()-(1+j),pixels);
							raster2.setPixel(i, j, pixels);
						}
					}
					try {
						newImg.setData(raster2);
					} catch (Exception e) {
						System.out.println("Erro ao inverter imagem");
						e.printStackTrace();
					}
					return newImg;
				}
				
				
				// #######################################################################
				// FUNÇÕES UTILIZADAS NA PROVA 05/05/2016
				// #######################################################################
				
				// FUNÇÕES PARA IMAGEM ZEBRADA
				public BufferedImage filtroZebrada(BufferedImage image, int nCol) {
					WritableRaster raster = image.getRaster();
					BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
					int widhtMedio = (int) image.getWidth()/nCol;
					System.out.println("Media pixels -> "+widhtMedio);
					int contador = 0;
					boolean modifica = true;
					int pixels[] = new int[4];
					for (int i = 1; i < image.getHeight()-1; i++) {
						contador = 0;
						modifica = true;
						for (int j = 1; j <image.getWidth()-1; j++) {
							raster.getPixel(j,i,pixels);
							//System.out.println("MODIFICA É -> ("+modifica+")");
							if (modifica) {
								System.out.println(contador+" - Aplicando modificador ("+i+","+j+")");
								int escalaCinza = calculaEscalaCinza(1, pixels, 0,0,0);
								pixels[0] = escalaCinza;
								pixels[1] = escalaCinza;
								pixels[2] = escalaCinza;
								
							} 							
							contador++;
							if (contador == widhtMedio) {
								//System.err.println(contador+" - TROCANDO SINAL ... ("+i+","+j+")");
								modifica = (modifica) ? false : true; 
								contador = 0;
							}
							raster.setPixel(j, i, pixels);
						}
					}
					try {
						newImg.setData(raster);
					} catch (Exception e) {
						System.out.println("Erro ao modificar imagem");
						e.printStackTrace();
					}
					return newImg;
				}
				
				// FUNÇÕES PARA O INVERSAO DA IMAGEM COM FORMA
				public BufferedImage filtroInversaoComDesenho(BufferedImage image, int x1, int x2, int y1, int y2) {
					int xMaior = Math.max(x1, x2);
					int xMenor = Math.min(x1, x2);
					int yMaior = Math.max(y1, y2);
					int yMenor = Math.min(y1, y2);
					BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
					WritableRaster raster = image.getRaster();
					WritableRaster raster2 = newImg.getRaster();
					int pixels[] = new int[4];
					for (int i = 1; i < image.getWidth()-1; i++) {
						for (int j = 1; j <image.getHeight()-1; j++) {
							if (i >= xMenor && i <= xMaior && j >= yMenor && j <= yMaior) {
								raster.getPixel(xMaior-(Math.max(xMenor,i)-Math.min(xMenor,i)),yMaior-(Math.max(yMenor,j)-Math.min(yMenor,j)),pixels);
							} else {
								raster.getPixel(i,j,pixels);
							}
							raster2.setPixel(i, j, pixels);
						}
					}
					try {
						newImg.setData(raster2);
					} catch (Exception e) {
						System.out.println("Erro ao inverter imagem");
						e.printStackTrace();
					}
					return newImg;
				}
				
				// VERIFICA SE UM QUADRADO ESTÁ PREENCHIDO
				public String analiseQuadrado(BufferedImage image) {
					WritableRaster raster = image.getRaster();
					int pixels[] = new int[4];
					int xInicio = 0,yInicio = 0;
					int xFim = 0,yFim = 0;
					boolean achouInicio = false;
					for (int i = 1; i < image.getHeight()-1; i++) {
						for (int j = 1; j <image.getWidth()-1; j++) {
							raster.getPixel(j,i,pixels);
								if (pixels[0] == 0) {
									if (!achouInicio) {
										achouInicio = true;
										xInicio = i;
										yInicio = j;
										xFim = i;
										yFim = j;
									} else {
										xFim = Math.max(xFim,i);
										yFim = Math.max(yFim,j);
									}
								}
						}
					}
					raster.getPixel((int) ((xFim-xInicio)/2), (int) ((yFim-yInicio)/2), pixels);
					if (pixels[0] == 0) {
						return "QUADRADO PREENCHIDO";
					} else {
						return "QUADRADO VAZIO";
					}
				}
				
				// REALIZA A SEGMENTAÇÃO POR GRUPOS
				public BufferedImage segmentacaoGrupos(BufferedImage image, int nGrupos) {
					int[][] matrizCores = new int[5][5];
					matrizCores[0][0] = 255;
					matrizCores[0][1] = 0;
					matrizCores[0][2] = 0;
					matrizCores[1][0] = 255;
					matrizCores[1][1] = 255;
					matrizCores[1][2] = 0;
					matrizCores[2][0] = 0;
					matrizCores[2][1] = 0;
					matrizCores[2][2] = 0;
					matrizCores[3][0] = 255;
					matrizCores[3][1] = 0;
					matrizCores[3][2] = 255;
					WritableRaster raster = image.getRaster();
					BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
					WritableRaster raster2 = newImg.getRaster();
					int pixels[] = new int[4];
					int menor = 255, maior = 0;
					for (int i = 1; i < image.getWidth()-1; i++) {
						for (int j = 1; j <image.getHeight()-1; j++) {
							raster.getPixel(i,j,pixels);
							int media = calculaEscalaCinza(1, pixels, 0,0,0);
							if (media < menor) { 
								menor = media; 
							} else if (media > maior) {
								maior = media;
							}
						}
					}
					int mediaGrupo = Math.round((maior-menor) / nGrupos)+1;
					System.out.println("MAIOR -> "+maior+" - MENOR -> "+menor+" - MEDIA -> "+mediaGrupo);
					for (int j = 1; j <image.getHeight()-1; j++) {
						for (int i = 1; i < image.getWidth()-1; i++) {
							raster.getPixel(i,j,pixels);
							int mediaPixel = calculaEscalaCinza(1, pixels, 0,0,0);
							int grupo = Math.floorDiv(mediaPixel,mediaGrupo);
							if (grupo < 3) {
								//System.out.println("TROCANDO GRUPO ["+grupo+"] ["+pixels[0]+","+pixels[1]+","+pixels[2]+"] PARA ESCALA -> ["+matrizCores[grupo][0]+","+matrizCores[grupo][1]+","+matrizCores[grupo][2]+"]");
								pixels[0] = matrizCores[grupo][0];
								pixels[1] = matrizCores[grupo][1];
								pixels[2] = matrizCores[grupo][2];
							} else {
								//System.err.println("ERRO: grupo inválido -> ("+grupo+") - MEDIA -> ("+mediaGrupo+") - PIXELS -> ["+pixels[0]+","+pixels[1]+","+pixels[2]+"] ESCALA -> ("+mediaPixel+")");
							}
							raster2.setPixel(i,j,pixels);
						}
					}
					try {
						newImg.setData(raster2);
					} catch (Exception e) {
						System.out.println("Erro ao inverter imagem");
						e.printStackTrace();
					}
					return newImg;
				}
}
