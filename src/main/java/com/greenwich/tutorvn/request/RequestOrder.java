package com.greenwich.tutorvn.request;

public class RequestOrder {

    private Long idTutor;
    private  Long idOrder;

    public RequestOrder(Long idTutor, Long idOrder) {
        this.idTutor = idTutor;
        this.idOrder = idOrder;
    }

    public RequestOrder()
    {
    }

    public Long getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Long idTutor) {
        this.idTutor = idTutor;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }
}
