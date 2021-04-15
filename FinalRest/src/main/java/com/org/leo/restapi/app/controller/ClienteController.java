package com.org.leo.restapi.app.controller;

import com.org.leo.restapi.app.model.entity.Cliente;
import com.org.leo.restapi.app.model.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//Se conecta directamente con angular
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/rest")
public class ClienteController {

    @Autowired
    ClienteServiceImpl clienteService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/clientes")
    public List<Cliente> showClients(){
        return clienteService.listar();
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> saveClient(@RequestBody Cliente cliente){

        Map<String,Object> response = new HashMap<>();
        Cliente clienteNew = null;
        try {
            clienteNew = clienteService.save(cliente);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al guardar el cliente ".concat(e.getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ".concat(clienteNew.getNombre()).concat(" ha sido guardado"));
        response.put("cliente", clienteNew);

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> saveClient(@RequestBody Cliente cliente, @PathVariable Long id){

        Map<String,Object> response = new HashMap<>();
        Cliente clienteUpd = clienteService.findId(id);

        if(clienteUpd == null) {
            response.put("mensaje", "El cliente con el id: "+ id.toString().concat(" no se encuentra en la base de datos"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        clienteUpd.setNombre(cliente.getNombre());
        clienteUpd.setApellido(cliente.getApellido());
        clienteUpd.setEmail(cliente.getEmail());
        clienteService.save(clienteUpd);

        response.put("mensaje", "El cliente ".concat(clienteUpd.getNombre()).concat(" ha sido actualizado"));
        response.put("cliente", clienteUpd);

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> findClient(@PathVariable Long id){

        Map<String,Object> response = new HashMap<>();
        Cliente clienteFind = clienteService.findId(id);

        if(clienteFind == null){
            response.put("mensaje", "El cliente no fue encontrado");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Cliente>(clienteFind,HttpStatus.OK);

    }

    @DeleteMapping("clientes/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id){

        Map<String,Object> response = new HashMap<>();
        try {
            clienteService.delete(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al tratar de eliminar el cliente con id "+id);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Cliente borrado exitosamente, id "+id);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }




}
