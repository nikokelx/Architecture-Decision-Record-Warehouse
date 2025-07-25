package ch.unisg.sat.controller.http;

import ch.unisg.sat.controller.http.dto.request.entity.concept.ArchitectureRationaleRequestDto;
import ch.unisg.sat.controller.http.dto.response.ad.ArchitectureRationaleResponseDto;
import ch.unisg.sat.core.domain.entities.classes.ad.ArchitectureRationale;
import ch.unisg.sat.core.port.in.ArchitectureRationaleUseCase;
import ch.unisg.sat.core.port.in.command.classes.ArchitectureRationaleCommand;
import ch.unisg.sat.core.port.in.query.classes.ArchitectureRationaleQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ArchitectureRationaleController {

    private final ArchitectureRationaleUseCase architectureRationaleUseCase;

    @PostMapping(path = "/architectural-rationales", consumes = ArchitectureRationaleRequestDto.MEDIA_TYPE)
    public ResponseEntity<String> createArchitectureRational(
            @RequestBody ArchitectureRationaleRequestDto payload
    ) {

        ArchitectureRationaleCommand command = ArchitectureRationaleCommand.create(
                payload.getId(),
                payload.getTitle(),
                payload.getContext(),
                payload.getDecision(),
                payload.getStatus(),
                payload.getConsequences()
        );

        architectureRationaleUseCase.create(command);

        return ResponseEntity.created(URI.create(ArchitectureRationaleResponseDto.uri(command.id()))).build();
    }

    @GetMapping(path = "/architectural-rationals/{architectureRationaleId}")
    public ResponseEntity<HashMap<String, Object>> findArchitectureRationaleById(
            @PathVariable UUID architectureRationaleId
    ) {

        ArchitectureRationaleQuery query = new ArchitectureRationaleQuery(architectureRationaleId);

        ArchitectureRationale architectureRationale = architectureRationaleUseCase.findById(query);

        return ResponseEntity.ok(ArchitectureRationaleResponseDto.create(architectureRationale));
    }

}
