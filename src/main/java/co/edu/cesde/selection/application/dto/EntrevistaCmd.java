package co.edu.cesde.selection.application.dto;




public class EntrevistaCmd {
    private Long idAspirante;
    private String notasEntrevistador;

    public EntrevistaCmd() {
    }

    public EntrevistaCmd(Long idAspirante, String notasEntrevistador) {
        this.idAspirante = idAspirante;
        this.notasEntrevistador = notasEntrevistador;
    }

    public Long getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Long idAspirante) {
        this.idAspirante = idAspirante;
    }

    public String getNotasEntrevistador() {
        return notasEntrevistador;
    }

    public void setNotasEntrevistador(String notasEntrevistador) {
        this.notasEntrevistador = notasEntrevistador;
    }
}