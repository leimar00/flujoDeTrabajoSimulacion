package co.edu.cesde.selection.application.dto;


import lombok.Data;

@Data
public class AspiranteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String cargo;
    private String estadoSeleccion;
}
