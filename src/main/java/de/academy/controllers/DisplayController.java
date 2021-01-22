package de.academy.controllers;

import de.academy.entities.Lecture;
import de.academy.entities.Professor;
import de.academy.entities.Student;
import de.academy.services.LectureService;
import de.academy.services.ProfessorService;
import de.academy.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/list")
public class DisplayController {

    private final ProfessorService professorService;
    private final StudentService studentService;
    private final LectureService lectureService;

    @Autowired
    public DisplayController(ProfessorService professorService, StudentService studentService,
                             LectureService lectureService) {
        this.professorService = professorService;
        this.studentService = studentService;
        this.lectureService = lectureService;
    }

    @GetMapping("/professors")
    public String listProfessors(Model model) {
        List<Professor> professors = professorService.getAllProfessors();

        if(!professors.isEmpty()) {
            model.addAttribute("professors", professors);
        }
        return "lists/list-professors";
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();

        if(!students.isEmpty()) {
            model.addAttribute("students", students);
        }
        return "lists/list-students";
    }

    @GetMapping("/lectures")
    public String listLectures(Model model) {
        Set<Lecture> lectures = lectureService.getAllLectures();

        if(!lectures.isEmpty()) {
            model.addAttribute("lectures", lectures);
        }
        return "lists/list-lectures";
    }

}
