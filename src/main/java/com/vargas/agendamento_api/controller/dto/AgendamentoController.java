package com.vargas.agendamento_api.controller.dto;

import com.vargas.agendamento_api.business.service.AgendamentoService;
import com.vargas.agendamento_api.controller.dto.in.AgendamentoDTOIn;
import com.vargas.agendamento_api.controller.dto.out.AgendamentoDTOOut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agendamento")
public class AgendamentoController {
    private final AgendamentoService agService;

    @PostMapping
    public ResponseEntity<AgendamentoDTOOut> gravarAgendamentos(@RequestBody AgendamentoDTOIn agDTO){
        return ResponseEntity.ok(agService.gravarAgendamento(agDTO));
    }
}
