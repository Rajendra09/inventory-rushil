package im.sma.bysma.springbootsqlite.web.rest;

import im.sma.bysma.springbootsqlite.domain.Inventory;
import im.sma.bysma.springbootsqlite.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory updatedInventory) {
        return inventoryRepository.findById(id)
                .map(inventory -> {
                    inventory.setName(updatedInventory.getName());
                    inventory.setQuantity(updatedInventory.getQuantity());
                    inventory.setUnit(updatedInventory.getUnit());
                    inventory.setAverageDailyConsumption(updatedInventory.getAverageDailyConsumption());
                    inventory.setLowThresholdLimit(updatedInventory.getLowThresholdLimit());
                    inventory.setNotes(updatedInventory.getNotes());
                    inventoryRepository.save(inventory);
                    return ResponseEntity.ok(inventory);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}