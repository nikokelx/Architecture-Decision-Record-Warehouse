package ch.unisg.sat.core.domain.aggregate;

import java.util.UUID;

public interface ArchitecturalRequirement {
    UUID getId();
    String getTitle();
    String getType();
}
