package com.turnos.apirest;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controlador.Controlador;
import exceptions.ListaDeEsperaException;
import exceptions.TurnoException;
import views.EspecialidadView;
import views.MedicoView;
import views.PacienteView;
import views.TurnoView;
import views.UsuarioView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody<json> String login(@RequestParam(value="usuario", required=true) String usuario, @RequestParam(value="password", required=true) String password) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			UsuarioView user = Controlador.getInstancia().getUsuarioLogIn(usuario, password);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(user);

	}
	
	@RequestMapping(value = "/getUsuario", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getUsuario(@RequestParam(value="usuario", required=true) String usuario) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			UsuarioView user = Controlador.getInstancia().getUsuario(usuario);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(user);

	}

	@RequestMapping(value = "/getTurno", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getTurno(@RequestParam(value="id", required=true) int id) throws JsonProcessingException {
		
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			TurnoView Turno = null;
			try {
				Turno = Controlador.getInstancia().getTurno(id);
			} catch (TurnoException e) {
			}	
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(Turno);
	}

	@RequestMapping(value = "/getPaciente", method = RequestMethod.GET, produces = {"application/json"})	
	public @ResponseBody<json> String getPaciente(@RequestParam(value="id", required=true) int id) throws JsonProcessingException {
		
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			PacienteView Paciente = Controlador.getInstancia().getPaciente(id);	
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(Paciente);
	}
	
	@RequestMapping(value = "/getMedico", method = RequestMethod.GET, produces = {"application/json"})	
	public @ResponseBody<json> String getMedico(@RequestParam(value="matricula", required=true) String matricula) throws JsonProcessingException {	
		//ResponseBody<json>: Aclara que el String guarda un JSON		
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

		MedicoView med = Controlador.getInstancia().getMedico(matricula);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(med);	
	}

	@RequestMapping(value = "/getEspecialidades", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getEspecialidades() throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<EspecialidadView> especialidades = Controlador.getInstancia().getEspecialidades();
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(especialidades);

	}

	@RequestMapping(value = "/getMedicosPorEspecialidad", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getMedicosPorEspecialidad(@RequestParam(value="idEspecialidad", required=true) int idEspecialidad) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<MedicoView> medicoPorEspecialidad = Controlador.getInstancia().getMedicosPorEspecialidad(idEspecialidad);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(medicoPorEspecialidad);

	}

	@RequestMapping(value = "/eliminarTurno", method = RequestMethod.DELETE)
	public ResponseEntity<Void>  eliminarTurno(@RequestParam(value="id", required=true) int id) {
		try {
			Controlador.getInstancia().eliminarTurno(id);
			return new ResponseEntity<Void>(HttpStatus.OK);						
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			// TODO Auto-generated catch block
			//e.getMessage();
		}
		
	}

	@RequestMapping(value = "/eliminarTurnos", method = RequestMethod.DELETE)
	public ResponseEntity<Void>  eliminarTurnos(@RequestParam(value="hora_inicial", required=true) LocalTime horaInicial, @RequestParam(value="hora_final", required=true) LocalTime horaFinal, @RequestParam(value="fecha_inicial", required=true) Date fechaInicial, @RequestParam(value="fecha_final", required=true) Date fechaFinal, @RequestParam(value="lunes", required=true) boolean lunes, @RequestParam(value="martes", required=true) boolean martes, @RequestParam(value="miercoles", required=true) boolean miercoles, @RequestParam(value="jueves", required=true) boolean jueves, @RequestParam(value="viernes", required=true) boolean viernes, @RequestParam(value="sabado", required=true) boolean sabado, @RequestParam(value="domingo", required=true) boolean domingo) {
		try {
			Controlador.getInstancia().eliminarTurnos(horaInicial, horaFinal, fechaInicial, fechaFinal, lunes, martes, miercoles, jueves, viernes, sabado, domingo);
			return new ResponseEntity<Void>(HttpStatus.OK);						
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			// TODO Auto-generated catch block
			//e.getMessage();
		}
		
	}
	
	@RequestMapping(value = "/agregarAListaDeEspera", method = RequestMethod.PUT)
	public ResponseEntity<Void> agregarAListaDeEspera(@RequestParam(value="idPaciente", required=true) int idPaciente, @RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="matricula", required=true) String matricula) {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
				
		try {
			
			Controlador.getInstancia().agregarAListaDeEspera(idPaciente, idEspecialidad, matricula);
			return new ResponseEntity<Void>(HttpStatus.CREATED);												
		} catch (ListaDeEsperaException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			
		}
		
	}
	
	@RequestMapping(value = "/agregarTurno", method = RequestMethod.PUT)
	public ResponseEntity<Void> agregarTurno(@RequestParam(value="fecha", required=true) @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy hh:mm:ss") Date fecha, @RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="matricula", required=true) String matricula) {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

		try {
			Controlador.getInstancia().agregarTurno(fecha, idEspecialidad, matricula);
			return new ResponseEntity<Void>(HttpStatus.CREATED);												
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			
		}
		
	}
	
	@RequestMapping(value = "/agregarTurnos", method = RequestMethod.PUT)
	public ResponseEntity<Void> agregarTurnos(@RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="matricula", required=true) String matricula, @RequestParam(value="duracion", required=true) int duracion, @RequestParam(value="hora_inicial", required=true) LocalTime horaInicial, @RequestParam(value="hora_final", required=true) LocalTime horaFinal, @RequestParam(value="fecha_inicial", required=true) Date fechaInicial, @RequestParam(value="fecha_final", required=true) Date fechaFinal, @RequestParam(value="lunes", required=true) boolean lunes, @RequestParam(value="martes", required=true) boolean martes, @RequestParam(value="miercoles", required=true) boolean miercoles, @RequestParam(value="jueves", required=true) boolean jueves, @RequestParam(value="viernes", required=true) boolean viernes, @RequestParam(value="sabado", required=true) boolean sabado, @RequestParam(value="domingo", required=true) boolean domingo) {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
				
		try {
			
			Controlador.getInstancia().agregarTurnos(idEspecialidad, matricula, duracion, horaInicial, horaFinal, fechaInicial, fechaFinal, lunes, martes, miercoles, jueves, viernes, sabado, domingo);
			return new ResponseEntity<Void>(HttpStatus.CREATED);												
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			
		}
		
	}

	@RequestMapping(value = "/modificarTurno", method = RequestMethod.PUT)
	public ResponseEntity<Void> modificarTurnos( @RequestParam(value="idTurno", required=true) int idTurno, @RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="hora_inicial", required=true) LocalTime horaInicial ) {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
				
		try {
			
			Controlador.getInstancia().modificarTurno(idTurno, idEspecialidad,horaInicial);
			return new ResponseEntity<Void>(HttpStatus.CREATED);												
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			
		}
		
	}

	@RequestMapping(value = "/getTurnosPaciente", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getTurnosPaciente(@RequestParam(value="idPaciente", required=true) int idPaciente, @RequestParam(value="viernes", required=true) boolean proximos) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<TurnoView> turnosPac = Controlador.getInstancia().getTurnosPaciente(idPaciente, proximos);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(turnosPac);
	}

	@RequestMapping(value = "/cambiarEstadoDeTurno", method = RequestMethod.PUT)
	public ResponseEntity<Void> cambiarEstadoDeTurno( @RequestParam(value="idTurno", required=true) int idTurno) {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
				
		try {
			
			Controlador.getInstancia().cambiarEstadoDeTurno(idTurno);
			return new ResponseEntity<Void>(HttpStatus.CREATED);												
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			
		}
		
	}

	@RequestMapping(value = "/getProximoTurnoPaciente", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getProximoTurnoPaciente(@RequestParam(value="idPaciente", required=true) int idPaciente) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			TurnoView ProxTurnoPac = Controlador.getInstancia().buscarProxTurnoPaciente(idPaciente);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(ProxTurnoPac);
	}

	@RequestMapping(value = "/getTurnosPacientePorEstado", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getTurnosPacientePorEstado(@RequestParam(value="idPaciente", required=true) int idPaciente, @RequestParam(value="estado", required=true) int estado) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<TurnoView> Turnos = Controlador.getInstancia().getTurnosPacientePorEstado(idPaciente, estado);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(Turnos);
	}

	@RequestMapping(value = "/getTurnosMedico", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getTurnosMedico(@RequestParam(value="idMedico", required=true) int idMedico, @RequestParam(value="estado", required=true) int estado) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<TurnoView> turnosMed = Controlador.getInstancia().getTurnosMedico(idMedico, estado);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(turnosMed);
	}

	@RequestMapping(value = "/getAgendaMedico", method = RequestMethod.GET, produces = {"application/json"})	
	public @ResponseBody<json> String getAgendaMedico(@RequestParam(value="matricula", required=true) String matricula) throws JsonProcessingException {	
		//ResponseBody<json>: Aclara que el String guarda un JSON		
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

		List<TurnoView> turnosDelMedico = Controlador.getInstancia().obtenerAgendaMedico(matricula);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(turnosDelMedico);	
	}

	@RequestMapping(value = "/getTurnosMedicoPorDia", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getTurnosMedicoPorDia(@RequestParam(value="idMedico", required=true) String idMedico, @RequestParam(value="idEspecialidad", required=true) int idEspecialidad) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<TurnoView> turnosMed = Controlador.getInstancia().getTurnosMedicoPorDia(idMedico, idEspecialidad);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(turnosMed);
	}

	@RequestMapping(value = "/getTurnosMedicoPorEspecialidad", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getTurnosMedicoPorEspecialidad(@RequestParam(value="idMedico", required=true) int idMedico, @RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="estado", required=true) int estado) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<TurnoView> turnosMedPorEsp = Controlador.getInstancia().getTurnosMedicoPorEspecialidad(idMedico,idEspecialidad,estado);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(turnosMedPorEsp);
	}

	@RequestMapping(value = "/getInfoInicioMedico", method = RequestMethod.GET, produces = {"application/json"})	//MAL PLANTEADO -> VER DE API REST
	public @ResponseBody<json> String getInfoInicioMedico(@RequestParam(value="matricula", required=true) String matricula) throws JsonProcessingException {	
		//ResponseBody<json>: Aclara que el String guarda un JSON		
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

		MedicoView medInfo = Controlador.getInstancia().getInfoMedico(matricula);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(medInfo);	
	}

	@RequestMapping(value = "/getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad(@RequestParam(value="idEspecialidad", required=true) int idEspecialidad) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<TurnoView> turnosDisponibles = Controlador.getInstancia().getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad(idEspecialidad);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(turnosDisponibles);
	}

	@RequestMapping(value = "/getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedico", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedico(@RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="matricula", required=true) String matricula) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<TurnoView> turnosDisponibles = Controlador.getInstancia().getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedico(idEspecialidad, matricula);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(turnosDisponibles);
	}
}
