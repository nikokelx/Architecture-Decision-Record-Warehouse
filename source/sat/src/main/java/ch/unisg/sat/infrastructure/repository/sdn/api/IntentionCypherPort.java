package ch.unisg.sat.infrastructure.repository.sdn.api;

import ch.unisg.sat.infrastructure.repository.sdn.node.IntentionNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface IntentionCypherPort extends Neo4jRepository<IntentionNode, UUID> {
}
