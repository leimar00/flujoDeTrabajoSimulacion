package co.edu.cesde.selection.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "aspirantes")
public class AspiranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;
    private String email;
    private String estado;

    @Column(name = "cargo_aplicado")
    private String cargoAplicado;

    public AspiranteEntity() {}

    // Getters y Setters exigidos por JPA
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getCargoAplicado() { return cargoAplicado; }
    public void setCargoAplicado(String cargoAplicado) { this.cargoAplicado = cargoAplicado; }
}
