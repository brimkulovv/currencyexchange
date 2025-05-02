import com.uzan.currencyexchange.service.AuthService;
import com.uzan.currencyexchange.service.CurrencyService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Запуск приложения Currency Exchange...");

        AuthService authService = new AuthService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите логин: ");
        String username = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        authService.authenticate(username, password);
        CurrencyService currencyService = new CurrencyService();
        currencyService.displayAllCurrencies();
    }
}
