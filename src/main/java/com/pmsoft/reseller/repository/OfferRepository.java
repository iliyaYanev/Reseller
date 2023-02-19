package com.pmsoft.reseller.repository;

import com.pmsoft.reseller.model.entity.Offer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByCreatedBy_IdNot(Long ownerId);
}
