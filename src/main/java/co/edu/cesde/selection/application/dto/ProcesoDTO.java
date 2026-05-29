package co.edu.cesde.selection.application.dto;

public class ProcesoDTO {
    private Long idAspirante;
    private String nombreCompleto;
    private String cargo;
    private String estadoActual;

    public ProcesoDTO() {
    }

    public ProcesoDTO(Long idAspirante, String nombreCompleto, String cargo, String estadoActual) {
        this.idAspirante = idAspirante;
        this.nombreCompleto = nombreCompleto;
        this.cargo = cargo;
        this.estadoActual = estadoActual;
    }

    public Long getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Long idAspirante) {
        this.idAspirante = idAspirante;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }
}