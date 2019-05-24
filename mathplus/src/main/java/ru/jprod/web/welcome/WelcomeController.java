package ru.jprod.web.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер приветствия приложения
 */
@Controller
public class WelcomeController
{
    @GetMapping("/")
    public String greeting(Model model)
    {
        return "greeting";
    }
}
