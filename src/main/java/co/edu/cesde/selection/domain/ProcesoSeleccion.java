package co.edu.cesde.selection.domain;

import co.edu.cesde.selection.domain.enums.EstadoSeleccion;
import java.time.LocalDate;

public class ProcesoSeleccion {
    private Long id;
    private Long aspiranteId;
    private String nombreAspirante;
    private String cargoAplicado;
    private EstadoSeleccion estado;
    private Double puntajeFinal;
    private LocalDate fechaInicio;
    private LocalDate fechaDecision;
    private String observaciones;

    public ProcesoSeleccion() {
    }

    public ProcesoSeleccion(Long id, Long aspiranteId, String nombreAspirante, String cargoAplicado,
                            EstadoSeleccion estado, Double puntajeFinal, LocalDate fechaInicio,
                            LocalDate fechaDecision, String observaciones) {
        this.id = id;
        this.aspiranteId = aspiranteId;
        this.nombreAspirante = nombreAspirante;
        this.cargoAplicado = cargoAplicado;
        this.estado = estado;
        this.puntajeFinal = puntajeFinal;
        this.fechaInicio = fechaInicio;
        this.fechaDecision = fechaDecision;
        this.observaciones = observaciones;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAspiranteId() { return aspiranteId; }
    public void setAspiranteId(Long aspiranteId) { this.aspiranteId = aspiranteId; }

    public String getNombreAspirante() { return nombreAspirante; }
    public void setNombreAspirante(String nombreAspirante) { this.nombreAspirante = nombreAspirante; }

    public String getCargoAplicado() { return cargoAplicado; }
    public void setCargoAplicado(String cargoAplicado) { this.cargoAplicado = cargoAplicado; }

    public EstadoSeleccion getEstado() { return estado; }
    public void setEstado(EstadoSeleccion estado) { this.estado = estado; }

    public Double getPuntajeFinal() { return puntajeFinal; }
    public void setPuntajeFinal(Double puntajeFinal) { this.puntajeFinal = puntajeFinal; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaDecision() { return fechaDecision; }
    public void setFechaDecision(LocalDate fechaDecision) { this.fechaDecision = fechaDecision; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}