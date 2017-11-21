package pl.akademiakodu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.akademiakodu.model.SearchModel;
import pl.akademiakodu.repository.BookRepository;
import pl.akademiakodu.model.Book;

import javax.validation.Valid;

/**
 * Created by Norbert on 2017-10-14.
 */
@Controller
@RequestMapping("/book")
public class BookController {


    @Autowired
    private BookRepository bookRepository;


    @GetMapping
    public ModelAndView getBooks() {
        ModelAndView mav = new ModelAndView("book-list");
        mav.addObject("list", bookRepository.findAll());
        mav.addObject("searchmodel", new SearchModel());
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView addBookForm() {
        ModelAndView mav = new ModelAndView("book-form");
        mav.addObject("book", new Book());
        return mav;
    }

    @PostMapping("/add")
    public ModelAndView saveBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        System.out.println(book);
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mav.setViewName("book-form");
        } else {
            bookRepository.save(book);
            mav.setViewName("redirect:/book");
        }
        return mav;
    }


    @GetMapping("/{id}")
    public ModelAndView getDetails(@PathVariable("id") int bookId) {
        ModelAndView mav = new ModelAndView("book-details");
        mav.addObject("details", bookRepository.findOne(bookId));
        return mav;
    }

    @GetMapping("/remove/{id}")
    public String deleteBook(@PathVariable("id") int bookId) {
        bookRepository.delete(bookId);
        return "redirect:/book";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editBook(@PathVariable("id") int bookId) {
        ModelAndView mav = new ModelAndView("book-form");
        mav.addObject("book", bookRepository.findOne(bookId));
        return mav;
    }

    @PostMapping("/find")
    public ModelAndView findByTitle(@ModelAttribute("searchmodel") SearchModel searchModel) {
        ModelAndView mav = new ModelAndView("book-find");
        mav.addObject("book",bookRepository.findBooksByTitle(searchModel.getValue()));
        return mav;
    }

}