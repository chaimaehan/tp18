package io.grpc.services;

import io.grpc.entities.Compte;
import io.grpc.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteService {

    private final CompteRepository compteRepository;

    @Autowired
    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    public List<Compte> findAllComptes() {
        return compteRepository.findAll();
    }

public Compte findCompteById(String id) {
        return compteRepository.findById(id).orElse(null);
    }

    public Compte saveCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    public void deleteCompte(String id) {
        compteRepository.deleteById(id);
    }

    public float calculateTotalSolde() {
        return (float) compteRepository.findAll()
                .stream()
                .mapToDouble(Compte::getSolde)
                .sum();
    }

    public float calculateAverageSolde() {
        List<Compte> comptes = compteRepository.findAll();
        if (comptes.isEmpty()) {
            return 0;
        }
        return (float) comptes.stream()
                .mapToDouble(Compte::getSolde)
                .average()
                .orElse(0);
    }
}