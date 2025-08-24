package im.sma.bysma.springbootsqlite.repository;

import im.sma.bysma.springbootsqlite.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}