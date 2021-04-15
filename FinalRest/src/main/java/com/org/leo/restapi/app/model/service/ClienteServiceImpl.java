package com.org.leo.restapi.app.model.service;

import com.org.leo.restapi.app.model.clientedao.ClienteDao;
import com.org.leo.restapi.app.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> listar() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    public Cliente findId(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        clienteDao.deleteById(id);
    }
}
