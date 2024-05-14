package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // INDICANDO QUE ESTA CLASSE É UM CONTROLADOR SPRING REST
@RequestMapping("/hello")// MAPEANDO TODAS AS SOLICITAÇÕES COM O PREFIXO "/HELLO" PARA ESTE CONTROLADOR
public class HelloController {

    @GetMapping // A ANOTAÇÃO @GetMapping mapeia solicitações HTTP GET para esté método
    public String hello() {
        return "Olá mundo, eu estou programando Java WEB";
    }
}