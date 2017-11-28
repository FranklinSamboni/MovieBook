# MovieBook

MovieBook está dividida en 3 partes bien definidas views, viewmodels y data (models): 
Views: Se encargan de manejar lógica solo asociada a la vista, esta solo se preocupa de la manera en cómo se van a mostrar los datos.
	ej:
	- Mostrar u ocultar elementos.
	- Desplegar datos en interfaz de usuario.
	- Configurar elementos de vista (Toolbar, RecyclerView, etc.)
	
ViewModels: Contiene lógica de presentación, es el intermediario entre view y data. Maneja los datos del paquete data y decide la forma de presentarlos en la vista. 
(data)Models: Contiene los objetos representativos de la lógica de negocio (Movie, Serie). Maneja el repositorio de datos conectándose a 	dos fuentes de datos, una local utilizando SQLite y una remota que se conecta a 'themoviedb'.

	

