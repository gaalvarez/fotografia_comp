package gc;

/**
 * 
 * @author gaalvarez
 *
 */
public class Blend {

	/**
	 * modes taken of https://processing.org/reference/blend_.html
	 * 
	 * @author gaalvarez
	 *
	 */
	public enum BlindModes {
		BLEND, ADD, SUBTRACT, DARKEST, LIGHTEST
	}

	public static int blend(int a, int b, BlindModes mode, float alfa) {
		switch (mode) {
		case BLEND:
			return blind(a, b, alfa);
		case ADD:
			return addBlind(a, b, alfa);
		case SUBTRACT:
			return substractBlind(a, b, alfa);
		case DARKEST:
			return darkestBlind(a, b, alfa);
		case LIGHTEST:
			return lightestBlind(a, b, alfa);
		default:
			throw new IllegalArgumentException("the mode indicated is incorrect");
		}
	}

	/**
	 * LIGHTEST - only the lightest colour succeeds: C = max(A*factor, B)
	 * 
	 * @param a
	 * @param b
	 * @param alfa
	 * @return
	 */
	private static int lightestBlind(int a, int b, float alfa) {
		a = (int) (a * alfa);
		return a < b ? b : a;
	}

	/**
	 * DARKEST - only the darkest colour succeeds: C = min(A*factor, B)
	 * 
	 * @param a
	 * @param b
	 * @param alfa
	 * @return the min value
	 */
	private static int darkestBlind(int a, int b, float alfa) {
		a = (int) (a * alfa);
		return a > b ? b : a;
	}

	/**
	 * SUBTRACT - subtractive blending with black clip: C = max(B - A*factor, 0)
	 * 
	 * @param a
	 * @param b
	 * @param alfa
	 * @return
	 */
	private static int substractBlind(int a, int b, float alfa) {
		a = (int) (a * alfa);
		int c = b - a;
		return c < 0 ? 0 : c;
	}

	/**
	 * BLEND - linear interpolation of colours: C = A*factor + B
	 * 
	 * @param a
	 * @param b
	 * @param alfa
	 * @return
	 */
	private static int blind(int a, int b, float alfa) {
		return  ((int)(a * alfa)) + b;
	}

	/**
	 * ADD - additive blending with white clip: C = min(A*factor + B, 255)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private static int addBlind(int a, int b, float alfa) {
		a = (int) (a * alfa);
		int c = a + b;
		return c > 255 ? 255 : c;
	}

}
