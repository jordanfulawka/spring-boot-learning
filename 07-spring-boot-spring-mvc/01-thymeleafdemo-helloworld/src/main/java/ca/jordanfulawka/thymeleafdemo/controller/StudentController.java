package ca.jordanfulawka.thymeleafdemo.controller;

import ca.jordanfulawka.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @GetMapping("/showStudentForm")
    public String showForm(Model model) {

        // create new Student object
        Student student = new Student();

        // add student object to the model
        model.addAttribute("student", student);
        model.addAttribute("countries", countries);
        model.addAttribute("languages", languages);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student student) {

        // log the input data
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return "student-confirmation";
    }
}
