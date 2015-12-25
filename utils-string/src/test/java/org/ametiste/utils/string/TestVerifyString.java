package org.ametiste.utils.string;

public class TestVerifyString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String stt = "белый {} сцуко?  // \"кот";
		String wqe = "ਭਾਸ਼ਾ ਸੰਦ34";
		VerifiedString sdsds = VerifiedString.trim(stt);
		VerifiedString qwe = VerifiedString.trim(wqe);
		System.out.println(sdsds.getString().trim());
		System.out.println(qwe.getString());

	}

}
