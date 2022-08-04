package modelo;

public class VehiculoBaseDTO {

    private VehiculoDTO vehiculo;

    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Integer getMatricula() {
        if (vehiculo != null) {
            return vehiculo.getMatricula();
        }
        return null;
    }

    public void setMatricula(Integer matricula) {
        if (vehiculo == null) {
            vehiculo = new VehiculoDTO();
        }
        vehiculo.setMatricula(matricula);
    }
}
