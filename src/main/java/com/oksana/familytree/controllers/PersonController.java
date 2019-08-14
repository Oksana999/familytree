package com.oksana.familytree.controllers;

import com.oksana.familytree.entity.Person;
import com.oksana.familytree.entity.PersonRelation;
import com.oksana.familytree.entity.RelationType;
import com.oksana.familytree.services.PersonRelationService;
import com.oksana.familytree.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final PersonRelationService personRelationService;

    @RequestMapping(value = "/", method = GET)
    public String persons(Model model) {
        List<Person> persons = this.personService.findAll();
        model.addAttribute("persons", persons);
        return "persons";
    }

    @RequestMapping(value = "person", method = POST)
    public String createOrUpdate(Long id, String name, String surname, String fathername, boolean gender,
            /*LocalDateTime birthday, LocalDateTime deadDay,*/ Model model) {
        Person person = this.personService.createOrUpdate(id, name, surname, fathername, LocalDateTime.now().minusYears(20L), null, gender);
        return getById(person.getId(), model);
    }

    @RequestMapping(value = "/person/{id}", method = GET)
    public String getById(@PathVariable Long id, Model model) {
        Person person = this.personService.findById(id);
        List<PersonRelation> relationsParents = person.getRelationsParent();
        List<Person> children = new ArrayList<>();
        for (PersonRelation relationsParent : relationsParents) {
            children.add(relationsParent.getChild());
        }
        List<Person> parents = new ArrayList<>();
        List<PersonRelation> relationsChild = person.getRelationsChild();
        for (PersonRelation personRelation : relationsChild) {
            parents.add(personRelation.getParent());
        }

        model.addAttribute("person", person);
        model.addAttribute("parents", parents);
        model.addAttribute("children", children);
        return "person";
    }

    @RequestMapping(value = "person/{id}/delete", method = GET)
    public String delete(@PathVariable Long id, Model model) {
        Person foundPerson = this.personService.findById(id);
        this.personService.delete(foundPerson.getId());
        return persons(model);
    }

    @RequestMapping(value = "/relation", method = POST)
    public String getParents(Long personId, Long relationId, int relationType, Model model) {
        this.personRelationService.createRelation(personId, relationId, RelationType.of(relationType));
        return getById(personId, model);
    }

    @RequestMapping(method = GET)
    public @ResponseBody String g404() {
        return "404 not found. GET";
    }

    @RequestMapping(method = POST)
    public @ResponseBody String p404() {
        return "404 not found. POST";
    }

}

