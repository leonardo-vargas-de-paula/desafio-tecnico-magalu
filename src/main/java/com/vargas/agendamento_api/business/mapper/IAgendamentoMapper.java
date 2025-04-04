package com.vargas.agendamento_api.business.mapper;

import com.vargas.agendamento_api.controller.dto.out.AgendamentoDTOOut;
import com.vargas.agendamento_api.infra.entity.Agendamento;
import org.mapstruct.Mapper;
import com.vargas.agendamento_api.controller.dto.in.AgendamentoDTOIn;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel= SPRING)
public interface IAgendamentoMapper {

    Agendamento paraEntity(AgendamentoDTOIn agendamento);

    @Mapping(target = "statusNotificacao", source = "statusNotificacao")
    AgendamentoDTOOut paraOut(Agendamento agendamento);

    @Mapping(target = "dataHoraModificacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "statusNotificacao", expression = "java(StatusNotificacaoEnum.CANCELADO)")
    Agendamento paraEntityCancelamento(Agendamento agendamento);


}
