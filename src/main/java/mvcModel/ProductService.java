package mvcModel;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import entities.Order;
import entities.Product;

/**
 * Session Bean implementation class ProductService
 */
@Stateless
@LocalBean
public class ProductService {

    @PersistenceContext(unitName = "projectfinal") 
    private EntityManager em;

    /**
     * Default constructor.
     */
    public ProductService() {
        // Auto-generated constructor stub
    }

    
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
        products = query.getResultList();
        System.out.println("List of products returned");
        return products;
    }

    
    public void addProduct(Product product) {
        em.persist(product);
        System.out.println("Product " + product.getName() + " is added to database");
    }

    
    public Product updateProduct(int productId, Product updatedProduct) {
        Product product = em.find(Product.class, productId);
        if (product != null) {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setImageurl(updatedProduct.getImageurl());
            em.merge(product);
            System.out.println("Product updated");
        }
        return product;
    }

    
    public void deleteProduct(int productId) {
        Product product = em.find(Product.class, productId);
        if (product != null) {
            if (product.getOrders().isEmpty()) {  
                em.remove(product);  
            }
        } else {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }
    }



    
    public Product findProductById(int productId) {
        Product product = em.find(Product.class, productId);
        if (product != null) {
            System.out.println("Product found");
        } else {
            System.out.println("Product not found");
        }
        return product;
    }
}