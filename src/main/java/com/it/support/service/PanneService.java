package com.it.support.service;

import com.it.support.dto.PanneDto;
import com.it.support.exception.PanneNotFoundException;
import com.it.support.mapper.PanneMapper;
import com.it.support.model.Panne;
import com.it.support.repository.PanneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PanneService {

    private final PanneMapper panneMapper;
    private final PanneRepository panneRepository;

    /**
     * Saves a new panne (failure) into the repository.
     *
     * @param panneDto The PanneDto object representing the panne to be saved.
     * @return The saved PanneDto object.
     */
    public PanneDto save(PanneDto panneDto) {
        Panne panne = panneMapper.toEntity(panneDto);
        Panne savedPanne = panneRepository.save(panne);
        return panneMapper.toDto(savedPanne);
    }

    /**
     * Retrieves all pannes from the repository.
     *
     * @return A list of PanneDto objects representing all pannes.
     */
    public List<Panne> findAll() {

        return panneRepository.findAll();
    }

    /**
     * Deletes a panne by its ID.
     *
     * @param id The ID of the panne to be deleted.
     */
    public void delete(Long id) {
        panneRepository.deleteById(id);
    }

    /**
     * Updates an existing panne with the given ID using data from the provided PanneDto.
     *
     * @param id       The ID of the panne to be updated.
     * @param panneDto The PanneDto object containing updated data for the panne.
     * @return The updated PanneDto object.
     * @throws PanneNotFoundException if the panne with the specified ID is not found.
     */
    public PanneDto updatePanne(Long id, PanneDto panneDto) {
        Panne panneUpdated = panneRepository.findById(id)
                .orElseThrow(() -> new PanneNotFoundException("Panne not found"));
        panneMapper.partialUpdate(panneDto, panneUpdated);
        Panne savedPanne = panneRepository.save(panneUpdated);
        return panneMapper.toDto(savedPanne);
    }

    public PanneDto getPanneById(Long id) {
        Panne panne = panneRepository.findById(id).orElseThrow(() -> new PanneNotFoundException("Panne not found"));
        return panneMapper.toDto(panne);
    }
}