package com.example.bookmanagement.controllers;
import com.example.bookmanagement.models.Book;
import com.example.bookmanagement.services.BookService;
import com.example.bookmanagement.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class BookController {

    private BookService bookService;

    private UploadService uploadService;
    @Autowired
    public BookController(BookService bookService, UploadService uploadService) {
        this.bookService = bookService;
        this.uploadService = uploadService;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/index", "/", "/home"})

    public String index(ModelMap model) {

        List<Book> bookList = this.bookService.getAll();

        model.addAttribute("books", bookList);

        return "book/index";
    }

    @GetMapping("/view/{id}")
    public String viewDetail(@PathVariable("id") Integer id, Model model) {

        System.out.println("ID: " + id);

        Book book = this.bookService.findOne(id);

        model.addAttribute("book", book);

        return "book/view-detail";
    }


    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, ModelMap modelMap) {

        Book book = this.bookService.findOne(id);

        modelMap.addAttribute("isNew", false);
        modelMap.addAttribute("book", book);

        return "book/update";
    }


    @PostMapping("update/submit")
    public String updateSubmit(@ModelAttribute @Valid Book book, MultipartFile file) {
        System.out.println(book);


        File path = new File("/pp6th");

        if (!path.exists())
            path.mkdir();


        String filename = file.getOriginalFilename();

        String extension = filename.substring(filename.lastIndexOf('.') + 1);

        System.out.println(filename);
        System.out.println(extension);


        filename = UUID.randomUUID() + "." + extension;


        System.out.println(filename);

        try {
            Files.copy(file.getInputStream(), Paths.get("/pp6th", filename));
        } catch (IOException e) {

        }

        if (!file.isEmpty()) {
            book.setThumbnail("/images-pp/" + filename);
        }

        this.bookService.update(book);

        return "redirect:/index";
    }


    @GetMapping("delete/{id}")
    public String remove(@PathVariable Integer id) {
        this.bookService.remove(id);

        return "redirect:/index";
    }


    @GetMapping("/count")
    @ResponseBody
    public Map<String, Object> count() {
        Map<String, Object> response = new HashMap<>();

        response.put("record_count", this.bookService.count());
        response.put("status", true);

        return response;
    }


    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("book", new Book());
        model.addAttribute("isNew", true);

        return "book/create-book";
    }


    @PostMapping("/create/submit")
    public String createSubmit(@Valid Book book, BindingResult bindingResult, MultipartFile file, Model model) {
//        System.out.println(book);

        if (bindingResult.hasErrors()) {
            model.addAttribute("isNew", true);
            return "book/create-book";
        }

        String filename = this.uploadService.upload(file, "pp/");


        book.setThumbnail("/images-pp/" + filename);


        this.bookService.create(book);

        return "redirect:/home";
    }


    @GetMapping("/multiple-upload")
    public String testMultipleUpload() {
        return "book/multiple-upload";
    }


    @PostMapping("/multiple-upload/submit")
    public String uploadSubmit(@RequestParam("file") List<MultipartFile> files) {

        if (files.isEmpty()) {
            System.out.println("no");
        }


        files.forEach(file -> {
            System.out.println(file.getOriginalFilename());
        });


        return "";
    }

    @GetMapping("/master")
    public String master(){
        return "master";
    }

}
