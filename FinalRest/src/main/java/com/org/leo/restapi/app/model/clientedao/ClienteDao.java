package com.org.leo.restapi.app.model.clientedao;

import com.org.leo.restapi.app.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository <Cliente, Long> {
}
