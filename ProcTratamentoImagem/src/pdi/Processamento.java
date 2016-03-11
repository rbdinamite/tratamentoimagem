package pdi;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import utils.Pixel;

public class Processamento {

	public BufferedImage filtroMediana(int tipoVizinho, BufferedImage image) {
		System.out.println("Chegou na função -> filtroMediana");
		WritableRaster raster = image.getRaster();
		BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		int pixels[] = new int[4];
		System.out.println("Iniciando loop pelo tamanho da imagem");
		for (int i = 1; i < image.getWidth()-1; i++) {
			for (int j = 1; j <image.getHeight()-1; j++) {
				raster.getPixel(i,j,pixels);
				int[] medianas = verificaTipoMediana(tipoVizinho, image, i, j);
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
	
	public int[] verificaTipoMediana(int tipo, BufferedImage image, int x, int y) {
		switch (tipo) {
		case 1:
			return calculaMediana3X3(image, x, y);
		default:
			return null;
		}
	}
	
	public int[] calculaMediana3X3(BufferedImage image, int x, int y) {
		Pixel[][] vizinhos = criaMatrizVizinho3X3(x, y);
		WritableRaster raster = image.getRaster();
		int[] pixels = new int[4];
		for (int i = 0; i < vizinhos.length; i++) {
			for (int j = 0; i < vizinhos.length; i++) {
				raster.getPixel(vizinhos[i][j].getX(), vizinhos[i][j].getY(), pixels);
				System.out.println(pixels[0]+" - "+pixels[1]+" - "+pixels[2]);
				vizinhos[i][j].setR(pixels[0]);
				vizinhos[i][j].setG(pixels[1]);
				vizinhos[i][j].setB(pixels[2]);
			}
		}
		pixels[0] = retornaMediana(vizinhos, 0);
		pixels[1] = retornaMediana(vizinhos, 1);
		pixels[2] = retornaMediana(vizinhos, 2);
		return pixels;
	}
	
	public int retornaMediana(Pixel[][] vizinhos, int indice) {
		int[] valores = new int[9];
		int cont = 0;
		for (int i = 0; i < vizinhos.length; i++) {
			for (int j = 0; j < vizinhos.length; j++) {
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
				cont++;
			}
		}
		valores = ordenaVetor(valores);
		//System.out.println("Valores -> "+valores[4]+"-"+valores[4]+"-"+valores[4]+"-"+valores[4]+"-"+valores[4]+"-"+valores[4]+"-"+valores[4]+"-"+valores[4]+"-"+valores[4]);
		return valores[4];
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
}
