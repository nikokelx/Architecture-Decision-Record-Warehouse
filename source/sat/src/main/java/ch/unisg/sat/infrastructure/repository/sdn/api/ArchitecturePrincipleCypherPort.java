package ch.unisg.sat.infrastructure.repository.sdn.api;

import ch.unisg.sat.infrastructure.repository.sdn.node.ArchitecturePrincipleNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface ArchitecturePrincipleCypherPort extends Neo4jRepository<ArchitecturePrincipleNode, UUID> {
}
