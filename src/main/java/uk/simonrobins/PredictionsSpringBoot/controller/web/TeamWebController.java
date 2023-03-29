package uk.simonrobins.PredictionsSpringBoot.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import uk.simonrobins.PredictionsSpringBoot.entity.Team;
import uk.simonrobins.PredictionsSpringBoot.service.TeamService;

import java.util.Set;

@Controller
@RequestMapping("/teams")
public class TeamWebController
{
    @Autowired
    private TeamService teamService;

    @GetMapping
    public String index(Model model)
    {
        Set<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        return "teams/index";
    }

    @GetMapping("create")
    public String create(Model model)
    {
        Team team = new Team();
        model.addAttribute("action", "create");
        model.addAttribute("team", team);
        return "teams/edit";
    }

    @GetMapping("update/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
        Team team = teamService.findById(id);
        model.addAttribute("action", "update");
        model.addAttribute("team", team);
        return "teams/edit";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model)
    {
        Team team = teamService.findById(id);
        model.addAttribute("action", "delete");
        model.addAttribute("team", team);
        return "teams/delete";
    }

    @PostMapping(path = "update", consumes = "application/x-www-form-urlencoded")
    public RedirectView update(Team team, @RequestParam("action") String action)
    {
        switch (action)
        {
        case "create":
            teamService.create(team);
            break;
        case "update":
            teamService.update(team);
            break;
        case "delete":
            teamService.delete(team.getId());
            break;
        }

        return new RedirectView("/teams");
    }
}