package ca.jordanfulawka.aopdemo.dao;

import ca.jordanfulawka.aopdemo.Account;

public interface AccountDAO {
    void addAccount(Account account, boolean vipFlag);

    boolean doWork();
}
