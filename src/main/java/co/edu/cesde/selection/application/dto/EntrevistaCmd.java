package co.edu.cesde.selection.application.dto;

import java.time.LocalDateTime;

public class EntrevistaCmd {

    private Long procesoId;
    private String tipo;
    private String entrevistador;
    private LocalDateTime fecha;
    private Double puntaje;
    private String observaciones;


        public Long getProcesoId() {
            return procesoId;
        }

        public void setProcesoId(Long procesoId) {
            this.procesoId = procesoId;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getEntrevistador() {
            return entrevistador;
        }

        public void setEntrevistador(String entrevistador) {
            this.entrevistador = entrevistador;
        }

        public LocalDateTime getFecha() {
            return fecha;
        }

        public void setFecha(LocalDateTime fecha) {
            this.fecha = fecha;
        }

        public Double getPuntaje() {
            return puntaje;
        }

        public void setPuntaje(Double puntaje) {
            this.puntaje = puntaje;
        }

        public String getObservaciones() {
            return observaciones;
        }

        public void setObservaciones(String observaciones) {
            this.observaciones = observaciones;
        }
    }


