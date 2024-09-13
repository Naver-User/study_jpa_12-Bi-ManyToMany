package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.zerock.myapp.listener.CommonEntityLifecyleListener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


//@Log4j2
@Slf4j

@Data

@EntityListeners(CommonEntityLifecyleListener.class)

@Entity(name = "Shopper2")
@Table(name="shopper2")
public class Shopper2
	implements Serializable {		// T1, Many (N)
	@Serial private static final long serialVersionUID = 1L;

	// 1. Set PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shopper_id")	// PK
	private Long id;
	
	
	// 2. Generals
	@Basic(optional = false)		// Non-Null Constraint
	private String name;
	
	
	// 3. Mapping Association
	
	// ============================
	// ManyToMany(N:M), Bi-directional Mapping
	// ============================
	
//	@ManyToMany									// 1
	@ManyToMany(targetEntity = Product2.class)	// 2
	
	// Cross Table 선언
	// (1) Cross Table 의 PK = Composite(T1 FK, T2 FK )
	@JoinTable(
		name = "shopper2_product2",								// Default = T1_T2
		joinColumns = @JoinColumn(name = "shopper_id"),			// T1 FK
		inverseJoinColumns = @JoinColumn(name = "product_id")	// T2 FK
	)
	
	// T1 -> T2
	// 다대다 양방향 연관관계에 의해서, 사용(=참조)가능한
	// 상품들을 저장할 공간으로 List<T2> 컬렉션 속성 필요.
	private List<Product2> myOrderedProducts = new Vector<>();	
	
	
	// 상품(product)을 새로이 주문할 때마다, 주문한 상품정보를
	// List<Product1> 컬렉션에, 연관관계에 맞게 넣어줄 Setter 메소드
	public void order(Product2 newProduct) {
		log.trace("order({}) invoked.", newProduct);
		
		if(Objects.nonNull(newProduct)) {
			this.getMyOrderedProducts().add(newProduct);
		} else return;
	} // order
	
	
   
} // end class
