package com.it.support;

import com.it.support.dto.PanneDto;
import com.it.support.exception.PanneNotFoundException;
import com.it.support.mapper.PanneMapper;
import com.it.support.model.Panne;
import com.it.support.repository.PanneRepository;
import com.it.support.service.PanneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PanneServiceTest {

    @Mock
    private PanneMapper panneMapper;

    @Mock
    private PanneRepository panneRepository;

    @InjectMocks
    private PanneService panneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        PanneDto inputDto = new PanneDto();
        Panne panne = new Panne();
        Panne savedPanne = new Panne();
        PanneDto outputDto = new PanneDto();

        when(panneMapper.toEntity(inputDto)).thenReturn(panne);
        when(panneRepository.save(panne)).thenReturn(savedPanne);
        when(panneMapper.toDto(savedPanne)).thenReturn(outputDto);

        PanneDto result = panneService.save(inputDto);

        assertEquals(outputDto, result);
        verify(panneMapper).toEntity(inputDto);
        verify(panneRepository).save(panne);
        verify(panneMapper).toDto(savedPanne);
    }

    @Test
    void testFindAll() {
        List<Panne> pannes = Arrays.asList(new Panne(), new Panne());
        when(panneRepository.findAll()).thenReturn(pannes);

        List<Panne> result = panneService.findAll();

        assertEquals(pannes, result);
        verify(panneRepository).findAll();
    }

    @Test
    void testDelete() {
        Long id = 1L;
        panneService.delete(id);
        verify(panneRepository).deleteById(id);
    }

    @Test
    void testUpdatePanne() {
        Long id = 1L;
        PanneDto inputDto = new PanneDto();
        Panne existingPanne = new Panne();
        Panne updatedPanne = new Panne();
        PanneDto outputDto = new PanneDto();

        when(panneRepository.findById(id)).thenReturn(Optional.of(existingPanne));
        when(panneRepository.save(existingPanne)).thenReturn(updatedPanne);
        when(panneMapper.toDto(updatedPanne)).thenReturn(outputDto);

        PanneDto result = panneService.updatePanne(id, inputDto);

        assertEquals(outputDto, result);
        verify(panneRepository).findById(id);
        verify(panneMapper).partialUpdate(inputDto, existingPanne);
        verify(panneRepository).save(existingPanne);
        verify(panneMapper).toDto(updatedPanne);
    }

    @Test
    void testUpdatePanneNotFound() {
        Long id = 1L;
        PanneDto inputDto = new PanneDto();

        when(panneRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PanneNotFoundException.class, () -> panneService.updatePanne(id, inputDto));
        verify(panneRepository).findById(id);
    }

    @Test
    void testGetPanneById() {
        Long id = 1L;
        Panne panne = new Panne();
        PanneDto outputDto = new PanneDto();

        when(panneRepository.findById(id)).thenReturn(Optional.of(panne));
        when(panneMapper.toDto(panne)).thenReturn(outputDto);

        PanneDto result = panneService.getPanneById(id);

        assertEquals(outputDto, result);
        verify(panneRepository).findById(id);
        verify(panneMapper).toDto(panne);
    }

    @Test
    void testGetPanneByIdNotFound() {
        Long id = 1L;

        when(panneRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PanneNotFoundException.class, () -> panneService.getPanneById(id));
        verify(panneRepository).findById(id);
    }
}