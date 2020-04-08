package views;

import java.util.Collection;
import java.util.Date;

import modelo.Agenda;
import modelo.Especialidad;

public class MedicoView extends UsuarioView {
	public MedicoView(int idUsr,String usr, String pass, String nombre, String telefono, String dni, Date fechaNac,
			String matricula, Collection<Especialidad> especialidades, Agenda agenda) {
    	
		super(idUsr, usr, pass, nombre, telefono, dni, fechaNac);
		this.matricula = matricula;
		this.especialidades = especialidades;
		this.agenda = agenda;
	}
	
	private String matricula;
    private Collection<Especialidad> especialidades;
    private Agenda agenda;
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Collection<Especialidad> getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(Collection<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}    
	
	
}