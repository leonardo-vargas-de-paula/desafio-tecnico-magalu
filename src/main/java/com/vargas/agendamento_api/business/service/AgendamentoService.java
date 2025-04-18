package com.vargas.agendamento_api.business.service;

import com.vargas.agendamento_api.business.mapper.IAgendamentoMapper;
import com.vargas.agendamento_api.controller.dto.in.AgendamentoDTOIn;
import com.vargas.agendamento_api.controller.dto.out.AgendamentoDTOOut;
import com.vargas.agendamento_api.infra.exception.NotFoundException;
import com.vargas.agendamento_api.infra.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.vargas.agendamento_api.infra.entity.Agendamento;

@RequiredArgsConstructor
@Service
public class AgendamentoService {
    private final AgendamentoRepository repository;
    private final IAgendamentoMapper agMapper;

    public AgendamentoDTOOut gravarAgendamento(AgendamentoDTOIn agendamento){
        return agMapper.paraOut(
                repository.save(agMapper.paraEntity(agendamento))
        );
    }

    public AgendamentoDTOOut buscarAgendamentoPorId(Long id){
        return agMapper.paraOut(
                repository.findById(id).orElseThrow(()-> new NotFoundException("Id não encontrado."))
        );
    }

    public void cancelarAgendamento(Long id){
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(()-> new NotFoundException("Id não encontrado."));
        repository.save(agMapper.paraEntityCancelamento(agendamento));
    }

}
