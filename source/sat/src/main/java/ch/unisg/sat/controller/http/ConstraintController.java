package ch.unisg.sat.controller.http;

import ch.unisg.sat.controller.http.dto.request.entity.concept.ConstraintRequestDto;
import ch.unisg.sat.controller.http.dto.response.ar.ConstraintResponseDto;
import ch.unisg.sat.core.domain.entities.classes.ar.Constraint;
import ch.unisg.sat.core.port.in.ConstraintUseCase;
import ch.unisg.sat.core.port.in.command.classes.ConstraintCommand;
import ch.unisg.sat.core.port.in.query.classes.ConstraintQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ConstraintController {

    private final ConstraintUseCase constraintUseCase;

    @PostMapping(path = "/constraints", consumes = ConstraintRequestDto.MEDIA_TYPE)
    public ResponseEntity<String> createConstraint(
            @RequestBody ConstraintRequestDto payload
    ) {
        ConstraintCommand command = ConstraintCommand.create(payload.getId(), payload.getTitle());

        constraintUseCase.create(command);

        return new ResponseEntity<>(command.id().toString(), HttpStatus.CREATED);
    }

    @GetMapping(path = "/constraints/{constraintId}")
    public ResponseEntity<HashMap<String, Object>> findConstraintById(
            @PathVariable UUID constraintId
    ) {
        ConstraintQuery query = ConstraintQuery.create(constraintId);

        Constraint constraint = constraintUseCase.findById(query);

        return ResponseEntity.ok(ConstraintResponseDto.create(constraint));
    }
}
