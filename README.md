# 📝 Aplicación de Almacenamiento de Notas para Android

## 📖 Descripción
Esta aplicación permite a los usuarios **crear, gestionar y almacenar notas** de forma rápida y segura desde su dispositivo Android.  
El objetivo del proyecto es demostrar habilidades en el desarrollo de aplicaciones móviles, manejo de almacenamiento local y diseño de interfaces intuitivas en Android Studio.

---

## 🚀 Funcionalidades Principales

### 🧩 1. Interfaz de Usuario Intuitiva
- Diseño simple, limpio y fácil de navegar.
- Pantalla principal con lista de notas creadas.
- Botones claros para crear, editar y eliminar notas.

### 📝 2. Gestión de Notas
- Crear nuevas notas con **título y contenido**.
- Editar notas existentes.
- Eliminar notas de forma individual.
- Visualizar detalles de una nota en una vista dedicada.
- Contenido de texto enriquecido (negrita, cursiva, etc. opcional).

### 🗂️ 3. Organización de Notas
- Posibilidad de organizar notas mediante **etiquetas o categorías**.
- Búsqueda de notas por título o etiqueta.

### 💾 4. Sincronización y Copia de Seguridad
- Exportar notas a un **archivo de texto (.txt)** almacenado en el dispositivo.
- Posibilidad de realizar **copias de seguridad locales** para evitar pérdida de información.
- *(Avanzado)*: Integración opcional con almacenamiento en la nube.

### 🎨 5. Tema Personalizable (Opcional)
- Modo claro y oscuro.
- Cambio de colores principales para personalizar la apariencia de la app.

### 🔒 6. Seguridad de Datos
- Protección de notas mediante **contraseña o PIN**.
- Almacenamiento interno seguro, inaccesible para otras aplicaciones.

---

## 🧠 Tecnologías Utilizadas
- **Lenguaje:** Java  
- **Entorno:** Android Studio  
- **SDK:** Android API 29 o superior  
- **Diseño UI:** XML y Material Design  
- **Almacenamiento:**  
  - Interno del dispositivo (SharedPreferences / archivos locales)  
  - Exportación a almacenamiento externo  
  - *(Opcional)*: Sincronización en la nube  

---

## 🧩 Estructura Básica del Proyecto
/app
├── java/com.example.notas
│    ├── MainActivity.java           # Pantalla principal (lista de notas)
│    ├── VistaNotas.java             # Vista detallada de cada nota
│    ├── AdaptadorNotas.java         # Adaptador para RecyclerView/ListView
│    ├── ModeloNota.java             # Clase modelo para las notas
│    └── Utilidades.java             # Métodos auxiliares (exportar, formatear, etc.)
│
└── res/
├── layout/
│    ├── activity_main.xml
│    ├── activity_vista_notas.xml
│    └── item_nota.xml
└── values/
├── colors.xml
├── strings.xml
└── themes.xml

---

## ⚙️ Instalación y Ejecución

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tuusuario/NotasApp.git
2.	Abre el proyecto en Android Studio.
3.	Conecta un dispositivo físico o inicia un emulador.
4.	Ejecuta la aplicación con el botón ▶️ Run.
---

👨‍💻 Autor

Roberto de Frutos Jiménez
Estudiante de Desarrollo de Aplicaciones Multiplataforma
📧 robertodfj93@gmail.com
📅 Año: 2025

⸻

📝 Licencia

Este proyecto se distribuye bajo la licencia MIT, lo que permite su uso, modificación y distribución con reconocimiento al autor original.

