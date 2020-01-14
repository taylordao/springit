package us.daofamily.springit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import us.daofamily.springit.domain.Link;
import us.daofamily.springit.repository.LinkRepository;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class LinkController {

    private static final Logger loger = LoggerFactory.getLogger(LinkController.class);
    private LinkRepository linkRepository;

    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("links", linkRepository.findAll());
        return "link/list";
    }

    @GetMapping("/link/{id}")
    public String read(@PathVariable long id, Model model){
        Optional<Link> link = linkRepository.findById(id);
        if(link.isPresent()) {
            model.addAttribute("link", link.get());
            model.addAttribute("success", model.containsAttribute("success"));
            return "link/view";
        }else {
            return "redirect:/";
        }
    }

    @GetMapping("/link/submit")
    public String newLinkForm(Model model){
        model.addAttribute("link", new Link());
        return "/link/submit";
    }

    @PostMapping("/link/submit")
    public String creatLink(@Valid Link link, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            loger.info("Validation errors were found while submitting a new link");
            model.addAttribute("link", link);
            return  "link/submit";
        }else {
            linkRepository.save(link);
            loger.info("New Link was saved successfully");
            redirectAttributes
                    .addAttribute("id", link.getId())
                    .addFlashAttribute("success", true);
            return "redirect:/link/{id}";
        }
    }
}
