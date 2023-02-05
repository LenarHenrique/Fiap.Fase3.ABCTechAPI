package br.com.fiap.abctechapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Transient
    private final int MIN_ASSISTS_GT = 0;
    @Transient
    private final int MAX_ASSISTS = 15;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "operator_id", nullable = false)
    private Long operatorId;

    @ManyToMany
    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "FK01_service_id"))
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "FK02_order_id"))
    private List<Assist> services;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "start_order_location_id", foreignKey = @ForeignKey(name = "FK_start_order_id"))
    private OrderLocation startOrderLocation;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "end_order_location_id", foreignKey = @ForeignKey(name = "FK_end_order_id"))
    private OrderLocation endOrderLocation;

    public boolean hasMinAssists(){
        return services.size() > MIN_ASSISTS_GT;
    }

    public boolean exceedsMaxAssists(){
        return  services.size() > MAX_ASSISTS;
    }



}
