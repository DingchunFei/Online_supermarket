package com.fei.store.utils;

import com.fei.store.domain.Order;
import com.fei.store.service.OrderService;
import com.fei.store.service.serviceImpl.OrderServiceImp;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
    private static ThreadLocal current = new ThreadLocal();

    private List<Order> newOrders = new ArrayList();
    private List<Order> dirtyOrders = new ArrayList();
    private List<Order> deletedOrders = new ArrayList();

    public static void newCurrent(){
        setCurrent(new UnitOfWork());
    }

    public static void setCurrent(UnitOfWork uow){
        current.set(uow);
    }

    public static UnitOfWork getCurrent(){
        return (UnitOfWork) current.get();
    }

    public void registerNew(Order order) {
        //Assert.assertNotNull(order.getOid(),"id is null");
        Assert.assertTrue("object is dirty", !dirtyOrders.contains(order));
        Assert.assertTrue("object is deleted", !deletedOrders.contains(order));
        Assert.assertTrue("object is new", !newOrders.contains(order));
        newOrders.add(order);
    }

    public void registerDirty(Order order){
        Assert.assertNotNull("id is null",order.getOid());
        Assert.assertTrue("object is deleted", !deletedOrders.contains(order));
        if(!dirtyOrders.contains(order) && !newOrders.contains(order)){
            dirtyOrders.add(order);
        }
    }

    public void registerDeleted(Order order){
        Assert.assertNotNull("id is null",order.getOid());
        if(newOrders.remove(order)) return;
        dirtyOrders.remove(order);
        if(!deletedOrders.contains(order)){
            deletedOrders.add(order);
        }
    }

    public void registerClean(Order order){
        Assert.assertNotNull("id is null",order.getOid());
    }

    OrderService orderService = new OrderServiceImp();
    public void commit() throws Exception {
        for(Order order : newOrders){
            orderService.saveOrder(order);
        }

        for(Order order : deletedOrders){
            orderService.deleteOrder(order);
        }

//        for(Order order : newOrders){
//            orderService.saveOrder(order);
//        }
    }
}
