package pro.sky.calculator.controller;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pro.sky.calculator.service.CalculatorService;
import java.text.MessageFormat;

@RequestMapping("/calculator")
@RestController
public class CalculatorController {
    public CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping
    public String printHello (){
        return "Добро пожаловать в калькулятор";
    }

    @GetMapping("/plus")
    public String plus(@RequestParam Integer num1, @RequestParam Integer num2) {
        return MessageFormat.format("{0} + {1} = {2}", num1, num2, calculatorService.plus(num1, num2));
    }
    @GetMapping("/minus")
    public String minus(@RequestParam Integer num1, @RequestParam Integer num2) {
        return MessageFormat.format("{0} - {1} = {2}", num1, num2, calculatorService.minus(num1, num2));
    }
    @GetMapping("/multiply")
    public String multiply(@RequestParam Integer num1, @RequestParam Integer num2) {
        return MessageFormat.format("{0} * {1} = {2}", num1, num2, calculatorService.multiply(num1, num2));
    }
    @GetMapping("/divide")
    public String divide(@RequestParam Integer num1, @RequestParam Integer num2) {
        return MessageFormat.format("{0} / {1} = {2}", num1, num2, calculatorService.divide(num1, num2));
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        return name + " parameter is missing";

    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleMissingParams(MethodArgumentTypeMismatchException ex) {
        String name = ex.getName();
        return name + " parameter is not integer";
    }
}
