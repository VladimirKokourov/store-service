package dao;

import pojo.output.search.Customer;

import java.math.BigDecimal;
import java.util.List;

public interface Repository {
    List<Customer> getCustomersByLastName(String lastName);

    List<Customer> getCustomersByMinAndMaxExpenses(BigDecimal minExpenses, BigDecimal maxExpenses);

    List<Customer> getCustomersByProductAndMinTimes(String productName, int minTimes);

    List<Customer> getBadCustomers(int limitCount);
}
