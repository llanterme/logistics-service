package za.co.digitalcowboy.logistics.service;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import za.co.digitalcowboy.logistics.service.domain.Event;
import za.co.digitalcowboy.logistics.service.domain.Order;
import za.co.digitalcowboy.logistics.service.domain.OrderContext;

import java.io.InputStream;

public class Debug {

    public static void main(String args[])  //static method
    {
       InputStream is =  Thread.currentThread().getContextClassLoader().getResourceAsStream("payload.json");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

         final JavaType accountType = TypeFactory.defaultInstance().constructParametricType(
                Event.class, Order.class, OrderContext.class);

        try {
            var d =mapper.readValue(is, accountType);
            System.out.println(d);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
