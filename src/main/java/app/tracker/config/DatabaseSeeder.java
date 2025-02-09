package app.tracker.config;

import net.datafaker.Faker;
import app.tracker.entity.*;
import app.tracker.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    private final Faker faker = new Faker();
    private final Random random = new Random();

    public DatabaseSeeder(UserRepository userRepository, AccountRepository accountRepository,
            CategoryRepository categoryRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run(String... args) {
        seedUsers();
        seedCategories();
        seedAccounts();
        seedTransactions();
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            List<User> users = IntStream.range(0, 10)
                    .mapToObj(i -> new User(
                            faker.internet().emailAddress(),
                            faker.internet().password(),
                            faker.name().fullName()))
                    .toList();
            userRepository.saveAll(users);
        }
    }

    private void seedCategories() {
        if (categoryRepository.count() == 0) {
            List<Category> categories = List.of(
                    new Category("Food", "Food and dining expenses"),
                    new Category("Transport", "Travel and commuting"),
                    new Category("Entertainment", "Movies, games, and events"));
            categoryRepository.saveAll(categories);
        }
    }

    private void seedAccounts() {
        if (accountRepository.count() == 0) {
            List<User> users = userRepository.findAll();
            List<Account> accounts = users.stream()
                    .map(user -> new Account(
                            user,
                            faker.funnyName().name(),
                            random.nextBoolean() ? "SAVINGS" : "CHECKING"))
                    .toList();
            accountRepository.saveAll(accounts);
        }
    }

    private void seedTransactions() {
        if (transactionRepository.count() == 0) {
            List<Account> accounts = accountRepository.findAll();
            List<Category> categories = categoryRepository.findAll();

            List<Transaction> transactions = IntStream.range(0, 50)
                    .mapToObj(i -> new Transaction(
                            getRandomElement(accounts),
                            faker.number().randomDouble(2, 10, 1000),
                            Currency.getInstance("EUR"),
                            getRandomElement(categories)))
                    .toList();
            transactionRepository.saveAll(transactions);
        }
    }

    private <T> T getRandomElement(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}
