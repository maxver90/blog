package maxver90.blog.controller;

import maxver90.blog.pojo.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/test")
public class TestController {

    // Основная задача контроллера - получить информацию из запроса пользователя и
    // вернуть ему результат, чаще всего в виде HTML документа.
    // Класс контроллер, как и его методы, обрабатывают запросы на определенных ресурсах (адресах)

    // localhost:8080/ -> MainController (path = "/")
    // localhost:8080/test -> TestController (path = "/test")
    // localhost:8080/auth/login - AuthController (path = "/auth")
    // localhost:8080/auth/registration - AuthController (path = "/auth")

    // @Controller - аннотация, которая делает из обычного Java класса элемент приложения,
    // который будет обрабатывать запросы и возвращать результаты в виде HTML, т.е. контроллер.

    // @RequestMapping - аннотация, которая задает классу либо методу адрес ресурса, который он будет обрабатывать.

    // К одному и тоже же ресурсу можно обращаться разными методами.
    // Методы:
    // GET - данные передаются в открытом виде.
    // POST - данные скрываются внутри запроса.

    @GetMapping(path = "/part_1")
    public String firstPart() {
        return "test/part_1";
    }

    // localhost:8080/test?firstName=John&age=55

    // @RequestParam - аннотация, которая применяется для определения параметров из строки запроса.

    @GetMapping(path = "/part_2")
    public String secondPart(@RequestParam String firstName, @RequestParam int age, @RequestParam String lastName) {
        System.out.println(firstName + " " + age + " " + lastName);
        return "test/part_2";
    }

    // https://vk.com/profile?id=731
    // https://vk.com/profile/731
    // @PathVariable - аннотация, которая применяется для определения параметров, скрытых в адресе ресурса.

    @GetMapping(path = "/part_3/{login}")
    public String thirdPart(@PathVariable String login) {
        System.out.println(login);
        return "test/part_3";
    }

    // Model - объект, который применятся для передачи данных из контроллера в представление (HTML).
    // У объекта Model есть атрибуты. Атрибуты - структура по типу Map, в которой к каждому ключу соот-т
    // какая-то информация.

    // HTML - статический инструмент описания элементов страницы. Используя только HTML не получится
    // создавать динамические сайты.
    // Thymeleaf - библиотека, позволяющая генерировать динамические HTML страницы используя при этом
    // конструкции if, switch, for и т.п.
    // ${название переменной или атрибута} - конструкция, которая позволяет обращаться к переменным
    // и атрибутам внутри HTML, внутри ${} так же можно писать условия, арифметические действия и т.п.

    // th:text - атрибут, применяемы для встраивания текста внутрь HTML элемента.
    // th:each - аналог foreach
    // th:if - аналог if


    @GetMapping(path = "/part_4")
    public String fourthPart(Model model) {
        List<String> tasks = new ArrayList<>();
        tasks.add("Сделать дз");
        tasks.add("Захватить мир");
        model.addAttribute("tasks", tasks);
        return "test/part_4";
    }
    @GetMapping(path = "/part_5")
    public String fifthPart(Model model){
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task ("Сделать дз", true));
        tasks.add(new Task("Помыть посуду", true));
        tasks.add(new Task("Захватить мир", false));
        model.addAttribute("tasks", tasks);
        return "test/part_5";
    }
}
