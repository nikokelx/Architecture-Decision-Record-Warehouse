package ch.unisg.sat.infrastructure.repository;

import ch.unisg.sat.core.domain.entities.relationships.*;
import ch.unisg.sat.core.port.out.RelationshipManagerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RelationshipManagerRepository implements RelationshipManagerPort {

    private final Neo4jClient client;

    @Override
    public UUID create(SolvedBy solvedBy) {

        client.query("""
                    MATCH (a:Alternatives {id: $alternativeId})
                    MATCH (i:Issue {id: $issueId})
                    MERGE (i)-[:SOLVED_BY {id: $id}]->(a)
                """)
                .bind(solvedBy.getAlternative().getId().toString()).to("alternativeId")
                .bind(solvedBy.getIssue().getId().toString()).to("issueId")
                .bind(solvedBy.getId().toString()).to("id")
                .run();

        return solvedBy.getId();
    }

    @Override
    public UUID create(Raise raise) {
        client.query("""
                    MATCH (s:System {id: $systemId})
                    MATCH (i:Issue {id: $issueId})
                    MERGE (s)-[:RAISES {id: $id}]->(i)
                """)
                .bind(raise.getSystemClass().getId().toString()).to("systemId")
                .bind(raise.getIssue().getId().toString()).to("issueId")
                .bind(raise.getId().toString()).to("id")
                .run();

        return raise.getId();
    }

    @Override
    public UUID create(BelongsTo belongsTo) {

        client.query("""
                    MATCH (s:System {id: $systemId})
                    MATCH (se:SystemElement {id: $systemElementId})
                    MERGE (se)-[:BELONGS_TO {id: $id}]->(s)
                """)
                .bind(belongsTo.getSystemClass().getId().toString()).to("systemId")
                .bind(belongsTo.getSystemElement().getId().toString()).to("systemElementId")
                .bind(belongsTo.getId().toString()).to("id")
                .run();
        return belongsTo.getId();
    }

    @Override
    public UUID create(PartOf partOf) {
        client.query("""
                    MATCH (ps:System {id: $parentSystemClassId})
                    MATCH (cs:System {id: $childSystemClassId})
                    MERGE (cs)-[:PART_OF {id: $id}]->(ps)
                """)
                .bind(partOf.getParentSystemClass().getId().toString()).to("parentSystemClassId")
                .bind(partOf.getChildSystemClass().getId().toString()).to("childSystemClassId")
                .bind(partOf.getId().toString()).to("id")
                .run();
        return partOf.getId();
    }

    @Override
    public UUID create(ConstrainedBy constrainedBy) {
        client.query("""
                    MATCH (se:SystemElement {id: $systemElementId})
                    MATCH (c:Constraint {id: $constraintId})
                    MERGE (se)-[:CONSTRAINED_BY {id: $id}]->(c)
                """)
                .bind(constrainedBy.getSystemElement().getId().toString()).to("systemElementId")
                .bind(constrainedBy.getConstraint().getId().toString()).to("constraintId")
                .bind(constrainedBy.getId().toString()).to("id")
                .run();
        return constrainedBy.getId();
    }

    @Override
    public UUID create(Require require) {
        client.query("""
                    MATCH (s:System {id: $systemId})
                    MATCH (nfr:NonFunctionalRequirement {id: $nonFunctionalRequirementId})
                    MERGE (s)-[:REQUIRES {id: $id}]->(nfr)
                """)
                .bind(require.getSystemClass().getId().toString()).to("systemId")
                .bind(require.getNonFunctionalRequirement().getId().toString()).to("nonFunctionalRequirementId")
                .bind(require.getId().toString()).to("id")
                .run();
        return require.getId();
    }

    @Override
    public UUID create(ForcedBy forcedBy) {
        client.query("""
                    MATCH (a:Alternatives {id: $alternativeId})
                    MATCH (ar {id: $architectureRequirementId})
                    MERGE (a)-[:FORCED_BY {id: $id, value: $value}]->(ar)
                """)
                .bind(forcedBy.getAlternative().getId().toString()).to("alternativeId")
                .bind(forcedBy.getArchitectureRequirement().getId().toString()).to("architectureRequirementId")
                .bind(forcedBy.getId().toString()).to("id")
                .bind(forcedBy.getValue()).to("value")
                .run();
        return forcedBy.getId();
    }

    @Override
    public UUID create(JustifiedBy justifiedBy) {
        client.query("""
                    MATCH (a:Alternatives {id: $alternativeId})
                    MATCH (ar:ArchitectureRationale {id: $architectureRationalId})
                    MERGE (a)-[:JUSTIFIED_BY {id: $id}]->(ar)
                """)
                .bind(justifiedBy.getAlternative().getId().toString()).to("alternativeId")
                .bind(justifiedBy.getArchitectureRationale().getId().toString()).to("architectureRationalId")
                .bind(justifiedBy.getId().toString()).to("id")
                .run();
        return justifiedBy.getId();
    }

    @Override
    public UUID create(Has has) {
        client.query("""
                    MATCH (s:System {id: $systemId})
                    MATCH (ar:ArchitectureRationale {id: $architectureRationalId})
                    MERGE (s)-[:HAS {id: $id}]->(ar)
                """)
                .bind(has.getSystemClass().getId().toString()).to("systemId")
                .bind(has.getArchitectureRationale().getId().toString()).to("architectureRationalId")
                .bind(has.getId().toString()).to("id")
                .run();
        return has.getId();
    }
}
