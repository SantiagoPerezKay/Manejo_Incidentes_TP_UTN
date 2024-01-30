package controller;

import java.util.List;

public interface CrudInterface<T> {

	    void insertar(T objeto);
	    void actualizar(T objeto);
	    void eliminar(T objeto);
	    <T> T leer( Integer id);
	    List <T> listarTodos();
	}

