package com.bank.kantor.kantor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class KantorService {

    private final KantorRepository kantorRepository;
    private Status status;

    @Autowired
    public KantorService(KantorRepository kantorRepository, Status status) {
        this.kantorRepository = kantorRepository;
        this.status = status;
    }

    public List<Kantor> getKantors(){
        return kantorRepository.findAll();
    }

    public HashMap<String, Object> validateIdKantor(Long kantorId){
        HashMap<String, Object> response = new HashMap<>();
        Optional<Kantor> kantorOptional = kantorRepository.findKantorById(kantorId);
        if (kantorOptional.isPresent()){
            response.put("status", status.getStatusIdKantorFound());
            response.put("message", status.getMessageIdKantorFound());
        } else {
            response.put("status", status.getStatusIdKantorNotFound());
            response.put("message", status.getMessageIdKantorNotFound());
        }
        return response;
    }

    public void addNewKantor(Kantor kantor){
        Optional<Kantor> kantorOptional = kantorRepository.findKantorByAddress(kantor.getAlamat());
        if (kantorOptional.isPresent()){
            throw new IllegalStateException("alamat kantor sudah ditempati");
        }
        kantorRepository.save(kantor);
    }

    public void deleteKantor(Long kantorId){
        boolean exists = kantorRepository.existsById(kantorId);
        if (!exists){
            throw new IllegalStateException("kantor dengan id" + kantorId + "tidak ada");
        }
        kantorRepository.deleteById(kantorId);
    }

    @Transactional
    public void updateKantor(Long kantorId, String name, String alamat, String status){
        Kantor kantor = kantorRepository.findById(kantorId).orElseThrow(() -> new IllegalStateException(
                "kantor dengan id" + kantorId + "tidak ada"
        ));

        if (name != null && name.length() > 0 && !Objects.equals(kantor.getName(), name)){
            kantor.setName(name);
        }
        if (alamat != null && alamat.length() > 0 && !Objects.equals(kantor.getAlamat(), alamat)){
            kantor.setAlamat(alamat);
        }
        if (status != null && status.length() > 0 && !Objects.equals(kantor.getStatus(), status)){
            kantor.setStatus(status);
        }
    }
}
