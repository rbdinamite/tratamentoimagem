package utils;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Funcoes {

	static FileDialog fileDialog;
	static Image image1;
	static Image image2;
	static Image image3;
	static int idImagem;
	static String filePath1;
	static String filePath2;

	public Funcoes(Shell shell) {
		fileDialog = new FileDialog(shell);
		idImagem = 0;
	}

	public void abreImagem(int indice, CLabel label) {
		idImagem = indice;
		if (idImagem == 1) {
			filePath1 = fileDialog.open();
			if (filePath1 != null) {
				image1 = new Image(null, filePath1);
				label.setBackground(image1);
				label.setBounds(label.getBounds().x, label.getBounds().y, image1.getImageData().width,
						image1.getImageData().height);
			}
		} else if (idImagem == 2) {
			if (filePath1 != null) {
				filePath2 = fileDialog.open();	
				image2 = new Image(null, filePath2);
				label.setBackground(image2);
				label.setBounds(label.getBounds().x, label.getBounds().y, image2.getImageData().width,
						image2.getImageData().height);
			}
		} else if (idImagem == 3) {
			label.setBackground(image3);
			label.setBounds(label.getBounds().x, label.getBounds().y, image3.getImageData().width,
				image3.getImageData().height);
		}
	}

	public void capturaPixel(int indice, int x, int y, Label lblPos, Label lblCor, Label lblR, Label lblG,
			Label lblB) {
		ImageData imageData = null;
		if (idImagem != 0) {
			switch (indice) {
			case 1:
				imageData = image1.getImageData();
				break;
			case 2:
				imageData = image2.getImageData();
				break;
			default:
				break;
			}
			if (imageData != null) {
				PaletteData palette = imageData.palette;
				lblPos.setText(x + "," + y);
				try {
					int pixel = imageData.getPixel(x, y);
					RGB rgb = palette.getRGB(pixel);
					lblR.setText("R: " + rgb.red);
					lblG.setText("R: " + rgb.green);
					lblB.setText("R: " + rgb.blue);
					lblCor.setBackground(new Color(null, rgb));
				} catch (Exception e) {
					System.out.println("PIXEL inexistente");
				}
			}
		}
	}

	public  FileDialog getFileDialog() {
		return fileDialog;
	}

	public  void setFileDialog(FileDialog fileDialog) {
		Funcoes.fileDialog = fileDialog;
	}

	public  Image getImage1() {
		return image1;
	}

	public  void setImage1(Image image1) {
		Funcoes.image1 = image1;
	}

	public  Image getImage2() {
		return image2;
	}

	public  void setImage2(Image image2) {
		Funcoes.image2 = image2;
	}

	public  Image getImage3() {
		return image3;
	}

	public  void setImage3(Image image3) {
		Funcoes.image3 = image3;
	}

	public  int getIdImagem() {
		return idImagem;
	}

	public  void setIdImagem(int idImagem) {
		Funcoes.idImagem = idImagem;
	}

	public  String getFilePath1() {
		return filePath1;
	}

	public  void setFilePath1(String filePath1) {
		Funcoes.filePath1 = filePath1;
	}

	public  String getFilePath2() {
		return filePath2;
	}

	public  void setFilePath2(String filePath2) {
		Funcoes.filePath2 = filePath2;
	}
	
	
}
