package ch.unisg.sat.infrastructure.repository.sdn.node;

import ch.unisg.sat.core.domain.entities.classes.ad.Alternative;
import ch.unisg.sat.infrastructure.repository.sdn.relationship.ForcesRelationship;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Node("Intention")
@Getter
@Setter
public class IntentionNode {

    @Id
    private UUID id;

    @Property("title")
    private String title;

    @Relationship(type = "FORCED_BY", direction = Relationship.Direction.INCOMING)
    public Set<ForcesRelationship> forcesRelationships = new HashSet<>();

    public IntentionNode(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public static IntentionNode create(UUID id, String title) {
        return new IntentionNode(id, title);
    }

}
