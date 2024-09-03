package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/home")
    String GetHome(Model model){
        model.addAttribute("name", "Tom");
        return "home";
    }


    @PostMapping("/home")
    public String SetNameHome(Model model,
                              @RequestParam(name = "NameInput", required = false, defaultValue = "TestWord") String name1){
        model.addAttribute("name1", name1);
        return "home";
    }

    @GetMapping("/converter_coins")
    public String GetConverter(){
        return "converter_coins";
    }

    @PostMapping("/converter_coins")
    public String SetCalc(Model model,
                          @RequestParam(name = "a", required = false, defaultValue = "0") double a,
                          @RequestParam(name = "cur1") String cur1,
                          @RequestParam(name = "cur2") String cur2) {
        double b = 0;
        if (cur1.equals("RUB") && cur2.equals("USD")) {
            b = a / 91;
        } else if (cur1.equals("USD") && cur2.equals("RUB")) {
            b = a * 91;
        } else if (cur1.equals("RUB") && cur2.equals("DKK")) {
            b = a * 0.07475;
        } else if (cur1.equals("DKK") && cur2.equals("RUB")) {
            b = a / 0.07475;
        } else if (cur1.equals("USD") && cur2.equals("DKK")) {
            b = a * 91 * 0.07475;
        } else if (cur1.equals("DKK") && cur2.equals("USD")) {
            b = a / 0.07475 / 91;
        }
        else if (cur1.equals("USD") && cur2.equals("USD") || cur1.equals("RUB") && cur2.equals("RUB")
        || cur1.equals("DKK") && cur2.equals("DKK")){
            b = a;
        }
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("cur1", cur1);
        model.addAttribute("cur2", cur2);

        return "converter_coins";
    }


    @GetMapping("/calc")
    public String calc(){
        return "calc";
    }

    @PostMapping("/calc")
    public String SetCalc(Model model,
                          @RequestParam(name = "a", required = false) double a,
                          @RequestParam(name = "b", required = false) double b,
                          @RequestParam(name = "operation", required = false) String operation) {
        double result = 0;
        switch (operation) {
            case "add":
                result = a + b;
                break;
            case "minus":
                result = a - b;
                break;
            case "multiply":
                result = a * b;
                break;
            case "divide":
                if (b != 0) {
                    result = a / b;
                }
                break;
        }
        model.addAttribute("result", result);
        return "calc";
    }
}
