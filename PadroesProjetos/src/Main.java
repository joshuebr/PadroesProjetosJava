import java.util.ArrayList;
import java.util.List;

// Observer
interface Observer {
    void update(String message);
}


class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " recebeu a seguinte notificação: " + message);
    }
}

// Strategy
interface NotificationStrategy {
    void sendNotification(String message);
}

class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        System.out.println("Enviando e-mail: " + message);
    }
}

class SMSNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        System.out.println("Enviando SMS: " + message);
    }
}

// Singleton
class NotificationService {
    private static NotificationService instance;
    private List<Observer> observers = new ArrayList<>();
    private NotificationStrategy notificationStrategy;

    private NotificationService() {}

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void setNotificationStrategy(NotificationStrategy strategy) {
        this.notificationStrategy = strategy;
    }

    public void sendNotification(String message) {
        notificationStrategy.sendNotification(message);
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// Facade
class NotificationFacade {
    private NotificationService notificationService;

    public NotificationFacade() {
        this.notificationService = NotificationService.getInstance();
    }

    public void registerUser(Observer user) {
        notificationService.addObserver(user);
    }

    public void setNotificationStrategy(NotificationStrategy strategy) {
        notificationService.setNotificationStrategy(strategy);
    }

    public void sendNotification(String message) {
        notificationService.sendNotification(message);
    }
}

// Cliente
public class Main {
    public static void main(String[] args) {
        System.out.println("## Bem-vindo ao sistema de notificações ##");

        NotificationFacade notificationFacade = new NotificationFacade();

        User user1 = new User("Maria");
        User user2 = new User("José");

        notificationFacade.registerUser(user1);
        notificationFacade.registerUser(user2);

        notificationFacade.setNotificationStrategy(new EmailNotificationStrategy());
        notificationFacade.sendNotification("Nova promoção: 20% de desconto em todos os produtos!");

        notificationFacade.setNotificationStrategy(new SMSNotificationStrategy());
        notificationFacade.sendNotification("Oferta especial: Compre 1, leve 2!");
    }
}
