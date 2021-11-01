package org.utn.frd.dds.etp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utn.frd.dds.etp.dto.RequestOrderDTO;
import org.utn.frd.dds.etp.dto.ResponseOrderDTO;
import org.utn.frd.dds.etp.entity.Order;
import org.utn.frd.dds.etp.repository.OrderRepository;
import org.utn.frd.dds.etp.util.OrderUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author jonatan.moreira
 *
 */
@Service
public class OrderService {

    private static final Log log = LogFactory.getLog(OrderService.class);

    @Autowired
    OrderRepository orderRepository;

//    @Autowired
//    OrderRepository pedidoCabeceraRepository;

    public List<Order> getAll() {

        List<Order> orders = new ArrayList<Order>();
        //  orderRepository.findAll().forEach(order -> orderRepository.add(order));

        return orders;
    }

    public Order getOrderById(String id) {

        return orderRepository.findById(id).get();
    }

    /**
     * Busca las ordenes por fecha, las retorna como una lista de ResponseOrderDTO.
     *
     * @param date
     * @return
     */
    public List<ResponseOrderDTO> getOrdersByDate(LocalDate date) {

        log.info("OrderService.getOrdersByFecha Init: " + date);

        List<Order> orders = new ArrayList<Order>();
        orderRepository.findByDate(date).forEach(order -> orders.add((Order) order));

        return OrderUtil.getListResponseOrderDTO(orders);

    }

    /**
     * Persiste el pedido recibido.
     *
     * @param requestOrderDTO
     * @return
     */
    public ResponseOrderDTO saveOrUpdate(RequestOrderDTO requestOrderDTO) {

        log.info("OrderService.saveOrUpdate Init: " + requestOrderDTO.toString());

        Order order = new Order();

//        order.setLocal(requestOrderDTO.getLocal());
//        order.setUser(requestOrderDTO.getUser());
//        order.setLocalDateTime(LocalDateTime.now());

        Double montoTotal = 0.0;

        int countProductos = 0;

//        List<OrderItems> orderItems = new ArrayList<OrderItems>();
//
//        for(RequestOrderDTO p: pedido.getDetalle() ) {
//
//            Optional<Producto> p1 = productoRepository.findById(p.getProducto());
//            if(p1 != null) {
//
//                PedidoDetalle pedidoDetalle = new PedidoDetalle();
//
//                pedidoDetalle.setProducto(p1.get());
//
//                pedidoDetalle.setCantidad(p.getCantidad());
//                pedidoDetalle.setPrecioUnitario(p1.get().getPrecioUnitario());
//
//                pedidoDetalle.setPedidoCabecera(pedidoCabecera);
//
//                orderItems.add(pedidoDetalle);
//
//                montoTotal = montoTotal + pedidoDetalle.getPrecioUnitario().doubleValue()*pedidoDetalle.getCantidad();
//
//                countProductos = countProductos + pedidoDetalle.getCantidad();
//            } else {
//
//                // No se agrega el producto.
//            }
//
//            pedidoCabecera.setDetalle(orderItems);
//
//        }
//
//        // Actualizo pedidoCabecera
//        pedidoCabecera.setMontoTotal(new BigDecimal(montoTotal));
//        pedidoCabecera.setAplicoDescuento(countProductos>3);
//        pedidoCabecera.setEstado("PENDIENTE");
//
//        PedidoCabecera savePedido = null;
//        try {
//            savePedido = pedidoCabeceraRepository.save(pedidoCabecera);
//
//        }catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//        //Optional<PedidoCabecera> savePedido2 = pedidoCabeceraRepository.findById(savePedido.getId());

//        log.info("Creacion de pedido OK: " + pedido.toString());

        // ---------------------------------------------------------------------------------

        //return PedidosUtil.getRespuestaPedidoDTO(savePedido);

        return new ResponseOrderDTO();

    }

    /**
     * Eliimina un Pedido a partir de su id.
     *
     * @param id
     */
    public void delete(String id) {

        this.orderRepository.deleteById(id);

    }

}
