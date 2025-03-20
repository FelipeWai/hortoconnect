package com.horto.backend.infra.presentation;

import com.horto.backend.core.entities.Quality;
import com.horto.backend.core.entities.Size;
import com.horto.backend.core.usecases.quality.delete.DeleteQualityByIdCase;
import com.horto.backend.core.usecases.quality.get.GetAllQualitiesCase;
import com.horto.backend.core.usecases.quality.get.GetQualityByIdCase;
import com.horto.backend.core.usecases.quality.post.CreateQualityCase;
import com.horto.backend.infra.dto.quality.request.QualityRequestDTO;
import com.horto.backend.infra.dto.quality.response.QualityResponseDTO;
import com.horto.backend.infra.mapper.QualityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quality")
@RequiredArgsConstructor
public class QualityController {

    private final DeleteQualityByIdCase deleteQualityByIdCase;

    private final CreateQualityCase createQualityCase;

    private final GetAllQualitiesCase getAllQualitiesCase;
    private final GetQualityByIdCase getQualityByIdCase;

    private final QualityMapper qualityMapper;


    @GetMapping
    public ResponseEntity<List<QualityResponseDTO>> getAllQualities() {
        List<Quality> qualities = getAllQualitiesCase.execute();
        return ResponseEntity.ok(qualities.stream()
                .map(qualityMapper::toResponseDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<QualityResponseDTO> getQualityById(@PathVariable Long id) {
        Quality quality = getQualityByIdCase.execute(id);
        return ResponseEntity.ok(qualityMapper.toResponseDTO(quality));
    }

    @PostMapping
    public ResponseEntity<QualityResponseDTO> createQuality(@RequestBody QualityRequestDTO qualityRequestDTO) {
        Quality quality = createQualityCase.createQuality(qualityRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(qualityMapper.toResponseDTO(quality));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualityById(@PathVariable Long id) {
        deleteQualityByIdCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
