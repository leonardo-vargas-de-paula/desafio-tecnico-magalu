package com.vargas.agendamento_api.infra.entity;

import com.vargas.agendamento_api.infra.enums.StatusNotificacaoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="agendamento")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String emailDestinatario;
    private String telefoneDestinatario;
    private String mensagem;
    private LocalDateTime dataHoraEnvio;
    private LocalDateTime dataHoraAgendamento;
    private LocalDateTime dataHoraModificacao;
    @Enumerated(EnumType.STRING)
    private StatusNotificacaoEnum statusNotificacao;


    @PrePersist
    public void prePersist(){
        dataHoraAgendamento = LocalDateTime.now();
        statusNotificacao = StatusNotificacaoEnum.AGENDADO;

    }

}
