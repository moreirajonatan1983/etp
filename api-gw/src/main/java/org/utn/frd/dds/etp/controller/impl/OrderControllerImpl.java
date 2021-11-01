package org.utn.frd.dds.etp.controller.impl;

import com.etp.crud.controller.impl.CrudControllerImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.utn.frd.dds.etp.controller.OrderController;
import org.utn.frd.dds.etp.controller.ProductController;
import org.utn.frd.dds.etp.entity.Order;
import org.utn.frd.dds.etp.entity.Product;
import org.utn.frd.dds.etp.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("orders")
public class OrderControllerImpl extends CrudControllerImpl<Order, String> implements OrderController {

	private static final Log log = LogFactory.getLog(OrderController.class);

    @Autowired
	OrderServiceImpl orderService;

	@RequestMapping("/hello")
	@ResponseBody
	String home() {

		log.info("Hello Orders!!");

		return "Hello Orders!!";
	}

	@RequestMapping(value="/create/{orderUUID}", method= RequestMethod.POST)
	// @ApiOperation(value = "Crear una orden", notes = "Crear una nueva Orden")
	public ResponseEntity<Order> create(@RequestBody Order order){

		return ResponseEntity.ok(super.service.save(order));
	}

	@RequestMapping(value="/delete/{orderUUID}",method = RequestMethod.DELETE)
	// @ApiOperation(value = "Eliminar una orden", notes = "Eliminar una Orden")
	public ResponseEntity<HttpStatus> delete(String uuid){

		super.service.deleteById(uuid);

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestMapping(value="/qr/{orderUUID}",method = RequestMethod.GET)
	// @ApiOperation(value = "Obtener codigo QR", notes = "Obtener codigo QR")
	public ResponseEntity<HttpStatus> getQR(String orderUUID){

		orderService.getQR(orderUUID);

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestMapping(value="/csv/{orderUUID}",method = RequestMethod.GET)
	// @ApiOperation(value = "Obtener CSV de la Orden", notes = "Obtener CSV de la Orden")
	public ResponseEntity<HttpStatus> getCSV(String orderUUID){

		orderService.getCSV(orderUUID);

		return ResponseEntity.ok(HttpStatus.OK);
	}

}
