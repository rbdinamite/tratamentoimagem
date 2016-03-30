package pdi;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.util.Map;

import utils.Pixel;

public class Processamento {

	// FUN��ES PARA C�LCULO DE M�DIA E MEDIANA
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
	
	// FUN��ES PARA C�LCULO DE ESCALA DE CINZA
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
	
	// FUN��ES PARA O C�LCULO DA LIMIARIZA��O
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
	
	// FUN��ES PARA O C�LCULO DA NEGATIVA
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
		
		// FUNC�O PARA C�LCULO DO HISTOGRAMA DA IMAGEM
		public void filtroHistograma(BufferedImage image) {
			Map<Integer, Integer> valoresR = new HashMap<Integer, Integer>();
			Map<Integer, Integer> valoresG = new HashMap<Integer, Integer>();
			Map<Integer, Integer> valoresB = new HashMap<Integer, Integer>();
			WritableRaster raster = image.getRaster();
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
					System.out.println("P�xel: "+i+" - Qt: "+valoresR.get(i));
				}
			}
			System.out.println("VALORES G:");
			for (int i = 0; i < valoresG.size(); i++) {
				if (valoresG.get(i) != null) {
					System.out.println("P�xel: "+i+" - Qt: "+valoresG.get(i));
				}
			}
			System.out.println("VALORES B:");
			for (int i = 0; i < valoresB.size(); i++) {
				if (valoresB.get(i) != null) {
					System.out.println("P�xel: "+i+" - Qt: "+valoresB.get(i));
				}
			}
		}
}
