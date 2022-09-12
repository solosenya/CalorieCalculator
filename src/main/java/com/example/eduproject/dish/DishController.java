package com.example.eduproject.dish;

import com.example.eduproject.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Controller
public class DishController {

    @Autowired
    private DishRepository dishRepository;

    @GetMapping("/newDish")
    public String newDish(Model model) {
        model.addAttribute("newDish", new Dish());
        return "dishAdditionPages/newDish";
    }

    @PostMapping("/newDish")
    public String newDishSubmit(Dish dish) {
        ExampleMatcher nameMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id", "proteins", "carbs", "lipids", "kiloCalories")
                .withMatcher("name".trim(), ignoreCase());
        Example<Dish> example = Example.of(dish, nameMatcher);
        boolean exists = dishRepository.exists(example);
        if (exists) {
            return "dishAdditionPages/alreadyExists";
        }
        dishRepository.save(dish);
        return "dishAdditionPages/dishAdded";
    }

    @GetMapping("/dishesList20")
    public String getDishesList20(Model model, @RequestParam String page) {
        long pageLong = Integer.parseInt(page);
        model.addAttribute("dishesList", dishRepository
                .findAll()
                .stream()
                .skip((pageLong * 20L) - 20L)
                .limit(20L)
                .collect(Collectors.toList()));

        return "dishListPage/dishesList20";
    }

    @ModelAttribute("amountOfPages")
    public List<Integer> getAmountOfPagesToShow () {
        return IntStream
                .rangeClosed(1, (int) Math
                        .ceil(dishRepository
                                .findAll()
                                .size() / 20.0))
                .boxed()
                .toList();
    }

    @GetMapping("/dishesList")
    public String getDishesList(Model model) {
        model.addAttribute("dishesList", dishRepository.findAll());
        return "dishListPage/dishesList";
    }

    @GetMapping("/deleteDish")
    public String nameToDelete(Model model) {
        model.addAttribute("dishToDelete", new DishToDelete());
        return "dishDeletionPages/nameToDelete";
    }

    @PostMapping("/deleteDish")
    public String deleteDish(@ModelAttribute DishToDelete dishToDelete, Model model) {
        int deleted = dishRepository.deleteDish(dishToDelete.getName());
        if (deleted > 0) {
            model.addAttribute("dishToDelete", dishToDelete);
            return "dishDeletionPages/dishDeleted";
        }
        return "dishDeletionPages/nothingToDelete";
    }
}
