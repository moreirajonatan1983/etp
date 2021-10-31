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
import org.utn.frd.dds.etp.dto.RequestOrderDTO;
import org.utn.frd.dds.etp.dto.ResponseOrderDTO;
import org.utn.frd.dds.etp.entity.Order;
import org.utn.frd.dds.etp.service.OrderService;

@RestController
public class OrderController {

	private static final Log log = LogFactory.getLog(OrderController.class);
	
    @Autowired
	OrderService orderService;
	
	@RequestMapping("/hello")
	@ResponseBody
	String home() {
		
		log.info("Hello Orders!!");
		
		return "Hello Orders!!";
	}	
	
    @GetMapping("/order/find/{id}")
    private ResponseEntity<Object> getOrder(@PathVariable("uuid") String uuid) {

    	log.info("Consultando la order con uuid: " + uuid);

    	Order order = null;
    	
    	try {
    		order = orderService.getOrderById(uuid);
    		
    		if(order != null)
    			return ResponseEntity.status(HttpStatus.OK).body(order);
    		
    	} catch (Exception e) {
    		    		
    		log.error("Error al consultar la orden con uuid: " + uuid, e);
    	}

		Map<String, String> errors = new HashMap<String, String>();
		errors.put("error", "Orden no encontrada");

    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    	
    }

    @DeleteMapping("/order/delete/{id}")
    private ResponseEntity<Object> delete(@PathVariable("uuid") String uuid) {
        
    	log.info("Eliminando la orden con uuid: " + uuid);
    	    	
    	try {    		
    		orderService.delete(uuid);
    	} catch (Exception e) {
    		
    		log.error("Error al intentar eliminar la order. uuid: " + uuid , e);
    		    	        	
        	JsonObject errors = new JsonObject();
	    	
        	errors.addProperty("error", "Producto no encontrado");    		
    		
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors.toString());
    		    		
    	}
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 
     * @param requestOrderDTO
     * @param bindingResult
     * @return
     */
    @PostMapping("/order/save")
    @ResponseStatus(code = HttpStatus.CREATED)
    private ResponseEntity<String> save(@Valid @RequestBody RequestOrderDTO requestOrderDTO, BindingResult bindingResult) {
    	
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
    	
    	log.info("Creando la Orden: " + requestOrderDTO.toString());
    	
    	Map<String, String> errors = new HashMap<String, String>();
    	errors.put("error", "Error al crear un Pedido");    	
    	
    	ResponseOrderDTO responseOrderDTO = null;
        
    	String json = "";
    			
        try {

			responseOrderDTO = orderService.saveOrUpdate(requestOrderDTO);
        	
        	json = new Gson().toJson(responseOrderDTO);
        	
        	if(responseOrderDTO != null)
        		return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());
        	
        } catch(Exception e) {
        	
        	log.error("Error al crear la orden: " + requestOrderDTO, e);
        	
        	JsonObject jsonObject = new JsonObject();
        	
        	jsonObject.addProperty("errror", "Error al crear la orden: " + requestOrderDTO);
        	
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject.toString());
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(json.toString());        
        
    }	
	   
    /**
     * Listar Ordenes por fecha
	 * 	Method: GET
	 * Path: /orders?fecha=2020-05-26
	 * Response 200
     * 
     * @param date
     * @return listado de ordenes. Array Json
     */
    @GetMapping("/orders")
    private ResponseEntity<String> getOrdersByDate( @RequestParam("date") String date) {
    	
    	log.info("Buscando ordenes por fecha = " + date);
    	
    	LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	
    	List<ResponseOrderDTO> listResponseOrderDTO = orderService.getOrdersByDate(localDate);
    	    	
    	String json = new Gson().toJson(listResponseOrderDTO);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(json);
    	
    }
	
}
