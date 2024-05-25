package com.trioshop.model.dto.item;

//import com.trioshop.utils.StringToLongConverter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {
    private String orderCode;
    private long itemCode;
    private long orderQty;
}
//    public OrderItemEntity(String orderCode, String itemCode, String orderQty) {
//        this.orderCode = orderCode;
//        this.itemCode = StringToLongConverter.convertToLong(itemCode);
//        this.orderQty = StringToLongConverter.convertToLong(orderQty);
//    }
//    public OrderItemEntity(String orderCode, Long itemCode, Long orderQty) {
//        this.orderCode = orderCode;
//        this.itemCode = itemCode;
//        this.orderQty = orderQty;
//    }

//    public void setItemCode(String itemCode) {
//        this.itemCode = StringToLongConverter.convertToLong(itemCode);
//    }
//    public void setOrderQty(String orderQty) {
//        this.orderQty = StringToLongConverter.convertToLong(orderQty);
//    }

