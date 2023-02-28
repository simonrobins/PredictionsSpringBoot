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

import uk.simonrobins.PredictionsSpringBoot.entity.User;
import uk.simonrobins.PredictionsSpringBoot.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserWebController
{
    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model)
    {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("create")
    public String create(Model model)
    {
        User user = new User();
        model.addAttribute("action", "create");
        model.addAttribute("user", user);
        return "users/edit";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
        User user = userService.get(id);
        model.addAttribute("action", "edit");
        model.addAttribute("user", user);
        return "users/edit";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model)
    {
        User user = userService.get(id);
        model.addAttribute("action", "delete");
        model.addAttribute("user", user);
        return "users/delete";
    }

    @PostMapping(path = "update", consumes = "application/x-www-form-urlencoded")
    public RedirectView update(User user, @RequestParam("action") String action)
    {
        switch (action)
        {
        case "create":
            userService.create(user);
            break;
        case "edit":
            userService.update(user);
            break;
        case "delete":
            userService.delete(user.getId());
            break;
        }

        return new RedirectView("/users");
    }
}