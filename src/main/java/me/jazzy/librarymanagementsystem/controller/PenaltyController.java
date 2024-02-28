package me.jazzy.librarymanagementsystem.controller;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.dto.PenaltyDTO;
import me.jazzy.librarymanagementsystem.model.Penalty;
import me.jazzy.librarymanagementsystem.model.ResponseModel;
import me.jazzy.librarymanagementsystem.service.PenaltyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/penalties")
@AllArgsConstructor
public class PenaltyController {

    private final PenaltyService penaltyService;

    @PostMapping
    public ResponseEntity<ResponseModel> newPenalty(@RequestBody PenaltyDTO penaltyDTO) {
        return new ResponseEntity<>(penaltyService.newPenalty(penaltyDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Penalty> getPenaltyById(@PathVariable Long id) {
        return new ResponseEntity<>(penaltyService.getPenaltyById(id), HttpStatus.OK);
    }

}
