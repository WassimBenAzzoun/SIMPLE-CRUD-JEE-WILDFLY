package mvcModel;

import jakarta.ejb.LocalBean;
import jakarta.persistence.*;


import java.util.List;

import entities.Order;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class OrdersService
 */
@Stateless
@LocalBean
public class OrdersService {
	@PersistenceContext(unitName = "projectfinal") // Adjust to match your persistence unit name
    private EntityManager em;

    /**
     * Default constructor.
     */
    public OrdersService() {
        // Auto-generated constructor stub
    }

    public List<Order> getAllOrders() {
        TypedQuery<Order> query = em.createNamedQuery("Order.findAll", Order.class);
        return query.getResultList();
    }

    
    public void addOrder(Order order) {
        em.persist(order);
        System.out.println("Order added to database");
    }


    public Order updateOrder(int orderId, Order updatedOrder) {
        Order order = em.find(Order.class, orderId);
        if (order != null) {
            order.setNameCli(updatedOrder.getNameCli());
            order.setAddressCli(updatedOrder.getAddressCli());
            order.setCodepostal(updatedOrder.getCodepostal());
            order.setPhoneCli(updatedOrder.getPhoneCli());
            order.setProduct(updatedOrder.getProduct()); // Make sure Product relationship is managed properly
            em.merge(order);
            System.out.println("Order updated");
        }
        return order;
    }

    
    public void deleteOrder(int orderId) {
        Order order = em.find(Order.class, orderId);
        if (order != null) {
            em.remove(order);
            System.out.println("Order deleted");
        } else {
            System.out.println("Order not found");
        }
    }

 
    public Order findOrderById(int orderId) {
        Order order = em.find(Order.class, orderId);
        if (order != null) {
            System.out.println("Order found");
        } else {
            System.out.println("Order not found");
        }
        return order;
    }

}
