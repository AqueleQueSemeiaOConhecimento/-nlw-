package com.planner.planner.participant;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantRepository repository;

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload) {
        Optional<Participant> participant = this.repository.findById(id);

        if(participant.isPresent()) {
            Participant rowParticipant = participant.get();
            rowParticipant.setIsConfirmed(true);
            rowParticipant.setName(payload.name());

            this.repository.save(rowParticipant);

            return ResponseEntity.ok(rowParticipant);
        }

        return ResponseEntity.notFound().build();
    }

}
