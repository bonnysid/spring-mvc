package org.bonnysid.bloom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyController {

    @GetMapping("/hello")
    public String getUser(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("msg", name);
        return "hello";
    }

    @GetMapping("/calc")
    public String calculate(@RequestParam("a") String a,
                            @RequestParam("b") String b,
                            @RequestParam("action") String action, Model model) {
        char sign = 'n';
        double res = 0;

        switch (action) {
            case "mult":
                sign = '*';
                res = Double.parseDouble(a) * Double.parseDouble(b);
                break;
            case "sum":
                sign = '+';
                res = Double.parseDouble(a) + Double.parseDouble(b);
                break;
            case "div":
                sign = '/';
                res = Double.parseDouble(a) / Double.parseDouble(b);
                break;
            case "minus":
                sign = '-';
                res = Double.parseDouble(a) - Double.parseDouble(b);
                break;
        }
        model.addAttribute("res", a + " " + sign + " " + b + " = " + res);
        return "calculator";
    }
}
