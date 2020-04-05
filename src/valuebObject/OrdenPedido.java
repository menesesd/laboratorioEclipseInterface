package valuebObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrdenPedido {

	private String nombre;
	private String tipo;
	private int cantidad;
	private String distribuidor;
	private boolean isSucursalPrincipal;
	private boolean isSucursalSecundaria;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public boolean isSucursalPrincipal() {
		return isSucursalPrincipal;
	}

	public void setSucursalPrincipal(boolean isSucursalPrincipal) {
		this.isSucursalPrincipal = isSucursalPrincipal;
	}

	public boolean isSucursalSecundaria() {
		return isSucursalSecundaria;
	}

	public void setSucursalSecundaria(boolean isSucursalSecundaria) {
		this.isSucursalSecundaria = isSucursalSecundaria;
	}

	public String validar(String nombre, String tipo, String cantidad, String distribuidor, boolean isSucursalPrincipal,
			boolean isSucursalSecundaria) {
		String errores = "";
		Pattern p = Pattern.compile("^[a-zA-Z0-9 Ò·ÈÌÛ˙¡…Õ”⁄¸‹—]*$");
		Matcher m = p.matcher(nombre);

		if (nombre.trim().equals("") || !m.matches()) {
			errores += "- Se debe proporcionar un nombre de medicamento alfanumÈrico \n\r";
		}

		if (tipo.trim().equals("")) {
			errores += "- Debe elegir un tipo de medicamento \n\r";
		}

		try {
			this.cantidad = Integer.parseUnsignedInt(cantidad);
		} catch (NumberFormatException e) {
			errores += "- La cantidad debe ser un valor numÈrico positivo \n\r";
		}

		if (distribuidor.trim().equals("")) {
			errores += "- Debe elegir un distribuidor \n\r";
		}

		if (!isSucursalPrincipal && !isSucursalSecundaria) {
			errores += "- Debe elegir al menos una sucursal \n\r";
		}

		this.nombre = nombre;
		this.tipo = tipo;
		this.distribuidor = distribuidor;
		this.isSucursalPrincipal = isSucursalPrincipal;
		this.isSucursalSecundaria = isSucursalSecundaria;

		return errores.equals("") ? "success" : errores;
	}

}
