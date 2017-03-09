package co.in.where2learn_new.util;


public class StringUtil {

	public static String shrinkString(String title, int maxLength) {

		int length = title.length();

		if (length > maxLength) {
			title = title.subSequence(0, maxLength).toString() + "...";
		}

		return title;
	}

}
