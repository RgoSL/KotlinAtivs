enum class Periodo {
    MANHA,
    TARDE,
    NOITE,
}

// Código Anterior Atualizado

data class Evento(
    val titulo: String,
    val descricao: String? = null,
    val periodo: Perido,
    val duracaoMinutos: Int,
)