package modelo;

//Faltan m�todos (16/03)

public class Especialidad {
	
   	private int id;
	private String nombre;
    private String dia;
    
    public Especialidad(int id, String nombre, String dia) {
		this.id = id;
		this.nombre = nombre;
		this.dia = dia;
	}
    
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
    
}