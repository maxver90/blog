package maxver90.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Maxver90BlogApplication {

    // Веб приложение работает по принципу клиент серверной архитектуры, т.е.:
    // 1. Пользователь формирует обращение к ресурсу (адресу) из браузера.
    // 2. Приложение с сервер обрабатывает запрос пользователя.
    // 3. Приложение должно вернуть пользователю результат, зачастую в виде HTML документа.

    // Современные веб приложения строятся по методологии MVC.
    // MVC - мотодология, которая подразумевает 3 основных компонента в приложении :
    // M (Model) - компонент, который отвечает за работу с данными а приложении.
    // V (View) - компонент, который отвечает за визуализацию информации.
    // C (Controller) - компонент, который служит для приемки информации в ее первичной
    // обработке и формирования представления (view). Контроллеры являются связующим звеном между
    // остальными элементами приложения.



    public static void main(String[] args) {
        SpringApplication.run(Maxver90BlogApplication.class, args);
    }

}
