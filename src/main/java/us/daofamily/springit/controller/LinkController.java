package us.daofamily.springit.controller;

import org.hibernate.validator.internal.util.ConcurrentReferenceHashMap;
import org.springframework.web.bind.annotation.*;
import us.daofamily.springit.domain.Link;
import us.daofamily.springit.repository.LinkRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/links")
public class LinkController {

    private LinkRepository linkRepository;

    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    //list
    @GetMapping("/")
    public List<Link> list() {
        return  linkRepository.findAll();
    }

    //CRUD
    @PostMapping("/")
    public Link create(@ModelAttribute Link link) {
        return linkRepository.save(link);
    }

    @GetMapping("/{id}")
    public Optional<Link> read(@PathVariable long id) {
        return  linkRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Link update(@PathVariable long id, @ModelAttribute Link link) {
        return linkRepository.save(link);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        linkRepository.deleteById(id);
    }
}
