package com.example.calculator;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class MainController {

    @RequestMapping(value = "/calc/{action}", method = {RequestMethod.GET, RequestMethod.POST})
    public String calculate(
            @PathVariable String action,
            @RequestParam BigDecimal a, @RequestParam BigDecimal b
    ) {

        return switch (action) {
            case "add" -> a.add(b).toPlainString();
            case "subtract" -> a.subtract(b).toPlainString();
            case "multiply" -> a.multiply(b).toPlainString();
            case "divide" -> a.divide(b, 3, RoundingMode.HALF_UP).toPlainString();
            default -> throw new IllegalStateException("Wrong action: " + action);
        };
    }

    @ExceptionHandler(Exception.class)
    public String badRequestExceptionHandler(Exception ex, WebRequest request) {
        return "Error: " + ex;
    }
}