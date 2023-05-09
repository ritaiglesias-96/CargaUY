package dto;

import entity.Usuario;



public class UsuarioDTO {
    private int id;
    private String email;
    private String ci;

    public UsuarioDTO(){

    }

    public UsuarioDTO(Usuario u){

        this.id = u.getId();
        this.email = u.getEmail();
        this.ci = u.getCi();
    }

    public UsuarioDTO(int id, String email, String ci){

        this.id = id;
        this.email = email;
        this.ci = ci;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }


}
