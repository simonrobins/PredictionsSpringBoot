package uk.simonrobins.PredictionsSpringBoot.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.simonrobins.PredictionsSpringBoot.entity.TeamData;
import uk.simonrobins.PredictionsSpringBoot.service.TeamService;

import java.util.List;

@Controller
@RequestMapping("/league")
public class LeagueWebController
{
    @Autowired
    private TeamService teamService;

    @GetMapping
    public String index(Model model)
    {
        List<TeamData> results = teamService.resultsTable();
        model.addAttribute("results", results);

        return "league";
    }
}