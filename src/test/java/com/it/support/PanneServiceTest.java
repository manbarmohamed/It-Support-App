//package com.it.support;
//
//
//
//import com.it.support.dto.PanneDto;
//import com.it.support.exception.PanneNotFoundException;
//import com.it.support.mapper.PanneMapper;
//import com.it.support.model.Panne;
//import com.it.support.repository.PanneRepository;
//import com.it.support.service.PanneService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@SpringJUnitConfig
//public class PanneServiceTest {
//
//    @Mock
//    private PanneRepository panneRepository;
//
//    @Mock
//    private PanneMapper panneMapper;
//
//    @InjectMocks
//    private PanneService panneService;
//
//    public PanneServiceTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testSave() {
//        PanneDto panneDto = new PanneDto();
//        Panne panne = new Panne();
//        Panne savedPanne = new Panne();
//
//        when(panneMapper.toEntity(panneDto)).thenReturn(panne);
//        when(panneRepository.save(panne)).thenReturn(savedPanne);
//        when(panneMapper.toDto(savedPanne)).thenReturn(panneDto);
//
//        PanneDto result = panneService.save(panneDto);
//
//        assertEquals(panneDto, result);
//        verify(panneMapper).toEntity(panneDto);
//        verify(panneRepository).save(panne);
//        verify(panneMapper).toDto(savedPanne);
//    }
//
//    @Test
//    public void testFindAll() {
//        Panne panne = new Panne();
//        PanneDto panneDto = new PanneDto();
//        List<Panne> pannes = List.of(panne);
//        List<PanneDto> panneDtos = List.of(panneDto);
//
//        when(panneRepository.findAll()).thenReturn(pannes);
//        when(panneMapper.toDto(panne)).thenReturn(panneDto);
//
//        List<PanneDto> result = panneService.findAll();
//
//        assertEquals(panneDtos, result);
//        verify(panneRepository).findAll();
//        verify(panneMapper).toDto(panne);
//    }
//
//    @Test
//    public void testDelete() {
//        Long id = 1L;
//
//        panneService.delete(id);
//
//        verify(panneRepository).deleteById(id);
//    }
//
//    @Test
//    public void testUpdatePanne() {
//        Long id = 1L;
//        PanneDto panneDto = new PanneDto();
//        Panne existingPanne = new Panne();
//        Panne updatedPanne = new Panne();
//
//        when(panneRepository.findById(id)).thenReturn(Optional.of(existingPanne));
//        when(panneMapper.partialUpdate(panneDto, existingPanne)).thenReturn(updatedPanne);
//        when(panneRepository.save(existingPanne)).thenReturn(updatedPanne);
//        when(panneMapper.toDto(updatedPanne)).thenReturn(panneDto);
//
//        PanneDto result = panneService.updatePanne(id, panneDto);
//
//        assertEquals(panneDto, result);
//        verify(panneRepository).findById(id);
//        verify(panneMapper).partialUpdate(panneDto, existingPanne);
//        verify(panneRepository).save(existingPanne);
//        verify(panneMapper).toDto(updatedPanne);
//    }
//
//    @Test
//    public void testUpdatePanneNotFound() {
//        Long id = 1L;
//        PanneDto panneDto = new PanneDto();
//
//        when(panneRepository.findById(id)).thenReturn(Optional.empty());
//
//        assertThrows(PanneNotFoundException.class, () -> panneService.updatePanne(id, panneDto));
//        verify(panneRepository).findById(id);
//    }
//}
//
