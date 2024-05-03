package entities;

import java.io.Serializable;


import jakarta.persistence.*;

/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="address_cli")
	private String addressCli;

	private String codepostal;

	@Column(name="name_cli")
	private String nameCli;

	@Column(name="phone_cli")
	private String phoneCli;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productid")
	private Product product;

	public Order() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddressCli() {
		return this.addressCli;
	}

	public void setAddressCli(String addressCli) {
		this.addressCli = addressCli;
	}

	public String getCodepostal() {
		return this.codepostal;
	}

	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}

	public String getNameCli() {
		return this.nameCli;
	}

	public void setNameCli(String nameCli) {
		this.nameCli = nameCli;
	}

	public String getPhoneCli() {
		return this.phoneCli;
	}

	public void setPhoneCli(String phoneCli) {
		this.phoneCli = phoneCli;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}