package com.vargas.agendamento_api.business;


import com.vargas.agendamento_api.business.mapper.IAgendamentoMapper;
import com.vargas.agendamento_api.business.service.AgendamentoService;
import com.vargas.agendamento_api.controller.dto.in.AgendamentoDTOIn;
import com.vargas.agendamento_api.controller.dto.out.AgendamentoDTOOut;
import com.vargas.agendamento_api.infra.entity.Agendamento;
import com.vargas.agendamento_api.infra.enums.StatusNotificacaoEnum;
import com.vargas.agendamento_api.infra.repository.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agService;

    @Mock
    private AgendamentoRepository agRepository;

    @Mock
    private IAgendamentoMapper agMapper;

    private AgendamentoDTOIn dtoIn;
    private AgendamentoDTOOut dtoOut;

    private Agendamento agEntity;

    @BeforeEach
    void setUp() {

        agEntity = new Agendamento(
                1L,
                "email@email.com",
                "99999999999",
                "mensagem mensagem",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1),
                LocalDateTime.now(),
                null,
                StatusNotificacaoEnum.AGENDADO);

        dtoIn = new AgendamentoDTOIn(
                "email@email.com",
                "99999999999",
                "mensagem mensagem",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1)
        );
        dtoOut = new AgendamentoDTOOut(
                1L,
                "email@email.com",
                "99999999999",
                "mensagem mensagem",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1),
                StatusNotificacaoEnum.AGENDADO
        );


        }
    @Test
    void deveGravarAgendamentoComSucesso(){
        when(agMapper.paraEntity(dtoIn)).thenReturn(agEntity);
        when(agRepository.save(agEntity)).thenReturn(agEntity);
        when(agMapper.paraOut(agEntity)).thenReturn(dtoOut);

        AgendamentoDTOOut out = agService.gravarAgendamento(dtoIn);

        verify(agMapper, times(1)).paraEntity(dtoIn);
        verify(agRepository, times(1)).save(agEntity);
        verify(agMapper, times(1)).paraOut(agEntity);

        assertThat(out).usingRecursiveComparison().isEqualTo(dtoOut);

    }


}
