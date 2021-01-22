package de.academy.controllers;

import de.academy.dto.ProfessorDTO;
import de.academy.entities.Lecture;
import de.academy.services.LectureRegistrationService;
import de.academy.services.LectureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
@RequestMapping("/professor-dashboard")
public class ProfessorDashboardController {

    private final LectureService lectureService;
    private final LectureRegistrationService lectureRegistrationService;

    public ProfessorDashboardController(LectureService lectureService, LectureRegistrationService lectureRegistrationService) {
        this.lectureService = lectureService;
        this.lectureRegistrationService = lectureRegistrationService;
    }

    @GetMapping("/")
    public String showProfessorDashboardPage(Model model) {

        Set<Lecture> lectures = lectureService.getAllLectures();
        model.addAttribute("lectures", lectures);

        return "accounts/professor-dashboard";
    }

    @PostMapping("/register-lecture")
    public String registerForLecture(
            @RequestParam("lectureId") long lectureId,
            HttpSession session) {

        ProfessorDTO professorDTO = (ProfessorDTO) session.getAttribute("professor");
        professorDTO = lectureRegistrationService.registerProfessorForLecture(professorDTO.getId(), lectureId);

        session.setAttribute("professor", professorDTO);

        return "redirect:/professor-dashboard/";
    }

    @PostMapping("/deregister-lecture")
    public String deregisterFromLecture(
            @RequestParam("lectureId") long lectureId,
            HttpSession session) {

        ProfessorDTO professorDTO = (ProfessorDTO) session.getAttribute("professor");
        professorDTO = lectureRegistrationService.deregisterProfessorFromLecture(professorDTO.getId(), lectureId);

        session.setAttribute("professor", professorDTO);

        return "redirect:/professor-dashboard/";
    }
}
