package com.example.loginclinicapp;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String URL_API = "http://10.0.2.2:8080/apirest/";
    private static RetrofitClient instance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if(instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public LoginService getLoginService(){
        return retrofit.create(LoginService.class);
    }

    public PacientePorIdUsuarioService getPacientePorIdUsuarioService() {
        return retrofit.create(PacientePorIdUsuarioService.class);
    }
    public MedicoPorIdUsuarioService getMedicoPorIdUsuarioService() {
        return retrofit.create(MedicoPorIdUsuarioService.class);
    }

    public ProximoTurnoService getProximoTurnoPaciente() {
        return retrofit.create(ProximoTurnoService.class);
    }
    public InfoMedicoService getInfoMedico() {
        return retrofit.create(InfoMedicoService.class);
    }

    public GetTurnosPaciente getTurnoPaciente() {
        return retrofit.create(GetTurnosPaciente.class);
    }

    public EspecialidadesService getEspecialidadesService() {return retrofit.create(EspecialidadesService.class);}

    public MedicoPorEspecialidadService getMedicoPorEspecialidadService() {return retrofit.create(MedicoPorEspecialidadService.class);}

    public getTurnosMedicoPorDia getTurnosMedicoPorDia() { return retrofit.create(getTurnosMedicoPorDia.class); }
    public getTurno getTurno() { return retrofit.create(getTurno.class); }
    public AniadirTurnosService getAniadirTurnosService() {return retrofit.create(AniadirTurnosService.class);}
    public CambiarEstadoDeTurnoService getCambiarEstadoDeTurnoService() {return retrofit.create(CambiarEstadoDeTurnoService.class);}
    public EliminarTurnoService getEliminarTurnoService() {return retrofit.create(EliminarTurnoService.class);}
    public getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadService getGetCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadService() {return retrofit.create(getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadService.class);}
    public getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedicoService getGetCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedicoService() {return retrofit.create(getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedicoService.class);}
    public AgregarAListaDeEspera agregarAListaDeEspera() {return retrofit.create(AgregarAListaDeEspera.class);}
    public TurnosEspecialidadYMedicoPorDiaService getGetTurnosEspecialidadYMedicoPorDiaService() {return retrofit.create(TurnosEspecialidadYMedicoPorDiaService.class);}
    public TurnosEspecialidadPorDiaService getGetTurnosEspecialidadPorDiaService() {return retrofit.create(TurnosEspecialidadPorDiaService.class);}
    public EliminarTurnosService getEliminarTurnosService() {return retrofit.create(EliminarTurnosService.class);}
    public TurnosDisponiblesMedicoPorDiaService getTurnosDisponiblesMedicoPorDiaService() {return retrofit.create(TurnosDisponiblesMedicoPorDiaService.class);}




}
