package tse.java.service.impl;

import tse.java.dto.UsuarioDTO;
import tse.java.entity.Administrador;
import tse.java.entity.Autoridad;
import tse.java.entity.Usuario;
import tse.java.enumerated.AuthResponse;
import tse.java.enumerated.TipoUsuario;
import tse.java.exception.UsuarioExisteException;
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
import java.sql.Date;
import java.util.List;
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
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    @Override
    public boolean autenticarUsuario(String username, String password) {
        Usuario user = usuarioDAO.findByUsername(username);
        if (user == null)
            return false;
        try {
            return (Objects.equals(user.getPassword(), hashPassword(password)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("ERROR [" + UsuariosService.class.getName() + "]: No se pudo obtener el hash MD5 para la password.");
            return false;
        }
    }

    @Override
    public Usuario getUsuario(String username) {
        return usuarioDAO.findByUsername(username);
    }

    @Override
    public Administrador getAdminByID(int id) {
        return administradorDAO.findById(id);
    }

    @Override
    public Autoridad getAutoByID(int id) {
        return autoridadDAO.findById(id);
    }

    @Override
    public boolean registrarUsuario(UsuarioDTO user) {
        if (!usuarioDAO.existUserByUsername(user.getUsername())) {
            try {
                user.setPassword(hashPassword(user.getPassword()));
            } catch (NoSuchAlgorithmException e2) {
                System.out.println("ERROR [" + UsuariosService.class.getName() + "]: No se pudo obtener el hash MD5 para la password.");
            }
            try {
                if (user.getTipo() == TipoUsuario.AUTORIDAD) {
                    Autoridad autoridad = new Autoridad(user);
                    autoridadDAO.persist(autoridad);
                    return true;
                }
                if (user.getTipo() == TipoUsuario.ADMIN) {
                    Administrador administrador = new Administrador(user);
                    administradorDAO.persist(administrador);
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public void actualizarDatos(Usuario user){
        if (user instanceof Autoridad) {
            autoridadDAO.merge((Autoridad) user);
        } else if (user instanceof Administrador) {
            administradorDAO.merge((Administrador) user);
        }
    }

    @Override
    public void eliminarUsuario(UsuarioDTO user) throws UsuarioExisteException {
        if (usuarioDAO.existUserByUsername(user.getUsername())) {
            try {
                if (user.getTipo() == TipoUsuario.ADMIN) {
                    Administrador administrador = new Administrador(user);
                    administrador.setIdUsuario(user.getIdUsuario());
                    administradorDAO.delete(administrador);
                }
                if (user.getTipo() == TipoUsuario.AUTORIDAD) {
                    Autoridad autoridad = new Autoridad(user);
                    autoridad.setIdUsuario(user.getIdUsuario());
                    autoridadDAO.delete(autoridad);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new UsuarioExisteException("El usuario no existe en la base de datos");
        }
    }

    /* AUXILIAR */
    public String hashPassword(String clave) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(clave.getBytes());
        byte[] digest = md.digest();

        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

}
