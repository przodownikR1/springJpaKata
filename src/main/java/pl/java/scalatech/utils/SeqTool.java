package pl.java.scalatech.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SeqTool {

    public static long getNextSequenceValue(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(callSequenceSyntax())) {
                resultSet.next();
                return resultSet.getLong(1);
            }
        }
    }

    private static String callSequenceSyntax() {

        return "SELECT NEXTVAL('some_seq');";
    }
}
