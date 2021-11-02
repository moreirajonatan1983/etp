package org.utn.frd.dds.etp.controller.impl;

import com.etp.crud.controller.impl.CrudControllerImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.utn.frd.dds.etp.controller.ProductController;
import org.utn.frd.dds.etp.entity.Product;
import org.utn.frd.dds.etp.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("products")
public class ProductControllerImpl extends CrudControllerImpl<Product, String> implements ProductController {

	private static final Log log = LogFactory.getLog(ProductController.class);

	@RequestMapping("/hello")
	@ResponseBody
	String home() {

		log.info("Hello Products!!");

		return "Hello Products!!";
	}

	@RequestMapping(value="/create", method= RequestMethod.POST)
	@ApiOperation(value = "Crear un producto", notes = "Crear un producto")
	public ResponseEntity<Product> create(@RequestBody Product product){

		return ResponseEntity.ok(super.service.save(product));
	}

	@RequestMapping(value="/delete/{uuid}",method = RequestMethod.DELETE)
	@ApiOperation(value = "Eliminar un producto", notes = "Eliminar un producto")
	public ResponseEntity<HttpStatus> delete(@PathVariable String uuid){

		super.service.deleteById(uuid);

		return ResponseEntity.ok(HttpStatus.OK);
	}

}
