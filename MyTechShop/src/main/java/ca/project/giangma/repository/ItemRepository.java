package ca.project.giangma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.project.giangma.beans.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Additional query methods can be defined here if needed
}
