package org.utn.frd.dds.etp.util;

import org.utn.frd.dds.etp.dto.ResponseOrderDTO;
import org.utn.frd.dds.etp.entity.Order;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderUtil {


    public static ResponseOrderDTO getResponseOrderDTO(Order order) {

        return new ResponseOrderDTO();
    }

    public static List<ResponseOrderDTO> getListResponseOrderDTO(List<Order> listOrder) {

        return new ArrayList<ResponseOrderDTO>();
    }

    public static File generateCSV(Optional<Order> orderFound) {

        return null;
    }

    public static String generateQR(Optional<Order> orderFound) {

        return null;
    }

}
