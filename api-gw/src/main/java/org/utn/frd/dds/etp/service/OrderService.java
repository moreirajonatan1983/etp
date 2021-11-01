package org.utn.frd.dds.etp.service;

import java.io.File;

public interface OrderService {

    File getCSV(String orderUUID);

    String getQR(String orderUUID);
}
