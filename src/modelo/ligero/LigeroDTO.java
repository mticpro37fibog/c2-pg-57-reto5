package modelo.ligero;

import modelo.VehiculoBaseDTO;

public class LigeroDTO extends VehiculoBaseDTO {

    private String color;

    private String androide;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAndroide() {
        return androide;
    }

    public void setAndroide(String androide) {
        this.androide = androide;
    }

}
