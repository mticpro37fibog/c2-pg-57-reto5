package modelo;

public class VehiculoDTO {
    private Integer matricula;
    private String nombre;
    private Float velocidad;
    private Double longtitud;

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Float velocidad) {
        this.velocidad = velocidad;
    }

    public Double getLongtitud() {
        return longtitud;
    }

    public void setLongtitud(Double longtitud) {
        this.longtitud = longtitud;
    }
}
