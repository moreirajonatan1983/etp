package org.utn.frd.dds.etp.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.utn.frd.dds.etp.dto.RequestProductDTO;
import org.utn.frd.dds.etp.dto.ResponseProductDTO;
import org.utn.frd.dds.etp.entity.Product;
import org.utn.frd.dds.etp.service.impl.ProductServiceImpl;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

	private static final Log log = LogFactory.getLog(ProductController.class);
	
    @Autowired
	ProductServiceImpl productService;
	
	@RequestMapping("/hello")
	@ResponseBody
	String home() {
		
		log.info("Hello Products!!");
		
		return "Hello Products!!";
	}	
	
    @GetMapping("/product/find/{id}")
    private ResponseEntity<Object> getProduct(@PathVariable("uuid") String uuid) {

    	log.info("Consultando la product con uuid: " + uuid);

    	Product product = null;
    	
    	try {
    		product = productService.getProductById(uuid);
    		
    		if(product != null)
    			return ResponseEntity.status(HttpStatus.OK).body(product);
    		
    	} catch (Exception e) {
    		    		
    		log.error("Error al consultar la orden con uuid: " + uuid, e);
    	}

		Map<String, String> errors = new HashMap<String, String>();
		errors.put("error", "Orden no encontrada");

    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    	
    }

    @DeleteMapping("/product/delete/{id}")
    private ResponseEntity<Object> delete(@PathVariable("uuid") String uuid) {
        
    	log.info("Eliminando la orden con uuid: " + uuid);
    	    	
    	try {    		
    		productService.delete(uuid);
    	} catch (Exception e) {
    		
    		log.error("Error al intentar eliminar la product. uuid: " + uuid , e);
    		    	        	
        	JsonObject errors = new JsonObject();
	    	
        	errors.addProperty("error", "Producto no encontrado");    		
    		
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors.toString());
    		    		
    	}
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 
     * @param requestProductDTO
     * @param bindingResult
     * @return
     */
    @PostMapping("/product/save")
    @ResponseStatus(code = HttpStatus.CREATED)
    private ResponseEntity<String> save(@Valid @RequestBody RequestProductDTO requestProductDTO, BindingResult bindingResult) {
    	
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
    	
    	log.info("Creando la Orden: " + requestProductDTO.toString());
    	
    	Map<String, String> errors = new HashMap<String, String>();
    	errors.put("error", "Error al crear un Pedido");    	
    	
    	ResponseProductDTO responseProductDTO = null;
        
    	String json = "";
    			
        try {

			responseProductDTO = productService.saveOrUpdate(requestProductDTO);
        	
        	json = new Gson().toJson(responseProductDTO);
        	
        	if(responseProductDTO != null)
        		return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());
        	
        } catch(Exception e) {
        	
        	log.error("Error al crear la orden: " + requestProductDTO, e);
        	
        	JsonObject jsonObject = new JsonObject();
        	
        	jsonObject.addProperty("errror", "Error al crear la orden: " + requestProductDTO);
        	
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject.toString());
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(json.toString());        
        
    }	
	   
    /**
     * Listar Ordenes por fecha
	 * 	Method: GET
	 * Path: /products?fecha=2020-05-26
	 * Response 200
     * 
     * @param date
     * @return listado de ordenes. Array Json
     */
    @GetMapping("/products")
    private ResponseEntity<String> getProductsByDate( @RequestParam("date") String date) {
    	
    	log.info("Buscando ordenes por fecha = " + date);
    	
    	LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	
    	List<ResponseProductDTO> listResponseProductDTO = productService.getProductsByDate(localDate);
    	    	
    	String json = new Gson().toJson(listResponseProductDTO);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(json);
    	
    }
	
}
