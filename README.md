# MovieBook

MovieBook est� dividida en 3 partes bien definidas views, viewmodels y data (models): 
Views: Se encargan de manejar l�gica solo asociada a la vista, esta solo se preocupa de la manera en c�mo se van a mostrar los datos.
	ej:
	- Mostrar u ocultar elementos.
	- Desplegar datos en interfaz de usuario.
	- Configurar elementos de vista (Toolbar, RecyclerView, etc.)
	
ViewModels: Contiene l�gica de presentaci�n, es el intermediario entre view y data. Maneja los datos del paquete data y decide la forma de presentarlos en la vista. 
(data)Models: Contiene los objetos representativos de la l�gica de negocio (Movie, Serie). Maneja el repositorio de datos conect�ndose a 	dos fuentes de datos, una local utilizando SQLite y una remota que se conecta a 'themoviedb'.

	

