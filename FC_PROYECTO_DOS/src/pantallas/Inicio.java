package pantallas;

import gc.Blend;
import gc.Filtro;
import gc.Blend.BlindModes;
import processing.core.PImage;
import setup.Pantalla;

public class Inicio extends Pantalla {
	PImage ima1, ima2, blendImg;
	private PImage[] imasGaussian;
	private PImage[] imaslaplacian;
	private static final float ALPHA = 0.5f;// change this value for analysis

	public void iniciar() {
		ima1 = app.loadImage("../data/imagenes/1.jpg");
		ima2 = app.loadImage("../data/imagenes/2.jpg");
		// el filtro recibe la imagen y el la cantidad de niveles que queres de
		// la piramide

		/* este filtro aplica la matriz que le envies por parametro */
		// Filtro.filtroMatrix(ima1, matrixLaplacian);

		// imasGaussian = Filtro.gaussianPyramid(ima1, 8);
		// imaslaplacian = Filtro.laplacianPyramid(ima2, 8);

		// ima1 = imasGaussian[2];
		// ima2 = imaslaplacian[2];

		// con las dos primeras se obtienen mejores resultados
//		float[][] matrixLaplacian = { { 0, -1, 0, }, { -1, 4, -1, }, { 0, -1, 0 }, };

		float[][] matrixLaplacian = { { 1, 1, 1, }, { 1, -8, 1, }, { 1, 1, 1 }, };

		// float[][] matrixLaplacian = { { -1, 0, 1, }, { -1, 0, 1, }, { -1, 0,
		// 1 }, };

		// float[][] matrixLaplacian = { { -1, 2, -1, }, { 2, -4, 2, }, { -1, 2,
		// -1 }, };

//		float[][] matrixLaplacian = { { 0, 1, 0, }, { 1, -4, 1, }, { 0, 1, 0 }, };
		
		ima2 = Filtro.filtroMatrix(ima2, Filtro.calcularMatrizGaussiana(8, 5));

		ima1 = Filtro.filtroMatrix(ima1, matrixLaplacian);
		blendImg = blend(ima1, ima2, BlindModes.ADD);// cambiar aqui los modos,
														// parece ser que ADD es
														// el mas indicado para
														// el efecto
	}

	public PImage blend(PImage source, PImage target, Blend.BlindModes mode) {
		final PImage blend = app.createImage(source.width, source.height, app.ARGB);
		for (int i = 0; i < source.width; i++) {// equals size condition
			for (int j = 0; j < source.height; j++) {
				int A = (int) app.brightness(source.get(i, j));
				int B = (int) app.brightness(target.get(i, j));
				int C = Blend.blend(A, B, mode, ALPHA);
				blend.set(i, j, app.color((int) C));
			}
		}
		return blend;
	}

	public void pintar() {
		app.image(ima1, 0, 0);
		app.image(ima2, 500, 000);
		app.image(blendImg, 0, 300);
	}

	public void finalizar() {

	}
}
