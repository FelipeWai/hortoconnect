package com.horto.backend.infra.presentation;

import com.horto.backend.core.entities.Size;
import com.horto.backend.core.usecases.size.get.GetAllSizesCase;
import com.horto.backend.core.usecases.size.get.GetSizeByIdCase;
import com.horto.backend.core.usecases.size.post.CreateSizeCase;
import com.horto.backend.infra.dto.size.request.SizeRequestDTO;
import com.horto.backend.infra.dto.size.response.SizeResponseDTO;
import com.horto.backend.infra.mapper.SizeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/size")
@RequiredArgsConstructor
public class SizeController {

    private final CreateSizeCase createSizeCase;

    private final GetAllSizesCase getAllSizesCase;
    private final GetSizeByIdCase getSizeByIdCase;

    private final SizeMapper sizeMapper;

    @GetMapping
    public ResponseEntity<List<SizeResponseDTO>> getAllSizes() {
        List<Size> sizeList = getAllSizesCase.execute();
        return ResponseEntity.ok(sizeList.stream()
                .map(sizeMapper::toResponseDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SizeResponseDTO> getSizeById(@PathVariable Long id) {
        Size size = getSizeByIdCase.execute(id);
        return ResponseEntity.ok(sizeMapper.toResponseDTO(size));
    }


    @PostMapping
    public ResponseEntity<SizeResponseDTO> createSize(@RequestBody @Valid SizeRequestDTO requestDTO) {
        Size size = createSizeCase.execute(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(sizeMapper.toResponseDTO(size));
    }

}
