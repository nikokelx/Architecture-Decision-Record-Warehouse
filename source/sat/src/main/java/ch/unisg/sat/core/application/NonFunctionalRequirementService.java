package ch.unisg.sat.core.application;

import ch.unisg.sat.core.domain.entities.classes.ar.NonFunctionalRequirement;
import ch.unisg.sat.core.port.in.NonFunctionalRequirementUseCase;
import ch.unisg.sat.core.port.in.command.classes.NonFunctionalRequirementCommand;
import ch.unisg.sat.core.port.in.query.classes.NonFunctionalRequirementQuery;
import ch.unisg.sat.core.port.out.NonFunctionalRequirementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NonFunctionalRequirementService implements NonFunctionalRequirementUseCase {

    private final NonFunctionalRequirementPort nonFunctionalRequirementPort;

    @Override
    public void create(NonFunctionalRequirementCommand command) {
        NonFunctionalRequirement nonFunctionalRequirement = NonFunctionalRequirement.create(command.id(), command.title());
        nonFunctionalRequirementPort.create(nonFunctionalRequirement);
    }

    @Override
    public NonFunctionalRequirement findById(NonFunctionalRequirementQuery query) {
        NonFunctionalRequirement nonFunctionalRequirement = NonFunctionalRequirement.create(query.id());
        return nonFunctionalRequirementPort.findById(nonFunctionalRequirement);
    }
}
