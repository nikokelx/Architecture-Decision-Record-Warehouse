package ch.unisg.sat.infrastructure.repository.sdn.api;

import ch.unisg.sat.infrastructure.repository.sdn.node.SystemElementNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface SystemElementCypherPort extends Neo4jRepository<SystemElementNode, UUID> {
}
