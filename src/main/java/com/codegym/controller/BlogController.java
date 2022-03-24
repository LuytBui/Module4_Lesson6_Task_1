package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/list")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Blog> blogs = blogService.findAll();
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("create")
    public ModelAndView createBlog(@ModelAttribute(name="blog") Blog blog){
        blogService.save(blog);
        return new ModelAndView("redirect:/blogs/list");
    }

    @GetMapping ("{id}/edit")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Blog blog = blogService.findOne(id);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @PostMapping ("{id}/edit")
    public ModelAndView editBlog(@ModelAttribute Blog blog){
        blogService.save(blog);
        return new ModelAndView("redirect:/blogs/list");
    }
    @GetMapping ("{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Blog blog = blogService.findOne(id);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @PostMapping ("{id}/delete")
    public ModelAndView deleteBlog(@PathVariable Long id){
        Blog blog = blogService.remove(id);
        return new ModelAndView("redirect:/blogs/list");
    }

    @GetMapping("/{id}")
    public ModelAndView viewOneBlog(@PathVariable Long id){
        Blog blog = blogService.findOne(id);
        ModelAndView modelAndView;
        if (blog == null){
            modelAndView = new ModelAndView("redirect:/blogs/list");
        } else {
            modelAndView = new ModelAndView("view");
            modelAndView.addObject("blog", blog);
        }
        return modelAndView;

    }
}
