package ch.unisg.sat.core.port.in.query.classes;

import lombok.NonNull;

import java.util.UUID;

public record SystemElementQuery(
        @NonNull UUID id,
        @NonNull String title
        ) {

    public SystemElementQuery(
            UUID id,
            String title
    ) {
        this.id = id;
        this.title = title;
    }

    public SystemElementQuery(
            UUID id
    ) {
        this(id, "");
    }
}
