package modelo;

public abstract class Usuario{

    protected String nombreUsuario;
    protected String contraseña;
    protected String nombreCompleto;

    public Usuario(String nombreUsuario, String contraseña, String nombreCompleto) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }


}
