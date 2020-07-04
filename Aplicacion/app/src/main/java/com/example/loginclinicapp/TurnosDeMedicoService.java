package com.example.loginclinicapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TurnosDeMedicoService {

    String API_ROUTE = "/apirest/getTurnosMedico";
    @GET(API_ROUTE)
    Call<List<Turno>> getTurnosDeMedico(
            @Query("matricula") String matricula,
            @Query("estado") Integer estado
    );
}
