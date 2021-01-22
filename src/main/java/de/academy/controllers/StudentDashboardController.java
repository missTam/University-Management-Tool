package de.academy.controllers;

import de.academy.services.LectureEnrollmentService;
import de.academy.dto.StudentDTO;
import de.academy.entities.Lecture;
import de.academy.services.LectureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
@RequestMapping("/student-dashboard")
public class StudentDashboardController {

    private final LectureService lectureService;
    private final LectureEnrollmentService lectureEnrollmentService;

    public StudentDashboardController(LectureService lectureService, LectureEnrollmentService lectureEnrollmentService) {
        this.lectureService = lectureService;
        this.lectureEnrollmentService = lectureEnrollmentService;
    }

    @GetMapping("/")
    public String showStudentDashboardPage(
            Model model,
            HttpSession session) {

        StudentDTO studentDTO = (StudentDTO) session.getAttribute("student");
        Set<Lecture> unregisteredLectures = lectureService.getAllUnregisteredLecturesForStudent(studentDTO.getLectures());

        model.addAttribute("lectures", unregisteredLectures);

        return "accounts/student-dashboard";
    }

    @PostMapping("/enroll")
    public String enrollInLecture(
            @RequestParam("lectureId") long lectureId,
            HttpSession session) {

        StudentDTO studentDTO = (StudentDTO) session.getAttribute("student");
        studentDTO = lectureEnrollmentService.enrollStudentInLecture(studentDTO.getId(), lectureId);

        session.setAttribute("student", studentDTO);

        return "redirect:/student-dashboard/";
    }

    @PostMapping("/disenroll")
    public String disenrollFromLecture(
            @RequestParam("lectureId") long lectureId,
            HttpSession session) {

        StudentDTO studentDTO = (StudentDTO) session.getAttribute("student");
        studentDTO = lectureEnrollmentService.disenrollStudentFromLecture(studentDTO.getId(), lectureId);

        session.setAttribute("student", studentDTO);

        return "redirect:/student-dashboard/";
    }
}
