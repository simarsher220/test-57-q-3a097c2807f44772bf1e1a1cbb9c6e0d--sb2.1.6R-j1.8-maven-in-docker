package org.codejudge.sb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.codejudge.sb.exception.RangeException;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true, doNotUseGetters=true)
@Entity
@Table(name="product")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Column(name="category")
	@JsonProperty("category_name")
	private String category;
	
	private String description;
	
	@Column(name="buy_price")
	private Double buyPrice;
	
	@Column(name="sell_price")
	private Double sellPrice;
	
	private Integer quantity;

	public static void validateForUpsertion(Product product) throws RangeException {
		if (product == null) {
			throw new IllegalArgumentException("Product can't be empty!");
		}
		product.validateForCreation();
	}

	private void validateForCreation() throws RangeException {
		this.validateName();
		this.validateCategoryName();
		this.validateDescription();
		this.validateBuyPrice();
		this.validateSellPrice();
		this.validateQuantity();
	}

	private void validateName() {
		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Name can't be empty!");
		}
	}

	private void validateCategoryName() {
		if (StringUtils.isEmpty(category)) {
			throw new IllegalArgumentException("Category Name can't be empty!");
		}
	}

	private void validateDescription() {
		if (StringUtils.isEmpty(description)) {
			throw new IllegalArgumentException("Description can't be empty!");
		}
	}

	private void validateBuyPrice() {
		if (null == buyPrice) {
			throw new IllegalArgumentException("Buy Price can't be empty!");
		}
	}

	private void validateSellPrice() {
		if (null == sellPrice) {
			throw new IllegalArgumentException("Sell Price can't be empty!");
		}
	}

	private void validateQuantity() throws RangeException {
		if (null == quantity) {
			throw new IllegalArgumentException("Quantity can't be empty!");
		}
		if (quantity < 0) {
			throw new RangeException("Quantity can't be negative!");
		}
	}
}
