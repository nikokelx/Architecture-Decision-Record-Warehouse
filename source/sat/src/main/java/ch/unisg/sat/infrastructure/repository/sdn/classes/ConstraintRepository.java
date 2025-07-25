package ch.unisg.sat.infrastructure.repository.sdn.classes;

import ch.unisg.sat.core.domain.entities.classes.ar.Constraint;
import ch.unisg.sat.core.port.out.ConstraintPort;
import ch.unisg.sat.infrastructure.repository.sdn.api.ConstraintCypherPort;
import ch.unisg.sat.infrastructure.repository.sdn.node.ConstraintNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ConstraintRepository implements ConstraintPort {

    private final Neo4jClient client;
    private final ConstraintCypherPort repository;

    @Override
    public void create(Constraint constraint) {
        repository.save(ConstraintNode.create(constraint.getId(), constraint.getTitle()));
    }

    @Override
    public Constraint readById(Constraint constraint) {

        Optional<ConstraintNode> entity = repository.findById(constraint.getId());

        entity.ifPresent(element -> constraint.setTitle(element.getTitle()));

        return constraint;
    }

    @Override
    public void readAll(List<Constraint> constraintList) {

        List<ConstraintNode> constraintNodeList = repository.findAll();

        for (ConstraintNode constraintNode : constraintNodeList) {
            constraintList.add(Constraint.create(constraintNode.getId(), constraintNode.getTitle()));
        }
    }
}
