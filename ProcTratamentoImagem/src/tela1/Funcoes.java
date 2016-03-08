package tela1;

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
	static Image image1 = null;
	static Image image2 = null;
	static int idImagem;
	
	public Funcoes(Shell shell) {
		 fileDialog = new FileDialog(shell);
		 idImagem = 0;
	}

	public static void abreImagem(int indice, CLabel label) {
		idImagem = indice;
		String filePath = fileDialog.open();
		fileDialog.setFilterExtensions( new String[]{"*.*","*.bmp","*.jpg","*.png"});
		switch (indice) {
		case 1:
			image1 = new Image(null,filePath);
			label.setBackground(image1);
			label.setBounds(label.getBounds().x,label.getBounds().y, image1.getImageData().width, image1.getImageData().height);
			break;
		case 2:
			image2 = new Image(null,filePath);
			label.setBackground(image2);
			label.setBounds(label.getBounds().x,label.getBounds().y, image2.getImageData().width, image2.getImageData().height);
			break;
		default:
			break;
		}
	}
	
	public static void capturaPixel(int x, int y, Label lblPos, Label lblCor, Label lblR, Label lblG, Label lblB, int indice) {
		ImageData imageData = null;
		if (idImagem != 0) {
			if (indice == 1 && image1 != null) {
				imageData = image1.getImageData();
			} else if (indice == 2 && image2 != null) {
				imageData = image1.getImageData();
			} 
			if (imageData != null) {
				try {
					PaletteData palette = imageData.palette;
					lblPos.setText(x+","+y);
					int pixel = imageData.getPixel(x,y);
					RGB rgb = palette.getRGB(pixel);
					lblR.setText("R: "+rgb.red);
					lblG.setText("R: "+rgb.green);
					lblB.setText("R: "+rgb.blue);
					lblCor.setBackground(new Color(null, rgb));
				} catch (Exception e) {
					System.out.println("PIXEL inexistente");
					e.printStackTrace();
				}
			}
		}
	}
}
