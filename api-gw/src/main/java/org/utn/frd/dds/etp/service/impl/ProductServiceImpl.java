package org.utn.frd.dds.etp.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.utn.frd.dds.etp.dto.RequestProductDTO;
import org.utn.frd.dds.etp.dto.ResponseProductDTO;
import org.utn.frd.dds.etp.entity.Product;
import org.utn.frd.dds.etp.repository.ProductRepository;
import org.utn.frd.dds.etp.util.ProductUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ProductServiceImpl {

    private static final Log log = LogFactory.getLog(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() {

        List<Product> products = new ArrayList<Product>();
        //  productRepository.findAll().forEach(product -> productRepository.add(product));

        return products;
    }

    public Product getProductById(String id) {

        return productRepository.findById(id).get();
    }

    public List<ResponseProductDTO> getProductsByDate(LocalDate date) {

        log.info("ProductService.getProductsByFecha Init: " + date);

        List<Product> products = new ArrayList<Product>();
        productRepository.findByDate(date).forEach(product -> products.add((Product) product));

        return ProductUtil.getListResponseProductDTO(products);

    }

    public ResponseProductDTO saveOrUpdate(RequestProductDTO requestProductDTO) {

        log.info("ProductService.saveOrUpdate Init: " + requestProductDTO.toString());

        Product product = new Product();

//        product.setLocal(requestProductDTO.getLocal());
//        product.setUser(requestProductDTO.getUser());
//        product.setLocalDateTime(LocalDateTime.now());


//        List<ProductItems> productItems = new ArrayList<ProductItems>();
//
//        for(RequestProductDTO p: pedido.getDetalle() ) {
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
//                productItems.add(pedidoDetalle);
//
//                montoTotal = montoTotal + pedidoDetalle.getPrecioUnitario().doubleValue()*pedidoDetalle.getCantidad();
//
//                countProductos = countProductos + pedidoDetalle.getCantidad();
//            } else {
//
//                // No se agrega el producto.
//            }
//
//            pedidoCabecera.setDetalle(productItems);
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

        return new ResponseProductDTO();

    }

    public void delete(String id) {

        this.productRepository.deleteById(id);

    }

}
