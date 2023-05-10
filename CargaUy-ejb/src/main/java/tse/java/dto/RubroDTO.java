package tse.java.dto;

public class RubroDTO {

    private Long id;

    private String nombre;

    public RubroDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public RubroDTO(){}

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
}
