package recursos.clases;

public class Item {
    private Long ID;
    private String Nombre;

    public Item(Long ID,String Nombre) {
        this.ID = ID;
        this.Nombre = Nombre;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    @Override
    public String toString(){
        return Nombre;
    }
}
