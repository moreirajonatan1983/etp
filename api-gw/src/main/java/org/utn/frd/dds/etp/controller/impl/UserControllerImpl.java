package org.utn.frd.dds.etp.controller.impl;

import com.etp.crud.controller.impl.CrudControllerImpl;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.utn.frd.dds.etp.controller.UserController;
import org.utn.frd.dds.etp.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserControllerImpl extends CrudControllerImpl<User, String> implements UserController {

	private static final Log log = LogFactory.getLog(UserController.class);

	@RequestMapping(value="/create", method= RequestMethod.POST)
	@ApiOperation(value = "Crear un usuario", notes = "Crear un usuario")
	public ResponseEntity<User> create(@RequestBody User product){

		return ResponseEntity.ok(super.service.save(product));
	}

	@RequestMapping(value="/delete/{uuid}",method = RequestMethod.DELETE)
	@ApiOperation(value = "Eliminar un usuario", notes = "Eliminar un usuario")
	public ResponseEntity<HttpStatus> delete(@PathVariable String uuid){

		super.service.deleteById(uuid);

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestMapping(value="/find/{uuid}", method= RequestMethod.POST)
	@ApiOperation(value = "Buscar usuario por Id", notes = "Buscar usuario por Id")
	public Optional<User> findById(@PathVariable String uuid){

		return super.service.findById(uuid);
	}

	//@RequestMapping(value="/findAll", method= RequestMethod.POST)
	//@ApiOperation(value = "Buscar todos los usuarios", notes = "Buscar todos los usuarios.")
	public List<User> findAll(){

		return Lists.newArrayList(super.service.findAll());
	}

}
