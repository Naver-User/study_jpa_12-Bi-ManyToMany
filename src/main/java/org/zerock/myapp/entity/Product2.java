package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.zerock.myapp.listener.CommonEntityLifecyleListener;

import lombok.Data;
import lombok.ToString;


@Data

@EntityListeners(CommonEntityLifecyleListener.class)

@Entity(name = "Product2")
@Table(name="product2")
@SequenceGenerator(name = "MySequence", sequenceName = "seq_product2")
public class Product2
	implements Serializable {		// T2, Many (M)	
	@Serial private static final long serialVersionUID = 1L;

	// 1. Set PK
	@Id
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE, 
		generator = "MySequence")
	@Column(name = "product_id")	// PK
	private Long id;
	
	
	// 2. Generals
	@Basic(optional = false, fetch = FetchType.EAGER)	// Not Null Constraint
	private String name;
	
	
	// 3. Mapping Association
	
	// ============================
	// ManyToMany(N:M), Bi-directional Mapping
	// ============================
	
	// 다대다 양방향 관계에서, FK 속성명을 연관관계의 주인으로 지정해야 함이
	// 당연한데, 정작 T1에는 FK 속성자체가 없습니다. 이유는 다대다이니, 모든
	// T1과 T2에 대한 FK속성은 Cross Table에 있습니다. 이렇게 다대다 양방향
	// 관계에서, 연관관계의 주인을 정할 때에는, T1의 컬렉션 속성명을 지정합니다!!!(***)
	@ManyToMany(
		mappedBy = "myOrderedProducts",		// T1의 컬렉션 속성명 지정(***)
		targetEntity = Shopper2.class)
	
	// T2 -> T1
	// 다대다 양방향 연관관계에 의해서, 사용(=참조)가능한
	// 고객들을 저장할 공간으로 List<T1> 컬렉션 속성 필요.
	
	@ToString.Exclude
	private List<Shopper2> myShoppers = new Vector<>();
	
	// T2 에는, 컬렉션의 요소를 추가해줄 편의성 Setter 메소드를
	// 만들지 않아도 됩니다.. 왜????
	
	
	
   
} // end class

