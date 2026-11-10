package com.gremio.service;

import com.gremio.model.Movimiento;
import com.gremio.repository.MovimientoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovimientoService {
    private final MovimientoRepository repo;

    public MovimientoService(MovimientoRepository repo) { this.repo = repo; }

    public List<Movimiento> listarTodos() { return repo.findAll(); }
    public void guardar(Movimiento m) { repo.save(m); }
    public Movimiento buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }
    public void eliminar(Long id) { repo.deleteById(id); }

    public Double calcularSaldo() {
        return listarTodos().stream()
            .mapToDouble(m -> m.getTipo().equals("INGRESO") ? m.getMonto() : -m.getMonto())
            .sum();
    }
}