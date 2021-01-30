package com.game.imona.service.impl;

import com.game.imona.model.dto.BaseDto;
import com.game.imona.model.response.CitiesResponse;
import com.game.imona.repository.CityRepository;
import com.game.imona.service.CityService;
import com.imona.core.entity.City;
import com.imona.core.generic.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImp implements CityService {

    private final CityRepository cityRepository;

    @Override
    public GenericResponse<CitiesResponse> getAllCities() {
        List<City> cities = cityRepository.findAllByEnabledTrue();
        if (CollectionUtils.isEmpty(cities)) {
            return GenericResponse.resourceNotFound();
        }
        List<BaseDto> responses = cities.stream()
                .map(player -> new BaseDto(
                        player.getId(),
                        player.getName()))
                .collect(Collectors.toList());
        return GenericResponse.ok(new CitiesResponse(responses));
    }
}
