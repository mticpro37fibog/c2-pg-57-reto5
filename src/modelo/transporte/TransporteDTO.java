package modelo.transporte;

import modelo.VehiculoDTO;

public class TransporteDTO {
    private VehiculoDTO vehiculo;

    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }

    private Integer tripulantes;
    private Integer pasajeros;

    public Integer getTripulantes() {
        return tripulantes;
    }

    public void setTripulantes(Integer tripulantes) {
        this.tripulantes = tripulantes;
    }

    public Integer getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(Integer pasajeros) {
        this.pasajeros = pasajeros;
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
