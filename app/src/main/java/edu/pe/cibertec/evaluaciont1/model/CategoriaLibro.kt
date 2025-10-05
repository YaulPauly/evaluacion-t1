package edu.pe.cibertec.evaluaciont1.model

enum class CategoriaLibro(val texto: String, val id_categoria: Int) {
    FICCION("Ficción", 1),
    NO_FICCION("No Ficción", 2),
    EDUCATIVO("Educativo / Académico", 3),
    INFANTIL("Infantil / Juvenil", 4)
}
