package todo;

public class Functions {
	public static boolean hasPoint(String text) {
		for(int i=0;i<text.length();i++) {
			if(text.charAt(i) == '.') {
				return true;
			}
			
		}
		return false;
	}
	public static int parseInt(String text) {
		int value;
		try {
			value = Integer.parseInt(text);
		}catch(Exception e) {
			value = 0;
		}
		return value;
	}
	public static float parseFloat(String text) {
		float value;
		try {
			value = Float.parseFloat(text);
		}catch(Exception e) {
			value = 0;
		}
		return value;
	}
}
