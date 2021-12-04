package com.unihack.votefortimisoara.controllers;

import com.unihack.votefortimisoara.controllers.interfaces.EntityControllerInterface;
import com.unihack.votefortimisoara.entities.Decision;
import com.unihack.votefortimisoara.services.DecisionServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : Onetiu-George
 * @since : 12/3/2021, Fri
 **/

@RestController
@RequestMapping(value = "/decision")
public class DecisionController implements EntityControllerInterface<Decision> {

    private final DecisionServiceImplementation decisionServiceImplementation;

    @Autowired
    public DecisionController(final DecisionServiceImplementation decisionServiceImplementation) {
        this.decisionServiceImplementation = decisionServiceImplementation;
    }

    // CREATE
    @PostMapping("")
    @Override
    public ResponseEntity<Object> createEntity(@RequestBody final Decision decision) {
        return decisionServiceImplementation.createEntity(decision);
    }

    @PostMapping("/l")
    @Override
    public ResponseEntity<Object> createEntities(@Valid @RequestBody final List<Decision> decisions) {
        return decisionServiceImplementation.createEntities(decisions);
    }

    // READ
    @GetMapping("/{id}")
    @Override
    public Decision getEntityById(@PathVariable final Long id) {
        return decisionServiceImplementation.getEntityById(id);
    }

    @GetMapping("/l")
    @Override
    public List<Decision> getEntityByIdList(@RequestBody final List<Long> ids) {
        return decisionServiceImplementation.getEntitiesByIdList(ids);
    }

    @GetMapping("")
    @Override
    public List<Decision> getAllEntities() {
        return decisionServiceImplementation.getAllEntities();
    }

    // UPDATE
    @PutMapping("/{id}")
    @Override
    public Decision updateEntityById(@PathVariable final Long id, @RequestBody final Decision decision) throws IllegalAccessException {
        return decisionServiceImplementation.updateEntityById(id, decision);
    }

    // DELETE
    @DeleteMapping("")
    @Override
    public ResponseEntity<String> deleteAllEntities() {
        decisionServiceImplementation.deleteAllEntities();
        return new ResponseEntity<>("Deleted all Decisions!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String> deleteEntityById(@PathVariable final Long id) {
        final boolean isPresent = decisionServiceImplementation.deleteEntityById(id);

        if (isPresent) {
            return new ResponseEntity<>("Deleted Decision with id " + id, HttpStatus.OK);
        }
        return new ResponseEntity<>("Id is non existent", HttpStatus.BAD_REQUEST);
    }
}
