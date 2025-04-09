package com.vargas.agendamento_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vargas.agendamento_api.business.service.AgendamentoService;
import com.vargas.agendamento_api.controller.dto.AgendamentoController;
import com.vargas.agendamento_api.controller.dto.in.AgendamentoDTOIn;
import com.vargas.agendamento_api.controller.dto.out.AgendamentoDTOOut;
import com.vargas.agendamento_api.infra.enums.StatusNotificacaoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class AgendamentoControllerTest {

    @InjectMocks
    AgendamentoController agController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    AgendamentoService agService;

    private AgendamentoDTOIn dtoIn;
    private AgendamentoDTOOut dtoOut;


    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(agController).build();

        objectMapper.registerModule(new JavaTimeModule());

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
    void deveCriarAgendamentoComSucesso() throws Exception {
        when(agService.gravarAgendamento(dtoIn)).thenReturn(dtoOut);

        mockMvc.perform(post("/agendamento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(dtoIn)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.emailDestinatario").value("email@email.com"))
                .andExpect(jsonPath("$.telefoneDestinatario").value(dtoOut.telefoneDestinatario()))
                .andExpect(jsonPath("$.mensagem").value(dtoOut.mensagem()))
                .andExpect(jsonPath("$.dataHoraEnvio").value("02-01-2025 11:01:01"))
                .andExpect(jsonPath("$.statusNotificacao").value("AGENDADO"));

        verify(agService, times(1)).gravarAgendamento(dtoIn);

        ; ; ;
    }

}
