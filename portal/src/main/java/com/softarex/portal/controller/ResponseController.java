package com.softarex.portal.controller;

import com.softarex.portal.dto.ResponseDto;
import com.softarex.portal.mapper.ResponseMapper;
import com.softarex.portal.model.Response;
import com.softarex.portal.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class ResponseController {
    private final ResponseMapper responseMapper;
    private final ResponseService responseService;
    private final SimpMessagingTemplate template;

    @PostMapping
    public ResponseDto save(@RequestBody ResponseDto responseDto) {
        Response response = responseMapper.mapToEntity(responseDto);
        Response savedResponse = responseService.save(response);
        ResponseDto savedResponseDto = responseMapper.mapToDto(savedResponse);
        template.convertAndSend("/topic/responses", savedResponseDto);
        return savedResponseDto;
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