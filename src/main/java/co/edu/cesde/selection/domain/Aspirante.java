    package co.edu.cesde.selection.domain;

    import co.edu.cesde.selection.domain.enums.EstadoSeleccion;


    public class Aspirante {
        private Long id;
        private String nombre;
        private String apellido;
        private String email;
        private String telefono;
        private String cargoAplicado;
        private EstadoSeleccion estadoSeleccion;

        public Aspirante() {
        }

        public Aspirante(Long id, String nombre, String apellido, String email, String telefono, String cargoAplicado, EstadoSeleccion estadoSeleccion) {
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.email = email;
            this.telefono = telefono;
            this.cargoAplicado = cargoAplicado;
            this.estadoSeleccion = estadoSeleccion;
        }


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getCargoAplicado() {
            return cargoAplicado;
        }

        public void setCargoAplicado(String cargoAplicado) {
            this.cargoAplicado = cargoAplicado;
        }

        public EstadoSeleccion getEstadoSeleccion() {
            return estadoSeleccion;
        }

        public void setEstadoSeleccion(EstadoSeleccion estadoSeleccion) {
            this.estadoSeleccion = estadoSeleccion;
        }
    }
