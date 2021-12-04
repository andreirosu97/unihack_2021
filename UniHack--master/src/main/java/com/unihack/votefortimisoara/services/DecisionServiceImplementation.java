package com.unihack.votefortimisoara.services;

import com.unihack.votefortimisoara.entities.Decision;
import com.unihack.votefortimisoara.repositories.DecisionRepository;
import com.unihack.votefortimisoara.services.interfaces.RestInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Onetiu-George
 * @since : 12/3/2021, Fri
 **/
@Service
public class DecisionServiceImplementation implements RestInterface<Decision> {

    private final DecisionRepository decisionRepository;
    private final RestService<Decision> restService;

    public DecisionServiceImplementation(final DecisionRepository decisionRepository, final RestService<Decision> restService) {
        this.decisionRepository = decisionRepository;
        this.restService = restService;
    }

    @Override
    public ResponseEntity<Object> createEntity(Decision decision) {
        return restService.createEntity(decision, decisionRepository);
    }

    @Override
    public ResponseEntity<Object> createEntities(List<Decision> decisions) {
        return restService.createEntities(decisions, decisionRepository);
    }

    @Override
    public Decision getEntityById(Long id) {
        return restService.getEntityById(id, decisionRepository, Decision.class);
    }

    @Override
    public List<Decision> getEntitiesByIdList(List<Long> ids) {
        return restService.getEntitiesByIdList(ids, decisionRepository, Decision.class);
    }

    @Override
    public List<Decision> getAllEntities() {
        return restService.getAllEntities(decisionRepository, Decision.class);
    }

    @Override
    public Decision updateEntityById(Long id, Decision decision) throws IllegalAccessException {
        if (decisionRepository.findById(id).isPresent()) {
            final Decision decisionToUpdate = decisionRepository.findById(id).get();

            return restService.updateEntity(decision, decisionToUpdate, decisionRepository);
        }
        return null;
    }

    @Override
    public void deleteAllEntities() {
        restService.deleteAllEntities(decisionRepository, Decision.class);
    }

    @Override
    public boolean deleteEntityById(Long id) {
        return restService.deleteEntityById(id, decisionRepository, Decision.class);
    }
}
