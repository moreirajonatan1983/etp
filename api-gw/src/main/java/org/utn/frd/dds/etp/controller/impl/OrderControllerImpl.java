package org.utn.frd.dds.etp.controller.impl;

import com.etp.crud.controller.impl.CrudControllerImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.utn.frd.dds.etp.controller.OrderController;
import org.utn.frd.dds.etp.entity.Order;
import org.utn.frd.dds.etp.service.impl.OrderServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@RequestMapping(value="/create", method= RequestMethod.POST)
	@ApiOperation(value = "Crear una orden", notes = "Crear una nueva Orden")
	public ResponseEntity<Order> create(@RequestBody Order order){

		return ResponseEntity.ok(super.service.save(order));
	}

	@RequestMapping(value="/delete/{uuid}",method = RequestMethod.DELETE)
	@ApiOperation(value = "Eliminar una orden", notes = "Eliminar una Orden")
	public ResponseEntity<HttpStatus> delete(@PathVariable String uuid){

		super.service.deleteById(uuid);

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestMapping(value="/find/{uuid}", method= RequestMethod.POST)
	@ApiOperation(value = "Buscar Orden por Id", notes = "Buscar Orden por Id")
	public List<Order> findById(@PathVariable String uuid){

		return super.service.findById(uuid).stream().collect(Collectors.toList());
	}

	@RequestMapping(value="/findAll/{userUUID}", method= RequestMethod.POST)
	@ApiOperation(value = "Buscar Orden de un usuario", notes = "Buscar Orden de un usuario")
	public List<Order> findAll(@PathVariable String uuid){

		return super.service.findById(uuid).stream().collect(Collectors.toList());
	}

	@RequestMapping(value="/qr/{uuid}",method = RequestMethod.GET)
	@ApiOperation(value = "Obtener codigo QR", notes = "Obtener codigo QR")
	public String getQR(@PathVariable String uuid){

		return orderService.getQR(uuid);
	}

	@RequestMapping(value="/csv/{uuid}",method = RequestMethod.GET)
	@ApiOperation(value = "Obtener CSV de la Orden", notes = "Obtener CSV de la Orden")
	public ResponseEntity<HttpStatus> getCSV(@PathVariable String uuid){

		orderService.getCSV(uuid);

		return ResponseEntity.ok(HttpStatus.OK);
	}

}
