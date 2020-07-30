package com.markjr.food.controller;

import com.markjr.food.entity.Food;
import com.markjr.food.entity.MenuEN;
import com.markjr.food.entity.MenuTH;
import com.markjr.food.service.FoodService;
import com.markjr.food.service.MenuService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FoodController {

    @Autowired
    private  FoodService foodService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/{lang}")
    public String index(@PathVariable String lang) {
        foodService.clear();
        foodService.resetTotal();
        if(lang.equals("thai")) {
            return "indexTH";
        }
        return "index";
    }

    @GetMapping("/pingyang/{lang}/{type}")
    public String pingyang(Model model,@PathVariable String lang,@PathVariable String type) {
        int total = foodService.getTotal();
        model.addAttribute("food",foodService.find());
        if(lang.equals("thai")) {
            String temp = "ราคารวม "+total+" บาท";
            model.addAttribute("total", temp);
            model.addAttribute("menu", menuService.findTH(type));
            return "pingyangTH";
        }
        String temp = "Total "+total+" Bath";
        model.addAttribute("total", temp);
        model.addAttribute("menu", menuService.findEN(type));
        return "pingyangEN";
    }

    @GetMapping("/add/{lang}/{type}/{id}")
    public String add(@PathVariable String lang,@PathVariable String type,@PathVariable Long id) throws NotFoundException {
        if(lang.equals("thai")) {
            MenuTH temp = menuService.findByIdTH(id);
            foodService.saveTH(temp);
        }
        else {
            MenuEN temp = menuService.findByIdEN(id);
            foodService.saveEN(temp);
        }
        return "redirect:/pingyang/"+lang+"/"+type;
    }

    @GetMapping("/delete/{lang}/{type}/{id}")
    public String delete(@PathVariable String lang,@PathVariable String type,@PathVariable Long id) throws NotFoundException {
        Food food = foodService.findById(id);
        foodService.delete(food);
        return "redirect:/pingyang/"+lang+"/"+type;
    }

    @GetMapping("/pay/{lang}")
    public String pay(Model model,@PathVariable String lang) {
        int total = foodService.getTotal();
        model.addAttribute("food",foodService.find());
        if(lang.equals("thai")) {
            String temp = "ราคารวม "+total+" บาท";
            model.addAttribute("total", temp);
            return "payTH";
        }
        String temp = "Total "+total+" Bath";
        model.addAttribute("total", temp);
        return "payEN";
    }
}
