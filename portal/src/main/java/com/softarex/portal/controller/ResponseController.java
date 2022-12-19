package com.softarex.portal.controller;

import com.softarex.portal.dto.ResponseDto;
import com.softarex.portal.mapper.ResponseMapper;
import com.softarex.portal.model.Response;
import com.softarex.portal.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class ResponseController {
    private final ResponseMapper responseMapper;
    private final ResponseService responseService;

    @PostMapping
    public ResponseDto save(@RequestBody ResponseDto responseDto) {
        Response response = responseMapper.mapToEntity(responseDto);
        Response savedResponse = responseService.save(response);
        return responseMapper.mapToDto(savedResponse);
    }

    @Secured("ROLE_USER")
    @PreAuthorize(value = "@securityServiceImpl.isUserOwnerOfQuestionnaire(authentication.principal,#id)")
    @GetMapping("/{id}")
    public List<ResponseDto> getResponses(@PathVariable Long id) {
        List<Response> responses = responseService.getResponses(id);
        return responses.stream()
                .map(responseMapper::mapToDto)
                .toList();
    }
}
