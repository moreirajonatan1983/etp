package org.utn.frd.dds.etp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
public class OrderController {

	private static final Log log = LogFactory.getLog(OrderController.class);
	
    @Autowired
    OrderService orderService;	
	
	@RequestMapping("/hello")
	@ResponseBody
	String home() {
		
		log.info("Hola Pedidos!!");
		
		return "Hola Pedidos!!";
	}	
	
    @GetMapping("/pedidos/{id}")    
    private ResponseEntity<Object> getPedido(@PathVariable("id") String id) {

    	log.info("Consultando el pedido con id: " + id);
    	
    	Map<String, String> errors = new HashMap<String, String>();
    	errors.put("error", "Pedido no encontrado");
    	
    	PedidoCabecera pedido = null;
    	
    	try {
    		pedido = pedidoService.getPedidoById(id);
    		
    		if(pedido != null)
    			return ResponseEntity.status(HttpStatus.OK).body(pedido);
    		
    	} catch (Exception e) {
    		    		
    		log.error("Error al consultar el pedido con id: " + id, e);
    	}    	
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    	
    }

    @DeleteMapping("/pedidos/{id}")
    private ResponseEntity<Object> deletePedidos(@PathVariable("id") String id) {
        
    	log.info("Eliminando el PedidoDetalle con id: " + id);
    	    	
    	try {    		
    		pedidoService.delete(id);
    	} catch (Exception e) {
    		
    		log.error("Error al intentar eliminar un pedido. id: " + id , e);
    		    	        	
        	JsonObject errors = new JsonObject();
	    	
        	errors.addProperty("error", "Producto no encontrado");    		
    		
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors.toString());
    		    		
    	}
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 
     * @param pedido
     * @param bindingResult
     * @return
     */
    @PostMapping("/pedidos")
    @ResponseStatus(code = HttpStatus.CREATED)
    private ResponseEntity<String>  savePedido(@Valid @RequestBody PedidoDTO pedido, BindingResult bindingResult) {
    	
		if(bindingResult.hasErrors()){
			//handle errors and return
	    	
        	JsonObject jsonObject = new JsonObject();
	    	    		
        	JsonArray errors = new JsonArray();
	    		    	
			for (Object object : bindingResult.getAllErrors()) {
			    
				if(object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;
			        
			        JsonObject err = new JsonObject();
			        
			        err.addProperty("error", fieldError.getDefaultMessage());
			        
			        errors.add(err);
			        
			    }

			}			
			
			jsonObject.add("Errores", errors);
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
		}
    	
    	log.info("Creando un Pedido: " + pedido.toString());
    	
    	Map<String, String> errors = new HashMap<String, String>();
    	errors.put("error", "Error al crear un Pedido");    	
    	
    	RespuestaPedidoDTO respuestaPedido = null;                
        
    	String json = "";
    			
        try {
        	
        	respuestaPedido = pedidoService.saveOrUpdate(pedido);
        	
        	json = new Gson().toJson(respuestaPedido);
        	
        	if(respuestaPedido != null)
        		return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());
        	
        } catch(Exception e) {
        	
        	log.error("Error al crear el pedido: " + pedido, e);
        	
        	JsonObject jsonObject = new JsonObject();
        	
        	jsonObject.addProperty("errror", "Error al crear el pedido: " + pedido);
        	
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject.toString());
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(json.toString());        
        
    }	
	   
    /**
     * Listar pedidos por fecha
	 * 	Method: GET
	 * Path: /pedidos?fecha=2020-05-26
	 * Response 200
     * 
     * @param fecha
     * @return listado de pedidos. Array Json 
     */
    @GetMapping("/pedidos")
    private ResponseEntity<String> getPedidosByFecha( @RequestParam("fecha") String fecha) {
    	
    	log.info("Buscando pedidos por fecha = " + fecha);
    	
    	LocalDate localDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	
    	List<RespuestaPedidoDTO> listaRespuestaPedidoDTO = pedidoService.getPedidosByFecha(localDate);
    	    	
    	String json = new Gson().toJson(listaRespuestaPedidoDTO);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(json);
    	
    }
	
}
