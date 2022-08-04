package modelo.transporte;

import modelo.VehiculoBaseDTO;

public class TransporteDTO extends VehiculoBaseDTO {
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
}
