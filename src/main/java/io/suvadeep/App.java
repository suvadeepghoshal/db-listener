package io.suvadeep;

import java.sql.Statement;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;
import com.impossibl.postgres.jdbc.PGDataSource;

public class App {
	public static void main(String args[]) {
		listenToDataBaseAsync();
	}

	private static void listenToDataBaseAsync() {
		PGDataSource pgDataSource = new PGDataSource();

		pgDataSource.setHost(Constants.HOST);
		pgDataSource.setPort(Constants.PORT);
		pgDataSource.setDatabaseName(Constants.DATABASE_NAME);
		pgDataSource.setUser(Constants.USER);
		pgDataSource.setPassword(Constants.PASSWORD);

		try (PGConnection pgConnection = (PGConnection) pgDataSource.getConnection()) {
			if (null != pgConnection) {
				System.out.println(Constants.CONNECTION_ESTABLISHED);
				Statement statement = pgConnection.createStatement();
				boolean result = statement.execute(Constants.LISTEN_QUERY_UPDATE)
						| statement.execute(Constants.LISTEN_QUERY_INSERT)
						| statement.execute(Constants.LISTEN_QUERY_DELETE);
				statement.close();
				pgConnection.addNotificationListener(new PGNotificationListener() {
					@Override
					public void notification(int processId, String channelName, String payload) {
						System.out.println(Constants.INSIDE_NOTIFICATION);
						if (channelName.equals(Constants.DELETE))
							System.out.println(Constants.DELETION);
						else if (channelName.equals(Constants.UPDATE))
							System.out.println(Constants.UPDATION);
						else if (channelName.equals(Constants.INSERT))
							System.out.println(Constants.INSERTION);
						System.out.println("Payload: " + payload);
					}
				});
				// to make the application continue to listen for any upcoming notifications
				while (true) {
				}
			}
		} catch (Exception me) {
			System.err.println(me.getMessage());
		}

	}

	static class Constants {
		protected static final String INSERTION = "Insertion of data Happened";
		protected static final String UPDATION = "Updation of data Happened";
		protected static final String DELETION = "Deletion of data Happened";
		protected static final String HOST = "localhost";
		protected static final Integer PORT = 5432;
		protected static final String DATABASE_NAME = "students_db";
		protected static final String USER = "postgres";
		protected static final String PASSWORD = "admin";
		protected static final String LISTEN_QUERY_UPDATE = "LISTEN update_notification";
		protected static final String LISTEN_QUERY_INSERT = "LISTEN insert_notification";
		protected static final String LISTEN_QUERY_DELETE = "LISTEN delete_notification";
		protected static final String CONNECTION_ESTABLISHED = "Connection with the database is established.";
		protected static final String INSIDE_NOTIFICATION = "********INSIDE NOTIFICATION********";
		protected static final String CHANGES_HAPPENED = "Some changes happened!";
		protected static final String UPDATE = "update_notification";
		protected static final String INSERT = "insert_notification";
		protected static final String DELETE = "delete_notification";
	}
}
