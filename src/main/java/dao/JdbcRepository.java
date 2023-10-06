package dao;

import exception.ApplicationException;
import exception.FileReadException;
import exception.ReadSQLException;
import pojo.output.search.Customer;
import pojo.output.stat.Purchase;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class JdbcRepository implements Repository {
    private static String url;
    private static String user;
    private static String password;

    public Connection connect() {
        Properties properties = new Properties();

        try (InputStream input = JdbcRepository.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new ApplicationException("Файл config.properties не найден");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new FileReadException("Ошибка чтения config.properties");
        }

        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");

        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new ApplicationException("Ошибка подключения к БД");
        }

        return connection;
    }

    @Override
    public List<Customer> getCustomersByLastName(String lastName) {
        String SQL = "SELECT last_name, first_name FROM customer " +
                "WHERE last_name LIKE ?";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setString(1, lastName);
            addCustomers(pstmt, customers);
        } catch (SQLException e) {
            throw new ReadSQLException("Ошибка чтения из БД");
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersByProductAndMinTimes(String productName, int minTimes) {
        String SQL = "SELECT first_name, last_name FROM customer\n" +
                "         JOIN customer_product USING (customer_id)\n" +
                "         JOIN product USING (product_id)\n" +
                "WHERE product_name LIKE ?\n" +
                "GROUP BY first_name, last_name\n" +
                "HAVING count(first_name) >= ?";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setString(1, productName);
            pstmt.setInt(2, minTimes);
            addCustomers(pstmt, customers);
        } catch (SQLException e) {
            throw new ReadSQLException("Ошибка чтения из БД");
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersByMinAndMaxExpenses(BigDecimal minExpenses, BigDecimal maxExpenses) {
        String SQL = "SELECT first_name, last_name FROM customer\n" +
                "         JOIN customer_product USING (customer_id)\n" +
                "         JOIN product USING (product_id)\n" +
                "GROUP BY first_name, last_name\n" +
                "HAVING sum(product_price) >= ? AND sum(product_price) <= ?";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setBigDecimal(1, minExpenses);
            pstmt.setBigDecimal(2, maxExpenses);
            addCustomers(pstmt, customers);
        } catch (SQLException e) {
            throw new ReadSQLException("Ошибка чтения из БД");
        }
        return customers;
    }

    @Override
    public List<Customer> getBadCustomers(int limitCount) {
        String SQL = "SELECT first_name, last_name FROM customer\n" +
                "         JOIN customer_product USING (customer_id)\n" +
                "         JOIN product USING (product_id)\n" +
                "GROUP BY first_name, last_name\n" +
                "ORDER BY sum(product_price)\n" +
                "LIMIT ?";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setInt(1, limitCount);
            addCustomers(pstmt, customers);
        } catch (SQLException e) {
            throw new ReadSQLException("Ошибка чтения из БД");
        }
        return customers;
    }

    @Override
    public Map<Customer, List<Purchase>> getCustomerPurchaseStats(LocalDate startDate, LocalDate endDate) {
        String SQL = "SELECT last_name, first_name, product_name, sum(product_price)\n" +
                "FROM customer\n" +
                "         JOIN customer_product USING (customer_id)\n" +
                "         JOIN product USING (product_id)\n" +
                "WHERE purchase_date >= ?\n" +
                "  AND purchase_date <= ?\n" +
                "  AND date_part('isodow', purchase_date) != 6\n" +
                "  AND date_part('isodow', purchase_date) != 7\n" +
                "GROUP BY product_name, first_name, last_name\n" +
                "ORDER BY sum(product_price)";
        HashMap<Customer, List<Purchase>> map = new HashMap<>();
        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setDate(1, Date.valueOf(startDate));
            pstmt.setDate(2, Date.valueOf(endDate));

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setLastName(rs.getString(1));
                customer.setFirstName(rs.getString(2));

                Purchase purchase = new Purchase();
                purchase.setName(rs.getString(3));
                purchase.setExpenses(rs.getBigDecimal(4));

                List<Purchase> purchases = map.getOrDefault(customer, new ArrayList<>());
                purchases.add(purchase);
                map.put(customer, purchases);
            }
        } catch (SQLException e) {
            throw new ReadSQLException("Ошибка чтения из БД");
        }

        return map;
    }

    private void addCustomers(PreparedStatement pstmt, List<Customer> customers) throws SQLException {
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setLastName(rs.getString("last_name"));
            customer.setFirstName(rs.getString("first_name"));
            customers.add(customer);
        }
    }
}
