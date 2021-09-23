package com.bank.kantor.kantor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

import static com.sun.beans.introspect.PropertyInfo.Name.required;

@RestController
@RequestMapping(path = "kantor")
public class KantorController {

    private final KantorService kantorService;

    @Autowired
    public KantorController(KantorService kantorService) {
        this.kantorService = kantorService;
    }

    @GetMapping
    public List<Kantor> getKantors(){
        return kantorService.getKantors();
    }

    @PostMapping
    public void registerNewKantor(@RequestBody Kantor kantor){
        kantorService.addNewKantor(kantor);
    }

    @DeleteMapping(path = "{kantorId}")
    public void deleteKantor(@PathVariable("kantorId") Long id){
        kantorService.deleteKantor(id);
    }

    @PutMapping(path = "{kantorId}")
    public void updateKantor(
            @PathVariable("kantorId") Long kantorId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String alamat,
            @RequestParam(required = false) String status) {
        kantorService.updateKantor(kantorId, name, alamat, status);
    }
}
