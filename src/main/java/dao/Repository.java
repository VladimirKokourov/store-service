package dao;

import pojo.output.search.Customer;
import pojo.output.stat.Purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface Repository {
    List<Customer> getCustomersByLastName(String lastName);

    List<Customer> getCustomersByMinAndMaxExpenses(BigDecimal minExpenses, BigDecimal maxExpenses);

    List<Customer> getCustomersByProductAndMinTimes(String productName, int minTimes);

    List<Customer> getBadCustomers(int limitCount);

    Map<Customer, List<Purchase>> getCustomerPurchaseStats(LocalDate startDate, LocalDate endDate);
}
