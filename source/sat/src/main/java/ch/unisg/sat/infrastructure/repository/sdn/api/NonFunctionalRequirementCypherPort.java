package ch.unisg.sat.infrastructure.repository.sdn.api;

import ch.unisg.sat.infrastructure.repository.sdn.node.NonFunctionalRequirementNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface NonFunctionalRequirementCypherPort extends Neo4jRepository<NonFunctionalRequirementNode, UUID> {
}
