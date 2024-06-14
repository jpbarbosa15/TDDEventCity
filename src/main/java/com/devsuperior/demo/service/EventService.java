package com.devsuperior.demo.service;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;

    public EventDTO update(EventDTO dto, Long id){
        try {
            Event entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new EventDTO(entity);
        }catch (Exception e){
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }


    private void copyDtoToEntity(EventDTO dto, Event entity){
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        entity.getCity().setId(dto.getCityId());
    }
}
