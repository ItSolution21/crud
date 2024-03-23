package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@Controller
public class HomeController {
	
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/{id}")
    public String getStudentById(@PathVariable("id") Integer id, Model model) {
        studentService.getStudentById(id).ifPresent(student -> model.addAttribute("student", student));
        return "student";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "create-student";
    }

    @PostMapping("/students")
    public String saveOrUpdateStudent(@ModelAttribute("student") Student student) {
        studentService.saveOrUpdateStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Integer id, Model model) {
        studentService.getStudentById(id).ifPresent(student -> model.addAttribute("student", student));
        return "edit-student";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
    }
