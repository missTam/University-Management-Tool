package de.academy.controllers;

import de.academy.dto.UserDTO;
import de.academy.entities.User;
import de.academy.services.LoginService;
import de.academy.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {

    private final LoginService loginService;
    private final UserRegistrationService registrationService;

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public UserRegistrationController(UserRegistrationService registrationService, LoginService loginService) {
        this.registrationService = registrationService;
        this.loginService = loginService;
    }

    // removes leading and trailing whitespace
    // resolves issue of space validation; only has white spaces ? trim to null
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/show-registration-form")
    public String showRegistrationForm(Model model) {

        // create model attribute to bind form data
        // instantiate User Data Transfer Object (view model)
        model.addAttribute("userDTO", new UserDTO());
        return "forms/registration-form";
    }

    @PostMapping("/process-registration-form")
    public String processRegistrationForm(
            @Valid @ModelAttribute("userDTO") UserDTO userDTO,
            BindingResult bindingResult,
            Model model) {

        String username = userDTO.getUsername();
        logger.info("Processing registration form for: " + username);

        // validate form; show errors but keep user input
        if(bindingResult.hasErrors()) {
            return "forms/registration-form";
        }

        // check the database if the user already exists
        // if yes, show error message and refresh the form fields: new UserDTO()
        User existing = loginService.findByUsername(username);
        if (existing != null) {
            model.addAttribute("userDTO", new UserDTO());
            model.addAttribute("registrationError", "This username already exists.");

            logger.warning("Username already exists.");
            return "forms/registration-form";
        }

        // create user account
        registrationService.registerUser(userDTO);

        logger.info("Successfully created user: " + username);

        return "forms/registration-confirmation";
    }
}
