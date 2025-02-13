package ca.project.giangma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.project.giangma.beans.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    // Additional query methods (if needed) can be defined here
}
