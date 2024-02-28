package me.jazzy.librarymanagementsystem.service;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.dto.PenaltyDTO;
import me.jazzy.librarymanagementsystem.model.Penalty;
import me.jazzy.librarymanagementsystem.model.ResponseModel;
import me.jazzy.librarymanagementsystem.model.User;
import me.jazzy.librarymanagementsystem.repository.PenaltyRepository;
import me.jazzy.librarymanagementsystem.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PenaltyService {

    private final PenaltyRepository penaltyRepository;
    private final UserRepository userRepository;

    public ResponseModel newPenalty(PenaltyDTO penaltyDTO) {

        User user = userRepository.findById(penaltyDTO.getUserId())
                        .orElseThrow(() -> new IllegalStateException("There is no such user"));

        Penalty penalty = new Penalty(
                penaltyDTO.getReason(),
                penaltyDTO.getStartingDate(),
                penaltyDTO.getStartingDate().plusDays(15),
                user
        );

        penaltyRepository.save(penalty);

        return new ResponseModel(
                HttpStatus.OK.value(),
                "New penalty added.",
                LocalDateTime.now()
        );
    }

    public Penalty getPenaltyById(Long id) {
        return penaltyRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException("There is no such penalty"));
    }

}
