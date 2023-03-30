package uk.simonrobins.PredictionsSpringBoot.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uk.simonrobins.PredictionsSpringBoot.entity.Prediction;
import uk.simonrobins.PredictionsSpringBoot.entity.User;
import uk.simonrobins.PredictionsSpringBoot.entity.Result;
import uk.simonrobins.PredictionsSpringBoot.service.FixtureService;
import uk.simonrobins.PredictionsSpringBoot.service.PredictionService;
import uk.simonrobins.PredictionsSpringBoot.service.UserService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/predictions")
public class PredictionWebController {
    @Autowired
    private FixtureService fixtureService;
    @Autowired
    private PredictionService predictionService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Integer round) {
        List<User> users = userService.findAll();
        if (null == userId)
            userId = users.get(0).getId();

        List<Prediction> predictions;
        if (round != null)
            predictions = predictionService.getByUserIdAndRound(userId, round);
        else
            predictions = predictionService.getByUserId(userId);

        Set<Integer> rounds = fixtureService.findAllRounds();

        model.addAttribute("round", round);
        model.addAttribute("rounds", rounds);
        model.addAttribute("userId", userId);
        model.addAttribute("users", users);
        model.addAttribute("predictions", predictions);

        Result[] results = Result.values();
        model.addAttribute("results", results);

        // The fixture result can only be changed once the fixture is in the past :)
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        model.addAttribute("today", today);

        return "predictions";
    }
}