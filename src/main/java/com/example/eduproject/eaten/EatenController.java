package com.example.eduproject.eaten;

import com.example.eduproject.dish.Dish;
import com.example.eduproject.repository.DishRepository;
import com.example.eduproject.repository.EatenRepository;
import com.example.eduproject.statistics.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EatenController {

    @Autowired
    EatenRepository eatenRepository;
    @Autowired
    DishRepository dishRepository;

    @GetMapping("/eaten")
    public String eaten(Model model) {
        model.addAttribute("eaten", new EatenFood());
        model.addAttribute("eatenList", eatenRepository.findAll());
        return "eatenAdditionPages/eaten";
    }

    @PostMapping("/eaten")
    public String eatenProven(EatenFood eatenFood, Model model) {
        boolean exists = dishRepository.existsDishByName(eatenFood.getFood());

        if (exists) {
            eatenRepository.save(eatenFood);
            model.addAttribute("eatenList", eatenRepository.findAll());
            return "eatenAdditionPages/result";
        }
        model.addAttribute("eatenList", eatenRepository.findAll());
        return "eatenAdditionPages/foodDoesNotExistInDishesDB";
    }

    @GetMapping("/deleteEaten")
    public String nameToDelete(Model model) {
        model.addAttribute("eatenToDelete", new EatenToDelete());
        model.addAttribute("eatenList", eatenRepository.findAll());
        return "eatenDeletionPages/eatenToDelete";
    }

    @PostMapping("/deleteEaten")
    public String deleteDish(@ModelAttribute EatenToDelete eatenToDelete, Model model) {
        int deleted = eatenRepository.deleteEaten(eatenToDelete.getFood());
        if (deleted > 0) {
            model.addAttribute("eatenToDelete", eatenToDelete);
            model.addAttribute("eatenList", eatenRepository.findAll());
            return "eatenDeletionPages/eatenDeleted";
        }
        model.addAttribute("eatenList", eatenRepository.findAll());
        return "eatenDeletionPages/eatenNothingToDelete";
    }

    @GetMapping("/statistics")
    public String getStatistics(Model model) {
        Map<EatenFood, Dish> eatenDishMap = new HashMap<>();
        List<EatenFood> eatenList = eatenRepository.findAll();
        for (EatenFood eatenFood : eatenList) {
            Dish dishFromDB = dishRepository.findDishByName(eatenFood.getFood());
            eatenDishMap.put(eatenFood, dishFromDB);
        }
        Statistics statistics = new Statistics(eatenDishMap);
        model.addAttribute("statistics", statistics);
        return "statistics/statistics";
    }
}
