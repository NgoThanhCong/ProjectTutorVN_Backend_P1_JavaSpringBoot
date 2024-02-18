package com.greenwich.tutorvn.service;
import com.greenwich.tutorvn.model.Order;
import com.greenwich.tutorvn.model.OrderStatus;
import com.greenwich.tutorvn.model.Tutor;
import com.greenwich.tutorvn.repository.OrderRepository;
import com.greenwich.tutorvn.request.RequestOrder;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Configurable

public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private NotificationService notificationService;

    public List<Order> getOrderpage(int pageNum, int pageSize)
    {
        Pageable pageOne = PageRequest.of(pageNum, pageSize);
        Page<Order> returnList = orderRepository.findAll(pageOne);   //(pageOne)
        return returnList.stream().toList();
    }

    public Order assignTutor(RequestOrder requestOrder){
        Optional<Order> optionalOrder = orderRepository.findById(requestOrder.getIdOrder()); // tim id oder
        if(optionalOrder.isPresent()) {   // ton tai
            Order order = optionalOrder.get();    // lay model cua order
            order.setStatus(OrderStatus.ORDER_DONE);   // set trang thai da dk oder

            order.setTutor_ID(requestOrder.getIdTutor());  // lay id cua tutor > set vao order
            notificationService.insert(order);  // tao 1 thong bao gan vowi order
            return orderRepository.save(order);   // save datas
        }
        return  null;
    }

    public Order requestTutor(RequestOrder requestOrder){
        Optional<Order> optionalOrder = orderRepository.findById(requestOrder.getIdOrder());  // optional la kieu co the mang gia tri null
        if(optionalOrder.isPresent()){  // check oder co ton tai khong / neu tim dk
            Order order = optionalOrder.get();  // lay thong tin cua oder
            List<Long> listIDTutor = order.getListTutorRequired();  // lay ve danh sach nhung tutor da dang ki day
            for(Long id: listIDTutor)   //
            {
                if(requestOrder.getIdTutor()==id){ // nEU MA CO ID === VOI ID TRUYEN CO NGHIA LA GIA SU NAY DA DANG KY DAY ROI
                    return null;
                }
            }
            // NEU MA KO TIM THAY ID NAO TRUNG VOI ID CUA TUTOR THI THEM VAO
            listIDTutor.add(requestOrder.getIdTutor());   // ADD VAO DANH SACH TUTOR YEU CAU
            order.setListTutorRequired(listIDTutor); // CAP NHAT LAI DANH SACH TUTOR YEU CAU THAM GIA DAY LOP
            return  orderRepository.save(order);  // UPDATE TRONG DB
        }
        return null;
    }
}
