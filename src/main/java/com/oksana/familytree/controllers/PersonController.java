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

    @RequestMapping(method = GET)
    public String persons(Model model) {
        List<Person> persons = this.personService.findAll();
        model.addAttribute("persons", persons);
        return "persons";
    }

    @RequestMapping(value = "person", method = POST)
    public String createOrUpdate(Long id, String name, String surname, String fathername,
            /*LocalDateTime birthday, LocalDateTime deadDay,*/ Model model) {
        Person person = this.personService.createOrUpdate(id, name, surname, fathername, LocalDateTime.now().minusYears(20L), null);
        model.addAttribute(person);
        return "person";
    }

    @RequestMapping(value = "person/{id}", method = GET)
    public String getByFIO(@PathVariable Long id, Model model) {
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


    @RequestMapping(value = "r/pc/{id}/{id2}", method = GET)
    public String relationParentChild(@PathVariable Long id, @PathVariable Long id2, Model model) {
        personRelationService.createRelation(id, id2, RelationType.PARENT_CHILD);
        return persons(model);
    }

    @RequestMapping(value = "r/m/{id}/{id2}", method = GET)
    public String relationMarriage(@PathVariable Long id, @PathVariable Long id2, Model model) {
        personRelationService.createRelation(id, id2, RelationType.MARRIAGE);
        return persons(model);
    }

}

