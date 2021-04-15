package com.org.leo.restapi.app.model.service;

import ch.qos.logback.core.net.server.Client;
import com.org.leo.restapi.app.model.entity.Cliente;

import java.util.List;

public interface IClienteService {

    public List<Cliente> listar();
    public Cliente save(Cliente cliente);
    public Cliente findId(Long id);
    public void delete(Long id);

}
