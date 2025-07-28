package com.example.eclipselink.service;

import com.example.eclipselink.model.Calculated;
import com.example.eclipselink.model.Operation;
import com.example.eclipselink.repository.CalculatedRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class CalculatedService {

    private final CalculatedRepository calculatedRepository;
    private final EntityManager entityManager;

    public CalculatedService(CalculatedRepository calculatedRepository, EntityManager entityManager) {
        this.calculatedRepository = calculatedRepository;
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Calculated> getAll(){
        return calculatedRepository.findAll();
    }

    public Calculated save(Calculated calculated){
        Calculated getId = calculatedRepository.getReferenceById(1);
        log.info("Old calc - {}",getId);
        return calculatedRepository.save(calculated);
    }

    @Transactional
    public Integer listSaveGenerated(){
        List<Calculated> calc = new ArrayList<>();
        for(int i = 0;i < 500;i++){
            Calculated calculated = new Calculated();
            calculated.setSum((int) (Math.random() * 100));
            calculated.setOrders(createOper(calculated));
            calc.add(calculated);
        }
        long start = System.currentTimeMillis();
        calc = calculatedRepository.saveAll(calc);
        calculatedRepository.flush();
        long end = System.currentTimeMillis() - start;
        log.info("End save - {}",end);
        return calc.size();
    }

    private List<Operation> createOper(Calculated calculated){
        return Stream.generate(() -> Operation.builder().calculated(calculated).operation(Math.random() * 100).build())
                .limit(1000)
                .toList();
    }
}
