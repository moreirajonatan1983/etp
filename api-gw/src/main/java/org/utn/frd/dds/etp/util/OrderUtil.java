package org.utn.frd.dds.etp.util;

import org.utn.frd.dds.etp.dto.ResponseOrderDTO;
import org.utn.frd.dds.etp.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderUtil {


    public static ResponseOrderDTO getResponseOrderDTO(Order order) {

        return new ResponseOrderDTO();
    }

    public static List<ResponseOrderDTO> getListResponseOrderDTO(List<Order> listOrder) {

        return new ArrayList<ResponseOrderDTO>();
    }


}
