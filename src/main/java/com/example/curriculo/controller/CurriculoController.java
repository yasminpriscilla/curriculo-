package com.example.curriculo.controller;

import com.example.curriculo.model.Curriculo;
import com.example.curriculo.repository.CurriculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curriculos")
public class CurriculoController {

    @Autowired
    private CurriculoRepository curriculoRepository;

    @GetMapping
    public List<Curriculo> getAllCurriculos() {
        return curriculoRepository.findAll();
    }

    @GetMapping("/")
    public String home() {
        return "Hello, World!";
    }

    @GetMapping("/{id}")
    public Curriculo getCurriculoById(@PathVariable Long id) {
        System.out.println("pegou");
        return curriculoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Curriculo createCurriculo(@RequestBody Curriculo curriculo) {
        return curriculoRepository.save(curriculo);
    }

    @PutMapping("/{id}")
    public Curriculo updateCurriculo(@PathVariable Long id, @RequestBody Curriculo curriculoDetails) {
        Curriculo curriculo = curriculoRepository.findById(id).orElse(null);

        if (curriculo != null) {
            // Atualize os campos do currículo conforme necessário
            curriculo.setNome(curriculoDetails.getNome());
            curriculo.setEmail(curriculoDetails.getEmail());

            return curriculoRepository.save(curriculo);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCurriculo(@PathVariable Long id) {
        curriculoRepository.deleteById(id);
    }
}
