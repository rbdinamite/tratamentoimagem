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
	static Image image;
	static int idImagem;
	
	public Funcoes(Shell shell) {
		 fileDialog = new FileDialog(shell);
		 idImagem = 0;
	}

	public static void abreImagem(int indice, CLabel label) {
		idImagem = indice;
		String filePath = fileDialog.open();
		fileDialog.setText("Imagem "+indice);
		image = new Image(null,filePath);
		label.setBackgroundImage(image);
		label.setBounds(label.getBounds().x,label.getBounds().y, image.getImageData().width, image.getImageData().height);
	}
	
	public static void capturaPixel(int x, int y, Label lblPos, Label lblCor, Label lblR, Label lblG, Label lblB) {
		if (idImagem != 0) {
			ImageData imageData = image.getImageData();
			PaletteData palette = imageData.palette;
			lblPos.setText(x+","+y);
			try {
				int pixel = imageData.getPixel(x,y);
				RGB rgb = palette.getRGB(pixel);
				lblR.setText("R: "+rgb.red);
				lblG.setText("R: "+rgb.green);
				lblB.setText("R: "+rgb.blue);
				lblCor.setBackground(new Color(null, rgb));
			} catch (Exception e) {
				System.out.println("PIXEL inexistente");
			}
			}
	}
}
