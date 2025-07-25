package ch.unisg.sat.controller.http;

import ch.unisg.sat.controller.http.dto.request.entity.concept.ConstraintRequestDto;
import ch.unisg.sat.controller.http.dto.response.ar.IntentionResponseDto;
import ch.unisg.sat.core.domain.entities.classes.ar.Intention;
import ch.unisg.sat.core.port.in.IntentionUseCase;
import ch.unisg.sat.core.port.in.command.classes.IntentionCommand;
import ch.unisg.sat.core.port.in.query.classes.IntentionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class IntentionController {

    private final IntentionUseCase intentionUseCase;

    @PostMapping(path = "/intentions", consumes = ConstraintRequestDto.IntentionRequestDto.MEDIA_TYPE)
    public ResponseEntity<String> createIntention(@RequestBody ConstraintRequestDto.IntentionRequestDto payload) {

        IntentionCommand command = IntentionCommand.create(payload.getId(), payload.getTitle());

        Intention intention = intentionUseCase.create(command);

        return ResponseEntity.created(URI.create("http://localhost:4000/intentions?id=" + intention.getId().toString())).build();
    }

    @GetMapping(path = "/intentions/{intentionId}")
    public ResponseEntity<HashMap<String, Object>> getIntentionById(
            @PathVariable UUID intentionId
    ) {

        IntentionQuery query = IntentionQuery.create(intentionId);

        Intention intention = intentionUseCase.findIntentionById(query);

        return ResponseEntity.ok(IntentionResponseDto.create(intention));
    }
}
