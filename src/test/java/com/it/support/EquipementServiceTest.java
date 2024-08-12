package com.it.support;



import com.it.support.dto.EquipementDto;
import com.it.support.enums.EquipementStatus;
import com.it.support.exception.EquipementNotFoundException;
import com.it.support.exception.UserNotFoundException;
import com.it.support.mapper.EquipementMapper;
import com.it.support.model.Equipement;
import com.it.support.model.User;
import com.it.support.repository.EquipementRepository;
import com.it.support.repository.UserRepository;
import com.it.support.service.EquipementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipementServiceTest {

    @Mock
    private EquipementRepository equipementRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EquipementMapper equipementMapper;

    @InjectMocks
    private EquipementService equipementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Equipement> equipements = Arrays.asList(new Equipement(), new Equipement());
        when(equipementRepository.findAll()).thenReturn(equipements);

        List<Equipement> result = equipementService.findAll();

        assertEquals(equipements, result);
        verify(equipementRepository).findAll();
    }

    @Test
    void testFindOne() {
        Long id = 1L;
        Equipement equipement = new Equipement();
        when(equipementRepository.findById(id)).thenReturn(Optional.of(equipement));

        Equipement result = equipementService.findOne(id);

        assertEquals(equipement, result);
        verify(equipementRepository).findById(id);
    }

    @Test
    void testFindOneNotFound() {
        Long id = 1L;
        when(equipementRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EquipementNotFoundException.class, () -> equipementService.findOne(id));
        verify(equipementRepository).findById(id);
    }

    @Test
    void testSave() {
        EquipementDto inputDto = new EquipementDto();
        Equipement equipement = new Equipement();
        Equipement savedEquipement = new Equipement();
        EquipementDto outputDto = new EquipementDto();

        when(equipementMapper.toEntity(inputDto)).thenReturn(equipement);
        when(equipementRepository.save(equipement)).thenReturn(savedEquipement);
        when(equipementMapper.toDto(savedEquipement)).thenReturn(outputDto);

        EquipementDto result = equipementService.save(inputDto);

        assertEquals(outputDto, result);
        assertEquals(EquipementStatus.AVAILABLE, equipement.getStatus());
        verify(equipementMapper).toEntity(inputDto);
        verify(equipementRepository).save(equipement);
        verify(equipementMapper).toDto(savedEquipement);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        EquipementDto inputDto = new EquipementDto();
        Equipement existingEquipement = new Equipement();
        Equipement updatedEquipement = new Equipement();
        EquipementDto outputDto = new EquipementDto();

        when(equipementRepository.findById(id)).thenReturn(Optional.of(existingEquipement));
        when(equipementRepository.save(existingEquipement)).thenReturn(updatedEquipement);
        when(equipementMapper.toDto(updatedEquipement)).thenReturn(outputDto);

        EquipementDto result = equipementService.update(id, inputDto);

        assertEquals(outputDto, result);
        verify(equipementRepository).findById(id);
        verify(equipementMapper).partialUpdate(inputDto, existingEquipement);
        verify(equipementRepository).save(existingEquipement);
        verify(equipementMapper).toDto(updatedEquipement);
    }

    @Test
    void testUpdateNotFound() {
        Long id = 1L;
        EquipementDto inputDto = new EquipementDto();
        when(equipementRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EquipementNotFoundException.class, () -> equipementService.update(id, inputDto));
        verify(equipementRepository).findById(id);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        Equipement equipement = new Equipement();
        equipement.setStatus(EquipementStatus.INACTIVE);
        when(equipementRepository.findById(id)).thenReturn(Optional.of(equipement));

        equipementService.delete(id);

        verify(equipementRepository).findById(id);
        verify(equipementRepository).delete(equipement);
    }

    @Test
    void testDeleteNotInactive() {
        Long id = 1L;
        Equipement equipement = new Equipement();
        equipement.setStatus(EquipementStatus.ACTIVE);
        when(equipementRepository.findById(id)).thenReturn(Optional.of(equipement));

        assertThrows(IllegalStateException.class, () -> equipementService.delete(id));
        verify(equipementRepository).findById(id);
        verify(equipementRepository, never()).delete(any());
    }

    @Test
    void testAssigneEquipementToUser() {
        Long equipementId = 1L;
        Long userId = 1L;
        Equipement equipement = new Equipement();
        User user = new User();

        when(equipementRepository.findById(equipementId)).thenReturn(Optional.of(equipement));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(equipementRepository.save(equipement)).thenReturn(equipement);

        Equipement result = equipementService.assigneEquipementToUser(equipementId, userId);

        assertEquals(user, result.getUser());
        assertEquals(EquipementStatus.ACTIVE, result.getStatus());
        verify(equipementRepository).findById(equipementId);
        verify(userRepository).findById(userId);
        verify(equipementRepository).save(equipement);
    }

    @Test
    void testAssigneEquipementToUserEquipementNotFound() {
        Long equipementId = 1L;
        Long userId = 1L;
        when(equipementRepository.findById(equipementId)).thenReturn(Optional.empty());

        assertThrows(EquipementNotFoundException.class, () -> equipementService.assigneEquipementToUser(equipementId, userId));
        verify(equipementRepository).findById(equipementId);
        verify(userRepository, never()).findById(any());
    }

    @Test
    void testAssigneEquipementToUserUserNotFound() {
        Long equipementId = 1L;
        Long userId = 1L;
        Equipement equipement = new Equipement();
        when(equipementRepository.findById(equipementId)).thenReturn(Optional.of(equipement));
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> equipementService.assigneEquipementToUser(equipementId, userId));
        verify(equipementRepository).findById(equipementId);
        verify(userRepository).findById(userId);
    }
}