package tse.java.service.impl;

import tse.java.entity.Administrador;
import tse.java.entity.Autoridad;
import tse.java.entity.Usuario;
import tse.java.persistance.IAdministradorDAO;
import tse.java.persistance.IAutoridadDAO;
import tse.java.persistance.IUsuarioDAO;
import tse.java.service.IUsuariosService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Stateless
@Named("usuariosService")
public class UsuariosService implements IUsuariosService {

    @EJB
    IUsuarioDAO usuarioDAO;

    @EJB
    IAdministradorDAO administradorDAO;
    @EJB
    IAutoridadDAO autoridadDAO;

    @Override
    public boolean autenticarUsuario(String username, String password) {
        Usuario user = usuarioDAO.findByUsername(username);
        if (user == null)
            return false; // El usuario no existe en la base de datos.
        try {
            return (Objects.equals(user.getPassword(), hashPassword(password)));
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
            System.out.println("ERROR ["+UsuariosService.class.getName()+"]: No se pudo obtener el hash MD5 para la password.");
            return false;
        }
    }

    @Override
    public Usuario getUsuario(String username) {
        return usuarioDAO.findByUsername(username);
    }

    @Override
    public boolean registrarUsuario(Usuario user) {

        if (!usuarioDAO.existUserByUsername(user.getUsername())) {

            try {
                user.setPassword(hashPassword(user.getPassword()));
            } catch (NoSuchAlgorithmException e2) {
                System.out.println("ERROR ["+UsuariosService.class.getName()+"]: No se pudo obtener el hash MD5 para la password.");
            }

            try {
                autoridadDAO.persist((Autoridad) user);
            } catch(Exception e) {
                try {
                    administradorDAO.persist((Administrador) user);
                } catch(Exception e1) {
                    e.printStackTrace();
                    e1.printStackTrace();
                    return false;
                }
            }
            return true;
        }else {
            return false;
        }
    }
    @Override
    public void actualizarDatos(Usuario user) {

    }

    /* AUXILIAR */
    private String hashPassword(String clave) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(clave.getBytes());
        byte[] digest = md.digest();

        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

}
