package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {

    public boolean transferMoney (
            long creditAccount,
            long debitAccount,
            double amount
    ) throws SQLException {
        Connection connection = JdbcConfig.getConnectionObject();

        if(connection == null) {
            System.out.println("Failed to establish database connection!");
            return false;
        }

        double debitorBalance = this.getBalanceByAccountNumber (
                                        connection,
                                        debitAccount
                                );

        if(debitorBalance < amount) {
            System.out.println("Insufficient balance!");
            return false;
        }

        try {
            connection.setAutoCommit(false);

            String debitQuery = "UPDATE account SET balance = balance - ? WHERE account_number = ?;";
            String creditQuery = "UPDATE account SET balance = balance + ? WHERE account_number = ?;";

            PreparedStatement debitStatement = connection.prepareStatement(debitQuery);
            PreparedStatement creditStatement = connection.prepareStatement(creditQuery);

            debitStatement.setDouble(1, amount);
            debitStatement.setLong(2, debitAccount);

            creditStatement.setDouble(1, amount);
            creditStatement.setLong(2, creditAccount);

            int affectedRows = debitStatement.executeUpdate();
            affectedRows += creditStatement.executeUpdate();

            if(affectedRows == 2) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();

        }
        return false;
    }


    public double getBalanceByAccountNumber(
            Connection connection,
            long accountNumber
    ) {

        double balance = 0.0;

        if(connection == null) {
            System.out.println("Failed to establish database connection!");
            return balance;
        }

        String query = "SELECT balance FROM account WHERE account_number = ?;";

        try {
            PreparedStatement preparedStatement = connection
                                                    .prepareStatement(query);

            preparedStatement.setLong(1, accountNumber);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                balance = rs.getDouble(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return balance;
    }


}
