package ca.project.giangma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.project.giangma.beans.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Additional query methods (if needed) can be defined here
}
