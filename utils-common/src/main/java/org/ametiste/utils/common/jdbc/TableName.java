package org.ametiste.utils.common.jdbc;

public class TableName {

	private final String tableName;
	private final String databaseName;

	@Deprecated
	public TableName(String tableName) {
		if (tableName == null || tableName.isEmpty())
			throw new IllegalArgumentException("Table name cant be empty or null");

		this.tableName = tableName;
		this.databaseName = "";

	}

	public TableName(String databaseName, String tableName) {
		if (tableName == null || tableName.isEmpty())
			throw new IllegalArgumentException("Table name cant be empty or null");
		if (databaseName == null || databaseName.isEmpty())
			throw new IllegalArgumentException("Database name cant be empty or null, use constructor "
					+ "JdbcTableName(tableName) if not specified");

		this.tableName = databaseName + "." + tableName;
		this.databaseName = databaseName;

	}

	public String getFullTableName() {
		return tableName;
	}

	public String getDatabaseName() {
		return databaseName;
	}

}
