package org.ametiste.utils.common.jdbc;

public class JdbcUtils {

	@Deprecated
	public static String buildQuery(String sqlQuery, int size) {

		StringBuilder build = new StringBuilder(sqlQuery);
		for (int i = 0; i < size; i++) {
			build.append("(?, ?, ?)");
			if (i < size - 1) {
				build.append(", ");
			} else {
				build.append(";");
			}
		}

		return build.toString();
	}

	public static String buildPlaceholders(int size, int blockSize) {

		StringBuilder build = new StringBuilder();
		for (int i = 1; i <= size * blockSize; i++) {

			if (i % blockSize == 0) {
				build.append("?)");
				if (i < size * blockSize) {
					build.append(", ");
				} else {
					build.append(";");
				}
			} else {
				if (i % blockSize == 1)
					build.append("(?, ");
				else
					build.append("?, ");
			}
		}

		return build.toString();
	}
}
