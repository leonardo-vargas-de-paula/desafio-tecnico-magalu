package com.vargas.agendamento_api.infra.repository;

import com.vargas.agendamento_api.infra.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository  extends JpaRepository <Agendamento, Long>{
}
