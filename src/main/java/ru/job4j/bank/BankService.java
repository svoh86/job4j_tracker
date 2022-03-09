package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс позволяет добавлять пользователя, добавлять ему счета, искать пользователя по паспорту
 * и счет по реквизитам, переводить деньги со счета на счет
 *
 * @author Svistunov_MS
 * @version 1.0
 */
public class BankService {
    /**
     * Поле содержит всех пользователей системы с привязанными к ним счетами
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод позволяет добавить пользователя в систему
     *
     * @param user добавляемый пользователь
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод позволяет добавить новый счет пользователю
     *
     * @param passport паспорт пользователя
     * @param account  новый счет пользователя
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод позволяет найти пользователя по паспорту
     *
     * @param passport паспорт пользователя
     * @return возвращает пользователя или null
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(user -> passport.equals(user.getPassport()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод позволяет найти счет пользователя по реквизитам
     *
     * @param passport  паспорт пользователя
     * @param requisite реквизиты
     * @return возвращает счет пользователя или null
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            return users.get(user)
                    .stream()
                    .filter(account -> requisite.equals(account.getRequisite()))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод позволяет перечислить деньги с одного счета на другой
     *
     * @param srcPassport   паспорт пользователя, который переводит деньги
     * @param srcRequisite  реквизиты счета пользователя, с которого переводятся деньги
     * @param destPassport  паспорт пользователя, который получает деньги
     * @param destRequisite реквизиты счета пользователя, с которого получает деньги
     * @param amount        суммы перевода
     * @return возвращает true, если счета найдены и баланс счета позволяет выполнить перевод
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account src = findByRequisite(srcPassport, srcRequisite);
        Account dest = findByRequisite(destPassport, destRequisite);
        if (src != null && dest != null && src.getBalance() >= amount) {
            src.setBalance(src.getBalance() - amount);
            dest.setBalance(dest.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
