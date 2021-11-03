package org.utn.frd.dds.etp.controller.impl;

import com.etp.crud.controller.impl.CrudControllerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.utn.frd.dds.etp.controller.OrderItemController;
import org.utn.frd.dds.etp.controller.ProductController;
import org.utn.frd.dds.etp.entity.OrderItem;
import org.utn.frd.dds.etp.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("order_items")
@Api(tags ="Order Items", description = "Manejo de los items de una orden.", position = 3)
public class OrderItemControllerImpl extends CrudControllerImpl<OrderItem, String> implements OrderItemController {

	private static final Log log = LogFactory.getLog(ProductController.class);

	@RequestMapping(value="/add", method= RequestMethod.POST)
	@ApiOperation(value = "Agregar un item a una orden", notes = "Agregar un item a una orden")
	public ResponseEntity<OrderItem> create(@RequestBody OrderItem orderItem){

		return ResponseEntity.ok(super.service.save(orderItem));
	}

	@RequestMapping(value="/delete/{uuid}",method = RequestMethod.DELETE)
	@ApiOperation(value = "Eliminar un item de una orden", notes = "Eliminar un item de orden")
	public ResponseEntity delete(@PathVariable String uuid){

		super.service.deleteById(uuid);

		return ResponseEntity.ok().build();
	}

	// @RequestMapping(value="/find/{uuid}", method= RequestMethod.POST)
	// @ApiOperation(value = "Buscar item de una orden por uuid", notes = "Buscar item de una orden por uuid.")
	public Optional<OrderItem> findById(@PathVariable String uuid){

		return super.service.findById(uuid);
	}

	@RequestMapping(value="/findAll/{uuidOrder}", method= RequestMethod.POST)
	@ApiOperation(value = "Buscar todos los items de una orden", notes = "Buscar todos los items de una orden.")
	public List<OrderItem> findAll(@PathVariable String uuidOrder){

		return super.service.findById(uuidOrder).stream().collect(Collectors.toList());
	}

}
