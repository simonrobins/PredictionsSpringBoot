package uk.simonrobins.PredictionsSpringBoot.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.simonrobins.PredictionsSpringBoot.entity.UserAndScore;
import uk.simonrobins.PredictionsSpringBoot.service.PredictionService;
import uk.simonrobins.PredictionsSpringBoot.service.UserService;

@Controller
@RequestMapping("/")
public class IndexWebController
{
    @Autowired
    UserService userService;
    @Autowired
    PredictionService predictionService;

    @GetMapping
    public String index(Model model)
    {
        List<UserAndScore> usersAndScores = predictionService.predictionScores();
        model.addAttribute("usersAndScores", usersAndScores);

        return "index";
    }
}